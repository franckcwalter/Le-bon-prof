package com.devid_academy.projetfinal.ui.main

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentAdCreateBinding
import com.devid_academy.projetfinal.databinding.FragmentMainBinding
import com.devid_academy.projetfinal.databinding.FragmentProfileLearnerBinding
import com.devid_academy.projetfinal.network.SubjectDto
import com.devid_academy.projetfinal.ui.register.RegisterViewModel
import com.devid_academy.projetfinal.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val fragmentViewModel : MainViewModel by viewModels()

    private var _binding : FragmentMainBinding? = null
    private val binding : FragmentMainBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    { _binding = FragmentMainBinding.inflate(inflater, container,false)


        val articleAdapter = AdAdapter().apply {
            onItemClick = { articleId ->
                fragmentViewModel.goToDetail(articleId) }
        }.also {
            binding.rvMainAds.adapter = it
        }

        binding.swipeRefreshLayout.apply {
            setOnRefreshListener {
                fragmentViewModel.fetchAds()
                if(this.isRefreshing) this.isRefreshing = false
            }
        }

        binding.btnMainToProfile.setOnClickListener{
            fragmentViewModel.goToProfile()
        }


        with(fragmentViewModel){

            adListLivedata.observe(viewLifecycleOwner){
                articleAdapter.submitList(it)
            }

            navDirLiveData.observe(viewLifecycleOwner){
                it.getContentIfNotHandeled()?.let {
                    findNavController().navigate(it)
                }
            }

            userMessageLiveData.observe(viewLifecycleOwner){
                it.getContentIfNotHandeled()?.let {
                    requireContext().toast(it)
                }
            }

        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        fragmentViewModel.fetchAds()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
