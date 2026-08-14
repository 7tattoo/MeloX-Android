package com.lladlam.melox.ui.account
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.library.NeteaseLibraryClient
import com.lladlam.melox.core.library.NeteasePlaylistSummary
import com.lladlam.melox.core.network.*
import com.lladlam.melox.playback.PlaybackCommands
import com.lladlam.melox.ui.MeloXBottomContentClearance
import com.lladlam.melox.ui.glass.MeloXActionIcon
import com.lladlam.melox.ui.glass.meloXLiquidButton
import com.lladlam.melox.ui.theme.MeloXTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MeloXAccountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); enableEdgeToEdge(); val userId = intent.getLongExtra(EXTRA_USER_ID, -1L); if (userId <= 0L) { finish(); return }; setContent { MeloXTheme { Screen(userId, ::finish) } } }
    companion object { private const val EXTRA_USER_ID = "user_id"; fun launch(context: Context, userId: Long) { if (userId <= 0L) return; context.startActivity(Intent(context, MeloXAccountActivity::class.java).putExtra(EXTRA_USER_ID, userId).apply { if (context !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }) } }
}
@Composable private fun Screen(userId: Long, onBack: () -> Unit) {
    val context = LocalContext.current; val app = context.applicationContext; val cookie = remember(app) { { NeteaseSessionStore.readCookie(app) } }; val details = remember(app) { NeteaseAccountDetailsClient(cookie) }; val social = remember(app) { NeteaseSocialExtrasClient(cookie) }; val library = remember(app) { NeteaseLibraryClient(cookie) }; val scope = rememberCoroutineScope()
    var profile by remember(userId) { mutableStateOf<MeloXAccountDetail?>(null) }; var playlists by remember(userId) { mutableStateOf<List<NeteasePlaylistSummary>>(emptyList()) }; var period by remember(userId) { mutableStateOf(MeloXUserPlayRecordPeriod.Week) }; var records by remember(userId) { mutableStateOf<List<MeloXUserPlayRecord>>(emptyList()) }; var loading by remember(userId) { mutableStateOf(true) }; var error by remember(userId) { mutableStateOf<String?>(null) }
    suspend fun loadRank(requested: MeloXUserPlayRecordPeriod) { period = requested; runCatching { social.userPlayRecords(userId, requested) }.onSuccess { records = it }.onFailure { error = it.message ?: "听歌排行加载失败" } }
    LaunchedEffect(userId) { loading = true; runCatching { details.userDetail(userId) to withContext(Dispatchers.IO) { library.userPlaylistsBlocking(userId) } }.onSuccess { (p, lists) -> profile = p; playlists = if (lists.firstOrNull()?.name?.contains("喜欢") == true) lists.drop(1) else lists; loadRank(MeloXUserPlayRecordPeriod.Week) }.onFailure { error = it.message ?: "用户资料加载失败" }; loading = false }
    BackHandler(onBack = onBack)
    LazyColumn(Modifier.fillMaxSize().statusBarsPadding(), contentPadding = PaddingValues(20.dp, 12.dp, 20.dp, MeloXBottomContentClearance), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item { Row(verticalAlignment = Alignment.CenterVertically) { Box(Modifier.size(44.dp).meloXLiquidButton(shape = CircleShape).clickable(onClick = onBack), contentAlignment = Alignment.Center) { MeloXActionIcon("‹", Modifier.size(20.dp), MaterialTheme.colorScheme.onSurface) }; Spacer(Modifier.width(12.dp)); Text("用户主页", fontSize = 26.sp, fontWeight = FontWeight.Bold) } }
        profile?.let { v -> item { Column(Modifier.fillMaxWidth().padding(vertical = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) { AsyncImage(v.avatarUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(132.dp).clip(CircleShape)); Text(v.nickname, Modifier.padding(top = 14.dp), fontSize = 24.sp, fontWeight = FontWeight.Bold); v.signature?.let { Text(it, Modifier.padding(top = 6.dp), color = MaterialTheme.colorScheme.onSurface.copy(alpha = .58f), maxLines = 3, overflow = TextOverflow.Ellipsis) }; Text("Lv.${v.level} · 累计听歌 ${v.listenSongs} 首", Modifier.padding(top = 7.dp), color = MaterialTheme.colorScheme.onSurface.copy(alpha = .52f)); Row(Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly) { Metric(v.follows, "关注"); Metric(v.followers, "粉丝"); Metric(if (v.playlistCount > 0) v.playlistCount else playlists.size, "歌单") } } } }
        if (loading) item { Box(Modifier.fillMaxWidth().height(150.dp), contentAlignment = Alignment.Center) { CircularProgressIndicator() } }; error?.let { item { Text(it, color = MaterialTheme.colorScheme.error) } }
        if (profile != null) { item { Text("听歌排行", fontSize = 22.sp, fontWeight = FontWeight.Bold) }; item { Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) { RankButton("最近一周", period == MeloXUserPlayRecordPeriod.Week, Modifier.weight(1f)) { scope.launch { loadRank(MeloXUserPlayRecordPeriod.Week) } }; RankButton("所有时间", period == MeloXUserPlayRecordPeriod.AllTime, Modifier.weight(1f)) { scope.launch { loadRank(MeloXUserPlayRecordPeriod.AllTime) } } } }; items(records.take(100), key = { "rank-${it.song.id}" }) { r -> Row(Modifier.fillMaxWidth().clickable { PlaybackCommands.playQueue(context, records.map(MeloXUserPlayRecord::song), r.song.id) }.padding(vertical = 7.dp), verticalAlignment = Alignment.CenterVertically) { AsyncImage(r.song.artworkUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(46.dp).clip(RoundedCornerShape(8.dp))); Column(Modifier.weight(1f).padding(start = 10.dp)) { Text(r.song.name, maxLines = 1, overflow = TextOverflow.Ellipsis); Text(r.song.artists, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = .48f), maxLines = 1) }; Text("${r.playCount} 次", fontSize = 12.sp) } }; if (playlists.isNotEmpty()) { item { Text("歌单", fontSize = 22.sp, fontWeight = FontWeight.Bold) }; items(playlists, key = { "pl-${it.id}" }) { pl -> Row(Modifier.fillMaxWidth().clickable { scope.launch { runCatching { library.playlistDetail(pl.id) }.onSuccess { d -> d.songs.firstOrNull()?.let { PlaybackCommands.playQueue(context, d.songs, it.id) } } } }.padding(vertical = 7.dp), verticalAlignment = Alignment.CenterVertically) { AsyncImage(pl.coverUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(50.dp).clip(RoundedCornerShape(9.dp))); Column(Modifier.weight(1f).padding(start = 11.dp)) { Text(pl.name, maxLines = 1); Text("${pl.trackCount} 首歌曲", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = .48f)) } } } } }
    }
}
@Composable private fun Metric(value: Int, title: String) = Column(horizontalAlignment = Alignment.CenterHorizontally) { Text(value.toString(), fontWeight = FontWeight.Bold, fontSize = 18.sp); Text(title, fontSize = 12.sp) }
@Composable private fun RankButton(title: String, selected: Boolean, modifier: Modifier, onClick: () -> Unit) = Box(modifier.clip(RoundedCornerShape(16.dp)).background(MaterialTheme.colorScheme.onSurface.copy(alpha = if (selected) .12f else .05f)).clickable(onClick = onClick).padding(vertical = 10.dp), contentAlignment = Alignment.Center) { Text(title, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium) }
