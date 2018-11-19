package net.sarangnamu.ehanex.views

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.view.*
import net.sarangnamu.common.BaseRuleFragment
import net.sarangnamu.common.animate
import net.sarangnamu.common.replace
import net.sarangnamu.common.viewModel
import net.sarangnamu.ehanex.R
import net.sarangnamu.ehanex.databinding.LoginFragmentBinding
import net.sarangnamu.ehanex.viewmodels.LoginViewModel

/**
 * Created by <a href="mailto:aucd29@hanwha.com">Burke Choi</a> on 2018. 11. 14. <p/>
 */

class LoginFragment : BaseRuleFragment<LoginFragmentBinding>() {
    override fun bindViewModel() {
        mBinding.model = viewmodel()
    }

    fun viewmodel() = viewModel(LoginViewModel::class.java)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewmodel()?.run {
            observe(eventJoinMember) {
                activity?.supportFragmentManager?.replace(R.id.container, MemberFragment::class.java,
                    listener = { _, trans -> trans.animate(1) })
            }
        }
    }
}