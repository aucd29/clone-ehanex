@file:Suppress("NOTHING_TO_INLINE", "unused")
package net.sarangnamu.common

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Base64
import android.view.View

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2018. 10. 30. <p/>
 */


/**
 * 문자열을 Spanned 형태 문자열로 반환한다.
 */
inline fun String.html(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

inline fun String.lenToVisible() =
    if (length > 0) View.VISIBLE else View.GONE


/**
 * 문자열을 base64 인코딩하여 반환 한다.
 */
inline fun String.encodeBase64(): String = Base64.encodeToString(this.toByteArray(), Base64.DEFAULT)

/**
 * base64 인코딩된 문자열을 디코딩 하여 반환 한다.
 */
inline fun String.decodeBase64(): String = String(Base64.decode(this, Base64.DEFAULT))
