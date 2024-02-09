package com.devid_academy.projetfinal.ui.ad_create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devid_academy.ui.databinding.FragmentAdCreateBinding
import com.devid_academy.ui.util.Place
import com.devid_academy.ui.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdCreateFragment : Fragment() {

    private val fragmentViewModel : AdCreateViewModel by viewModel()

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