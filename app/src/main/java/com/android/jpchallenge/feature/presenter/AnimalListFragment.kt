package com.android.jpchallenge.feature.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.android.jpchallenge.databinding.FragmentAnimalListBinding
import com.android.jpchallenge.utility.showSnack
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class AnimalListFragment : Fragment() {

    private var _binding: FragmentAnimalListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AnimalListFragmentViewModel by viewModels()
    private lateinit var  adapter : AnimalListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAnimalListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AnimalListAdapter(arrayListOf())
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.animalRecyclerView.layoutManager = layoutManager
        adapter = AnimalListAdapter(arrayListOf())
        binding.animalRecyclerView.adapter = adapter

        initObserver()
        callSwipeToRefresh()
    }

    private fun initObserver() {
        viewModel.animalData.observe(viewLifecycleOwner, {
            animalList ->
            adapter.setItem(animalList)
            binding.progressBar.visibility = View.INVISIBLE
        })
        viewModel.errorResponse.observe(viewLifecycleOwner, {
            requireView().showSnack("There is some API error")
            binding.progressBar.visibility = View.INVISIBLE
        })
        viewModel.fetchAnimalList()
    }

    private fun callSwipeToRefresh() {
        binding.swipeContainer.setOnRefreshListener(OnRefreshListener {
            viewModel.fetchAnimalList()
            Executors.newSingleThreadScheduledExecutor().schedule({
                binding.swipeContainer.setRefreshing(false)
            }, 3, TimeUnit.SECONDS)

        })
        binding.swipeContainer.setColorSchemeColors(
            ContextCompat.getColor(requireContext(),android.R.color.holo_blue_bright),
            ContextCompat.getColor(requireContext(),android.R.color.holo_green_light),
            ContextCompat.getColor(requireContext(),android.R.color.holo_orange_light),
            ContextCompat.getColor(requireContext(),android.R.color.holo_red_light)
        );
    }



}