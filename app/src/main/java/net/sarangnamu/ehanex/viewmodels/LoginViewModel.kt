package net.sarangnamu.ehanex.viewmodels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import net.sarangnamu.common.arch.SingleLiveEvent
import net.sarangnamu.common.prefs
import net.sarangnamu.ehanex.Config
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2018. 11. 14. <p/>
 */

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private val mLog = LoggerFactory.getLogger(LoginViewModel::class.java)
    }

    val userid      = ObservableField<String>()
    val userpw      = ObservableField<String>()
    val rememberId  = MutableLiveData<Boolean>()
    val rememberPw  = MutableLiveData<Boolean>()
    var isLogin     = false

    val eventLogined     = SingleLiveEvent<Void>()
    val eventJoinMember  = SingleLiveEvent<Void>()

    init {
        rememberId.value = prefs().getBoolean(Config.REMEMBER_ID, false)
        rememberPw.value = prefs().getBoolean(Config.REMEMBER_PW, false)
    }

    fun login() {
        if (mLog.isDebugEnabled) {
            mLog.debug("LOGIN id   : ${userid.get()}, " +
                       "LOGIN pw   : ${userpw.get()}, " +
                       "rememberId : ${rememberId.getValue()}, " +
                       "rememberPw : ${rememberPw.getValue()}")
        }

        val d = Observable.just("").delay(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                isLogin = true
                eventLogined.call()
            }
    }

    fun findUserName() {
        if (mLog.isDebugEnabled) {
            mLog.debug("FIND USER NAME")
        }
    }

    fun findUserPassword() {
        if (mLog.isDebugEnabled) {
            mLog.debug("FIND PASSWORD")
        }
    }

    fun joinMember() {
        if (mLog.isDebugEnabled) {
            mLog.debug("JOIN MEMBER")
        }

        eventJoinMember.call()
    }
}

