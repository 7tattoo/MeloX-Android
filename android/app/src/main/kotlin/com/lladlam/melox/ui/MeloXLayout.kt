package com.lladlam.melox.ui

import androidx.compose.ui.unit.dp

/**
 * Scroll clearance for the floating mini-player and tab/search chrome.
 *
 * The chrome deliberately draws over the scene so that Backdrop can sample the
 * content below it. Lists therefore reserve their clearance as trailing scroll
 * space instead of shrinking the whole scene or painting an opaque bottom bar.
 */
// Expanded chrome includes the mini-player, so keep enough trailing space for
// both the player and the floating tab/search controls.
val MeloXBottomContentClearance = 156.dp
