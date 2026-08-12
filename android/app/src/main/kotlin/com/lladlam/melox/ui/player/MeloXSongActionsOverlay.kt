package com.lladlam.melox.ui.player

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.audio.MusicQualityPreferences
import com.lladlam.melox.core.download.MeloXDownloadPlaylistRef
import com.lladlam.melox.core.download.MeloXDownloadStore
import com.lladlam.melox.core.library.NeteaseLibraryClient
import com.lladlam.melox.core.library.NeteasePlaylistSummary
import com.lladlam.melox.core.model.SearchSong
import com.lladlam.melox.core.network.MeloXCommentRepliesPage
import com.lladlam.melox.core.network.MeloXListenTogetherRoom
import com.lladlam.melox.core.network.MeloXMusicComment
import com.lladlam.melox.core.network.MeloXSearchKind
import com.lladlam.melox.core.network.MeloXUserPlayRecord
import com.lladlam.melox.core.network.MeloXUserPlayRecordPeriod
import com.lladlam.melox.core.network.MeloXWikiSection
import com.lladlam.melox.core.network.NeteaseMusicOperationsClient
import com.lladlam.melox.core.network.NeteaseSearchClient
import com.lladlam.melox.core.network.NeteaseSocialExtrasClient
import com.lladlam.melox.playback.MeloXListenTogetherCoordinator
import com.lladlam.melox.playback.PlaybackCommands
import com.lladlam.melox.ui.glass.meloXLiquidButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private enum class SongActionPage {
    Main,
    Sleep,
    AddToPlaylist,
    Comments,
    CommentReplies,
    ListeningRank,
    Wiki,
    ListenTogether,
}

