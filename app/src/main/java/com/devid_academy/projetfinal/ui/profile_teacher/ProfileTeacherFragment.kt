package com.devid_academy.projetfinal.ui.profile_teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devid_academy.projetfinal.databinding.FragmentProfileTeacherBinding
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

        _binding = FragmentProfileTeacherBinding.inflate(inflater, container,false)


        /**  TODO : if teacher has an ad, change text of button : **/
        // binding.buttonProfileTeacherToCreateOrUpdateAd
        /**   binding.tvProfileTeacherNoAds.visibility = VISIBLE   **/

        binding.buttonProfileTeacherToCreateOrUpdateAd.setOnClickListener {
            fragmentViewModel.goToCreateOrUpdateAd()
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