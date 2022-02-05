package com.android.jpchallenge.feature.presenter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.jpchallenge.databinding.FragmentAnimalListBinding
import com.android.jpchallenge.utility.showSnack
import dagger.hilt.android.AndroidEntryPoint

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
    }

    private fun initObserver() {
        viewModel.animalData.observe(viewLifecycleOwner, {
            animalList ->
            Log.i("",""+animalList)
            adapter.setItem(animalList)
            binding.progressBar.visibility = View.INVISIBLE
        })
        viewModel.errorResponse.observe(viewLifecycleOwner, {
            requireView().showSnack("There is some API error")
            binding.progressBar.visibility = View.INVISIBLE
        })
        viewModel.fetchAnimalList()
    }


}