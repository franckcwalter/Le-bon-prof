package com.devid_academy.projetfinal.ui.profile_teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentProfileTeacherBinding
import com.devid_academy.projetfinal.ui.ad_details.AdDetailsFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileTeacherFragment : Fragment() {

    private val args : ProfileTeacherFragmentArgs by navArgs()

    private val fragmentViewModel : ProfileTeacherViewModel by viewModels()

    private var _binding : FragmentProfileTeacherBinding? = null
    private val binding : FragmentProfileTeacherBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileTeacherBinding.inflate(inflater, container,false)

        val userHasPostedAd : Boolean = args.adId > 0

        if (!userHasPostedAd){
            binding.tvProfileTeacherNoAds.visibility = VISIBLE
            binding.buttonProfileTeacherToCreateOrUpdateAd.text = getString(R.string.create_ad)
        } else args.adId.let {
            fragmentViewModel.fetchAd(it)
        }

        binding.buttonProfileTeacherToCreateOrUpdateAd.setOnClickListener {
                fragmentViewModel.goToCreateOrUpdateAd(userHasPostedAd)
        }


        binding.buttonProfileTeacherBackToMain.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.buttonProfileTeacherLogOutUser.setOnClickListener {
            fragmentViewModel.logOutUser()
        }

        fragmentViewModel.adLiveData.observe(viewLifecycleOwner){
            binding.tvProfileTeacherName.text = it.firstName
            binding.tvProfileTeacherAdTitle.text = it.title
            binding.tvProfileTeacherAd.text = it.description
            binding.tvProfileTeacherAdPrice.text = String.format(getString(R.string.price_and_currency), it.price)
        }

        fragmentViewModel.navDirLiveData
            .observe(viewLifecycleOwner){
                it.getContentIfNotHandeled()?.let {
                    findNavController().navigate(it)
                }
            }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}