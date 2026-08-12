#!/usr/bin/env python3
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]

def patch(path: str, old: str, new: str, expected: int = 1) -> None:
    target = ROOT / path
    text = target.read_text(encoding="utf-8")
    count = text.count(old)
    if count != expected:
        raise RuntimeError(f"{path}: expected {expected} matches, got {count}: {old}")
    target.write_text(text.replace(old, new), encoding="utf-8")

patch(
    "android/app/src/main/kotlin/com/lladlam/melox/playback/MeloXPlaybackHistoryReporter.kt",
    "private val eapi = NeteaseAuthenticatedEapi { NeteaseSessionStore.readCookie(app) }",
    "private val eapi = NeteaseAuthenticatedEapi(cookieProvider = { NeteaseSessionStore.readCookie(app) })",
)
patch(
    "android/app/src/main/kotlin/com/lladlam/melox/ui/collection/MeloXCollectionDetailActivity.kt",
    "NeteaseCollectionDetailsClient { NeteaseSessionStore.readCookie(app) }",
    "NeteaseCollectionDetailsClient(cookieProvider = { NeteaseSessionStore.readCookie(app) })",
    expected=2,
)
patch(
    "android/app/src/main/kotlin/com/lladlam/melox/ui/collection/MeloXCollectionDetailActivity.kt",
    "NeteaseUniversalSearchClient { NeteaseSessionStore.readCookie(app) }",
    "NeteaseUniversalSearchClient(cookieProvider = { NeteaseSessionStore.readCookie(app) })",
)
patch(
    "android/app/src/main/kotlin/com/lladlam/melox/ui/player/MeloXListenTogetherInviteActivity.kt",
    "NeteaseMusicOperationsClient { NeteaseSessionStore.readCookie(context.applicationContext) }",
    "NeteaseMusicOperationsClient(cookieProvider = { NeteaseSessionStore.readCookie(context.applicationContext) })",
)
patch(
    "android/app/src/main/kotlin/com/lladlam/melox/core/network/NeteaseUniversalSearchClient.kt",
    '''            for (i in 0 until values.length()) {
                val value = values.optJSONObject(i) ?: continue
                val id = value.optLong("id", -1L)
                if (id <= 0L) continue
''',
    '''            for (i in 0 until values.length()) {
                val value = values.optJSONObject(i) ?: continue
                val id = if (kind == MeloXSearchKind.Users) value.optLong("userId", -1L) else value.optLong("id", -1L)
                if (id <= 0L) continue
''',
)

# This helper is temporary and must not survive the validated repair commit.
Path(__file__).unlink()
