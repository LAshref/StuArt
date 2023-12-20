package com.example.stuart.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stuart.R
import com.example.stuart.adapters.BestProductsAdapter
import com.example.stuart.databinding.FragmentBaseCategoryBinding


open class BaseCategoryFragment: Fragment(R.layout.fragment_base_category) {
    private lateinit var binding:FragmentBaseCategoryBinding
    private lateinit var offeradapter :BestProductsAdapter
    private lateinit var bestProductsAdapter: BestProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOfferRv()
        setupBestProductsRv()
    }

    private fun setupBestProductsRv() {
        bestProductsAdapter = BestProductsAdapter()
        binding.rvBestProducts.apply{
            layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)
            adapter = bestProductsAdapter
        }
    }
    private fun setupOfferRv() {
        offeradapter = BestProductsAdapter();
        binding.rvBestProducts.apply{
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = offeradapter
        }
    }
}


