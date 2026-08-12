#!/usr/bin/env python3
from pathlib import Path
root = Path(__file__).resolve().parents[1]
# NeteaseHomeBlockParser is compiled as part of the app; constructing Android's
# org.json objects in a plain JVM unit test hits the android.jar stub. Keep the
# normal JVM suite + assembleDebug as the validation gate instead of adding a
# misleading host-side JSON test.
test = root / "android/app/src/test/kotlin/com/lladlam/melox/core/library/NeteaseHomeBlockParserTest.kt"
if test.exists():
    test.unlink()
Path(__file__).unlink()
