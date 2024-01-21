package com.devid_academy.projetfinal.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentAdCreateBinding
import com.devid_academy.projetfinal.databinding.FragmentAdminBinding
import com.devid_academy.projetfinal.databinding.FragmentRegisterBinding
import com.devid_academy.projetfinal.databinding.FragmentSplashBinding
import com.devid_academy.projetfinal.ui.splash.SplashViewModel
import com.devid_academy.projetfinal.util.Role
import com.devid_academy.projetfinal.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val fragmentViewModel : RegisterViewModel by viewModels()

    private var _binding : FragmentRegisterBinding? = null
    private val binding : FragmentRegisterBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    { _binding = FragmentRegisterBinding.inflate(inflater, container,false)

        with(binding){

            buttonRegisterRegisterUser.setOnClickListener {
                fragmentViewModel.registerUser(
                    etRegisterEmail.text.toString(),
                    etRegisterName.text.toString(),
                    etRegisterPassword.text.toString(),
                    etRegisterPasswordConfirmation.text.toString(),
                    if(rgRegisterTeachOrLearn.checkedRadioButtonId == rbRegisterTeach.id)
                         Role.TEACH
                    else Role.LEARN
                )
            }

            buttonRegisterToLogin.setOnClickListener {
                findNavController().popBackStack()
            }
        }


        fragmentViewModel.userMessageLiveData.observe(viewLifecycleOwner){
            it.getContentIfNotHandeled()?.let {
                requireContext().toast(it)
            }
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