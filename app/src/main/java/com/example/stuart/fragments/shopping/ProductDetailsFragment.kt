package com.example.stuart.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuart.adapters.ColorsAdapter
import com.example.stuart.adapters.SizesAdapter
import com.example.stuart.adapters.ViewPager2Images
import com.example.stuart.databinding.FragmentProductDetailsBinding
import com.example.stuart.util.hideBottomNavigationView


class ProductDetailsFragment: Fragment() {
    private val args by navArgs<ProductDetailsFragmentArgs>()
    private lateinit var binding : FragmentProductDetailsBinding
    private val viewPagerAdapter by lazy { ViewPager2Images() }
    private val sizesAdapter by lazy { SizesAdapter()}
    private val colorsAdapter by lazy { ColorsAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hideBottomNavigationView()
        binding= FragmentProductDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.product

        setupSizesRv()
        setupColorRv()
        setupViewpager()

        binding.imageClose.setOnClickListener{
            findNavController().navigateUp()
        }

        binding.apply{
            tvProductName.text=product.name
            tvProductMPrice.text="${product.price}DT"
            tvProductDescription.text = product.description
            if (product.colors.isNullOrEmpty())
                tvProductColors.visibility = View.INVISIBLE
            if (product.sizes.isNullOrEmpty())
                tvProductSize.visibility =View.INVISIBLE
        }
        viewPagerAdapter.differ.submitList(product.images)
        product.colors?.let{ colorsAdapter.differ.submitList(it)}
        product.sizes?.let{ sizesAdapter.differ.submitList(it)}
    }

    private fun setupSizesRv(){
     binding.apply {
         viewPagerProductImages.adapter = viewPagerAdapter
     }
    }
    private fun setupColorRv(){
      binding.rvColors.apply {
          adapter =  colorsAdapter
          layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
      }
    }
    private fun setupViewpager(){
        binding.rvSize.apply {
            adapter =  sizesAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        }
    }

}

