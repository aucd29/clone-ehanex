package net.sarangnamu.ehanex.views.member

import net.sarangnamu.common.BaseRuleFragment
import net.sarangnamu.common.viewModel
import net.sarangnamu.ehanex.databinding.MemberCoporateFragmentBinding
import net.sarangnamu.ehanex.databinding.MemberIndividualFragmentBinding
import net.sarangnamu.ehanex.databinding.MemberOverseasFragmentBinding
import net.sarangnamu.ehanex.viewmodels.member.MemberCoporateViewModel
import net.sarangnamu.ehanex.viewmodels.member.MemberIndividualViewModel
import net.sarangnamu.ehanex.viewmodels.member.MemberOverseasViewModel

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2018. 11. 19. <p/>
 */

class MemberIndividualFragment : BaseRuleFragment<MemberIndividualFragmentBinding>() {
    override fun bindViewModel() {
        mBinding.model = viewmodel()
    }

    fun viewmodel() = viewModel(MemberIndividualViewModel::class.java)
}


class MemberCoporateFragment : BaseRuleFragment<MemberCoporateFragmentBinding>() {
    override fun bindViewModel() {
        mBinding.model = viewmodel()
    }

    fun viewmodel() = viewModel(MemberCoporateViewModel::class.java)
}


class MemberOverseasFragment : BaseRuleFragment<MemberOverseasFragmentBinding>() {
    override fun bindViewModel() {
        mBinding.model = viewmodel()
    }

    fun viewmodel() = viewModel(MemberOverseasViewModel::class.java)
}