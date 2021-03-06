@file:Suppress("NOTHING_TO_INLINE", "unused")
package net.sarangnamu.common

import android.app.ActivityManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Environment
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2018. 1. 24.. <p/>
 */


/**
 * pkgName 에 해당하는 앱이 foreground 인지 확인
 */
inline fun Context.isForegroundApp(pkgName: String) = systemService(ActivityManager::class.java)?.run {
    runningAppProcesses.filter {
        it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
            && it.processName == pkgName
    }.size == 1
}

/**
 * 현재 앱이 foreground 인지 확인
 */
inline fun Context.isForegroundApp() = isForegroundApp(packageName)

/**
 * sdcard 내 app 경로 전달
 */
inline fun Context.externalFilePath() = getExternalFilesDir(null)?.absolutePath

/**
 * display density 반환
 */
inline fun Context.displayDensity() = resources.displayMetrics.density

/**
 * open keyboard
 */
inline fun Context.showKeyboard(view: View?) {
    view?.let {
        it.postDelayed({
            it.requestFocus()
            systemService(InputMethodManager::class.java)?.run {
                showSoftInput(it, InputMethodManager.SHOW_FORCED)
            }
        }, 400)
    }
}

/**
 * hide keyboard
 */
inline fun Context.hideKeyboard(view: View?) {
    view?.let {
        systemService(InputMethodManager::class.java)?.run {
            hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}

/**
 * force hide keyboard
 */
inline fun Context.forceHideKeyboard(window: Window?) {
    window?.run { setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN) }
}

/**
 * 문자열 데이터를 얻는다.
 */
inline fun Context.string(@StringRes resid: Int) = getString(resid)

/**
 * 문자열 데이터를 얻는다.
 */
inline fun Context.string(resid: String) =
    string(resources.getIdentifier(resid, "string", packageName))


////////////////////////////////////////////////////////////////////////////////////
//
// ENVIRONMENT EXTENSION
//
////////////////////////////////////////////////////////////////////////////////////

/** sdcard 경로 반환 */
inline fun Context.sdPath() = Environment.getExternalStorageDirectory().absolutePath

/**
 * clipboard 데이터를 읽어온다.
 */
inline fun Context.clipboard(): String? = systemService(ClipboardManager::class.java)?.run {
    primaryClip?.getItemAt(0)?.text.toString()
}

/**
 * clipboard 떼이터를 설정 한다.
 */
inline fun Context.clipboard(value: String) = systemService(ClipboardManager::class.java)?.run {
    primaryClip = ClipData.newPlainText("hspdata", value)
}


inline fun <T> Context.systemService(serviceClass: Class<T>): T? {
    return ContextCompat.getSystemService(this, serviceClass)
}
