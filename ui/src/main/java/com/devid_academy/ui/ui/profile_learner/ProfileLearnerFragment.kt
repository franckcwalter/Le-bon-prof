package com.devid_academy.projetfinal.ui.profile_learner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devid_academy.projetfinal.util.alertDialog
import com.devid_academy.ui.R
import com.devid_academy.ui.databinding.FragmentProfileLearnerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileLearnerFragment : Fragment() {

    private val fragmentViewModel : ProfileLearnerViewModel by viewModels()

    private var _binding : FragmentProfileLearnerBinding? = null
    private val binding : FragmentProfileLearnerBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    { _binding = FragmentProfileLearnerBinding.inflate(inflater, container,false)


        binding.buttonProfileLearnerLogOutUser.setOnClickListener {
            requireContext().alertDialog(R.string.alertdialog_confirm_logout){
                fragmentViewModel.logOutUser()
            }
        }

        binding.buttonProfileLearnerBackToMain.setOnClickListener{
            findNavController().popBackStack()
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