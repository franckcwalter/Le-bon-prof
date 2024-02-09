package com.devid_academy.ui.ui.ad_update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devid_academy.projetfinal.ui.ad_update.AdUpdateViewModel
import com.devid_academy.ui.R
import com.devid_academy.ui.databinding.FragmentAdUpdateBinding
import com.devid_academy.ui.util.Place
import com.devid_academy.ui.util.alertDialog
import com.devid_academy.ui.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdUpdateFragment : Fragment() {

    private val fragmentViewModel : AdUpdateViewModel by viewModel()

    private val args : AdUpdateFragmentArgs by navArgs()

    private var _binding : FragmentAdUpdateBinding? = null
    private val binding : FragmentAdUpdateBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    { _binding = FragmentAdUpdateBinding.inflate(inflater, container,false)

        with(binding){
            args.articleDto.let {
                etAdUpdateTitle.setText(it.title)
                etAdUpdatePhoto.setText(it.photo)
                etAdUpdatePrice.setText(it.price)
                etAdUpdateLocation.setText(it.location)
                when(it.place){
                    Place.MY_HOME -> rbAdUpdateMyHome.isChecked = true
                    Place.YOUR_HOME -> rbAdUpdateYourHome.isChecked = true
                    else -> rbAdUpdateThirdPlace.isChecked = true
                }
                etAdUpdateAd.setText(it.description)
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
                        else -> { Place.PUBLIC_PLACE }
                    },
                    etAdUpdateAd.text.toString(),
                    args.articleDto.createdAt,
                    args.articleDto.approved,
                    args.articleDto.idUser

                )
            }
        }

        binding.buttonAdUpdateDeleteAd.setOnClickListener{
            requireContext().alertDialog(R.string.alertdialog_confirm_article_delete){
                 fragmentViewModel.deleteAd(args.articleDto.id)
            }
        }

        binding.buttonAdUpdateToProfileTeacher.setOnClickListener{
            findNavController().popBackStack()
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