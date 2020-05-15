package com.onthecrow.trainy.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.auth.FirebaseAuth
import com.onthecrow.trainy.R
import com.onthecrow.trainy.base.BaseBindingFragment
import com.onthecrow.trainy.databinding.FragmentMainBinding
import com.onthecrow.trainy.extensions.setupWithNavController
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseBindingFragment<MainViewModel, FragmentMainBinding>() {

    override val layoutRes = R.layout.fragment_main

    override val viewModel: MainViewModel by activityViewModels { viewModelFactory }

    private var currentNavController: LiveData<NavController>? = null

    private val bottomSheet by lazy { BottomSheetBehavior.from(mainBottomSheet) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.mainToolbar)
        binding.mainToolbar.setNavigationOnClickListener {
            if (currentNavController?.value?.navigateUp() == false) {
                requireActivity().onBackPressed()
            }
        }
        fab.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAuthFragment())
        }
        bar.setNavigationOnClickListener {
            bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
        }

        setupBottomNavigationBar()
        binding.mainContentTint.setOnClickListener {
            bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        bottomSheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                binding.mainContentTint.alpha = slideOffset
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                binding.mainContentTint.visibility =
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        View.GONE
                    } else {
                        View.VISIBLE
                    }
            }
        })
        bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupBottomNavigationBar() {

        val navGraphIds = listOf(
            R.navigation.feed,
            R.navigation.map,
            R.navigation.chat,
            R.navigation.training,
            R.navigation.profile
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = binding.navigationNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = activity!!.supportFragmentManager,
            containerId = R.id.mainFragmentContainer,
            intent = activity?.intent ?: Intent()
        ) {
            bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(viewLifecycleOwner, Observer { navController ->
            (activity as? AppCompatActivity)?.let {
                setupActionBarWithNavController(it, navController)
            }
        })
        currentNavController = controller
    }
}