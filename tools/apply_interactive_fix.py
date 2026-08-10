from pathlib import Path


def rep(text: str, old: str, new: str, label: str) -> str:
    count = text.count(old)
    if count != 1:
        raise SystemExit(f"{label}: expected 1 match, got {count}")
    return text.replace(old, new, 1)


# --- MeloXApp.kt: one seekable transition owns open, back, drag and cancel. ---
p = Path("android/app/src/main/kotlin/com/lladlam/melox/ui/MeloXApp.kt")
s = p.read_text()
s = rep(s,
    "import androidx.compose.animation.core.animateFloatAsState\nimport androidx.compose.animation.core.spring",
    "import androidx.compose.animation.core.SeekableTransitionState\nimport androidx.compose.animation.core.animateFloatAsState\nimport androidx.compose.animation.core.rememberTransition\nimport androidx.compose.animation.core.spring",
    "app animation imports")
s = rep(s,
    "import androidx.compose.runtime.remember\nimport androidx.compose.runtime.setValue",
    "import androidx.compose.runtime.remember\nimport androidx.compose.runtime.rememberCoroutineScope\nimport androidx.compose.runtime.setValue",
    "app coroutine scope import")
s = rep(s,
    "import com.lladlam.melox.ui.settings.SettingsScreen\nimport kotlin.math.roundToInt",
    "import com.lladlam.melox.ui.settings.SettingsScreen\nimport kotlinx.coroutines.launch\nimport kotlin.math.roundToInt",
    "app launch import")
s = rep(s,
'''    var selectedTab by remember { mutableStateOf(AppTab.Home) }
    var showNowPlaying by remember { mutableStateOf(false) }
    var showNeteaseLogin by remember { mutableStateOf(false) }
    var loginReturnTab by remember { mutableStateOf(AppTab.Settings) }
    var tabBarMinimized by remember { mutableStateOf(false) }
    var scrollAccumulator by remember { mutableFloatStateOf(0f) }
    val playbackState = rememberMeloXPlaybackUiState()''',
'''    var selectedTab by remember { mutableStateOf(AppTab.Home) }
    var showNeteaseLogin by remember { mutableStateOf(false) }
    var loginReturnTab by remember { mutableStateOf(AppTab.Settings) }
    var tabBarMinimized by remember { mutableStateOf(false) }
    var scrollAccumulator by remember { mutableFloatStateOf(0f) }
    val playbackState = rememberMeloXPlaybackUiState()
    val playerTransitionState = remember { SeekableTransitionState(false) }
    val playerTransition = rememberTransition(
        transitionState = playerTransitionState,
        label = "melox-player-transition",
    )
    val playerScope = rememberCoroutineScope()
    val openPlayer: () -> Unit = {
        if (playbackState.hasMedia) {
            playerScope.launch {
                playerTransitionState.animateTo(
                    targetState = true,
                    animationSpec = playerTransitionSpec(),
                )
            }
        }
    }
    val closePlayer: () -> Unit = {
        playerScope.launch {
            playerTransitionState.animateTo(
                targetState = false,
                animationSpec = playerTransitionSpec(),
            )
        }
    }''',
    "app player state")
s = rep(s,
'''    LaunchedEffect(openNowPlayingRequest, playbackState.hasMedia) {
        if (openNowPlayingRequest > 0 && playbackState.hasMedia) {
            showNowPlaying = true
        }
    }''',
'''    LaunchedEffect(openNowPlayingRequest, playbackState.hasMedia) {
        if (openNowPlayingRequest > 0 && playbackState.hasMedia) {
            playerTransitionState.animateTo(
                targetState = true,
                animationSpec = playerTransitionSpec(),
            )
        }
    }

    LaunchedEffect(playbackState.hasMedia) {
        if (!playbackState.hasMedia) {
            playerTransitionState.snapTo(false)
        }
    }''',
    "app external open")
s = rep(s,
    "            val fullPlayerVisible = showNowPlaying && playbackState.hasMedia",
'''            val fullPlayerVisible = playbackState.hasMedia &&
                (playerTransitionState.currentState || playerTransitionState.targetState)''',
    "app active state")
s = rep(s,
'''                        AnimatedVisibility(
                            visible = !fullPlayerVisible,
                            enter = EnterTransition.None,
                            exit = ExitTransition.None,
                        ) {
                            MeloXIOSMiniPlayer(
                                state = playbackState,
                                onExpand = { showNowPlaying = true },''',
'''                        playerTransition.AnimatedVisibility(
                            visible = { value -> !value },
                            enter = EnterTransition.None,
                            exit = ExitTransition.None,
                        ) {
                            MeloXIOSMiniPlayer(
                                state = playbackState,
                                onExpand = openPlayer,''',
    "app mini transition")
