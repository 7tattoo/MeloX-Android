package com.lladlam.melox.core.crash;

import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.PersistableBundle;
import android.os.Process;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MeloXCrashReporter.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0005J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u0014J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u000bH\u0002J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u000bH\u0002J\u0018\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u0005H\u0002J\u0018\u0010&\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u0005H\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010*\u001a\n ,*\u0004\u0018\u00010+0+2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/lladlam/melox/core/crash/MeloXCrashReporter;", "", "<init>", "()V", "PENDING_REPORT_FILE", "", "PREFS_NAME", "LAST_ACTION", "LAST_ACTION_TIME", "LAST_SEEN_EXIT_TIME", "MAX_REPORT_LENGTH", "", "ACTION_EXIT_WINDOW_MS", "", "installed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "handlingCrash", "install", "", "context", "Landroid/content/Context;", "recordAction", "action", "restoreBestAvailableReportToClipboard", "restorePendingReportToClipboard", "", "buildHistoricalExitReport", "markHistoricalExitsSeen", "isActionableExit", "reason", "reasonName", "buildReport", "thread", "Ljava/lang/Thread;", "throwable", "", "redact", "value", "copyToClipboard", "report", "pendingReportFile", "Ljava/io/File;", "preferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MeloXCrashReporter {
    private static final long ACTION_EXIT_WINDOW_MS = 600000;
    private static final String LAST_ACTION = "last_action";
    private static final String LAST_ACTION_TIME = "last_action_time";
    private static final String LAST_SEEN_EXIT_TIME = "last_seen_exit_time";
    private static final int MAX_REPORT_LENGTH = 131072;
    private static final String PENDING_REPORT_FILE = "pending_crash_report.txt";
    private static final String PREFS_NAME = "melox_crash_reporter";
    public static final MeloXCrashReporter INSTANCE = new MeloXCrashReporter();
    private static final AtomicBoolean installed = new AtomicBoolean(false);
    private static final AtomicBoolean handlingCrash = new AtomicBoolean(false);
    public static final int $stable = 8;

    private MeloXCrashReporter() {
    }

    public final void install(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (installed.compareAndSet(false, true)) {
            final Context appContext = context.getApplicationContext();
            final Thread.UncaughtExceptionHandler previousHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.lladlam.melox.core.crash.MeloXCrashReporter$$ExternalSyntheticLambda0
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public final void uncaughtException(Thread thread, Throwable th) {
                    MeloXCrashReporter.install$lambda$0(previousHandler, appContext, thread, th);
                }
            });
        }
    }

    static final void install$lambda$0(Thread.UncaughtExceptionHandler $previousHandler, Context $appContext, Thread thread, Throwable throwable) {
        if (handlingCrash.compareAndSet(false, true)) {
            MeloXCrashReporter meloXCrashReporter = INSTANCE;
            Intrinsics.checkNotNull(thread);
            Intrinsics.checkNotNull(throwable);
            String report = meloXCrashReporter.buildReport(thread, throwable);
            MeloXCrashReporter meloXCrashReporter2 = INSTANCE;
            try {
                Result.Companion companion = Result.INSTANCE;
                Intrinsics.checkNotNull($appContext);
                FilesKt.writeText$default(meloXCrashReporter2.pendingReportFile($appContext), report, null, 2, null);
                Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.constructor-impl(ResultKt.createFailure(th));
            }
            MeloXCrashReporter meloXCrashReporter3 = INSTANCE;
            try {
                Result.Companion companion3 = Result.INSTANCE;
                Intrinsics.checkNotNull($appContext);
                Result.constructor-impl(Boolean.valueOf(meloXCrashReporter3.copyToClipboard($appContext, report)));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.constructor-impl(ResultKt.createFailure(th2));
            }
        }
        if ($previousHandler != null) {
            $previousHandler.uncaughtException(thread, throwable);
        } else {
            Process.killProcess(Process.myPid());
            System.exit(10);
            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
        }
    }

    public final void recordAction(Context context, String action) {
        Unit unit;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(action, "action");
        long now = System.currentTimeMillis();
        preferences(context).edit().putString(LAST_ACTION, action).putLong(LAST_ACTION_TIME, now).commit();
        if (Build.VERSION.SDK_INT >= 30) {
            byte[] state = ("action=" + action + ";time=" + now).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(state, "getBytes(...)");
            try {
                Result.Companion companion = Result.INSTANCE;
                MeloXCrashReporter meloXCrashReporter = this;
                ActivityManager activityManager = (ActivityManager) context.getSystemService(ActivityManager.class);
                if (activityManager != null) {
                    activityManager.setProcessStateSummary(state);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                Result.constructor-impl(unit);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.constructor-impl(ResultKt.createFailure(th));
            }
        }
    }

    public final String restoreBestAvailableReportToClipboard(Context context) {
        Object objM9714constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        if (restorePendingReportToClipboard(context)) {
            markHistoricalExitsSeen(context);
            return "上次 Kotlin/Java 崩溃日志";
        }
        String report = buildHistoricalExitReport(context);
        if (report == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM9714constructorimpl = Result.constructor-impl(Boolean.valueOf(copyToClipboard(context, report)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.m9720isFailureimpl(objM9714constructorimpl)) {
            objM9714constructorimpl = false;
        }
        if (((Boolean) objM9714constructorimpl).booleanValue()) {
            return "上次系统退出报告";
        }
        return null;
    }

    public final boolean restorePendingReportToClipboard(Context context) {
        Object objM9714constructorimpl;
        Object objM9714constructorimpl2;
        Intrinsics.checkNotNullParameter(context, "context");
        File file = pendingReportFile(context);
        if (!file.isFile()) {
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            MeloXCrashReporter meloXCrashReporter = this;
            objM9714constructorimpl = Result.constructor-impl(FilesKt.readText$default(file, null, 1, null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.m9720isFailureimpl(objM9714constructorimpl)) {
            objM9714constructorimpl = null;
        }
        String str = (String) objM9714constructorimpl;
        if (str != null) {
            String report = StringsKt.isBlank(str) ? null : str;
            if (report != null) {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    objM9714constructorimpl2 = Result.constructor-impl(Boolean.valueOf(copyToClipboard(context, report)));
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    objM9714constructorimpl2 = Result.constructor-impl(ResultKt.createFailure(th2));
                }
                if (Result.m9720isFailureimpl(objM9714constructorimpl2)) {
                    objM9714constructorimpl2 = false;
                }
                boolean copied = ((Boolean) objM9714constructorimpl2).booleanValue();
                if (copied) {
                    try {
                        Result.Companion companion5 = Result.INSTANCE;
                        MeloXCrashReporter meloXCrashReporter2 = this;
                        Result.constructor-impl(Boolean.valueOf(file.delete()));
                    } catch (Throwable th3) {
                        Result.Companion companion6 = Result.INSTANCE;
                        Result.constructor-impl(ResultKt.createFailure(th3));
                    }
                }
                return copied;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:111:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:112:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:115:0x028d  */
    /* JADX WARN: Code duplicated, block: B:119:0x0357  */
    /* JADX WARN: Code duplicated, block: B:122:0x0377  */
    /* JADX WARN: Code duplicated, block: B:123:0x037c  */
    /* JADX WARN: Code duplicated, block: B:144:0x011f A[EDGE_INSN: B:144:0x011f->B:61:0x011f BREAK  A[LOOP:0: B:42:0x00ce->B:59:0x0117], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x0117 A[LOOP:0: B:42:0x00ce->B:59:0x0117, LOOP_END] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.lang.Throwable] */
    private final String buildHistoricalExitReport(Context context) {
        Object objM9714constructorimpl;
        Long lValueOf;
        String str;
        Object next;
        String processState;
        String processState2;
        Object objM9714constructorimpl2;
        Object obj;
        Long traceBytes;
        String description;
        long jLongValue;
        Long lValueOf2;
        Throwable th;
        boolean z;
        String str2 = null;
        if (Build.VERSION.SDK_INT < 30) {
            return null;
        }
        SharedPreferences preferences = preferences(context);
        long lastSeenExitTime = preferences.getLong(LAST_SEEN_EXIT_TIME, 0L);
        String lastAction = preferences.getString(LAST_ACTION, null);
        if (lastAction == null) {
            return null;
        }
        long lastActionTime = preferences.getLong(LAST_ACTION_TIME, 0L);
        if (lastActionTime <= 0) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            MeloXCrashReporter meloXCrashReporter = this;
            try {
                ActivityManager activityManager = (ActivityManager) context.getSystemService(ActivityManager.class);
                List<ApplicationExitInfo> historicalProcessExitReasons = activityManager != null ? activityManager.getHistoricalProcessExitReasons(context.getPackageName(), 0, 8) : null;
                if (historicalProcessExitReasons == null) {
                    historicalProcessExitReasons = CollectionsKt.emptyList();
                }
                objM9714constructorimpl = Result.constructor-impl(historicalProcessExitReasons);
            } catch (Throwable th2) {
                th = th2;
                Result.Companion companion2 = Result.INSTANCE;
                objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
            }
        } catch (Throwable th3) {
            th = th3;
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.m9720isFailureimpl(objM9714constructorimpl)) {
            objM9714constructorimpl = listEmptyList;
        }
        Iterable exits = (List) objM9714constructorimpl;
        Iterator it = exits.iterator();
        if (it.hasNext()) {
            lValueOf = Long.valueOf(((ApplicationExitInfo) it.next()).getTimestamp());
            while (it.hasNext()) {
                Long lValueOf3 = Long.valueOf(((ApplicationExitInfo) it.next()).getTimestamp());
                if (lValueOf.compareTo(lValueOf3) < 0) {
                    lValueOf = lValueOf3;
                }
            }
        } else {
            lValueOf = null;
        }
        Long l = lValueOf;
        if (l == null) {
            return null;
        }
        long newestTimestamp = l.longValue();
        preferences.edit().putLong(LAST_SEEN_EXIT_TIME, newestTimestamp).apply();
        Iterator it2 = exits.iterator();
        while (true) {
            if (!it2.hasNext()) {
                str = str2;
                next = str;
                break;
            }
            next = it2.next();
            ApplicationExitInfo applicationExitInfo = (ApplicationExitInfo) next;
            if (applicationExitInfo.getTimestamp() <= lastSeenExitTime || applicationExitInfo.getTimestamp() < lastActionTime || applicationExitInfo.getTimestamp() - lastActionTime > 600000) {
                str = str2;
            } else {
                str = str2;
                z = INSTANCE.isActionableExit(applicationExitInfo.getReason());
                if (z) {
                    break;
                }
                str2 = str;
            }
            if (z) {
                break;
                break;
            }
            str2 = str;
        }
        ApplicationExitInfo exit = (ApplicationExitInfo) next;
        if (exit == null) {
            return str;
        }
        byte[] processStateSummary = exit.getProcessStateSummary();
        if (processStateSummary != null) {
            processState = new String(processStateSummary, Charsets.UTF_8);
            if (StringsKt.isBlank(processState)) {
                processState = str;
            }
        } else {
            processState = str;
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            MeloXCrashReporter meloXCrashReporter2 = this;
            InputStream traceInputStream = exit.getTraceInputStream();
            try {
                if (traceInputStream != null) {
                    InputStream inputStream = traceInputStream;
                    try {
                        InputStream inputStream2 = inputStream;
                        try {
                            byte[] bArr = new byte[8192];
                            long j = 0;
                            while (true) {
                                int i = inputStream2.read(bArr);
                                if (i < 0) {
                                    processState2 = processState;
                                    break;
                                }
                                InputStream inputStream3 = inputStream2;
                                byte[] bArr2 = bArr;
                                processState2 = processState;
                                j += (long) i;
                                if (j >= 131072) {
                                    break;
                                }
                                inputStream2 = inputStream3;
                                processState = processState2;
                                bArr = bArr2;
                            }
                            try {
                                lValueOf2 = Long.valueOf(j);
                                CloseableKt.closeFinally(inputStream, str);
                            } catch (Throwable th4) {
                                th = th4;
                                try {
                                    throw th;
                                } catch (Throwable th5) {
                                    CloseableKt.closeFinally(inputStream, th);
                                    throw th5;
                                }
                            }
                        } catch (Throwable th6) {
                            processState2 = processState;
                            th = th6;
                        }
                    } catch (Throwable th7) {
                        processState2 = processState;
                        th = th7;
                    }
                } else {
                    processState2 = processState;
                    lValueOf2 = null;
                }
                objM9714constructorimpl2 = Result.constructor-impl(lValueOf2);
            } catch (Throwable th8) {
                th = th8;
                Result.Companion companion4 = Result.INSTANCE;
                objM9714constructorimpl2 = Result.constructor-impl(ResultKt.createFailure(th));
                if (Result.m9720isFailureimpl(objM9714constructorimpl2)) {
                    obj = null;
                } else {
                    obj = objM9714constructorimpl2;
                }
                traceBytes = (Long) obj;
                StringBuilder sb = new StringBuilder();
                sb.append("MeloX system exit report").append('\n');
                sb.append("Exit time (UTC): " + Instant.ofEpochMilli(exit.getTimestamp())).append('\n');
                sb.append("Last action: " + lastAction).append('\n');
                sb.append("Last action time (UTC): " + Instant.ofEpochMilli(lastActionTime)).append('\n');
                sb.append("Reason: " + INSTANCE.reasonName(exit.getReason()) + " (" + exit.getReason() + ")").append('\n');
                description = exit.getDescription();
                if (description == null) {
                    description = "<none>";
                }
                sb.append("Description: " + description).append('\n');
                sb.append("Status/signal: " + exit.getStatus()).append('\n');
                sb.append("Importance: " + exit.getImportance()).append('\n');
                long pss = exit.getPss();
                long lastSeenExitTime2 = exit.getRss();
                sb.append("Memory: PSS=" + pss + " kB, RSS=" + lastSeenExitTime2 + " kB").append('\n');
                sb.append("Process: " + exit.getProcessName() + ", pid=" + exit.getPid()).append('\n');
                sb.append("Process state: " + (processState2 != null ? processState2 : "<none>")).append('\n');
                if (traceBytes != null) {
                    jLongValue = traceBytes.longValue();
                } else {
                    jLongValue = 0;
                }
                sb.append("System trace bytes available: " + jLongValue).append('\n');
                sb.append("App: com.lladlam.melox.android 0.1.0-dev (1)").append('\n');
                sb.append("Android: " + Build.VERSION.RELEASE + " / SDK " + Build.VERSION.SDK_INT).append('\n');
                sb.append("Device: " + Build.MANUFACTURER + " " + Build.MODEL + " (" + Build.DEVICE + ")").append('\n');
                sb.append("Build: " + Build.DISPLAY).append('\n');
                return StringsKt.take(redact(sb.toString()), 131072);
            }
        } catch (Throwable th9) {
            th = th9;
            processState2 = processState;
        }
        if (Result.m9720isFailureimpl(objM9714constructorimpl2)) {
            obj = null;
        } else {
            obj = objM9714constructorimpl2;
        }
        traceBytes = (Long) obj;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("MeloX system exit report").append('\n');
        sb2.append("Exit time (UTC): " + Instant.ofEpochMilli(exit.getTimestamp())).append('\n');
        sb2.append("Last action: " + lastAction).append('\n');
        sb2.append("Last action time (UTC): " + Instant.ofEpochMilli(lastActionTime)).append('\n');
        sb2.append("Reason: " + INSTANCE.reasonName(exit.getReason()) + " (" + exit.getReason() + ")").append('\n');
        description = exit.getDescription();
        if (description == null) {
            description = "<none>";
        }
        sb2.append("Description: " + description).append('\n');
        sb2.append("Status/signal: " + exit.getStatus()).append('\n');
        sb2.append("Importance: " + exit.getImportance()).append('\n');
        long pss2 = exit.getPss();
        long lastSeenExitTime3 = exit.getRss();
        sb2.append("Memory: PSS=" + pss2 + " kB, RSS=" + lastSeenExitTime3 + " kB").append('\n');
        sb2.append("Process: " + exit.getProcessName() + ", pid=" + exit.getPid()).append('\n');
        sb2.append("Process state: " + (processState2 != null ? processState2 : "<none>")).append('\n');
        if (traceBytes != null) {
            jLongValue = traceBytes.longValue();
        } else {
            jLongValue = 0;
        }
        sb2.append("System trace bytes available: " + jLongValue).append('\n');
        sb2.append("App: com.lladlam.melox.android 0.1.0-dev (1)").append('\n');
        sb2.append("Android: " + Build.VERSION.RELEASE + " / SDK " + Build.VERSION.SDK_INT).append('\n');
        sb2.append("Device: " + Build.MANUFACTURER + " " + Build.MODEL + " (" + Build.DEVICE + ")").append('\n');
        sb2.append("Build: " + Build.DISPLAY).append('\n');
        return StringsKt.take(redact(sb2.toString()), 131072);
    }

    private final void markHistoricalExitsSeen(Context context) {
        Object objM9714constructorimpl;
        List<ApplicationExitInfo> historicalProcessExitReasons;
        ApplicationExitInfo applicationExitInfo;
        if (Build.VERSION.SDK_INT < 30) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            MeloXCrashReporter meloXCrashReporter = this;
            ActivityManager activityManager = (ActivityManager) context.getSystemService(ActivityManager.class);
            objM9714constructorimpl = Result.constructor-impl((activityManager == null || (historicalProcessExitReasons = activityManager.getHistoricalProcessExitReasons(context.getPackageName(), 0, 1)) == null || (applicationExitInfo = (ApplicationExitInfo) CollectionsKt.firstOrNull((List) historicalProcessExitReasons)) == null) ? null : Long.valueOf(applicationExitInfo.getTimestamp()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM9714constructorimpl = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Long l = (Long) (Result.m9720isFailureimpl(objM9714constructorimpl) ? null : objM9714constructorimpl);
        if (l != null) {
            long newestTimestamp = l.longValue();
            preferences(context).edit().putLong(LAST_SEEN_EXIT_TIME, newestTimestamp).apply();
        }
    }

    private final boolean isActionableExit(int reason) {
        switch (reason) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
                return true;
            case 8:
            default:
                return false;
        }
    }

    private final String reasonName(int reason) {
        switch (reason) {
            case 2:
                return "SIGNALED";
            case 3:
                return "LOW_MEMORY";
            case 4:
                return "JAVA_CRASH";
            case 5:
                return "NATIVE_CRASH";
            case 6:
                return "ANR";
            case 7:
                return "INITIALIZATION_FAILURE";
            case 8:
            default:
                return "OTHER";
            case 9:
                return "EXCESSIVE_RESOURCE_USAGE";
        }
    }

    private final String buildReport(Thread thread, Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        String trace = stringWriter.toString();
        Intrinsics.checkNotNullExpressionValue(trace, "toString(...)");
        StringBuilder sb = new StringBuilder();
        sb.append("MeloX crash report").append('\n');
        sb.append("Time (UTC): " + Instant.now()).append('\n');
        sb.append("App: com.lladlam.melox.android 0.1.0-dev (1)").append('\n');
        sb.append("Android: " + Build.VERSION.RELEASE + " / SDK " + Build.VERSION.SDK_INT).append('\n');
        sb.append("Device: " + Build.MANUFACTURER + " " + Build.MODEL + " (" + Build.DEVICE + ")").append('\n');
        sb.append("Build: " + Build.DISPLAY).append('\n');
        sb.append("Thread: " + thread.getName()).append('\n');
        sb.append('\n');
        sb.append(trace);
        String report = sb.toString();
        return StringsKt.take(redact(report), 131072);
    }

    private final String redact(String value) {
        return new Regex("(?i)Bearer\\s+[A-Za-z0-9._~+/=-]+").replace(new Regex("(?i)([?&](?:cookie|authorization|music_u|__csrf|csrf|access[_-]?token|refresh[_-]?token|password)=)[^&\\s]+").replace(new Regex("(?i)(cookie|authorization|music_u|__csrf|csrf|access[_-]?token|refresh[_-]?token|password)\\s*[:=]\\s*[^\\s,;]+").replace(value, "$1=<redacted>"), "$1<redacted>"), "Bearer <redacted>");
    }

    private final boolean copyToClipboard(Context context, String report) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(ClipboardManager.class);
        if (clipboard == null) {
            return false;
        }
        ClipData clip = ClipData.newPlainText("MeloX crash report", report);
        if (Build.VERSION.SDK_INT >= 33) {
            ClipDescription description = clip.getDescription();
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putBoolean("android.content.extra.IS_SENSITIVE", true);
            description.setExtras(persistableBundle);
        }
        clipboard.setPrimaryClip(clip);
        return true;
    }

    private final File pendingReportFile(Context context) {
        return new File(context.getApplicationContext().getFilesDir(), PENDING_REPORT_FILE);
    }

    private final SharedPreferences preferences(Context context) {
        return context.getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
    }
}
