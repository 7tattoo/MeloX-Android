package com.lladlam.melox.ui

import androidx.compose.ui.unit.dp

/**
 * Scroll clearance for the floating mini-player and tab/search chrome.
 *
 * The chrome deliberately draws over the scene so that Backdrop can sample the
 * content below it. Lists therefore reserve their clearance as trailing scroll
 * space instead of shrinking the whole scene or painting an opaque bottom bar.
 */
val MeloXBottomContentClearance = 156.dp