s = rep(s,
'''            AnimatedVisibility(
                visible = fullPlayerVisible,
                enter = EnterTransition.None,
                exit = ExitTransition.None,
                modifier = Modifier.fillMaxSize(),
            ) {
                MeloXIOSNowPlayingSharedHost(
                    state = playbackState,
                    onDismiss = { showNowPlaying = false },
                    sharedTransitionScope = sharedScope,
                    animatedVisibilityScope = this,
                )
            }

            BackHandler(enabled = fullPlayerVisible && !showNeteaseLogin) {
                showNowPlaying = false
            }''',
'''            playerTransition.AnimatedVisibility(
                visible = { value -> value },
                enter = EnterTransition.None,
                exit = ExitTransition.None,
                modifier = Modifier.fillMaxSize(),
            ) {
                MeloXIOSNowPlayingSharedHost(
                    state = playbackState,
                    onDismiss = closePlayer,
                    onSeekCollapse = { fraction ->
                        playerTransitionState.seekTo(
                            fraction = fraction.coerceIn(0f, 0.999f),
                            targetState = false,
                        )
                    },
                    onSettleCollapse = { collapse ->
                        playerTransitionState.animateTo(
                            targetState = !collapse,
                            animationSpec = playerTransitionSpec(),
                        )
                    },
                    sharedTransitionScope = sharedScope,
                    animatedVisibilityScope = this,
                )
            }

            BackHandler(enabled = fullPlayerVisible && !showNeteaseLogin) {
                closePlayer()
            }''',
    "app full transition")
s = rep(s,
    "private fun smoothStep(value: Float, start: Float, end: Float): Float {",
'''private fun playerTransitionSpec() = spring<Float>(
    dampingRatio = 0.90f,
    stiffness = 320f,
    visibilityThreshold = 0.001f,
)

private fun smoothStep(value: Float, start: Float, end: Float): Float {''',
    "app transition spec")
p.write_text(s)


# --- MiniPlayer: play moves monotonically into Next's old slot. ---
p = Path("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXIOSMiniPlayer.kt")
s = p.read_text()
s = rep(s,
    "import androidx.compose.foundation.layout.size\n",
    "import androidx.compose.foundation.layout.size\nimport androidx.compose.foundation.layout.width\n",
    "mini width import")
s = rep(s,
    "    val compactNextAlpha = 1f - smoothStep(compact, 0.08f, 0.68f)\n    val artistHeight = lerpDp(15.dp, 0.dp, smoothStep(compact, 0.04f, 0.72f))",
    "    val compactNextAlpha = 1f - smoothStep(compact, 0.04f, 0.50f)\n    val controlStageWidth = lerpDp(82.dp, 36.dp, smoothStep(compact, 0.08f, 0.84f))\n    val artistHeight = lerpDp(15.dp, 0.dp, smoothStep(compact, 0.04f, 0.72f))",
    "mini control metrics")
s = rep(s,
'''            MiniVectorButton(
                kind = if (state.isPlaying) MiniGlyph.Pause else MiniGlyph.Play,
                enabled = true,
                onClick = state::togglePlayPause,
                modifier = chromeOverlayModifier,
                visualAlpha = miniChromeAlpha,
            )
            if (compact < 0.999f) {
                MiniVectorButton(
                    kind = MiniGlyph.Forward,
                    enabled = state.hasNext || state.repeatMode != 0,
                    onClick = state::next,
                    modifier = chromeOverlayModifier,
                    visualAlpha = miniChromeAlpha * compactNextAlpha,
                )
            }''',
'''            Box(
                modifier = Modifier
                    .width(controlStageWidth)
                    .height(40.dp),
            ) {
                MiniVectorButton(
                    kind = if (state.isPlaying) MiniGlyph.Pause else MiniGlyph.Play,
                    enabled = true,
                    onClick = state::togglePlayPause,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .then(chromeOverlayModifier),
                    visualAlpha = miniChromeAlpha,
                )
                MiniVectorButton(
                    kind = MiniGlyph.Forward,
                    enabled = state.hasNext || state.repeatMode != 0,
                    onClick = state::next,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .then(chromeOverlayModifier),
                    visualAlpha = miniChromeAlpha * compactNextAlpha,
                )
            }''',
    "mini controls")
p.write_text(s)


