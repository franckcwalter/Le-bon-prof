package com.devid_academy.projetfinal.ui.ad_update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentAdCreateBinding
import com.devid_academy.projetfinal.databinding.FragmentAdUpdateBinding
import com.devid_academy.projetfinal.databinding.FragmentAdminBinding
import com.devid_academy.projetfinal.ui.profile_teacher.ProfileTeacherFragmentArgs
import com.devid_academy.projetfinal.ui.register.RegisterViewModel
import com.devid_academy.projetfinal.util.Place
import com.devid_academy.projetfinal.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdUpdateFragment : Fragment() {

    private val fragmentViewModel : AdUpdateViewModel by viewModels()

    private val args : AdUpdateFragmentArgs by navArgs()


    private var _binding : FragmentAdUpdateBinding? = null
    private val binding : FragmentAdUpdateBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdUpdateBinding.inflate(inflater, container,false)

        with(binding){
            with(args.articleDto) {
                etAdUpdateTitle.setText(this.title)
                etAdUpdatePhoto.setText(this.photo)
                etAdUpdatePrice.setText(this.price)
                etAdUpdateLocation.setText(this.location)
                when(this.place){
                    Place.MY_HOME -> rbAdUpdateMyHome.isChecked = true
                    Place.YOUR_HOME -> rbAdUpdateYourHome.isChecked = true
                    else -> rbAdUpdateThirdPlace.isChecked = true
                 }
                etAdUpdateAd.setText(this.description)
            }

        }

        with(binding){
            buttonAdUpdateUpdateAd.setOnClickListener{

                fragmentViewModel.updateAd(
                    args.articleDto.id,
                    args.articleDto.adReference,
                    etAdUpdateTitle.text.toString(),
                    etAdUpdatePhoto.text.toString(),
                    etAdUpdatePrice.text.toString(),
                    etAdUpdateLocation.text.toString(),
                    when (binding.rgAdUpdatePlace.checkedRadioButtonId){
                        rbAdUpdateMyHome.id -> Place.MY_HOME
                        rbAdUpdateYourHome.id -> Place.YOUR_HOME
                        else -> { Place.THIRD_PLACE }
                    },
                    etAdUpdateAd.text.toString(),
                    args.articleDto.createdAt,
                    args.articleDto.approved,
                    args.articleDto.idUser

                )
            }
        }


        fragmentViewModel.adWasUpdatedLiveData
            .observe(viewLifecycleOwner){
                it.getContentIfNotHandeled()?.let {adWasUpdated ->
                    if(adWasUpdated) findNavController().popBackStack()
                }
            }

        fragmentViewModel.userMessageLiveData.observe(viewLifecycleOwner){
            it.getContentIfNotHandeled()?.let { userMessage ->
                requireContext().toast(userMessage)
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}