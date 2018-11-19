package net.sarangnamu.ehanex.viewmodels

import android.app.Application
import androidx.databinding.ObservableField
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.AndroidViewModel
import net.sarangnamu.ehanex.views.member.MemberCoporateFragment
import net.sarangnamu.ehanex.views.member.MemberIndividualFragment
import net.sarangnamu.ehanex.views.member.MemberOverseasFragment

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2018. 11. 16. <p/>
 */

class MemberViewModel(application: Application) : AndroidViewModel(application) {
    val adapter = ObservableField<MemberTabPagerAdapter>()
}

class MemberTabPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    companion object {
        const val COUNT = 3
    }

    override fun getItem(position: Int) =
        when (position) {
            1    -> MemberCoporateFragment()
            2    -> MemberOverseasFragment()
            else -> MemberIndividualFragment()
        }

    override fun getCount() = COUNT
}