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

        binding.viewpagerHome.isUserInputEnabled = false

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

/*

the lifecycle methods used in the provided HomeFragment class:
onAttach(): This method is called when the fragment has been associated with the activity. The fragment is now tightly bound to the activity.
onCreate(): This method is called to do initial creation of the fragment. It is where you should create the fragment and initialize essential components.
onCreateView(): In this method, the fragment inflates its layout and returns the view hierarchy associated with the fragment.
onViewCreated(): This method is called after the view has been created. It is a good place to perform actions that require a fully created view.
onStart(): This method is called once the fragment is visible to the user. At this point, the fragment is active.
onResume(): This method is called when the fragment is visible and actively running.
onPause(): This method is called when the fragment is no longer interacting with the user, either because a replacement fragment has been committed or the activity is being paused.
onStop(): This method is called when the fragment is no longer visible to the user.
onDestroyView(): This method allows the fragment to clean up resources associated with its View.
onDestroy(): This method is called when the fragment is no longer in use. This is where you should clean up any resources, such as threads, registered listeners, or any other objects that may consume a significant amount of memory.
onDetach(): This method is called when the fragment is being disassociated from the activity.
In the provided HomeFragment class, the onCreateView() and onViewCreated() methods are overridden to handle the inflation of the layout and the setup of the ViewPager2 and TabLayout. These methods are crucial for setting up the UI components and performing any necessary view-related operations.
 */