# --- Shared host: finger delta directly seeks the shared-transition timeline. ---
p = Path("android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXIOSNowPlayingSharedHost.kt")
s = p.read_text()
for line in (
    "import androidx.compose.animation.core.Animatable\n",
    "import androidx.compose.runtime.LaunchedEffect\n",
    "import androidx.compose.ui.graphics.TransformOrigin\n",
    "import androidx.compose.ui.platform.LocalDensity\n",
):
    s = s.replace(line, "")
s = rep(s,
    "import androidx.compose.runtime.getValue\nimport androidx.compose.runtime.mutableStateOf",
    "import androidx.compose.runtime.getValue\nimport androidx.compose.runtime.mutableFloatStateOf\nimport androidx.compose.runtime.mutableStateOf",
    "host float state")
start = s.index("@OptIn(ExperimentalSharedTransitionApi::class)\n@Composable\nfun MeloXIOSNowPlayingSharedHost(")
end = s.index("@OptIn(ExperimentalSharedTransitionApi::class)\n@Composable\nprivate fun SharedArtworkDestination(")
new_host = '''@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MeloXIOSNowPlayingSharedHost(
    state: MeloXPlaybackUiState,
    onDismiss: () -> Unit,
    onSeekCollapse: suspend (Float) -> Unit,
    onSettleCollapse: suspend (Boolean) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    var page by remember(state.mediaId) { mutableStateOf(MeloXNowPlayingPage.Artwork) }
    var gestureCollapseProgress by remember(state.mediaId) { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()

    val expansionProgress by animatedVisibilityScope.transition.animateFloat(
        transitionSpec = {
            spring(
                dampingRatio = 0.90f,
                stiffness = 320f,
                visibilityThreshold = 0.001f,
            )
        },
        label = "full-player-expansion-progress",
    ) { visibility ->
        if (visibility == EnterExitState.Visible) 1f else 0f
    }

    val backdropAlpha = smoothStep(expansionProgress, 0.08f, 0.58f)
    val fullPlayerAlpha = smoothStep(expansionProgress, 0.46f, 0.90f)
    val cornerRadius = (22f * (1f - smoothStep(expansionProgress, 0.00f, 0.94f))).dp

    val sharedContainerModifier = with(sharedTransitionScope) {
        Modifier.sharedBounds(
            sharedContentState = rememberSharedContentState(
                key = sharedPlayerContainerKey(state.mediaId),
            ),
            animatedVisibilityScope = animatedVisibilityScope,
            enter = EnterTransition.None,
            exit = ExitTransition.None,
            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds,
        )
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        val dragRangePx = (constraints.maxHeight * 0.72f).coerceAtLeast(1f)
        val dragState = rememberDraggableState { delta ->
            gestureCollapseProgress = (
                gestureCollapseProgress + delta / dragRangePx
                ).coerceIn(0f, 0.999f)
            scope.launch(start = CoroutineStart.UNDISPATCHED) {
                onSeekCollapse(gestureCollapseProgress)
            }
        }

        Box(
            modifier = sharedContainerModifier
                .fillMaxSize()
                .clip(RoundedCornerShape(cornerRadius))
                .draggable(
                    state = dragState,
                    orientation = Orientation.Vertical,
                    enabled = page == MeloXNowPlayingPage.Artwork && expansionProgress >= 0.995f,
                    onDragStarted = {
                        gestureCollapseProgress = 0f
                    },
                    onDragStopped = { velocity ->
                        val releaseProgress = gestureCollapseProgress
                        val shouldCollapse = releaseProgress >= 0.42f || velocity >= 1200f
                        scope.launch {
                            onSettleCollapse(shouldCollapse)
                            if (!shouldCollapse) gestureCollapseProgress = 0f
                        }
                    },
                ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = backdropAlpha },
            ) {
                MeloXFlowingLightBackdrop(
                    artworkUrl = state.artworkUrl,
                    isPlaying = state.isPlaying,
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = fullPlayerAlpha },
            ) {
                MeloXIOSNowPlayingV2(
                    state = state,
                    onDismiss = onDismiss,
                    page = page,
                    onPageChanged = { page = it },
                    drawBackdrop = false,
                    drawArtwork = false,
                )
            }

            SharedArtworkDestination(
                state = state,
                page = page,
                expansionProgress = expansionProgress,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
            )
        }
    }
}

'''
s = s[:start] + new_host + s[end:]
p.write_text(s)


# --- Music Library picker: one persistent moving liquid lens. ---
p = Path("android/app/src/main/kotlin/com/lladlam/melox/ui/library/LibraryScreen.kt")
s = p.read_text()
s = rep(s,
    "import androidx.compose.animation.core.FastOutSlowInEasing\nimport androidx.compose.animation.core.tween",
    "import androidx.compose.animation.core.FastOutSlowInEasing\nimport androidx.compose.animation.core.animateFloatAsState\nimport androidx.compose.animation.core.spring\nimport androidx.compose.animation.core.tween",
    "library animation imports")
