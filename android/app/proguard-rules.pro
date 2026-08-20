# JavaScript bridges are invoked by name from bundled WebView assets.
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# JSON/domain objects are decoded explicitly; retain enum names used in persisted settings.
-keepclassmembers enum com.lladlam.melox.** {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Shizuku and optional vendor bridges are discovered through Android framework metadata.
-keep class rikka.shizuku.** { *; }
-dontwarn rikka.shizuku.**
-dontwarn com.apple.android.music.**