@Composable
fun MeloXSongActionsOverlay(
    song: SearchSong,
    queue: List<SearchSong>,
    visible: Boolean,
    onDismiss: () -> Unit,
    playbackState: MeloXPlaybackUiState? = null,
    onNavigateSearch: ((String, MeloXSearchKind) -> Unit)? = null,
    sourcePlaylist: MeloXDownloadPlaylistRef? = null,
) {
    val context = LocalContext.current
    val app = context.applicationContext
    val scope = rememberCoroutineScope()
    val cookieProvider = remember(app) { { NeteaseSessionStore.readCookie(app) } }
    val library = remember(app) { NeteaseLibraryClient(cookieProvider = cookieProvider) }
    val ops = remember(app) { NeteaseMusicOperationsClient(cookieProvider = cookieProvider) }
    val social = remember(app) { NeteaseSocialExtrasClient(cookieProvider = cookieProvider) }
    val account = remember(app) { NeteaseSearchClient(cookieProvider = cookieProvider) }
    val downloads = remember(app) { MeloXDownloadStore.get(app) }

    var page by remember(song.id, visible) { mutableStateOf(SongActionPage.Main) }
    var busy by remember(song.id, visible) { mutableStateOf(false) }
    var message by remember(song.id, visible) { mutableStateOf<String?>(null) }
    var liked by remember(song.id, visible) { mutableStateOf<Boolean?>(null) }
    var writablePlaylists by remember(song.id, visible) { mutableStateOf<List<NeteasePlaylistSummary>>(emptyList()) }
    var comments by remember(song.id, visible) { mutableStateOf<List<MeloXMusicComment>>(emptyList()) }
    var selectedComment by remember(song.id, visible) { mutableStateOf<MeloXMusicComment?>(null) }
    var repliesPage by remember(song.id, visible) { mutableStateOf<MeloXCommentRepliesPage?>(null) }
    var playRecordPeriod by remember(song.id, visible) { mutableStateOf(MeloXUserPlayRecordPeriod.Week) }
    var playRecords by remember(song.id, visible) { mutableStateOf<List<MeloXUserPlayRecord>>(emptyList()) }
    var wiki by remember(song.id, visible) { mutableStateOf<List<MeloXWikiSection>>(emptyList()) }
    var listenRoom by remember(visible) { mutableStateOf<MeloXListenTogetherRoom?>(null) }
    var invitationText by remember(visible) { mutableStateOf("") }

    LaunchedEffect(visible, song.id) {
        if (!visible) return@LaunchedEffect
        runCatching {
            val profile = account.accountProfile()
            val ids = withContext(Dispatchers.IO) { library.likedSongIdsBlocking(profile.userId) }
            liked = song.id in ids
        }
    }
    LaunchedEffect(visible, playbackState != null) {
        if (!visible) return@LaunchedEffect
        if (playbackState != null) MeloXListenTogetherCoordinator.ensureStarted(app)
        runCatching { ops.listenTogetherRoomStatus() }.onSuccess { listenRoom = it }
    }

    suspend fun loadOwnedPlaylists() {
        busy = true
        message = null
        runCatching {
            val profile = account.accountProfile()
            withContext(Dispatchers.IO) { library.userPlaylistsBlocking(profile.userId) }
                .filter { it.creatorUserId == profile.userId }
        }.onSuccess { writablePlaylists = it }
            .onFailure { message = it.message ?: "歌单读取失败" }
        busy = false
    }

    suspend fun loadComments() {
        busy = true
        message = null
        runCatching { ops.songComments(song.id) }
            .onSuccess { comments = it }
            .onFailure { message = it.message ?: "评论加载失败" }
        busy = false
    }

    suspend fun loadReplies(parent: MeloXMusicComment, append: Boolean) {
        busy = true
        message = null
        val current = repliesPage
        val requestTime = if (append) current?.nextTime ?: -1L else -1L
        runCatching { social.songCommentReplies(song.id, parent.id, requestTime) }
            .onSuccess { loaded ->
                repliesPage = if (!append || current == null) {
                    loaded
                } else {
                    loaded.copy(
                        ownerComment = loaded.ownerComment ?: current.ownerComment,
                        replies = (current.replies + loaded.replies).distinctBy(MeloXMusicComment::id),
                        totalCount = maxOf(current.totalCount, loaded.totalCount),
                    )
                }
            }
            .onFailure { message = it.message ?: "评论回复加载失败" }
        busy = false
    }

    suspend fun loadPlayRecords(period: MeloXUserPlayRecordPeriod) {
        busy = true
        message = null
        playRecordPeriod = period
        runCatching {
            val profile = account.accountProfile()
            social.userPlayRecords(profile.userId, period)
        }.onSuccess { playRecords = it }
            .onFailure { message = it.message ?: "听歌排行加载失败" }
        busy = false
    }

    suspend fun loadWiki() {
        busy = true
        message = null
        runCatching { ops.songWiki(song.id) }
            .onSuccess { wiki = it }
            .onFailure { message = it.message ?: "百科加载失败" }
        busy = false
    }

    BackHandler(enabled = visible) {
        page = when (page) {
            SongActionPage.CommentReplies -> SongActionPage.Comments
            SongActionPage.Main -> {
                onDismiss()
                SongActionPage.Main
            }
            else -> SongActionPage.Main
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(spring(stiffness = 520f)),
        exit = fadeOut(spring(stiffness = 620f)),
    ) {
        Box(
            Modifier.fillMaxSize()
                .background(Color.Black.copy(alpha = .22f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss,
                )
                .padding(horizontal = 18.dp)
                .navigationBarsPadding(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 18.dp)
                    .meloXLiquidButton(
                        shape = RoundedCornerShape(30.dp),
                        tint = Color.White.copy(alpha = .08f),
                        surfaceColor = Color.Black.copy(alpha = .12f),
                        blurRadius = 14.dp,
                        lensRadius = 20.dp,
                        refractionHeight = 22.dp,
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {},
                    ),
            ) {
                AnimatedContent(
                    targetState = page,
                    transitionSpec = {
                        (fadeIn(spring(stiffness = 520f)) + scaleIn(initialScale = .96f)) togetherWith
                            (fadeOut(spring(stiffness = 620f)) + scaleOut(targetScale = .96f))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = "song-action-page",
                ) { target ->
                    Column(Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 18.dp)) {
                        ActionHeader(
                            song,
                            when (target) {
                                SongActionPage.Main -> "歌曲操作"
                                SongActionPage.Sleep -> "定时关闭"
                                SongActionPage.AddToPlaylist -> "添加到歌单"
                                SongActionPage.Comments -> "评论"
                                SongActionPage.CommentReplies -> "评论回复"
                                SongActionPage.ListeningRank -> "我的听歌排行"
                                SongActionPage.Wiki -> "歌曲百科"
                                SongActionPage.ListenTogether -> "一起听"
                            },
                        )
                        message?.let {
                            Text(
                                it,
                                color = Color(0xFFFF8A90),
                                fontSize = 12.sp,
                                modifier = Modifier.padding(bottom = 6.dp),
                            )
                        }

                        when (target) {
                            SongActionPage.Main -> {
                                playbackState?.let { ActionItem("定时关闭", "◷") { page = SongActionPage.Sleep } }
                                if (playbackState == null) {
                                    ActionItem("下一首播放", "⇥") {
                                        PlaybackCommands.playNext(context, song)
                                        onDismiss()
                                    }
                                }
                                ActionItem("添加到播放队列", "+") {
                                    if (playbackState != null) playbackState.addCurrentToQueue()
                                    else PlaybackCommands.addToQueue(context, song)
                                    onDismiss()
                                }
                                when {
                                    downloads.contains(song.id) -> ActionItem("删除下载", "↓×") { downloads.remove(song.id) }
                                    downloads.isDownloading(song.id) -> ActionItem("取消下载", "↓×") { downloads.cancel(song.id) }
                                    else -> ActionItem("下载歌曲", "↓") {
                                        downloads.start(song, MusicQualityPreferences.read(app), sourcePlaylist)
                                    }
                                }
                                downloads.activeDownloads[song.id]?.let { active ->
                                    val percent = active.fractionCompleted?.let { (it * 100).toInt() }
                                    Text(
                                        percent?.let { "正在下载 $it%" } ?: "正在下载…",
                                        color = Color.White.copy(alpha = .52f),
                                        fontSize = 11.sp,
                                        modifier = Modifier.padding(start = 46.dp, bottom = 4.dp),
                                    )
                                }
                                downloads.errorMessage?.let {
                                    Text(it, color = Color(0xFFFF8A90), fontSize = 11.sp, modifier = Modifier.padding(start = 46.dp, bottom = 4.dp))
                                }
                                ActionItem("添加到歌单", "≡") {
                                    page = SongActionPage.AddToPlaylist
                                    scope.launch { loadOwnedPlaylists() }
                                }
                                ActionItem(if (liked == true) "取消喜爱" else "喜爱", if (liked == true) "♥" else "♡") {
                                    val desired = liked != true
                                    busy = true
                                    scope.launch {
                                        runCatching { ops.setSongLiked(song.id, desired) }
                                            .onSuccess { liked = desired }
                                            .onFailure { message = it.message }
                                        busy = false
                                    }
                                }
                                ActionItem("分享", "↗") { shareSong(context, song); onDismiss() }
                                ActionItem("查看评论", "◌") {
                                    page = SongActionPage.Comments
                                    scope.launch { loadComments() }
                                }
                                ActionItem("我的听歌排行", "#") {
                                    page = SongActionPage.ListeningRank
                                    scope.launch { loadPlayRecords(MeloXUserPlayRecordPeriod.Week) }
                                }
                                ActionItem("歌曲百科", "i") {
                                    page = SongActionPage.Wiki
                                    scope.launch { loadWiki() }
                                }
                                playbackState?.let { ActionItem("一起听", "◎") { page = SongActionPage.ListenTogether } }
                                if (song.album.isNotBlank() && onNavigateSearch != null) {
                                    ActionItem("前往专辑：${song.album}", "▣") {
                                        onDismiss(); onNavigateSearch(song.album, MeloXSearchKind.Albums)
                                    }
                                }
                                if (song.artists.isNotBlank() && onNavigateSearch != null) {
                                    ActionItem("前往艺人：${song.artists}", "♬") {
                                        onDismiss(); onNavigateSearch(song.artists.substringBefore(" / "), MeloXSearchKind.Artists)
                                    }
                                }
                            }

                            SongActionPage.Sleep -> {
                                val state = playbackState
                                if (state != null) {
                                    listOf(15, 30, 45, 60).forEach { minutes ->
                                        ActionItem("$minutes 分钟后", "◷") { state.setSleepTimer(minutes); onDismiss() }
                                    }
                                    if (state.sleepTimerEndRealtimeMs > 0L) {
                                        ActionItem("取消定时", "×") { state.cancelSleepTimer(); onDismiss() }
                                    }
                                }
                                ActionItem("返回", "‹") { page = SongActionPage.Main }
                            }

                            SongActionPage.AddToPlaylist -> {
                                if (busy) LoadingRow("正在读取歌单")
                                writablePlaylists.forEach { playlist ->
                                    Row(
                                        Modifier.fillMaxWidth().height(58.dp).clickable(enabled = !busy) {
                                            busy = true
                                            scope.launch {
                                                runCatching { ops.addSongToPlaylist(song.id, playlist.id) }
                                                    .onSuccess { onDismiss() }
                                                    .onFailure { message = it.message }
                                                busy = false
                                            }
                                        },
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        AsyncImage(
                                            playlist.coverUrl,
                                            null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.size(46.dp).padding(2.dp),
                                        )
                                        Spacer(Modifier.size(10.dp))
                                        Column(Modifier.weight(1f)) {
                                            Text(playlist.name, color = Color.White, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                            Text("${playlist.trackCount} 首歌曲", color = Color.White.copy(alpha = .5f), fontSize = 12.sp)
                                        }
                                    }
                                }
                                if (!busy && writablePlaylists.isEmpty() && message == null) {
                                    Text("没有可写入的自建歌单。", color = Color.White.copy(alpha = .55f), modifier = Modifier.padding(12.dp))
                                }
                                ActionItem("返回", "‹") { page = SongActionPage.Main }
                            }

                            SongActionPage.Comments -> {
                                if (busy) LoadingRow("正在读取评论")
                                LazyColumn(Modifier.fillMaxWidth().height(360.dp)) {
                                    items(comments, key = { it.id }) { comment ->
                                        Column(
                                            Modifier.fillMaxWidth().clickable {
                                                selectedComment = comment
                                                repliesPage = null
                                                page = SongActionPage.CommentReplies
                                                scope.launch { loadReplies(comment, append = false) }
                                            }.padding(vertical = 9.dp),
                                        ) {
                                            Text(comment.user, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                                            Text(comment.content, color = Color.White.copy(alpha = .9f), fontSize = 14.sp, modifier = Modifier.padding(top = 3.dp))
                                            Text(
                                                listOf(comment.timeText.takeIf(String::isNotBlank), "♡ ${comment.likedCount}", "查看回复 ›")
                                                    .filterNotNull().joinToString(" · "),
                                                color = Color.White.copy(alpha = .42f),
                                                fontSize = 11.sp,
                                                modifier = Modifier.padding(top = 4.dp),
                                            )
                                        }
                                    }
                                }
                                ActionItem("返回", "‹") { page = SongActionPage.Main }
                            }

                            SongActionPage.CommentReplies -> {
                                val parent = selectedComment
                                val replies = repliesPage
                                if (parent != null) {
                                    Text("原评论", color = Color.White.copy(alpha = .5f), fontSize = 12.sp)
                                    CommentRow(replies?.ownerComment ?: parent)
                                }
                                if (busy && replies == null) LoadingRow("正在读取回复")
                                LazyColumn(Modifier.fillMaxWidth().height(280.dp)) {
                                    items(replies?.replies.orEmpty(), key = { it.id }) { reply -> CommentRow(reply) }
                                }
                                replies?.let {
                                    Text(
                                        if (it.totalCount > 0) "全部回复 · ${it.totalCount}" else "暂无回复",
                                        color = Color.White.copy(alpha = .5f),
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                                    )
                                    if (it.hasMore && parent != null) {
                                        ActionItem("加载更多回复", "+") {
                                            if (!busy) scope.launch { loadReplies(parent, append = true) }
                                        }
                                    }
                                }
                                ActionItem("返回评论", "‹") { page = SongActionPage.Comments }
                            }

                            SongActionPage.ListeningRank -> {
                                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    RankPeriodButton(
                                        title = "最近一周",
                                        selected = playRecordPeriod == MeloXUserPlayRecordPeriod.Week,
                                        modifier = Modifier.weight(1f),
                                    ) {
                                        if (!busy) scope.launch { loadPlayRecords(MeloXUserPlayRecordPeriod.Week) }
                                    }
                                    RankPeriodButton(
                                        title = "所有时间",
                                        selected = playRecordPeriod == MeloXUserPlayRecordPeriod.AllTime,
                                        modifier = Modifier.weight(1f),
                                    ) {
                                        if (!busy) scope.launch { loadPlayRecords(MeloXUserPlayRecordPeriod.AllTime) }
                                    }
                                }
                                if (busy) LoadingRow("正在读取听歌排行")
                                LazyColumn(Modifier.fillMaxWidth().height(330.dp)) {
                                    items(playRecords, key = { it.song.id }) { record ->
                                        Row(
                                            Modifier.fillMaxWidth().clickable {
                                                val songs = playRecords.map(MeloXUserPlayRecord::song)
                                                PlaybackCommands.playQueue(context, songs, record.song.id)
                                                onDismiss()
                                            }.padding(vertical = 8.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            AsyncImage(
                                                record.song.artworkUrl,
                                                null,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier.size(44.dp).padding(2.dp),
                                            )
                                            Spacer(Modifier.size(10.dp))
                                            Column(Modifier.weight(1f)) {
                                                Text(record.song.name, color = Color.White, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                                Text(record.song.artists, color = Color.White.copy(alpha = .5f), fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                            }
                                            Text("${record.playCount} 次", color = Color.White.copy(alpha = .65f), fontSize = 12.sp)
                                        }
                                    }
                                }
                                if (!busy && playRecords.isEmpty() && message == null) {
                                    Text("暂无听歌排行数据", color = Color.White.copy(alpha = .5f), modifier = Modifier.padding(12.dp))
                                }
                                ActionItem("返回", "‹") { page = SongActionPage.Main }
                            }

                            SongActionPage.Wiki -> {
                                if (busy) LoadingRow("正在读取百科")
                                LazyColumn(Modifier.fillMaxWidth().height(360.dp)) {
                                    items(wiki, key = { it.title + it.lines.hashCode() }) { section ->
                                        Column(Modifier.fillMaxWidth().padding(vertical = 9.dp)) {
                                            Text(section.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                            section.lines.forEach { line ->
                                                Text(line, color = Color.White.copy(alpha = .70f), fontSize = 13.sp, modifier = Modifier.padding(top = 4.dp))
                                            }
                                        }
                                    }
                                }
                                if (!busy && wiki.isEmpty() && message == null) {
                                    Text("暂无百科资料", color = Color.White.copy(alpha = .5f), modifier = Modifier.padding(12.dp))
                                }
                                ActionItem("返回", "‹") { page = SongActionPage.Main }
                            }

                            SongActionPage.ListenTogether -> {
                                val room = listenRoom
                                if (room == null) {
                                    Text(
                                        "一起听会在后台持续同步播放/暂停、切歌、拖动进度和队列；关闭这个面板不会中断会话。",
                                        color = Color.White.copy(alpha = .62f),
                                        fontSize = 13.sp,
                                        modifier = Modifier.padding(6.dp, 6.dp, 6.dp, 12.dp),
                                    )
                                    ActionItem("发起一起听", "◎") {
                                        if (!busy) {
                                            busy = true
                                            message = null
                                            scope.launch {
                                                runCatching {
                                                    val created = ops.createListenTogetherRoom()
                                                    val profile = account.accountProfile()
                                                    val ids = queue.map(SearchSong::id).filter { it > 0L }.ifEmpty { listOf(song.id) }
                                                    ops.reportListenTogetherPlaylist(created.id, profile.userId, ids, 1)
                                                    created
                                                }.onSuccess {
                                                    listenRoom = it
                                                    MeloXListenTogetherCoordinator.ensureStarted(app)
                                                }.onFailure { message = it.message ?: "创建房间失败" }
                                                busy = false
                                            }
                                        }
                                    }
                                    BasicTextField(
                                        value = invitationText,
                                        onValueChange = { invitationText = it },
                                        singleLine = true,
                                        textStyle = androidx.compose.ui.text.TextStyle(color = Color.White, fontSize = 14.sp),
                                        decorationBox = { inner ->
                                            Box(
                                                Modifier.fillMaxWidth().padding(horizontal = 6.dp, vertical = 6.dp)
                                                    .background(Color.White.copy(alpha = .08f), RoundedCornerShape(14.dp))
                                                    .padding(horizontal = 12.dp, vertical = 11.dp),
                                            ) {
                                                if (invitationText.isBlank()) {
                                                    Text("粘贴一起听邀请链接", color = Color.White.copy(alpha = .38f), fontSize = 14.sp)
                                                }
                                                inner()
                                            }
                                        },
                                    )
                                    ActionItem("加入邀请房间", "→") {
                                        val parsed = parseListenTogetherInvitation(invitationText)
                                        if (parsed == null) message = "邀请链接缺少 roomId 或 inviterId"
                                        else if (!busy) {
                                            busy = true
                                            message = null
                                            scope.launch {
                                                runCatching { ops.joinListenTogetherRoom(parsed.first, parsed.second) }
                                                    .onSuccess {
                                                        listenRoom = it
                                                        MeloXListenTogetherCoordinator.ensureStarted(app)
                                                    }
                                                    .onFailure { message = it.message ?: "加入房间失败" }
                                                busy = false
                                            }
                                        }
                                    }
                                } else {
                                    Text(
                                        "房间 ${room.id} · ${room.users.size.coerceAtLeast(1)} 位成员",
                                        color = Color.White.copy(alpha = .72f),
                                        fontSize = 13.sp,
                                        modifier = Modifier.padding(6.dp),
                                    )
                                    room.users.forEach { member ->
                                        Text("• ${member.name}", color = Color.White.copy(alpha = .58f), fontSize = 12.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp))
                                    }
                                    Text(
                                        "后台自动同步已启用 · 1 秒状态同步 · 5 秒心跳",
                                        color = Color.White.copy(alpha = .5f),
                                        fontSize = 11.sp,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    )
                                    ActionItem("分享房间邀请", "↗") { shareListenTogether(context, song.id, room) }
                                    ActionItem("结束/退出房间", "×") {
                                        if (!busy) {
                                            busy = true
                                            scope.launch {
                                                runCatching { ops.endListenTogetherRoom(room.id) }
                                                    .onSuccess { listenRoom = null }
                                                    .onFailure { message = it.message ?: "结束房间失败" }
                                                busy = false
                                            }
                                        }
                                    }
                                }
                                if (busy) LoadingRow("正在连接一起听")
                                ActionItem("返回", "‹") { page = SongActionPage.Main }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ActionHeader(song: SearchSong, title: String) {
    Text(title, color = Color.White.copy(alpha = .58f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
    Text(
        song.name,
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(top = 3.dp, bottom = 10.dp),
    )
}

@Composable
private fun ActionItem(title: String, symbol: String, onClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth().height(46.dp).clickable(onClick = onClick).padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(Modifier.size(28.dp), contentAlignment = Alignment.Center) {
            Text(symbol, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
        Text(title, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis)
        Spacer(Modifier.weight(1f))
    }
}

@Composable
private fun CommentRow(comment: MeloXMusicComment) {
    Column(Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 6.dp)) {
        Text(comment.user, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        Text(comment.content, color = Color.White.copy(alpha = .9f), fontSize = 14.sp, modifier = Modifier.padding(top = 3.dp))
        if (comment.timeText.isNotBlank() || comment.likedCount > 0L) {
            Text(
                listOf(comment.timeText.takeIf(String::isNotBlank), "♡ ${comment.likedCount}").filterNotNull().joinToString(" · "),
                color = Color.White.copy(alpha = .42f),
                fontSize = 11.sp,
                modifier = Modifier.padding(top = 4.dp),
            )
        }
    }
}

@Composable
private fun RankPeriodButton(
    title: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier
            .background(Color.White.copy(alpha = if (selected) .18f else .07f), RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(title, color = Color.White.copy(alpha = if (selected) 1f else .6f), fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun LoadingRow(text: String) {
    Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
        CircularProgressIndicator(Modifier.size(20.dp), color = Color.White, strokeWidth = 2.dp)
        Spacer(Modifier.size(10.dp))
        Text(text, color = Color.White.copy(alpha = .7f))
    }
}

private fun shareSong(context: Context, song: SearchSong) {
    val url = "https://music.163.com/song?id=${song.id}"
    runCatching {
        context.startActivity(
            Intent.createChooser(
                Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, "${song.name} - ${song.artists}\n$url"),
                "分享歌曲",
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
        )
    }
}

private fun parseListenTogetherInvitation(value: String): Pair<String, String>? = runCatching {
    val uri = Uri.parse(value.trim())
    val room = uri.getQueryParameter("roomId").orEmpty()
    val inviter = uri.getQueryParameter("inviterId").orEmpty()
    if (room.isBlank() || inviter.isBlank()) null else room to inviter
}.getOrNull()

private fun shareListenTogether(context: Context, songId: Long, room: MeloXListenTogetherRoom) {
    val inviter = room.creatorId.ifBlank { room.users.firstOrNull()?.id.orEmpty() }
    val url = "https://st.music.163.com/listen-together/share/?songId=$songId&roomId=${Uri.encode(room.id)}&inviterId=${Uri.encode(inviter)}"
    runCatching {
        context.startActivity(
            Intent.createChooser(
                Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, url),
                "分享一起听邀请",
            ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
        )
    }
}