s = rep(s,
    "import androidx.compose.foundation.clickable\nimport androidx.compose.foundation.interaction.MutableInteractionSource",
    "import androidx.compose.foundation.clickable\nimport androidx.compose.foundation.interaction.MutableInteractionSource\nimport androidx.compose.foundation.isSystemInDarkTheme",
    "library theme import")
s = rep(s,
    "import androidx.compose.foundation.layout.fillMaxSize\nimport androidx.compose.foundation.layout.fillMaxWidth\nimport androidx.compose.foundation.layout.height",
    "import androidx.compose.foundation.layout.fillMaxHeight\nimport androidx.compose.foundation.layout.fillMaxSize\nimport androidx.compose.foundation.layout.fillMaxWidth\nimport androidx.compose.foundation.layout.height\nimport androidx.compose.foundation.layout.offset",
    "library layout imports")
s = rep(s,
    "import androidx.compose.ui.draw.BlurredEdgeTreatment\nimport androidx.compose.ui.draw.blur",
    "import androidx.compose.ui.draw.BlurredEdgeTreatment\nimport androidx.compose.ui.draw.alpha\nimport androidx.compose.ui.draw.blur",
    "library alpha import")
s = rep(s,
    "import androidx.compose.ui.text.style.TextOverflow\nimport androidx.compose.ui.unit.dp",
    "import androidx.compose.ui.text.style.TextOverflow\nimport androidx.compose.ui.unit.IntOffset\nimport androidx.compose.ui.unit.dp",
    "library IntOffset import")
s = rep(s,
    "import com.lladlam.melox.ui.player.MeloXFlowingLightBackdrop\nimport kotlinx.coroutines.launch",
    "import com.lladlam.melox.ui.player.MeloXFlowingLightBackdrop\nimport com.kyant.backdrop.backdrops.layerBackdrop\nimport com.kyant.backdrop.backdrops.rememberLayerBackdrop\nimport kotlinx.coroutines.launch\nimport kotlin.math.roundToInt",
    "library backdrop imports")
start = s.index("@Composable\nprivate fun MeloXLibrarySegmentedPicker(")
end = s.index("@Composable\nprivate fun MeloXLibrarySongsPage(")
new_picker = '''@Composable
private fun MeloXLibrarySegmentedPicker(
    selected: MeloXLibraryPage,
    onSelected: (MeloXLibraryPage) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pages = MeloXLibraryPage.entries
    val panelShape = RoundedCornerShape(16.dp)
    val lensShape = RoundedCornerShape(15.dp)
    val panelBackdrop = rememberLayerBackdrop()
    val dark = isSystemInDarkTheme()
    val selectedIndex = pages.indexOf(selected).coerceAtLeast(0)
    val lensPosition by animateFloatAsState(
        targetValue = selectedIndex.toFloat(),
        animationSpec = spring(
            dampingRatio = 1f,
            stiffness = 460f,
            visibilityThreshold = 0.001f,
        ),
        label = "library-segment-lens-position",
    )
    val panelTint = MaterialTheme.colorScheme.surface.copy(alpha = 0.10f)
    val panelSurface = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.055f)
    val selectionTint = if (dark) {
        Color.White.copy(alpha = 0.22f)
    } else {
        Color.White.copy(alpha = 0.72f)
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(30.dp)
            .clip(panelShape)
            .meloXLiquidBottomBar(
                shape = panelShape,
                tint = panelTint,
                surfaceColor = panelSurface,
            ),
    ) {
        val panelWidthPx = constraints.maxWidth

        Row(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0f)
                .layerBackdrop(panelBackdrop)
                .meloXLiquidBottomBar(
                    shape = panelShape,
                    tint = panelTint,
                    surfaceColor = panelSurface,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            pages.forEach { page ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(page.title, fontSize = 13.sp)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(1f / pages.size)
                .fillMaxHeight()
                .offset {
                    IntOffset(
                        x = (lensPosition * panelWidthPx / pages.size).roundToInt(),
                        y = 0,
                    )
                }
                .padding(horizontal = 1.dp, vertical = 1.dp)
                .meloXLiquidTabSelection(
                    shape = lensShape,
                    selected = true,
                    tint = selectionTint,
                    panelBackdrop = panelBackdrop,
                ),
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            pages.forEach { page ->
                val isSelected = page == selected
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) { onSelected(page) },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = page.title,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        }
    }
}

'''
s = s[:start] + new_picker + s[end:]
p.write_text(s)
