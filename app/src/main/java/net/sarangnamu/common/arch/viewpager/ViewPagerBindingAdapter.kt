package net.sarangnamu.common.arch.viewpager

import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import org.slf4j.LoggerFactory

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2018. 11. 19. <p/>
 */

object ViewPagerBindingAdapter {
    private val mLog = LoggerFactory.getLogger(ViewPagerBindingAdapter::class.java)

    @JvmStatic
    @BindingAdapter(*arrayOf("bindPagerAdapter"))
    fun bindPagerAdapter(viewpager: ViewPager, adapter: FragmentStatePagerAdapter) {
        if (mLog.isTraceEnabled()) {
            mLog.trace("BIND PAGER ADAPTER")
        }

        viewpager.adapter = adapter
    }
}