package net.sarangnamu.ehanex.views

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import net.sarangnamu.common.BaseRuleFragment
import net.sarangnamu.common.viewModel
import net.sarangnamu.ehanex.databinding.MemberFragmentBinding
import net.sarangnamu.ehanex.viewmodels.MemberTabPagerAdapter
import net.sarangnamu.ehanex.viewmodels.MemberViewModel

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2018. 11. 16. <p/>
 */

class MemberFragment : BaseRuleFragment<MemberFragmentBinding>() {
    override fun bindViewModel() {
        viewmodel().adapter.set(MemberTabPagerAdapter(childFragmentManager))

        mBinding.model = viewmodel()
    }

    private fun viewmodel() = viewModel(MemberViewModel::class.java)!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.run {
            memberToolbar.title = "Member"
            memberTabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                override fun onTabReselected(p0: TabLayout.Tab?) { }
                override fun onTabUnselected(p0: TabLayout.Tab?) { }
                override fun onTabSelected(p0: TabLayout.Tab?) {
                    p0?.run { memberViewpager.currentItem = this.position }
                }
            })
        }
    }
}