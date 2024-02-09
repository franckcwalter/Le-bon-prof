package com.devid_academy.ui.ui.profile_teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devid_academy.ui.R
import com.devid_academy.ui.databinding.FragmentProfileTeacherBinding
import com.devid_academy.ui.util.alertDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileTeacherFragment : Fragment() {

    private val fragmentViewModel : ProfileTeacherViewModel by viewModel()

    private var _binding : FragmentProfileTeacherBinding? = null
    private val binding : FragmentProfileTeacherBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    { _binding = FragmentProfileTeacherBinding.inflate(inflater, container, false)

        binding.buttonProfileTeacherBackToMain.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonProfileTeacherLogOutUser.setOnClickListener {
            requireContext().alertDialog(R.string.alertdialog_confirm_logout){
                fragmentViewModel.logOutUser()
            }
        }

        fragmentViewModel.uiState.observe(viewLifecycleOwner) {

            with(binding){

                tvProfileTeacherPageTitle.text = it.pageTitle
                tvProfileTeacherAdTitle.text = it.adTitle
                tvProfileTeacherAd.text = it.adContent
                tvProfileTeacherAdPrice.text = it.adPrice
                tvProfileTeacherNoAds.isVisible = it.hasNoAd
                buttonProfileTeacherToCreateOrUpdateAd.text = it.buttonLabel

            }
        }

        binding.buttonProfileTeacherToCreateOrUpdateAd.setOnClickListener {
            fragmentViewModel.goToCreateOrUpdateAd()
        }

        fragmentViewModel.navDirLiveData
            .observe(viewLifecycleOwner) {
                it.getContentIfNotHandeled()?.let {
                    findNavController().navigate(it)
                }
            }

        fragmentViewModel.fetchAd()

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        fragmentViewModel.fetchAd()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}