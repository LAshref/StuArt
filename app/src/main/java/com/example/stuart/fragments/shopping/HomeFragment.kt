package com.example.stuart.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stuart.R
import com.example.stuart.adapters.HomeViewPageAdapter
import com.example.stuart.databinding.FragmentHomeBinding
import com.example.stuart.fragments.categories.HandMade
import com.example.stuart.fragments.categories.MainCategoryFragment
import com.example.stuart.fragments.categories.MusicTalents
import com.example.stuart.fragments.categories.Needlework
import com.example.stuart.fragments.categories.Painting
import com.google.android.material.tabs.TabLayoutMediator



class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments = arrayListOf<Fragment>(
            MainCategoryFragment(),
            HandMade(),
            Painting(),
            Needlework(),
            MusicTalents()
        )

        val viewPager2Adapter =
            HomeViewPageAdapter(categoriesFragments, childFragmentManager, lifecycle)
        binding.viewpagerHome.adapter = viewPager2Adapter
        TabLayoutMediator(binding.tabLayout,binding.viewpagerHome){tab,position->

            when(position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "HandMade"
                2 -> tab.text = "Painting"
                3 -> tab.text = "NeedleWork"
                4 -> tab.text = "MusicTalents"
            }

        }.attach()


        }

    

}