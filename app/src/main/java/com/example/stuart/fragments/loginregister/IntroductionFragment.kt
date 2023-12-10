package com.example.stuart.fragments.loginregister

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.stuart.R
import com.example.stuart.activities.ShoppingActivity
import com.example.stuart.databinding.FragmentIntroductionBinding
import com.example.stuart.viewmodel.IntroductionViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IntroductionFragment :Fragment(R.layout.fragment_introduction) {
    private lateinit var binding: FragmentIntroductionBinding
    private val viewModel by viewModels<IntroductionViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroductionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.navigate.collect{
                when(it){
                    //when "it" equal to shopping activity we will navigate to shopping Activity.
                    IntroductionViewModel.SHOPPING_ACTIVITY ->{
                        /* code copied from LoginFragment*/
                        Intent(requireActivity(), ShoppingActivity::class.java).also { intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)}
                    }
                    //when "it" equal  to
                    IntroductionViewModel.ACCOUNT_OPTIONS_FRAGMENT ->{
                        findNavController().navigate(R.id.action_introductionFragment_to_accountOptionsFragment)

                        // or replace  findNavController().navigate(it)
                        // because it = R.id.action_introductionFragment_to_accountOptionsFragment
                    }
                    else -> Unit
                }
            }

        }

        binding.buttonStart.setOnClickListener{

            //function hadhy aamlna definition ta3ha previously fil introduction ViewModel

            viewModel.startButtonClicked()
            //activated and executed whenever a user click on start
            findNavController().navigate(R.id.action_introductionFragment_to_accountOptionsFragment)
        }
    }
}