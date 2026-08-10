# MeloX Android V5

[![Android](https://github.com/lladlam/MeloX-Android/actions/workflows/android.yml/badge.svg)](https://github.com/lladlam/MeloX-Android/actions/workflows/android.yml)
[![License](https://img.shields.io/badge/License-GPLv3-blue.svg)](LICENSE)

<p align="center">
  MeloX V5 反编译源代码（基于 MeloX Android 项目结构重组）
</p>

> [!IMPORTANT]
> **此分支包含 MeloX V5 的反编译源代码。** 代码已按照 MeloX Android 项目结构重新组织，但未经编译验证。反编译代码可能存在语法错误或不完整，需要手动修复才能编译通过。

> MeloX Android 是非官方开源项目，与网易云音乐、小米、Apple 及其关联公司不存在隶属、合作或授权关系。

## 项目说明

MeloX Android 基于 [youshen2/MeloX](https://github.com/youshen2/MeloX) 的设计、交互与业务逻辑进行原生 Android 迁移。

项目目标不是使用 WebView 套壳，而是尽可能使用 Android 原生能力重新实现 MeloX：

- 使用 **Kotlin + Jetpack Compose** 重建界面与交互；
- 使用 **AndroidX Media3 / ExoPlayer** 实现播放、后台音频与系统媒体会话；
- 将 iOS 独有能力映射到 Android 平台能力；
- 在小米 HyperOS 上提供可选的焦点通知 / 超级岛适配，同时保持普通 Android 设备可用；
- 尽可能复刻 MeloX / Apple Music 风格的播放器、歌词、音乐库与过渡动画；
- Apple Watch 相关功能不在 Android 版本的兼容范围内。

Root 权限不是应用正常运行的必要条件；平台增强功能应尽量通过标准 Android 或 OEM 能力实现，并在不支持时降级。

## 当前已实现

### 账号与网易云音乐

- 网易云音乐网页登录；
- `MUSIC_U` Cookie 登录态持久化；
- 登录态搜索、歌曲详情、歌词、播放 URL 与音乐库请求；
- 账号权限变化后刷新播放资源缓存。

### 搜索与音乐库

- 歌曲搜索；
- 我喜欢的音乐；
- 用户歌单；
- 最近播放；
- 歌单详情与歌曲列表；
- 从音乐库 / 搜索结果直接播放。

### 播放器

- MiniPlayer；
- 全屏播放器；
- 播放队列；
- 播放 / 暂停、上一首 / 下一首、进度控制；
- 后台播放；
- MediaSession 与系统媒体控制；
- 锁屏播放信息；
- 封面与动态取色背景；
- 播放器共享元素与展开 / 收回动画（持续打磨中）。

### 音质

当前按照 MeloX 的音质模型接入网易云音乐播放接口：

- 标准：`standard`
- 高品质：`exhigh`
- 无损：`lossless`
- Hi-Res：`hires`
- 高清环绕声：`jyeffect`
- 沉浸环绕声：`sky`
- 超清母带：`jymaster`

实际可播放音质由**歌曲资源、账号权益、地区以及网易云音乐服务端返回结果**共同决定。请求的目标音质不可用时会按可用资源进行降级，并尽量显示服务端实际返回的音质等级。

### 歌词

- LRC 逐行歌词；
- YRC 官方逐字歌词；
- 当前行跟随与滚动；
- 逐字进度渲染；
- 当前行焦点、缩放与颜色过渡；
- 歌词页与播放页切换动画仍在继续优化。

### HyperOS

- 标准 Android 媒体通知与 MediaSession；
- 可选 HyperOS 焦点通知 / 超级岛桥接；
- OEM 特性通过独立适配层实现，不作为普通 Android 设备的硬依赖。

## 仍在迁移 / 打磨

- 首页与发现页完整内容；
- MeloX 更多详情页面与操作能力；
- 下载与离线播放；
- 更多歌词视觉效果；
- 播放器展开、暂停、打断与返回动画；
- Liquid Glass / 毛玻璃效果在不同 Android 与 HyperOS 版本上的一致性；
- 更多 MeloX iOS 功能的 Android 平台映射。

## 技术栈

| 用途 | Android 实现 |
| --- | --- |
| UI | Kotlin + Jetpack Compose |
| 导航 / 生命周期 | AndroidX Navigation / Lifecycle |
| 音频播放 | AndroidX Media3 / ExoPlayer |
| 系统媒体控制 | MediaSession / MediaSessionService |
| 网络请求 | OkHttp |
| 图片 / 封面加载 | Coil 3 |
| 异步任务 | Kotlin Coroutines |
| 玻璃 / Backdrop | Kyant0 `AndroidLiquidGlass` / `backdrop` |
| 小米平台增强 | HyperOS Focus Notification 适配层 |

## iOS → Android 平台映射

| MeloX / iOS | MeloX Android |
| --- | --- |
| SwiftUI | Jetpack Compose |
| NavigationStack | Navigation Compose |
| AVPlayer / AVFoundation | Media3 ExoPlayer |
| MPNowPlayingInfoCenter / Remote Commands | MediaSession |
| Live Activity / Dynamic Island | 标准媒体通知 + 可选 HyperOS 焦点通知 / 超级岛 |
| SwiftUI Mesh / Flowing Light | Compose Canvas / 动态取色背景 |
| Apple Liquid Glass | AndroidLiquidGlass Backdrop 折射、模糊与降级实现 |

## 运行环境

- Android 8.0（API 26）或更高版本；
- Android SDK 37；
- JDK 17；
- Gradle 9.5.0；
- 推荐使用当前版本 Android Studio 打开 `android/` 目录。

部分 RuntimeShader / Backdrop 视觉能力仅在较新的 Android 版本上可用；旧版本会使用降级样式。

## 本地构建

1. 克隆仓库：

   ```bash
   git clone https://github.com/lladlam/MeloX-Android.git
   cd MeloX-Android
   ```

2. 使用 Android Studio 打开：

   ```text
   MeloX-Android/android
   ```

3. 安装 Android SDK 37，并确保使用 JDK 17。

4. 也可以直接使用 Gradle 构建 Debug APK：

   ```bash
   cd android
   gradle :app:assembleDebug --stacktrace
   ```

5. 构建产物位于：

   ```text
   android/app/build/outputs/apk/debug/app-debug.apk
   ```

GitHub Actions 会在 `main` 分支代码更新时自动构建 Debug APK；以 `android-v*` 标签推送时可触发预发布 Release 工作流。

## 项目结构

```text
.
├── android/
│   ├── app/
│   │   └── src/main/
│   │       ├── java/com/lladlam/melox/
│   │       │   ├── core/          # 账号、网络、音质、歌词、音乐库模型
│   │       │   ├── playback/      # Media3 播放服务与播放资源解析
│   │       │   ├── platform/      # HyperOS 等平台适配
│   │       │   └── ui/            # Compose 页面、播放器、音乐库、玻璃效果
│   │       ├── res/
│   │       ├── assets/
│   │       └── jniLibs/           # 原生库
│   ├── build.gradle.kts
│   └── settings.gradle.kts
├── .github/workflows/             # Android CI / Release
├── LICENSE
└── README.md
```

## 开源项目与特别鸣谢

MeloX Android 的主体代码来自 MeloX 的 Android 迁移工作，同时直接使用或参考了以下开源项目。**各项目仍分别遵循其自己的许可证；MeloX Android 的 GPLv3 不会替代第三方项目原有许可证。**

### 上游项目

- [youshen2/MeloX](https://github.com/youshen2/MeloX) — 本项目的上游 MeloX，实现、界面、交互与网易云音乐业务逻辑的主要迁移来源；主体采用 GPLv3。

### Android 直接依赖

- [AndroidX / Jetpack Compose](https://github.com/androidx/androidx) — Compose UI、Activity、Lifecycle、Navigation 等 Android 基础能力；主要使用 Apache License 2.0。
- [AndroidX Media3](https://github.com/androidx/media) — ExoPlayer、MediaSession 与后台播放；Apache License 2.0。
- [Coil](https://github.com/coil-kt/coil) — Compose 图片与封面加载；Apache License 2.0。
- [OkHttp](https://github.com/square/okhttp) — HTTP 网络客户端；Apache License 2.0。
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) — Kotlin 协程与异步任务；Apache License 2.0。
- [Miuix](https://github.com/compose-miuix-ui/miuix) — 当前 Android Backdrop / Blur 实验实现使用 `miuix-blur`；Apache License 2.0。

### Liquid Glass / Backdrop 实现来源

Android 版直接依赖 [Kyant0/AndroidLiquidGlass](https://github.com/Kyant0/AndroidLiquidGlass) 的 `backdrop` 组件；`MeloXBackdropComponents.kt` 基于其官方 `LiquidButton` 与 `LiquidBottomTabs` 示例适配，在保留 MeloX iOS 尺寸和布局的前提下提供折射、模糊、按压高光与底栏选中透镜。

当前玻璃效果仍属于实验性实现，视觉与兼容性会继续调整。

### 上游 MeloX 的参考来源

上游 MeloX 还特别鸣谢了以下项目；Android 迁移版在歌词 / 网易云接口等部分通过上游实现间接继承了这些设计与研究成果：

- [jayfunc/BetterLyrics](https://github.com/jayfunc/BetterLyrics) — 逐字歌词渲染、光效与动效参考；
- [WXRIW/Lyricify-Lyrics-Helper](https://github.com/WXRIW/Lyricify-Lyrics-Helper) — 网易云 YRC 逐字歌词解析参考；
- [qier222/YesPlayMusic](https://github.com/qier222/YesPlayMusic) — 网易云接口与播放器实现参考。

如后续 Android 版本迁移 PV Tool、BeatNet 或其他 MeloX 功能，将继续按照上游项目要求保留对应的独立许可证与署名。

## 免责声明

本项目出于学习、研究与开源交流目的开发。

- MeloX Android 不以绕过付费、版权、地区限制或网易云音乐服务限制为目标；
- 使用者应自行遵守所在地法律法规、网易云音乐服务条款以及音乐内容的版权要求；
- 项目调用的第三方服务接口可能发生变化，开发者不保证持续可用；
- 本项目按许可证所述不提供任何担保，使用本项目产生的风险由使用者自行承担。

## 许可证

MeloX Android 主体代码按照与上游 MeloX 相同的 **GNU General Public License version 3（GPLv3）** 发布，完整条款见 [LICENSE](LICENSE)。

复制、修改或分发本项目时，请遵守 GPLv3 关于源代码提供、版权声明、修改说明以及同许可证分发等要求。

第三方代码、库、资源与模型继续适用各自的许可证。各贡献者保留其对应贡献的版权。
