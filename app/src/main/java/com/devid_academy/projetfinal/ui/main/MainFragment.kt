package com.devid_academy.projetfinal.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentAdCreateBinding
import com.devid_academy.projetfinal.databinding.FragmentMainBinding
import com.devid_academy.projetfinal.databinding.FragmentProfileLearnerBinding
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
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container,false)


        binding.btnMainToProfile.setOnClickListener{
            fragmentViewModel.goToProfile()
        }

        val articleAdapter = AdAdapter().apply {
            onItemClick = { articleId ->
                fragmentViewModel.goToDetail(articleId) }
        }.also {
            binding.rvMainAds.adapter = it
        }

        val swipeRefreshLayout = binding.swipeRefreshLayout

        binding.swipeRefreshLayout.setOnRefreshListener {
            fragmentViewModel.fetchAds()
            if(swipeRefreshLayout.isRefreshing) swipeRefreshLayout.isRefreshing = false
        }


        fragmentViewModel.adList.observe(viewLifecycleOwner){
            articleAdapter.submitList(it)
            // binding.rvMainAds.smoothScrollToPosition(0)

            /*
            binding.progressBar.visibility = View.GONE
            if(swipeRefreshLayout.isRefreshing) swipeRefreshLayout.isRefreshing = false
             */
        }



        fragmentViewModel.navDirLiveData
            .observe(viewLifecycleOwner){
                it.getContentIfNotHandeled()?.let {
                    findNavController().navigate(it)
                }
            }

        fragmentViewModel.userMessageLiveData.observe(viewLifecycleOwner){
            it.getContentIfNotHandeled()?.let {
                requireContext().toast(it)
            }
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}