@file:Suppress("NOTHING_TO_INLINE", "unused")
package net.sarangnamu.common

import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import net.sarangnamu.ehanex.R

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2018. 11. 8. <p/>
 */

inline fun Fragment.string(resid: String): String? = context?.string(resid)

inline val FragmentManager.frgmts: List<Fragment?>
    get() = (0..backStackEntryCount - 1).map { findFragmentByTag(getBackStackEntryAt(it).name) }

inline val FragmentManager.current: Fragment?
    get() = frgmts.last()

inline val FragmentManager.count: Int
    get() = backStackEntryCount

inline fun FragmentManager.find(clazz: Class<out Fragment>) = findFragmentByTag(clazz.name)

inline fun FragmentManager.add(id: Int, clazz: Class<out Fragment>,
                               noinline listener: ((FragmentManager, FragmentTransaction) -> Unit)? = null) {
    val frgmt = clazz.newInstance() as Fragment
    val transaction = beginTransaction()

    if (frgmt.isVisible) {
        return
    }

    listener?.invoke(this, transaction)

    transaction.add(id, frgmt, frgmt.javaClass.name)
    transaction.commit()
}

inline fun FragmentManager.replace(id: Int, clazz: Class<out Fragment>,
                                   noinline listener: ((FragmentManager, FragmentTransaction) -> Unit)? = null,
                                   bundle: Bundle? = null, stack: Boolean = true): Fragment {
    val existFrgmt = findFragmentByTag(clazz.name)
    if (existFrgmt != null && existFrgmt.isVisible) {
        // manager 내에 해당 fragment 가 이미 존재하면 해당 fragment 를 반환 한다
        return existFrgmt
    }

    val frgmt = clazz.newInstance() as Fragment
    val transaction = beginTransaction()

    bundle?.let { frgmt.arguments = it }
    listener?.invoke(this, transaction)

    transaction.replace(id, frgmt, frgmt.javaClass.name)
    if (stack) {
        transaction.addToBackStack(frgmt.javaClass.name)
    }

    transaction.commit()

    return frgmt
}

inline fun FragmentManager.pop() {
    popBackStack()
}

inline fun FragmentManager.popAll() {
    (0..count - 1).map { popBackStack(it, FragmentManager.POP_BACK_STACK_INCLUSIVE) }
}

public val FragmentTransaction.ANI_HORIZONTAL: Int
    get() = 1
public val FragmentTransaction.ANI_VERTICAL: Int
    get() = 2

inline fun FragmentTransaction.animate(anim: Int) {
    when (anim) {
        ANI_HORIZONTAL -> setCustomAnimations(
            R.anim.slide_in_current, R.anim.slide_in_next,
            R.anim.slide_out_current, R.anim.slide_out_prev)
        ANI_VERTICAL   -> setCustomAnimations(R.anim.slide_up_current, R.anim.slide_up_next,
            R.anim.slide_down_current, R.anim.slide_down_prev)
        else -> setCustomAnimations(0, 0, 0, 0)
    }
}

abstract class BaseRuleFragment<T: ViewDataBinding>: BaseFragment<T>() {
    companion object {
        protected val LAYOUT = "layout"
    }

    var className: String = ""

    fun layoutName(): String {
        if (!TextUtils.isEmpty(className)) {
            return className
        }

        val name = javaClass.simpleName
        className = name.get(0).toLowerCase().toString()

        name.substring(1, name.length).forEach {
            className += if (it.isUpperCase()) {
                "_${it.toLowerCase()}"
            } else {
                it
            }
        }

        return className
    }

    override fun layoutId() = resources.getIdentifier(layoutName(), LAYOUT, activity?.packageName)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (layoutId() == 0) {
            val view = LinearLayout(activity)
            view.lpmm()
            view.gravity = Gravity.CENTER

            val text = TextView(activity)
            text.gravityCenter()
            text.text = "FILE NOT FOUND (${layoutName()})"
            view.addView(text)

            return view
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}