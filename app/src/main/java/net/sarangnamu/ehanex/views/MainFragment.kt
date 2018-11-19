package net.sarangnamu.ehanex.views

import net.sarangnamu.common.BaseFragment
import net.sarangnamu.common.BaseRuleFragment
import net.sarangnamu.common.viewModel
import net.sarangnamu.ehanex.databinding.MainFragmentBinding
import net.sarangnamu.ehanex.viewmodels.MainViewModel

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2018. 11. 14. <p/>
 */

class MainFragment: BaseRuleFragment<MainFragmentBinding>() {
    override fun bindViewModel() {
        mBinding.model = viewmodel()
    }

    fun viewmodel() = viewModel(MainViewModel::class.java)
}