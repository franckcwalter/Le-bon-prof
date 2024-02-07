package com.devid_academy.projetfinal.ui.main

import android.annotation.SuppressLint
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
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devid_academy.projetfinal.network.AdsDto
import com.devid_academy.projetfinal.util.toast
import com.devid_academy.ui.R
import com.devid_academy.ui.databinding.FragmentMainBinding
import com.google.android.material.materialswitch.MaterialSwitch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val fragmentViewModel : MainViewModel by viewModels()

    private var _binding : FragmentMainBinding? = null
    private val binding : FragmentMainBinding
        get() = _binding!!

    private lateinit var seekBar : SeekBar
    private lateinit var switch : MaterialSwitch
    private lateinit var etLocation : EditText


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
                fetchAndFilterAds()
                if(this.isRefreshing) this.isRefreshing = false
            }
        }

        binding.btnMainToProfile.setOnClickListener{
            fragmentViewModel.goToProfile()
        }

        seekBar = binding.seekBarMainPrice.apply {
            progress = 60
            setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {

                    override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                        binding.tvMainMaxPrice.text = getString(R.string.main_label_filter_max_limit, seek.progress.toString())
                    }
                    override fun onStartTrackingTouch(seek: SeekBar) {}
                    override fun onStopTrackingTouch(seek: SeekBar) {
                        fetchAndFilterAds()
                    }
                }
            )
        }

        switch = binding.toggleButton.apply {
            setOnClickListener{
                fetchAndFilterAds()
            }
        }

        etLocation = binding.etMainLocation
        binding.btnMainLocationFilter.setOnClickListener{
            fetchAndFilterAds()
        }

        with(fragmentViewModel){

            adListLivedata.observe(viewLifecycleOwner){
                articleAdapter.submitList(it)
                binding.tvMainNoAds.isVisible = it == listOf<AdsDto>()
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
        fetchAndFilterAds()
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchAndFilterAds() {

        fragmentViewModel.fetchAds(
            filterByMaxPrice = if (seekBar.progress < 60) seekBar.progress else 60,
            filterByFav = switch.isChecked,
            filterByLocation = if (etLocation.text.toString().isNotBlank())
                                    etLocation.text.toString()
                                else ""
        )
    }
}
