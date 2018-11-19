@file:Suppress("NOTHING_TO_INLINE", "unused")
package net.sarangnamu.common

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2018. 10. 15. <p/>
 */

/**
 * android view model 에서 쉽게 문자열을 가져올 수 있도록 wrapping 함
 */
inline fun AndroidViewModel.string(@StringRes resid: Int) =
    getApplication<Application>().getString(resid)

/**
 * android view model 에서 쉽게 문자열을 가져올 수 있도록 wrapping 함
 */
inline fun AndroidViewModel.string(resid: String) =
    getApplication<Application>().string(resid)

inline val AndroidViewModel.app : Application
        get() = getApplication()


////////////////////////////////////////////////////////////////////////////////////
//
// VIEW MODEL
//
////////////////////////////////////////////////////////////////////////////////////

inline fun <reified T : ViewModel> FragmentActivity.viewModel(clazz: Class<T>,
                                                      provider: ViewModelProvider.Factory? = null) =
        provider?.let { ViewModelProviders.of(this, it).get(clazz) } ?:
                        ViewModelProviders.of(this).get(clazz)

inline fun <reified T : ViewModel> Fragment.viewModel(clazz: Class<T>,
                                              provider: ViewModelProvider.Factory? = null) =
        activity?.viewModel(clazz, provider)


////////////////////////////////////////////////////////////////////////////////////
//
// DATA BINDING
//
////////////////////////////////////////////////////////////////////////////////////


inline fun <T : ViewDataBinding> Fragment.dataBinding(@LayoutRes layoutid: Int, parent: ViewGroup? = null,
                                                      attachToParent: Boolean = false): T =
        DataBindingUtil.inflate(layoutInflater, layoutid, parent, attachToParent)

inline fun <T : ViewDataBinding> Activity.dataBinding(@LayoutRes layoutid: Int, parent: ViewGroup? = null,
                                                      attachToParent: Boolean = false): T =
        DataBindingUtil.inflate(layoutInflater, layoutid, parent, attachToParent)

inline fun <T : ViewDataBinding> Activity.dataBindingView(@LayoutRes layoutid: Int): T =
        DataBindingUtil.setContentView(this, layoutid)


////////////////////////////////////////////////////////////////////////////////////
//
//
//
////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////
//
//
//
////////////////////////////////////////////////////////////////////////////////////

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var mBinding : T

    val disposable = CompositeDisposable()
    protected lateinit var mBackPressed: BackPressedManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = dataBindingView(layoutId())
        mBackPressed = BackPressedManager(this, mBinding.root)
    }

    fun <T> observe(data: LiveData<T>, observer: (T) -> Unit) {
        data.observe(this, Observer { observer(it) })
    }

    abstract fun layoutId(): Int

    override fun onBackPressed() {
        if (mBackPressed.onBackPressed()) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        disposable.clear()

        super.onDestroy()
    }
}

abstract class BaseFragment<T: ViewDataBinding> : Fragment() {
    protected lateinit var mBinding : T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = dataBinding(layoutId(), container, false)
        mBinding.root.isClickable = true
        bindViewModel()

        return mBinding.root
    }

    fun <T> observe(data: LiveData<T>, observer: (T) -> Unit) {
        data.observe(this, Observer { observer(it) })
    }

    abstract fun layoutId(): Int
    abstract fun bindViewModel()
}