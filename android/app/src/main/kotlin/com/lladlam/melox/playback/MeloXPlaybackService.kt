package com.lladlam.melox.playback

import android.app.PendingIntent
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import java.io.File
import android.util.Log
import androidx.annotation.OptIn
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.ResolvingDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import androidx.core.app.NotificationCompat
import com.lladlam.melox.MainActivity
import com.lladlam.melox.MeloXAppVisibility
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.audio.MusicQualityPreferences
import com.lladlam.melox.core.download.MeloXDownloadStore
import com.lladlam.melox.core.library.NeteaseLibraryClient
import com.lladlam.melox.core.network.MeloXNetworkAvailability
import com.lladlam.melox.core.network.NeteaseUniversalSearchClient
import com.lladlam.melox.core.network.NeteaseSearchClient
import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.music.model.MusicResourceId
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.provider.LyricsCapability
import com.lladlam.melox.core.music.provider.MeloXMusicProviders
import com.lladlam.melox.core.music.provider.PlaybackAccountStore
import com.lladlam.melox.platform.xiaomi.HyperOsFocusBridge
import com.lladlam.melox.logic.car.CarLyricsManager
import com.lladlam.melox.logic.car.CarLyricsConstants
import com.lladlam.melox.ui.settings.MeloXSettingsPreferences
import com.lladlam.melox.ui.settings.MeloXSettingsRuntime
import com.lladlam.melox.ui.settings.MeloXSystemLyricTitleMode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(UnstableApi::class)
class MeloXPlaybackService : MediaSessionService() {
    private var player: ExoPlayer? = null
    private var incomingPlayer: ExoPlayer? = null
    private var mediaSession: MediaSession? = null
    private lateinit var mediaSourceFactory: DefaultMediaSourceFactory
    private lateinit var downloadStore: MeloXDownloadStore
    private lateinit var playbackResolver: NeteasePlaybackResolver
    private lateinit var autoMixAnalyzer: MeloXAutoMixAudioAnalyzer
    private lateinit var equalizerController: MeloXEqualizerController
    private lateinit var playbackHistoryReporter: MeloXPlaybackHistoryReporter
    private var historySongId: Long? = null
    private var historyPositionMs = 0L
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val handler = Handler(Looper.getMainLooper())
    private var recommendationJob: Job? = null
    private var recommendationSeed: Long? = null
    private var systemLyricsJob: Job? = null
    private var systemLyricsSongId: Long? = null
    private var systemLyricsDocument: LyricsDocument? = null
    private var systemLyricsOriginalMetadata: MediaMetadata? = null
    private var systemLyricsLastIndex = Int.MIN_VALUE
    private var systemLyricsLastDispatchRealtimeMs = 0L
    private var systemLyricsLastPlaying = false
    private var updatingSystemLyricsMetadata = false
    private var carLyricsManager: CarLyricsManager? = null
    // 记录上一次 transition 对应的 mediaId，用于区分「真正切歌」与
    // pushCarLyrics 注入 Channel A 元数据引起的 replaceMediaItem transition。
    private var carLyricsTransitionMediaId: String? = null
    // 车联歌词独立加载状态（多源，不依赖系统歌词的网易云路径）
    private var carLyricsResourceKey: String? = null
    private var carLyricsDocument: LyricsDocument? = null
    private var carLyricsJob: Job? = null
    // 加载结果分类：区分「真无歌词」与「网络/接口失败需重试」，避免一次失败就判死
    private var carLyricsFailed: Boolean = false
    private var carLyricsAttempts: Int = 0
    private var carLyricsLastAttemptRealtimeMs: Long = 0L
    private var mixAnalysisJob: Job? = null
    private var mixAnalysisSourceId: String? = null
    private var analyzedMixPlan: MeloXAutoMixPlan? = null
    private var preparedMixSourceId: String? = null
    private var preparedMixTargetId: String? = null
    private var autoMixRetrySourceId: String? = null
    private var autoMixRetryAfterRealtimeMs = 0L
    private var mixStartedAt = 0L
    private var mixDurationMs = 0L
    private var mixBaseVolume = 1f
    private var mixOutgoingStartPositionMs = 0L
    private var mixIncomingStartPositionMs = 0L
    private var mixLastProgress = 0.0
    private var mixSettings = MeloXAutoMixSettings()
    private var cachedAutoMixSettings = MeloXAutoMixSettings()
    private var cachedAutoMixSettingsAt = 0L
    private var mixPlan = MeloXAutoMixPlan(0L, 0L)
    private val mixEqualizerEnvelope = MeloXAutoMixEqualizerEnvelope()
    private var lastMaintenanceRealtimeMs = 0L
    private var lastPlaybackStatePersistRealtimeMs = 0L

    private val audioAttributes = AudioAttributes.Builder()
        .setUsage(C.USAGE_MEDIA)
        .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
        .build()

    private val playerListener = object : Player.Listener {
        override fun onPlayerError(error: PlaybackException) {
            val active = player
            if (active != null && !MeloXNetworkAvailability.isOnline(this@MeloXPlaybackService)) {
                if (skipToNextDownloaded(active)) {
                    Log.i(TAG, "Offline playback skipped unavailable item after player error")
                    return
                }
            }
            Log.e(TAG, "Playback failed: code=${error.errorCodeName}, message=${error.message}", error)
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            Log.d(TAG, "isPlaying=$isPlaying, ongoing=${isPlaybackOngoing()}")
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            val active = player ?: return
            if (playbackState == Player.STATE_ENDED) {
                historySongId?.let { playbackHistoryReporter.recordDuration(it, elapsedMs = historyPositionMs, durationMs = active.duration.takeIf { value -> value != C.TIME_UNSET && value > 0L }, completed = true) }
                historySongId = null; historyPositionMs = 0L
                if (!MeloXNetworkAvailability.isOnline(this@MeloXPlaybackService)) {
                    if (!skipToNextDownloaded(active)) active.pause()
                    return
                }
                ensureAutoplayRecommendations(forceAdvance = true)
            }
        }

        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            recommendationSeed = null
            autoMixRetrySourceId = null
            autoMixRetryAfterRealtimeMs = 0L
            // 切歌后立即保存新歌曲/新位置（播放刚开始，位置接近 0）
            persistPlaybackState()
            val transitionedId = mediaItem?.mediaId?.toLongOrNull(); val previousHistoryId = historySongId
            if (previousHistoryId != null && previousHistoryId != transitionedId) playbackHistoryReporter.recordDuration(previousHistoryId, elapsedMs = historyPositionMs)
            if (transitionedId != null && transitionedId != previousHistoryId) { historySongId = transitionedId; historyPositionMs = 0L; playbackHistoryReporter.recordStart(transitionedId) }
            MeloXAudioReactiveRuntime.select(mediaItem?.mediaId)
            mediaItem?.let(downloadStore::recordPlayback)
            if (transitionedId != systemLyricsSongId) resetSystemLyrics(mediaItem)
            // 车机歌词：仅在「真正切歌」时重置。pushCarLyrics 注入 Channel A
            // 元数据会触发 replaceMediaItem → 误触发本 transition，而 mediaId
            // 不变；若此处无条件 reset，会把正在加载/已加载的歌词清空，
            // 造成整首歌一直显示「暂无歌词」（切回来又突然恢复）。
            val transitionMediaId = mediaItem?.mediaId
            if (transitionMediaId != carLyricsTransitionMediaId) {
                carLog("transition: mediaId $carLyricsTransitionMediaId -> $transitionMediaId reason=$reason (reset)")
                carLyricsTransitionMediaId = transitionMediaId
                resetCarLyrics(mediaItem)
                carLyricsManager?.setLoading()
            } else {
                carLog("transition: same mediaId=$transitionMediaId reason=$reason (skip reset)")
            }
            val active = player
            if (active != null) {
                applyLocalArtworkMetadata(active)
                prefetchFollowing(active)
                if (!MeloXNetworkAvailability.isOnline(this@MeloXPlaybackService)) {
                    val id = active.currentMediaItem?.mediaId?.toLongOrNull()
                    if (id != null && !downloadStore.contains(id)) {
                        skipToNextDownloaded(active)
                    }
                }
            }
            // An active crossfade owns the handoff. Do not destroy the incoming
            // deck if the outgoing deck reaches its boundary a few milliseconds
            // before the monitor promotes the already-playing incoming deck.
            if (mixStartedAt == 0L) cancelPreparedMix()
        }

