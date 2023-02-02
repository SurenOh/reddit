package com.example.redditandroid.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.redditandroid.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
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
    }

}