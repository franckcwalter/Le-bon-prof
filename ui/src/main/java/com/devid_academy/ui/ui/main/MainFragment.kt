package com.devid_academy.ui.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devid_academy.domain.entities.AdsDto
import com.devid_academy.domain.utils.toast
import com.devid_academy.ui.R
import com.devid_academy.ui.databinding.FragmentMainBinding
import com.google.android.material.materialswitch.MaterialSwitch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private val fragmentViewModel: MainViewModel by viewModel()

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private lateinit var seekBarPrice: SeekBar
    private lateinit var switchFav: MaterialSwitch
    private lateinit var etLocation: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

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

        seekBarPrice = binding.seekBarMainPrice.apply {
            progress = 60
            setOnSeekBarChangeListener(
                object : SeekBar.OnSeekBarChangeListener {

                    override fun onProgressChanged(
                        seek: SeekBar,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        binding.tvMainMaxPrice.text = getString(
                            R.string.main_label_filter_max_limit,
                            seek.progress.toString()
                        )
                    }

                    override fun onStartTrackingTouch(seek: SeekBar) {}
                    override fun onStopTrackingTouch(seek: SeekBar) {
                        fetchAndFilterAds()
                    }
                }
            )
        }

        switchFav = binding.toggleButton.apply {
            setOnClickListener {
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
            filterByMaxPrice = if (seekBarPrice.progress < 60) seekBarPrice.progress else 60,
            filterByFav = switchFav.isChecked,
            filterByLocation = if (etLocation.text.toString().isNotBlank())
                etLocation.text.toString()
            else ""
        )
    }
}
