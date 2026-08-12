# MeloX Android 多音乐服务架构

本文档约束 MeloX Android 在网易云音乐、QQ 音乐、酷狗音乐等服务之间的边界，并确保上游 MeloX iOS（目前只实现网易云音乐）未来继续可以低成本迁移到 Android。

## 核心原则

1. **统一数据，不强行统一产品能力。**
   - `MusicTrack`、`MusicResourceId`、`PlaybackResolution`、`LyricsDocument` 等是跨平台语义。
   - 云盘、私人 FM、心动模式、一起听、网易云播客、私信、识曲等能力不要求 QQ/酷狗提供伪实现。
2. **统一 MeloX 视觉语言，不统一页面内容。**
   - MiniPlayer、Now Playing、歌词、Liquid Glass、转场等属于 MeloX Shell。
   - 首页、发现、音乐库/我的、推荐模块由 `MusicExperience` 决定。
3. **网易云模式继续以 MeloX iOS 为迁移基准。**
   - Android 的多 Provider 架构不得反向要求 iOS 支持 QQ/酷狗。
   - iOS 新增的网易云专属功能应优先进入 Android 的 NetEase capability / experience，而不是扩大所有 Provider 的强制接口。
4. **Provider 只负责“怎么从平台得到数据”，Core 负责“播放器需要什么”。**
5. **用户登录态只保存在本机。**
   - MeloX 不要求中转服务器保存用户的音乐平台 Cookie / Token。
6. **跨平台聚合显式 opt-in。**
   - `unifiedEnabled` 默认 `false`。
   - `automaticSourceFallback` 默认 `false`。
   - 默认一次只使用用户选择的一个音乐服务。

## 四层结构

```text
MeloX Shell
  Player / MiniPlayer / Lyrics / Liquid Glass / transitions
        |
Experience Layer
  NeteaseExperience / QQMusicExperience / KugouExperience
        |
Capability + Domain
  Search / Playback / Lyrics / Album / Artist / Playlist / ...
        |
Provider Protocol
  NetEase EAPI/WEAPI / QQ Music requests / Kugou requests
```

## Domain ID

禁止继续假设歌曲 ID 是 `Long`。

```text
MusicResourceId(
    source = QQMusic,
    value = "0039..."
)
```

资源身份必须由 `(source, value)` 共同确定。

- 网易云：数字 ID，保留为字符串进入公共层；旧播放队列继续兼容数字 `mediaId`。
- QQ 音乐：以 `songmid` 为公共 ID，`media_mid` 等放 `ProviderTrackMetadata.QQMusic`。
- 酷狗：以歌曲 hash 为公共 ID，`album_audio_id` / `album_id` 放 `ProviderTrackMetadata.Kugou`。

## Capability 规则

`MusicProvider` 本身只声明身份与能力；功能通过可选 Capability 提供。

公共第一层能力：

- Search
- Playback
- Lyrics
- Playlist（实现后）
- Album（实现后）
- Artist（实现后）
- Library（实现后）

可选第二层能力：

- Comments
- Rankings
- HomeRecommendations
- DailyRecommendations

Provider-native 第三层能力示例（网易云）：

- CloudMusic
- PrivateFm
- HeartMode
- ListenTogether
- Messages
- Recognition
- Podcasts

QQ/酷狗没有对应能力时不要显示入口，不要返回“假数据”占位。

## iOS -> Android 新功能迁移决策

每次 MeloX iOS 更新按以下顺序分类：

1. **纯 UI / 播放器 / 歌词表现**
   - 进入 MeloX Shell / common core。
   - 所有 Provider 自动受益。
2. **通用音乐概念，且至少两个 Provider 有等价能力**
   - 新增或扩展 Capability。
   - 各 Provider 按真实语义实现。
3. **网易云业务功能**
   - 保持 `Netease*` 实现或新增 NetEase-specific capability。
   - `NeteaseExperience` 增加对应入口。
   - QQ/Kugou 不需要修改。

因此，Android 多平台扩展不能破坏“iOS 新功能 -> Android 网易云模式”的单向迁移通道。

## 播放兼容策略

现有播放器大量使用纯数字 `mediaId`，这些继续视为网易云歌曲。

新增 Provider 使用带命名空间的 Media ID：

```text
melox:qq_music:<songmid>
melox:kugou:<hash>
```

Media URI：

```text
legacy: melox://song/<neteaseLongId>?quality=...
new:    melox://track/<source>/<providerId>?qualityTier=...
```

旧 URI 继续由 `NeteasePlaybackResolver` 原路径解析；新 URI 由 `ProviderPlaybackResolver` 解析。

NetEase 的播放历史、相似歌曲自动推荐、云盘、下载、智能 AutoMix 分析等原有业务仍然以数字 ID 分支工作。非网易云歌曲遇到这些 NetEase-only 分支时应安全跳过，而不是伪造数字 ID。

## UI 规则

Provider 切换后允许变化：

- 底部标签标题/内容语义
- 首页 section
- 音乐库/“我的”内容
- 推荐、排行榜、电台等入口
- 登录账号卡片
- Provider-specific 设置

保持一致：

- MeloX 视觉语言
- MiniPlayer / Now Playing
- 播放队列交互
- 歌词渲染能力
- Liquid Glass
- 页面转场与通用手势

## 聚合模式

聚合属于上层组合器，不属于单个 Provider：

```text
UnifiedMusicService
  |- NeteaseProvider
  |- QQMusicProvider
  `- KugouProvider
```

默认关闭。

聚合开启后仍应由用户明确选择参与的平台；“某平台不可播 -> 自动偷换另一个来源”不得作为默认行为。

## 当前实现阶段

当前分支已建立：

- Provider-neutral domain models
- Capability contract
- Experience descriptors
- 本地 Provider selection store
- `NeteaseProvider` 兼容适配
- `QQMusicProvider` 搜索 / 歌词 / vkey 播放
- `KugouProvider` Android 签名 / 搜索 / KRC 逐字歌词 / HTTPS 播放
- Provider-aware Media3 URI / queue bridge

后续 UI 接入应消费这些稳定接口，不允许 UI 直接解析 QQ/酷狗原始 JSON。
