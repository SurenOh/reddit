package com.example.redditandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditandroid.databinding.FragmentHomeBinding
import com.example.redditandroid.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (_binding == null) _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupAdapters()
        setupObservers()
        viewModel.firstPage()
        binding.btnUpdate.setOnClickListener {
            viewModel.firstPage()
        }
    }

    private fun setupObservers() {
        viewModel.reddits.observe(viewLifecycleOwner) { reddits ->
            adapter.setReddits(reddits)
        }
    }

    private fun setupAdapters() {
        adapter = HomeAdapter(arrayListOf())

        binding.rvRedditList.apply {
            adapter = this@HomeFragment.adapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        adapter.onClickImage = {image, isVideo ->
            if (isVideo == false) {
                image?.let {
                    findNavController().navigate(HomeFragmentDirections.goToDetail(image, isVideo))
                }
            } else {
                snackBarMessage("This reddit doesn't have an image")
            }
        }
    }

}