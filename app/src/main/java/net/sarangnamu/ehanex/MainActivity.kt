package net.sarangnamu.ehanex

import android.content.Context
import android.os.Bundle
import net.sarangnamu.common.*
import net.sarangnamu.ehanex.databinding.ActivityMainBinding
import net.sarangnamu.ehanex.viewmodels.LoginViewModel
import net.sarangnamu.ehanex.views.LoginFragment
import net.sarangnamu.ehanex.views.MainFragment
import org.slf4j.LoggerFactory
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : BaseActivity<ActivityMainBinding>() {
    companion object {
        private val mLog = LoggerFactory.getLogger(MainActivity::class.java)
    }

    override fun layoutId(): Int = R.layout.activity_main

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_home -> {
////                message.setText(R.string.title_home)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_dashboard -> {
////                message.setText(R.string.title_dashboard)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_notifications -> {
////                message.setText(R.string.title_notifications)
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        supportFragmentManager.run {
            if (savedInstanceState == null) {
                add(R.id.container, MainFragment::class.java)
            }

            // 로그인 안되어 있으면 해당 프레그먼트를 띄운다.
            loginviewmodel().run {
                if (!isLogin) {
                    replace(R.id.container, LoginFragment::class.java, listener = { _, t -> t.animate(1) })
                }

                observe(rememberId) { prefs().edit { putBoolean(Config.REMEMBER_ID, it) } }
                observe(rememberPw) { prefs().edit { putBoolean(Config.REMEMBER_PW, it) } }
                observe(eventLogined) { popBackStack() /* free login fragment */ }
            }
        }
    }

    fun loginviewmodel() = viewModel(LoginViewModel::class.java)

    ////////////////////////////////////////////////////////////////////////////////////
    //
    // FONTS
    //
    ////////////////////////////////////////////////////////////////////////////////////

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}