        override fun onAudioSessionIdChanged(audioSessionId: Int) {
            equalizerController.attach(audioSessionId)
        }

        override fun onTimelineChanged(timeline: androidx.media3.common.Timeline, reason: Int) {
            if (updatingSystemLyricsMetadata) return
            if (mixStartedAt == 0L && preparedMixSourceId != null) {
                val active = player
                val currentId = active?.currentMediaItem?.mediaId
                val nextId = active?.currentMediaItemIndex
                    ?.plus(1)
                    ?.takeIf { it in 0 until active.mediaItemCount }
                    ?.let(active::getMediaItemAt)
                    ?.mediaId
                if (currentId != preparedMixSourceId || nextId != preparedMixTargetId) {
                    cancelPreparedMix()
                }
            }
        }
    }

    private fun prefetchFollowing(active: ExoPlayer) {
        val nextIndex = active.currentMediaItemIndex + 1
        if (nextIndex !in 0 until active.mediaItemCount) return
        val uri = active.getMediaItemAt(nextIndex).localConfiguration?.uri ?: return
        serviceScope.launch(Dispatchers.IO) {
            runCatching { playbackResolver.prefetch(uri) }
                .onFailure { Log.d(TAG, "Next source prefetch skipped: ${it.message}") }
        }
    }

    private val modeMonitor = object : Runnable {
        override fun run() {
            val active = player
            if (active != null) {
                active.currentMediaItem?.mediaId?.toLongOrNull()?.let { current -> if (current == historySongId) historyPositionMs = active.currentPosition.coerceAtLeast(0L) }
                val uiTransitionActive = MeloXPlayerTransitionState.isActive
                runCatching {
                    maybePrepareAutoplay(active)
                    maybeRunAutoMix(active)
                    if (!uiTransitionActive) {
                        maybeUpdateSystemLyrics(active)
                        pushCarLyrics(active)
                        updateAudioReactiveVisuals(active)
                    }
                    val now = SystemClock.elapsedRealtime()
                    if (now - lastMaintenanceRealtimeMs >= PLAYBACK_MAINTENANCE_INTERVAL_MS) {
                        lastMaintenanceRealtimeMs = now
                        if (!uiTransitionActive) {
                            applyLocalArtworkMetadata(active)
                        }
                        PlaybackCommands.prioritizeManualQueue(active)
                        enforceSleepTimer(active)
                        equalizerController.applySettings()
                    }
                    // 定期持久化播放状态（防抖，低频落盘）
                    if (now - lastPlaybackStatePersistRealtimeMs >= PLAYBACK_STATE_PERSIST_INTERVAL_MS) {
                        lastPlaybackStatePersistRealtimeMs = now
                        persistPlaybackState()
                    }
                }.onFailure { error ->
                    Log.e(TAG, "Playback monitor recovered from failure", error)
                    recoverAutoMixFailure()
                }
            }
            val nextTickMs = when {
                mixStartedAt > 0L -> ACTIVE_MONITOR_INTERVAL_MS
                active?.isPlaying == true -> ACTIVE_MONITOR_INTERVAL_MS
                active?.currentMediaItem != null -> PAUSED_MONITOR_INTERVAL_MS
                else -> IDLE_MONITOR_INTERVAL_MS
            }
            handler.postDelayed(this, nextTickMs)
        }
    }

    private fun updateAudioReactiveVisuals(active: ExoPlayer) {
        val item = active.currentMediaItem ?: return
        // Visual motion follows the lightweight playback clock. Full-track
        // MediaCodec analysis is reserved for explicitly enabled AutoMix; doing
        // a second HTTP decode for decoration competes with ExoPlayer.
        MeloXAudioReactiveRuntime.publish(item.mediaId, active.currentPosition, active.isPlaying)
    }

    /** 车联歌词调试日志：同时输出 logcat 与文件（免 adb 抓取） */
    private fun carLog(msg: String) {
        Log.d(TAG, "CAR_LYRIC $msg")
        try {
            val dir = getExternalFilesDir(null) ?: return
            val f = File(dir, "car_lyrics.log")
            f.appendText("[${System.currentTimeMillis()}] $msg\n")
        } catch (_: Exception) {
        }
    }

    // ====================================================================
    // 播放状态持久化（重启/杀后台后恢复上次播放）
    // ====================================================================

    /**
     * 保存当前播放状态：队列（仅网易云纯数字 ID）、当前索引、播放位置、
     * 播放/暂停。队列含 provider 歌曲（非纯数字 mediaId）时跳过保存，
     * 避免恢复时索引错位。
     */
    private fun persistPlaybackState() {
        val active = player ?: return
        if (active.mediaItemCount <= 0 || active.currentMediaItemIndex !in 0 until active.mediaItemCount) return
        val songIds = List(active.mediaItemCount) { index ->
            active.getMediaItemAt(index).mediaId.toLongOrNull()
        }
        // 队列里存在 provider 歌曲：暂不支持恢复，跳过
        if (songIds.any { it == null }) return
        val ids = songIds.map { it!! }
        val index = active.currentMediaItemIndex
        val position = active.currentPosition.coerceAtLeast(0L)
        MeloXPlaybackStateStore.save(
            this,
            MeloXPlaybackStateStore.Snapshot(
                songIds = ids,
                index = index,
                positionMs = position,
                playWhenReady = active.playWhenReady,
                at = System.currentTimeMillis(),
            ),
        )
    }

    /**
     * 服务创建后尝试恢复上次播放状态。仅当播放器队列为空时执行，
     * 避免覆盖用户正在进行的播放操作。离线时不恢复（无法拉取歌曲详情）。
     */
    private fun maybeRestorePlayback() {
        val active = player ?: return
        if (active.mediaItemCount > 0) return
        if (!MeloXNetworkAvailability.isOnline(this)) return
        val snapshot = MeloXPlaybackStateStore.load(this) ?: return
        if (!snapshot.isValid()) {
            MeloXPlaybackStateStore.clear(this)
            return
        }
        val quality = MusicQualityPreferences.read(this)
        carLog("restore: ${snapshot.songIds.size} songs, index=${snapshot.index}, pos=${snapshot.positionMs}ms, play=${snapshot.playWhenReady}")
        serviceScope.launch {
            val client = NeteaseUniversalSearchClient(
                cookieProvider = { com.lladlam.melox.core.music.provider.PlaybackAccountStore.neteaseCookie(this@MeloXPlaybackService) },
            )
            val songs = withContext(Dispatchers.IO) {
                runCatching { client.songDetails(snapshot.songIds) }.getOrDefault(emptyList())
            }
            if (songs.isEmpty()) {
                carLog("restore: song detail fetch failed/empty")
                return@launch
            }
            // 竞态保护：拉取详情期间用户可能已主动建立队列，此时放弃恢复
            if (active.mediaItemCount > 0) {
                carLog("restore: skipped, user queue already present")
                return@launch
            }
            // 按快照顺序对齐（详情接口可能缺失个别歌曲）
            val byId = songs.associateBy { it.id }
            val items = snapshot.songIds.mapNotNull { byId[it] }.mapIndexed { offset, song ->
                PlaybackCommands.mediaItemFor(
                    song = song,
                    quality = quality,
                    queueOrigin = PlaybackCommands.QUEUE_ORIGIN_BASE,
                    originalIndex = offset,
                )
            }
            if (items.isEmpty()) return@launch
            val restoreIndex = snapshot.index.coerceIn(0, items.lastIndex)
            val restorePosition = if (snapshot.index == restoreIndex) snapshot.positionMs else 0L
            runCatching {
                active.setMediaItems(items, restoreIndex, restorePosition)
                active.prepare()
                if (snapshot.playWhenReady) active.play()
            }.onFailure { error ->
                carLog("restore: failed ${error.message}")
            }
            carLog("restore: restored ${items.size} songs at index=$restoreIndex pos=$restorePosition")
        }
    }

    /** Keep timers alive across Activity recreation by owning them in playback. */
    private fun enforceSleepTimer(active: ExoPlayer) {
        val end = MeloXSettingsPreferences.long(this, SLEEP_TIMER_END_KEY, 0L)
        if (end <= 0L || System.currentTimeMillis() < end) return
        active.pause()
        MeloXSettingsPreferences.setLong(this, SLEEP_TIMER_END_KEY, 0L)
    }

    /**
     * Keep the gain envelope independent from the slower queue/autoplay monitor.
     * Upstream MeloX advances its two-deck envelope every 20 ms; driving volume
     * from the 100 ms mode monitor made the crossfade audibly step between gains.
     */
    private val mixEnvelope = object : Runnable {
        override fun run() {
            val active = player
            val incoming = incomingPlayer
            if (mixStartedAt == 0L || active == null || incoming == null) return
            val updated = runCatching { updateAutoMixEnvelope(active, incoming) }
                .onFailure { error ->
                    Log.e(TAG, "AutoMix envelope failed; restoring normal playback", error)
                    recoverAutoMixFailure()
                }
                .isSuccess
            if (updated && mixStartedAt > 0L) {
                handler.postDelayed(this, AUTOMIX_ENVELOPE_INTERVAL_MS)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        MeloXPlaybackModePreferences.initialize(this)
        val httpFactory = DefaultHttpDataSource.Factory()
            .setAllowCrossProtocolRedirects(true)
            .setDefaultRequestProperties(
                mapOf(
                    "User-Agent" to "Mozilla/5.0 (Linux; Android 14) AppleWebKit/537.36 Chrome/124 Mobile Safari/537.36",
                    "Referer" to "https://music.163.com/",
                ),
            )
        downloadStore = MeloXDownloadStore.get(this)
        equalizerController = MeloXEqualizerController(this)
        playbackHistoryReporter = MeloXPlaybackHistoryReporter(this)
        val cookieProvider = { com.lladlam.melox.core.music.provider.PlaybackAccountStore.neteaseCookie(this@MeloXPlaybackService) }
        playbackResolver = NeteasePlaybackResolver(
            cookieProvider = cookieProvider,
            client = NeteaseSearchClient(cookieProvider = cookieProvider),
            localSourceProvider = downloadStore::localPlaybackUri,
        )
        autoMixAnalyzer = MeloXAutoMixAudioAnalyzer(this)
        val upstream = DefaultDataSource.Factory(this, httpFactory)
        val cached = CacheDataSource.Factory()
            .setCache(MeloXMediaCache.get(this))
            .setUpstreamDataSourceFactory(upstream)
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
        val resolving = ResolvingDataSource.Factory(
            cached,
            playbackResolver,
        )
        mediaSourceFactory = DefaultMediaSourceFactory(this).setDataSourceFactory(resolving)

        val active = buildPlayer(managesAudioFocus = true)
        player = active
        val sessionActivityIntent = Intent(this, MainActivity::class.java).apply {
            action = MainActivity.ACTION_OPEN_NOW_PLAYING
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val sessionActivity = PendingIntent.getActivity(
            this,
            1001,
            sessionActivityIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        mediaSession = MediaSession.Builder(this, active)
            .setSessionActivity(sessionActivity)
            .build()
        carLyricsManager = CarLyricsManager(mediaSession!!)
        carLyricsManager?.onPushLog = { line, whole, status, changed ->
            carLog("manager.push: line=${line?.take(24)} wholeLen=${whole?.length} status=$status changed=$changed")
        }
        createLyricsNotificationChannel()
        handler.post(modeMonitor)
        // 恢复上次播放状态（延迟一拍，避免与 modeMonitor 首轮竞争）
        handler.postDelayed({ maybeRestorePlayback() }, 300L)
    }

    private fun buildPlayer(
        managesAudioFocus: Boolean,
        observesSession: Boolean = managesAudioFocus,
    ): ExoPlayer =
        ExoPlayer.Builder(this)
            .setMediaSourceFactory(mediaSourceFactory)
            .setWakeMode(C.WAKE_MODE_LOCAL)
            .build()
            .apply {
                setAudioAttributes(audioAttributes, managesAudioFocus)
                setHandleAudioBecomingNoisy(managesAudioFocus)
                if (observesSession) addListener(playerListener)
            }

    private fun maybePrepareAutoplay(active: ExoPlayer) {
        if (!MeloXPlaybackModePreferences.autoplay(this)) return
        if (active.mediaItemCount <= 0 || active.currentMediaItemIndex < 0) return
        val atTail = active.currentMediaItemIndex >= active.mediaItemCount - 1
        if (!atTail) return
        val duration = active.duration.takeIf { it != C.TIME_UNSET && it > 0L } ?: return
        val remaining = duration - active.currentPosition
        val preloadMs = if (MeloXPlaybackModePreferences.autoMix(this)) {
            maxOf(AUTOPLAY_PRELOAD_MS, currentAutoMixSettings().preloadLeadMs)
        } else {
            AUTOPLAY_PRELOAD_MS
        }
        if (remaining <= preloadMs && MeloXNetworkAvailability.isOnline(this)) {
            ensureAutoplayRecommendations(forceAdvance = false)
        }
    }

    private fun ensureAutoplayRecommendations(forceAdvance: Boolean) {
        if (!MeloXNetworkAvailability.isOnline(this)) return
        if (!MeloXPlaybackModePreferences.autoplay(this)) return
        val active = player ?: return
        val seed = active.currentMediaItem?.mediaId?.toLongOrNull() ?: return
        if (recommendationJob?.isActive == true || recommendationSeed == seed) {
            if (forceAdvance && active.playbackState == Player.STATE_ENDED && active.hasNextMediaItem()) {
                active.seekToNextMediaItem()
                active.play()
            }
            return
        }
        recommendationSeed = seed
        recommendationJob = serviceScope.launch {
            val cookie = { NeteaseSessionStore.readCookie(this@MeloXPlaybackService) }
            val recommendations = withContext(Dispatchers.IO) {
                runCatching { NeteaseLibraryClient(cookieProvider = cookie).similarSongsBlocking(seed, 30) }
                    .getOrDefault(emptyList())
            }
            val existing = (0 until active.mediaItemCount).map { active.getMediaItemAt(it).mediaId }.toSet()
            val quality = MusicQualityPreferences.read(this@MeloXPlaybackService)
            recommendations
                .filterNot { it.id.toString() in existing }
                .take(20)
                .forEach { song ->
                    active.addMediaItem(
                        PlaybackCommands.mediaItemFor(
                            song,
                            quality,
                            PlaybackCommands.QUEUE_ORIGIN_BASE,
                        ),
                    )
                }
            PlaybackCommands.prioritizeManualQueue(active)
            if (forceAdvance && active.playbackState == Player.STATE_ENDED && active.hasNextMediaItem()) {
                active.seekToNextMediaItem()
                active.prepare()
                active.play()
            }
            recommendationJob = null
        }
    }

    private fun maybeRunAutoMix(active: ExoPlayer) {
        val enabled = MeloXPlaybackModePreferences.autoMix(this)
        if (MeloXPlaybackModeRuntime.autoMixEnabled != enabled) {
            MeloXPlaybackModeRuntime.autoMixEnabled = enabled
        }
        if (!enabled) {
            cancelPreparedMix(releaseStandby = true)
            return
        }
        if (!active.isPlaying || active.repeatMode == Player.REPEAT_MODE_ONE) return
        if (!active.hasNextMediaItem()) {
            if (MeloXPlaybackModePreferences.autoplay(this)) maybePrepareAutoplay(active)
            return
        }
        val duration = active.duration.takeIf { it != C.TIME_UNSET && it > 0L } ?: return
        val remaining = duration - active.currentPosition
        val sourceId = active.currentMediaItem?.mediaId ?: return
        if (autoMixRetrySourceId == sourceId && SystemClock.elapsedRealtime() < autoMixRetryAfterRealtimeMs) return
        if (autoMixRetrySourceId != null && autoMixRetrySourceId != sourceId) {
            autoMixRetrySourceId = null
            autoMixRetryAfterRealtimeMs = 0L
        }
        val settings = currentAutoMixSettings()
        if (preparedMixSourceId == null && remaining <= settings.preloadLeadMs) {
            PlaybackCommands.prioritizeManualQueue(active)
            prepareIncoming(active, sourceId)
        }
        val incoming = incomingPlayer ?: return
        if (preparedMixSourceId != sourceId) {
            if (mixStartedAt > 0L) completeAutoMix(active, incoming) else cancelPreparedMix()
            return
        }
        if (settings.mode == MeloXAutoMixMode.Smart &&
            mixAnalysisSourceId != sourceId &&
            mixAnalysisJob?.isActive != true
        ) {
            startAutoMixAnalysis(active, sourceId, settings)
        }
        val candidate = when (settings.mode) {
            MeloXAutoMixMode.Fixed -> MeloXAutoMixPlanner.plan(settings, remaining)
            MeloXAutoMixMode.Smart -> analyzedMixPlan ?: run {
                val fallback = MeloXAutoMixPlanner.plan(settings, remaining)
                // Give full-track analysis the whole preload window. Only use
                // the selected failure policy when the transition is imminent.
                if (remaining <= fallback.durationMs + ANALYSIS_FALLBACK_GUARD_MS) fallback else return
            }
        }
        if (!candidate.performsTransition) return
        val reachedTransitionPoint = if (candidate.usedSmartAnalysis) {
            active.currentPosition >= candidate.outgoingStartMs
        } else {
            remaining <= candidate.durationMs + candidate.outgoingEndOffsetMs
        }
        if (mixStartedAt == 0L && incoming.playbackState == Player.STATE_READY && reachedTransitionPoint) {
            // Do not start the second live decoder while either full-track
            // analyser still owns a MediaCodec instance. Cancellation is
            // cooperative; the next 100 ms monitor tick starts the mix after
            // the codec's finally block has released the native resource.
            val activeAnalysis = listOfNotNull(mixAnalysisJob)
                .filter(Job::isActive)
            if (activeAnalysis.isNotEmpty()) {
                activeAnalysis.forEach(Job::cancel)
                return
            }
            val actualDuration = minOf(
                candidate.durationMs,
                remaining - candidate.outgoingEndOffsetMs,
            )
            if (actualDuration < MeloXAutoMixPlanner.MIN_DURATION_MS) return
            runCatching {
                mixSettings = settings
                mixPlan = candidate.copy(durationMs = actualDuration)
                mixBaseVolume = active.volume.coerceIn(0f, 1f)
                mixDurationMs = actualDuration
                if (candidate.incomingStartMs > 0L) incoming.seekTo(candidate.incomingStartMs)
                active.setPlaybackSpeed(candidate.outgoingStartRate)
                incoming.setPlaybackSpeed(candidate.incomingStartRate)
                incoming.volume = 0f
                incoming.play()
                if (supportsStableDeckEqualizers()) {
                    mixEqualizerEnvelope.attach(active.audioSessionId, incoming.audioSessionId)
                } else {
                    mixEqualizerEnvelope.release()
                }
                mixStartedAt = SystemClock.elapsedRealtime()
                mixOutgoingStartPositionMs = active.currentPosition.coerceAtLeast(0L)
                mixIncomingStartPositionMs = incoming.currentPosition.coerceAtLeast(0L)
                mixLastProgress = 0.0
                handler.removeCallbacks(mixEnvelope)
                handler.post(mixEnvelope)
            }.onFailure { error ->
                Log.e(TAG, "AutoMix start failed; continuing current song", error)
                recoverAutoMixFailure()
            }
        }
    }

    private fun currentAutoMixSettings(): MeloXAutoMixSettings {
        val now = SystemClock.elapsedRealtime()
        if (now - cachedAutoMixSettingsAt >= SETTINGS_SNAPSHOT_INTERVAL_MS) {
            cachedAutoMixSettings = MeloXAutoMixSettings.read(this)
            cachedAutoMixSettingsAt = now
        }
        return cachedAutoMixSettings
    }

    private fun supportsStableDeckEqualizers(): Boolean {
        return MeloXAutoMixEqualizerEnvelope.supportsDeckEqualizers(
            manufacturer = Build.MANUFACTURER,
            brand = Build.BRAND,
            userEqualizerEnabled = MeloXSettingsPreferences.boolean(this, "equalizer_enabled", false),
        )
    }

    private fun recoverAutoMixFailure() {
        autoMixRetrySourceId = player?.currentMediaItem?.mediaId
        autoMixRetryAfterRealtimeMs = SystemClock.elapsedRealtime() + AUTOMIX_FAILURE_COOLDOWN_MS
        runCatching { cancelPreparedMix(releaseStandby = true) }
            .onFailure { Log.e(TAG, "AutoMix cleanup also failed", it) }
    }

    private fun maybeUpdateSystemLyrics(active: ExoPlayer) {
        val currentItem = active.currentMediaItem ?: return
        val metadataEnabled = MeloXSettingsRuntime.systemLyricsEnabled
        val notificationEnabled = MeloXSettingsRuntime.lyricNotificationsEnabled
        val carEnabled = MeloXSettingsPreferences.boolean(
            this, CarLyricsConstants.PREF_CAR_LYRICS_ENABLED, true,
        )
        val songId = currentItem.mediaId.toLongOrNull() ?: run {
            carLog("maybeSysLyrics: songId null (mediaId=${currentItem.mediaId})")
            return
        }
        // 只要有任何一处需要歌词（系统歌词 / 歌词通知 / 车载歌词），就加载。
        // 车载歌词复用这份已稳定加载的结果，避免独立链路引入竞态。
        if (!metadataEnabled && !notificationEnabled && !carEnabled) {
            restoreSystemLyricsMetadata(active)
            (getSystemService(NotificationManager::class.java)).cancel(LYRICS_NOTIFICATION_ID)
            return
        }
        if (systemLyricsSongId != songId) resetSystemLyrics(currentItem)
        if (systemLyricsDocument == null && systemLyricsJob?.isActive != true) loadSystemLyrics(songId, currentItem)
        val document = systemLyricsDocument ?: run {
            carLog("maybeSysLyrics: doc null (songId=$songId jobActive=${systemLyricsJob?.isActive})")
            return
        }
        // 车载歌词：直接复用刚加载好的歌词文档
        if (carEnabled && carLyricsDocument == null) {
            carLog("maybeSysLyrics: reuse doc -> carLyricsDocument (lines=${document.lines.size})")
            carLyricsDocument = document
            carLyricsFailed = false
            carLyricsJob?.cancel()
            carLyricsJob = null
        }
        val advance = MeloXSettingsRuntime.lyricAdvanceMs.toLong()
        val index = document.highlightedIndex(active.currentPosition + advance) ?: return
        val now = SystemClock.elapsedRealtime()
        val lineChanged = index != systemLyricsLastIndex
        val playbackChanged = active.isPlaying != systemLyricsLastPlaying
        val periodicRefresh = now - systemLyricsLastDispatchRealtimeMs >= 1_000L
        if (!lineChanged && !playbackChanged && !periodicRefresh) return
        systemLyricsLastIndex = index
        systemLyricsLastPlaying = active.isPlaying
        systemLyricsLastDispatchRealtimeMs = now
        var line = document.lines.getOrNull(index)?.text?.trim().orEmpty()
        val nextLine = document.lines.getOrNull(index + 1)?.text?.trim().orEmpty()
        val original = systemLyricsOriginalMetadata ?: currentItem.mediaMetadata.also {
            systemLyricsOriginalMetadata = it
        }
        if (line.isBlank()) {
            line = renderNotificationTemplate(MeloXSettingsRuntime.lyricNotificationFallback, "", original)
                .ifBlank { original.title?.toString().orEmpty() }
        }
        if (line.isBlank()) return
        if (metadataEnabled && lineChanged) {
            val originalExtras = Bundle(original.extras ?: Bundle()).apply {
                putString(SYSTEM_ORIGINAL_TITLE_KEY, original.title?.toString().orEmpty())
                putString(SYSTEM_ORIGINAL_ARTIST_KEY, original.artist?.toString().orEmpty())
            }
            val titleMode = MeloXSettingsRuntime.systemLyricTitleMode
            val lyricMetadata = original.buildUpon().apply {
                if (titleMode == MeloXSystemLyricTitleMode.LyricFirst) {
                    setTitle(line)
                    setArtist(original.title)
                    setAlbumTitle(original.artist)
                } else {
                    setTitle(original.title)
                    setArtist(line)
                    setAlbumTitle(original.artist)
                }
                setExtras(originalExtras)
            }.build()
            val item = currentItem.buildUpon().setMediaMetadata(lyricMetadata).build()
            updatingSystemLyricsMetadata = true
            active.replaceMediaItem(active.currentMediaItemIndex, item)
            handler.post { updatingSystemLyricsMetadata = false }
        } else if (!metadataEnabled) {
            restoreSystemLyricsMetadata(active)
        }
        val notificationAllowedByScene =
            (!MeloXSettingsRuntime.lyricNotificationBackgroundOnly || !MeloXAppVisibility.isForeground) &&
                (!MeloXSettingsRuntime.lyricNotificationDismissWhenPaused || active.isPlaying)
        if (notificationEnabled && notificationAllowedByScene) postLyricsNotification(line, nextLine, original) else {
            getSystemService(NotificationManager::class.java).cancel(LYRICS_NOTIFICATION_ID)
        }
    }

    private fun loadSystemLyrics(songId: Long, item: MediaItem) {
        systemLyricsSongId = songId
        systemLyricsOriginalMetadata = item.mediaMetadata
        systemLyricsJob = serviceScope.launch {
            val loaded = withContext(Dispatchers.IO) {
                // 1) 本地下载的歌词
                downloadStore.localLyrics(songId)
                    // 2) 多源在线歌词（QQ音乐/网易云/酷狗等，按歌曲来源自动匹配）
                    ?: loadLyricsFromAnySource(item)
                    // 3) 兜底：用网易云 ID 直接查（兼容纯数字 ID 场景）
                    ?: runCatching {
                        NeteaseSearchClient(
                            cookieProvider = { PlaybackAccountStore.neteaseCookie(this@MeloXPlaybackService) },
                        ).lyrics(songId)
                    }.getOrNull()
            }
            if (systemLyricsSongId == songId) systemLyricsDocument = loaded
            systemLyricsJob = null
        }
    }

    /**
     * 按歌曲来源加载多源歌词。
     * MeloX 支持网易云/QQ音乐/酷狗/AppleMusic/Bilibili 等音源，
     * 通过 MusicResourceId 识别来源，再调用对应 provider 的 LyricsCapability。
     */
    private suspend fun loadLyricsFromAnySource(item: MediaItem): LyricsDocument? {
        val resourceId = PlaybackTrackIdentity.fromMediaItem(item) ?: return null
        if (resourceId.source == com.lladlam.melox.core.music.model.MusicSource.Netease) {
            // 网易云纯数字 ID 已在 loadSystemLyrics 兜底处理
            return null
        }
        return runCatching {
            val registry = MeloXMusicProviders.create(this)
            val provider = registry[resourceId.source] ?: return null
            val lyricsCapability = provider as? LyricsCapability ?: return null
            val track = MusicTrack(
                id = resourceId,
                title = item.mediaMetadata.title?.toString().orEmpty(),
                artists = listOfNotNull(
                    item.mediaMetadata.artist?.toString()?.takeIf(String::isNotBlank)
                ).map { name ->
                    com.lladlam.melox.core.music.model.MusicArtistRef(name = name)
                },
                album = item.mediaMetadata.albumTitle?.toString()?.takeIf(String::isNotBlank)
                    ?.let { com.lladlam.melox.core.music.model.MusicAlbumRef(name = it) },
                durationMs = player?.duration?.takeIf { it != C.TIME_UNSET && it > 0L },
            )
            lyricsCapability.lyrics(track).takeIf { it.lines.isNotEmpty() }
        }.getOrNull()
    }

    /**
     * 车联歌词独立加载器：多源歌词，优先本地缓存，其次在线多源。
     * 加载完成写入 [carLyricsDocument]，由 [pushCarLyrics] 定时轮询推送。
     *
     * 竞态处理：根据传入的 [item] 计算归属 key，而不是读 `player?.currentMediaItem`
     * （协程挂起期间可能已切歌），确保写回的是本次发起请求的那首歌。
     */
    private fun loadCarLyrics(item: MediaItem) {
        // 先按传入 item 计算归属 key：作为本次加载的唯一标识
        val requestedKey = PlaybackTrackIdentity.fromMediaItem(item)?.let { rid ->
            "${rid.source.storageValue}:${rid.value}"
        } ?: item.mediaId
        carLog("loadCarLyrics start: key=$requestedKey mediaId=${item.mediaId} " +
            "neteaseId=${PlaybackTrackIdentity.neteaseNumericId(item)}")
        carLyricsJob = serviceScope.launch {
            val loaded = withContext(Dispatchers.IO) {
                // 1) 本地下载的歌词
                val local = PlaybackTrackIdentity.neteaseNumericId(item)?.let { downloadStore.localLyrics(it) }
                carLog("loadCarLyrics step1 local=$local (lines=${local?.lines?.size})")
                val any = local
                    // 2) 多源在线歌词（按歌曲来源自动匹配）
                    ?: loadLyricsFromAnySource(item)
                    // 3) 兜底：网易云 ID 直接查
                    ?: PlaybackTrackIdentity.neteaseNumericId(item)?.let { songId ->
                        runCatching {
                            NeteaseSearchClient(
                                cookieProvider = { PlaybackAccountStore.neteaseCookie(this@MeloXPlaybackService) },
                            ).lyrics(songId)
                        }.getOrNull()
                    }
                any
            }
            carLog("loadCarLyrics loaded=$loaded lines=${loaded?.lines?.size} " +
                "requestedKey=$requestedKey carLyricsResourceKey=$carLyricsResourceKey")
            // 防止切歌竞态：仅在当前仍处理同一首歌时写入结果
            if (requestedKey == carLyricsResourceKey) {
                val doc = loaded?.takeIf { it.lines.isNotEmpty() }
                carLyricsDocument = doc
                carLyricsFailed = (doc == null)
                carLog("loadCarLyrics writeback doc=${doc != null} failed=$carLyricsFailed")
            } else {
                carLog("loadCarLyrics writeback SKIPPED key mismatch!")
            }
            carLyricsJob = null
        }
    }

    private fun resetSystemLyrics(item: MediaItem?) {
        systemLyricsJob?.cancel()
        systemLyricsJob = null
        systemLyricsSongId = item?.mediaId?.toLongOrNull()
        systemLyricsDocument = null
        systemLyricsOriginalMetadata = item?.mediaMetadata
        systemLyricsLastIndex = Int.MIN_VALUE
        systemLyricsLastDispatchRealtimeMs = 0L
    }

    /** 车联歌词状态重置（切歌时调用） */
    private fun resetCarLyrics(item: MediaItem?) {
        carLyricsJob?.cancel()
        carLyricsJob = null
        carLyricsResourceKey = null
        carLyricsDocument = null
        carLyricsFailed = false
        carLyricsAttempts = 0
        carLyricsLastAttemptRealtimeMs = 0L
    }

    private fun restoreSystemLyricsMetadata(active: ExoPlayer) {
        val original = systemLyricsOriginalMetadata ?: return
        val index = active.currentMediaItemIndex
        if (index !in 0 until active.mediaItemCount) return
        val current = active.getMediaItemAt(index)
        if (current.mediaMetadata.extras?.containsKey(SYSTEM_ORIGINAL_TITLE_KEY) != true) return
        updatingSystemLyricsMetadata = true
        active.replaceMediaItem(index, current.buildUpon().setMediaMetadata(original).build())
        handler.post { updatingSystemLyricsMetadata = false }
    }

    // ====================================================================
    // vivo 车联投屏歌词
    // ====================================================================

    /**
     * 车机歌词推送入口。
     * 从 `MeloXSettingsRuntime` 读取开关状态，调用 CarLyricsManager 更新，
     * 仅在值变化时刷新播放器状态（通过 replacedMediaItem 注入 Channel A 元数据）。
     *
     * 注意：Channel A 通过 replaceMediaItem 注入，但受 `updatingSystemLyricsMetadata`
     * flag 保护，不会触发反馈循环。Channel B 通过 setSessionExtras 安全推送。
     */
    private fun pushCarLyrics(active: ExoPlayer) {
        val manager = carLyricsManager ?: return
        val currentItem = active.currentMediaItem ?: return
        val enabled = MeloXSettingsPreferences.boolean(
            this, CarLyricsConstants.PREF_CAR_LYRICS_ENABLED, true,
        )
        manager.enabled = enabled

        val changed = if (!enabled) {
            carLog("pushCarLyrics: DISABLED (mediaId=${currentItem.mediaId})")
            manager.push()
        } else {
            val lyrics = carLyricsDocument
            if (lyrics != null) {
                // 歌词已加载，推送实际歌词
                val advance = MeloXSettingsRuntime.lyricAdvanceMs.toLong()
                val index = lyrics.highlightedIndex(active.currentPosition + advance)
                val currentLine = index?.let { lyrics.lines.getOrNull(it)?.text?.trim() }
                manager.updateLyric(currentLine, lyrics)
            } else {
                // 车联歌词：独立多源加载（支持 QQ音乐/网易云/酷狗等全部音源）
                val resourceKey = PlaybackTrackIdentity.fromMediaItem(currentItem)?.let { rid ->
                    "${rid.source.storageValue}:${rid.value}"
                } ?: currentItem.mediaId
                when {
                    carLyricsResourceKey != resourceKey -> {
                        // 新歌曲：触发多源歌词加载，期间推 LOADING（不推 -1）
                        carLog("pushCarLyrics: NEW key=$resourceKey (old=${carLyricsResourceKey})")
                        carLyricsResourceKey = resourceKey
                        carLyricsDocument = null
                        carLyricsFailed = false
                        carLyricsAttempts = 1
                        carLyricsLastAttemptRealtimeMs = SystemClock.elapsedRealtime()
                        loadCarLyrics(currentItem)
                        manager.setLoading()
                    }
                    carLyricsJob?.isActive == true -> {
                        // 歌词正在加载中，保持 LOADING（不要误判为无歌词）
                        manager.setLoading()
                    }
                    carLyricsFailed -> {
                        // 上一次加载未成功（接口慢/超时/返回空），限速重试，
                        // 而不是一次性判死为「暂无歌词」。
                        val now = SystemClock.elapsedRealtime()
                        val canRetry = now - carLyricsLastAttemptRealtimeMs >=
                            CAR_LYRICS_RETRY_DELAY_MS
                        if (canRetry && carLyricsAttempts < CAR_LYRICS_MAX_ATTEMPTS) {
                            carLog("pushCarLyrics: RETRY $carLyricsAttempts/$CAR_LYRICS_MAX_ATTEMPTS")
                            carLyricsAttempts += 1
                            carLyricsLastAttemptRealtimeMs = now
                            loadCarLyrics(currentItem)
                            manager.setLoading()
                        } else if (canRetry) {
                            // 已达重试上限，确认无歌词
                            carLog("pushCarLyrics: GIVE_UP -> NO_LYRICS")
                            manager.updateLyric(null, null)
                        } else {
                            // 还没到下次重试时间，继续 LOADING 等待
                            manager.setLoading()
                        }
                    }
                    else -> {
                        // 无失败标记但也没在加载：视为尚无歌词
                        carLog("pushCarLyrics: else-branch -> NO_LYRICS (failed=$carLyricsFailed job=$carLyricsJob)")
                        manager.updateLyric(null, null)
                    }
                }
            }
        }

        // Channel A 注入：仅当值变化时通过 replaceMediaItem 注入元数据。
        // 注意：此 replaceMediaItem 会触发 onMediaItemTransition，好在已通过
        // mediaId 判断避免误 reset 歌词状态。
        if (changed && enabled) {
            val extras = manager.buildMetadataExtras()
            if (extras.isEmpty) return
            val merged = Bundle(currentItem.mediaMetadata.extras ?: Bundle()).apply {
                putAll(extras)
            }
            val metadata = currentItem.mediaMetadata.buildUpon()
                .setExtras(merged)
                .build()
            val updatedItem = currentItem.buildUpon().setMediaMetadata(metadata).build()
            updatingSystemLyricsMetadata = true
            active.replaceMediaItem(active.currentMediaItemIndex, updatedItem)
            handler.post { updatingSystemLyricsMetadata = false }
        } else if (changed && !enabled) {
            // 开关关闭，清除 Channel A 元数据
            val metadata = currentItem.mediaMetadata.buildUpon()
                .setExtras(Bundle()).build()
            val updatedItem = currentItem.buildUpon().setMediaMetadata(metadata).build()
            updatingSystemLyricsMetadata = true
            active.replaceMediaItem(active.currentMediaItemIndex, updatedItem)
            handler.post { updatingSystemLyricsMetadata = false }
        }
    }

    private fun createLyricsNotificationChannel() {
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(
            NotificationChannel(
                LYRICS_NOTIFICATION_CHANNEL,
                "歌词",
                NotificationManager.IMPORTANCE_LOW,
            ).apply {
                description = "显示当前播放歌词"
                setSound(null, null)
                enableVibration(false)
            },
        )
    }

    private fun postLyricsNotification(line: String, nextLine: String, metadata: MediaMetadata) {
        val intent = Intent(this, MainActivity::class.java).apply {
            action = MainActivity.ACTION_OPEN_NOW_PLAYING
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            1002,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        val showNext = MeloXSettingsRuntime.lyricNotificationShowNextLine
        val title = renderNotificationTemplate(MeloXSettingsRuntime.lyricNotificationTitleTemplate, line, metadata)
            .ifBlank { line }
        val subtitle = renderNotificationTemplate(MeloXSettingsRuntime.lyricNotificationSubtitleTemplate, line, metadata)
        val detail = listOf(subtitle, nextLine.takeIf { showNext && it.isNotBlank() })
            .filterNotNull().filter(String::isNotBlank).joinToString("\n")
        val builder = NotificationCompat.Builder(this, LYRICS_NOTIFICATION_CHANNEL)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setContentTitle(title)
            .setContentText(detail)
            .setStyle(NotificationCompat.BigTextStyle().bigText(detail))
            .setContentIntent(pendingIntent)
            .setSilent(true)
            .setOnlyAlertOnce(true)
            .setOngoing(player?.isPlaying == true)
            .setCategory(NotificationCompat.CATEGORY_TRANSPORT)
        if (MeloXSettingsRuntime.lyricNotificationShowArtwork) {
            metadata.artworkData?.let { bytes ->
                runCatching { BitmapFactory.decodeByteArray(bytes, 0, bytes.size) }.getOrNull()?.let(builder::setLargeIcon)
            }
        }
        if (MeloXSettingsRuntime.lyricNotificationShowProgress) {
            val duration = player?.duration?.takeIf { it != C.TIME_UNSET && it > 0L } ?: 0L
            if (duration > 0L) builder.setProgress(1_000, ((player?.currentPosition ?: 0L) * 1_000L / duration).toInt().coerceIn(0, 1_000), false)
        }
        val notification = builder.build()
        HyperOsFocusBridge.playbackPayload(
            context = this,
            lyric = line,
            songTitle = metadata.title?.toString().orEmpty(),
            artist = metadata.artist?.toString().orEmpty(),
            positionMs = player?.currentPosition ?: 0L,
            durationMs = player?.duration?.takeIf { it != C.TIME_UNSET } ?: 0L,
            isPlaying = player?.isPlaying == true,
        )?.let { HyperOsFocusBridge.attachFocusParams(notification, it) }
        getSystemService(NotificationManager::class.java).notify(LYRICS_NOTIFICATION_ID, notification)
    }

    private fun renderNotificationTemplate(template: String, lyric: String, metadata: MediaMetadata): String =
        template
            .replace("{lyric}", lyric)
            .replace("{song}", metadata.title?.toString().orEmpty())
            .replace("{artist}", metadata.artist?.toString().orEmpty())
            .replace("{album}", metadata.albumTitle?.toString().orEmpty())
            .trim().trim('·').trim()

    private fun updateAutoMixEnvelope(active: ExoPlayer, incoming: ExoPlayer) {
        val duration = active.duration.takeIf { it != C.TIME_UNSET && it > 0L }
        val remaining = duration?.minus(active.currentPosition) ?: Long.MAX_VALUE
        val durationMs = mixDurationMs.coerceAtLeast(1L)
        // Drive the envelope from both decks' rendered media clocks, matching
        // upstream MeloX. This prevents the fade from running ahead while the
        // incoming decoder is technically READY but has not advanced audio yet.
        val outgoingProgress =
            (active.currentPosition - mixOutgoingStartPositionMs).coerceAtLeast(0L).toDouble() /
                durationMs.toDouble()
        val incomingProgress =
            (incoming.currentPosition - mixIncomingStartPositionMs).coerceAtLeast(0L).toDouble() /
                durationMs.toDouble()
        val progress = maxOf(
            mixLastProgress,
            minOf(outgoingProgress, incomingProgress),
        ).coerceIn(0.0, 1.0)
        mixLastProgress = progress

        val gains = MeloXAutoMixEnvelope.gains(progress, mixSettings.fadeCurve)
        active.volume = mixBaseVolume * gains.outgoing
        incoming.volume = mixBaseVolume * gains.incoming
        active.setPlaybackSpeed(
            MeloXAutoMixEnvelope.rate(mixPlan.outgoingStartRate, mixPlan.outgoingEndRate, progress),
        )
        incoming.setPlaybackSpeed(
            MeloXAutoMixEnvelope.rate(mixPlan.incomingStartRate, mixPlan.incomingEndRate, progress),
        )
        mixEqualizerEnvelope.apply(progress)

        if (progress >= 1.0 ||
            remaining <= mixPlan.outgoingEndOffsetMs + MeloXAutoMixPlanner.HANDOFF_GUARD_MS
        ) {
            completeAutoMix(active, incoming)
        }
    }

    private fun prepareIncoming(active: ExoPlayer, sourceId: String) {
        PlaybackCommands.prioritizeManualQueue(active)
        val nextIndex = active.currentMediaItemIndex + 1
        if (nextIndex !in 0 until active.mediaItemCount) return
        val nextSongId = active.getMediaItemAt(nextIndex).mediaId.toLongOrNull()
        if (!MeloXNetworkAvailability.isOnline(this) &&
            (nextSongId == null || !downloadStore.contains(nextSongId))
        ) {
            return
        }
        // Reuse the inactive deck just like upstream MeloX. Constructing and
        // releasing ExoPlayer at every handoff adds avoidable work at song edges.
        val incoming = incomingPlayer ?: buildPlayer(
            managesAudioFocus = false,
            observesSession = false,
        )
        incoming.stop()
        incoming.clearMediaItems()
        incoming.setAudioAttributes(audioAttributes, false)
        incoming.setHandleAudioBecomingNoisy(false)
        val items = List(active.mediaItemCount) { active.getMediaItemAt(it) }
        incoming.setMediaItems(items, nextIndex, 0L)
        incoming.volume = 0f
        incoming.prepare()
        incomingPlayer = incoming
        preparedMixSourceId = sourceId
        preparedMixTargetId = active.getMediaItemAt(nextIndex).mediaId
        mixStartedAt = 0L
    }

    private fun startAutoMixAnalysis(
        active: ExoPlayer,
        sourceId: String,
        settings: MeloXAutoMixSettings,
    ) {
        val currentIndex = active.currentMediaItemIndex
        val nextIndex = currentIndex + 1
        if (currentIndex !in 0 until active.mediaItemCount || nextIndex !in 0 until active.mediaItemCount) return
        val outgoingId = active.getMediaItemAt(currentIndex).mediaId.toLongOrNull() ?: return
        val incomingId = active.getMediaItemAt(nextIndex).mediaId.toLongOrNull() ?: return
        mixAnalysisSourceId = sourceId
        analyzedMixPlan = null
        mixAnalysisJob = serviceScope.launch {
            val result = withContext(Dispatchers.IO) {
                runCatching {
                    val quality = MusicQualityPreferences.read(this@MeloXPlaybackService)
                    val outgoingUri = playbackResolver.resolveSongUri(outgoingId, quality)
                    val incomingUri = playbackResolver.resolveSongUri(incomingId, quality)
                    if (!settings.analyzeStreaming &&
                        (outgoingUri.scheme in setOf("http", "https") || incomingUri.scheme in setOf("http", "https"))
                    ) {
                        error("streaming analysis disabled")
                    }
                    val (outgoing, incomingAnalysis) = coroutineScope {
                        val outgoingDeferred = async { autoMixAnalyzer.analyze(outgoingId, outgoingUri) }
                        val incomingDeferred = async { autoMixAnalyzer.analyze(incomingId, incomingUri) }
                        outgoingDeferred.await() to incomingDeferred.await()
                    }
                    val plan = MeloXAutoMixTransitionScorer.plan(settings, outgoing, incomingAnalysis)
                        ?: error("analysis confidence below threshold")
                    Triple(plan, outgoing, incomingAnalysis)
                }
            }
            if (preparedMixSourceId == sourceId && mixAnalysisSourceId == sourceId) {
                result.onSuccess { (plan, outgoing, _) ->
                    MeloXAudioReactiveRuntime.attach(sourceId, outgoing)
                    analyzedMixPlan = plan
                    Log.i(
                        TAG,
                        "AutoMix analysis ready: source=$sourceId, start=${plan.outgoingStartMs}, " +
                            "incoming=${plan.incomingStartMs}, duration=${plan.durationMs}",
                    )
                }.onFailure { error ->
                    analyzedMixPlan = null
                    Log.w(TAG, "AutoMix smart analysis unavailable for $sourceId", error)
                }
            }
            mixAnalysisJob = null
        }
    }

    /**
     * Incoming has already been playing for the entire overlap. Promotion must
     * therefore never seek it again: seeking at handoff creates the audible
     * forward/backward jump reported at the outgoing song's original endpoint.
     */
    private fun completeAutoMix(old: ExoPlayer, incoming: ExoPlayer) {
        handler.removeCallbacks(mixEnvelope)
        mixEqualizerEnvelope.release()
        old.volume = 0f
        incoming.volume = mixBaseVolume
        incoming.setPlaybackSpeed(1f)
        incoming.setAudioAttributes(audioAttributes, true)
        incoming.setHandleAudioBecomingNoisy(true)
        incoming.addListener(playerListener)
        val session = mediaSession ?: error("MediaSession unavailable during AutoMix handoff")
        session.setPlayer(incoming)
        // Publish both deck references together. After this point cleanup must
        // not throw, otherwise recovery could mistake the promoted deck for the
        // standby player and stop the song that is already audible.
        player = incoming
        incomingPlayer = old
        // The listener is attached after this deck already owns its session, so
        // an audio-session callback is not guaranteed during promotion.
        equalizerController.attach(incoming.audioSessionId)
        autoMixRetrySourceId = null
        autoMixRetryAfterRealtimeMs = 0L
        preparedMixSourceId = null
        preparedMixTargetId = null
        mixAnalysisJob?.cancel()
        mixAnalysisJob = null
        mixAnalysisSourceId = null
        analyzedMixPlan = null
        mixStartedAt = 0L
        mixDurationMs = 0L
        mixOutgoingStartPositionMs = 0L
        mixIncomingStartPositionMs = 0L
        mixLastProgress = 0.0
        runCatching { old.removeListener(playerListener) }
        runCatching { old.pause() }
        runCatching { old.stop() }
        runCatching { old.clearMediaItems() }
        runCatching { old.setAudioAttributes(audioAttributes, false) }
        runCatching { old.setHandleAudioBecomingNoisy(false) }
        runCatching { old.setPlaybackSpeed(1f) }
        runCatching { old.volume = 0f }
        runCatching { applyLocalArtworkMetadata(incoming) }
    }

    private fun applyLocalArtworkMetadata(active: ExoPlayer) {
        val index = active.currentMediaItemIndex
        if (index !in 0 until active.mediaItemCount) return
        val item = active.getMediaItemAt(index)
        val songId = item.mediaId.toLongOrNull() ?: return
        val localArtwork = downloadStore.localArtworkUri(songId) ?: return
        if (item.mediaMetadata.artworkUri == localArtwork) return
        val localItem = item.buildUpon()
            .setMediaMetadata(
                item.mediaMetadata.buildUpon()
                    .setArtworkUri(localArtwork)
                    .build(),
            )
            .build()
        active.replaceMediaItem(index, localItem)
    }

    private fun skipToNextDownloaded(active: ExoPlayer): Boolean {
        val current = active.currentMediaItemIndex
        if (current !in 0 until active.mediaItemCount) return false
        val forward = ((current + 1) until active.mediaItemCount).toList()
        val wrapped = if (active.repeatMode == Player.REPEAT_MODE_ALL) {
            (0 until current).toList()
        } else {
            emptyList()
        }
        val target = (forward + wrapped).firstOrNull { index ->
            active.getMediaItemAt(index).mediaId.toLongOrNull()?.let(downloadStore::contains) == true
        } ?: return false
        cancelPreparedMix()
        active.seekToDefaultPosition(target)
        if (active.playbackState == Player.STATE_IDLE) active.prepare()
        active.play()
        return true
    }

    private fun cancelPreparedMix(releaseStandby: Boolean = false) {
        handler.removeCallbacks(mixEnvelope)
        mixEqualizerEnvelope.release()
        mixAnalysisJob?.cancel()
        mixAnalysisJob = null
        mixAnalysisSourceId = null
        analyzedMixPlan = null
        val active = player
        if (mixStartedAt > 0L && active != null) {
            runCatching { active.volume = mixBaseVolume }
            runCatching { active.setPlaybackSpeed(1f) }
        }
        incomingPlayer?.run {
            runCatching { removeListener(playerListener) }
            runCatching { pause() }
            runCatching { stop() }
            runCatching { clearMediaItems() }
            runCatching { volume = 0f }
            runCatching { setPlaybackSpeed(1f) }
            if (releaseStandby) runCatching { release() }
        }
        if (releaseStandby) incomingPlayer = null
        preparedMixSourceId = null
        preparedMixTargetId = null
        mixStartedAt = 0L
        mixDurationMs = 0L
        mixOutgoingStartPositionMs = 0L
        mixIncomingStartPositionMs = 0L
        mixLastProgress = 0.0
        mixSettings = MeloXAutoMixSettings()
        mixPlan = MeloXAutoMixPlan(0L, 0L)
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        Log.d(TAG, "Controller connected: ${controllerInfo.packageName}")
        return mediaSession
    }

    override fun onDestroy() {
        historySongId?.let { playbackHistoryReporter.recordDuration(it, elapsedMs = historyPositionMs) }; historySongId = null; playbackHistoryReporter.close()
        persistPlaybackState()
        handler.removeCallbacks(modeMonitor)
        recommendationJob?.cancel()
        systemLyricsJob?.cancel()
        carLyricsJob?.cancel()
        carLyricsJob = null
        getSystemService(NotificationManager::class.java).cancel(LYRICS_NOTIFICATION_ID)
        serviceScope.cancel()
        cancelPreparedMix(releaseStandby = true)
        equalizerController.release()
        autoMixAnalyzer.clear()
        MeloXAudioReactiveRuntime.clear()
        mediaSession?.release()
        mediaSession = null
        player?.removeListener(playerListener)
        player?.release()
        player = null
        super.onDestroy()
    }

    private companion object {
        const val TAG = "MeloXPlayback"
        const val AUTOPLAY_PRELOAD_MS = 15_000L
        const val PLAYBACK_MAINTENANCE_INTERVAL_MS = 1_000L
        const val AUTOMIX_ENVELOPE_INTERVAL_MS = 20L
        const val AUTOMIX_FAILURE_COOLDOWN_MS = 30_000L
        const val ANALYSIS_FALLBACK_GUARD_MS = 1_200L
        const val ACTIVE_MONITOR_INTERVAL_MS = 100L
        const val PAUSED_MONITOR_INTERVAL_MS = 500L
        const val IDLE_MONITOR_INTERVAL_MS = 2_000L
        const val SETTINGS_SNAPSHOT_INTERVAL_MS = 1_000L
        const val PLAYBACK_STATE_PERSIST_INTERVAL_MS = 10_000L
        const val CAR_LYRICS_RETRY_DELAY_MS = 1_200L
        const val CAR_LYRICS_MAX_ATTEMPTS = 8
        const val SLEEP_TIMER_END_KEY = "playback_sleep_timer_end_epoch_ms"
        const val SYSTEM_ORIGINAL_TITLE_KEY = "melox.system.original_title"
        const val SYSTEM_ORIGINAL_ARTIST_KEY = "melox.system.original_artist"
        const val LYRICS_NOTIFICATION_CHANNEL = "melox_lyrics"
        const val LYRICS_NOTIFICATION_ID = 1702
    }
}
