package com.devid_academy.projetfinal.ui.ad_create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentAdCreateBinding
import com.devid_academy.projetfinal.databinding.FragmentAdDetailsBinding
import com.devid_academy.projetfinal.ui.register.RegisterViewModel
import com.devid_academy.projetfinal.util.Place
import com.devid_academy.projetfinal.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdCreateFragment : Fragment() {

    private val fragmentViewModel : AdCreateViewModel by viewModels()

    private var _binding : FragmentAdCreateBinding? = null
    private val binding : FragmentAdCreateBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    { _binding = FragmentAdCreateBinding.inflate(inflater, container,false)

        with(binding){
            buttonAdCreateCreateAd.setOnClickListener{

                fragmentViewModel.createAd(
                    etAdCreateTitle.text.toString(),
                    etAdCreatePhoto.text.toString(),
                    etAdCreateAd.text.toString(),
                    when (binding.rgAdCreatePlace.checkedRadioButtonId){
                        rbAdCreateMyHome.id -> Place.MY_HOME
                        rbAdCreateYourHome.id -> Place.YOUR_HOME
                        else -> { Place.PUBLIC_PLACE }
                    },
                    etAdCreateLocation.text.toString(),
                    etAdCreatePrice.text.toString()
                )
            }
        }


        binding.buttonAdCreateToProfileTeacher.setOnClickListener{
            findNavController().popBackStack()
        }

        fragmentViewModel.navBackLiveData
            .observe(viewLifecycleOwner){
                it.getContentIfNotHandeled()?.let {
                    if(it) findNavController().popBackStack()
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