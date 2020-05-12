package com.onthecrow.trainy.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.onthecrow.trainy.R
import com.onthecrow.trainy.base.BaseBindingFragment
import com.onthecrow.trainy.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseBindingFragment<MainViewModel, FragmentMainBinding>() {

    override val layoutRes = R.layout.fragment_main

    override val viewModel: MainViewModel by activityViewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fab.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAuthFragment())
        }
        super.onViewCreated(view, savedInstanceState)
    }
}