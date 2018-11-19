package net.sarangnamu.ehanex

import android.app.Application
import org.slf4j.LoggerFactory
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2018. 11. 14. <p/>
 */

class MainApp : Application() {
    companion object {
        private val mLog = LoggerFactory.getLogger(MainApp::class.java)
    }

    override fun onCreate() {
        super.onCreate()

        mLog.error("INIT MAIN APP")

        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Light.ttf")
                .build())
    }
}