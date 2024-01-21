package com.devid_academy.projetfinal.ui.profile_teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
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

    private val fragmentViewModel : ProfileTeacherViewModel by viewModels()

    private var _binding : FragmentProfileTeacherBinding? = null
    private val binding : FragmentProfileTeacherBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileTeacherBinding.inflate(inflater, container, false)

        binding.buttonProfileTeacherBackToMain.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonProfileTeacherLogOutUser.setOnClickListener {
            fragmentViewModel.logOutUser()
        }

        fragmentViewModel.userNameLiveData.observe(viewLifecycleOwner) {
            binding.tvProfileTeacherName.text = it
        }
        fragmentViewModel.adLiveData.observe(viewLifecycleOwner) { adDtoNullable ->

            /*TODO : améliorer la logique ?? pb : remise à jour des TV après suppression */

            if (adDtoNullable == null) {

                with(binding){

                    binding.tvProfileTeacherNoAds.visibility = VISIBLE
                    binding.buttonProfileTeacherToCreateOrUpdateAd.text = getString(R.string.create_ad)

                    tvProfileTeacherAdTitle.text = ""
                    tvProfileTeacherAd.text = ""
                    tvProfileTeacherAdPrice.text =""
                }

            } else {

                with(binding) {

                    tvProfileTeacherNoAds.visibility = GONE
                    buttonProfileTeacherToCreateOrUpdateAd.text = getString(R.string.update_ad)



                        tvProfileTeacherAdTitle.text = adDtoNullable.title
                        tvProfileTeacherAd.text = adDtoNullable.description
                        tvProfileTeacherAdPrice.text =
                            String.format(getString(R.string.price_and_currency), adDtoNullable.price)
                    }

                }

            binding.buttonProfileTeacherToCreateOrUpdateAd.setOnClickListener {
                fragmentViewModel.goToCreateOrUpdateAd(adDtoNullable != null)
            }

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