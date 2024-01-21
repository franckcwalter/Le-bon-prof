package com.devid_academy.projetfinal.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentAdCreateBinding
import com.devid_academy.projetfinal.databinding.FragmentLoginBinding
import com.devid_academy.projetfinal.databinding.FragmentMainBinding
import com.devid_academy.projetfinal.ui.register.RegisterViewModel
import com.devid_academy.projetfinal.util.SingleEvent
import com.devid_academy.projetfinal.util.toast
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val fragmentViewModel : LoginViewModel by viewModels()

    private var _binding : FragmentLoginBinding? = null
    private val binding : FragmentLoginBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    { _binding = FragmentLoginBinding.inflate(inflater, container,false)

        binding.buttonLoginLoginUser.setOnClickListener {
            fragmentViewModel.logInUserIfDataIsCorrect(
                binding.etLoginEmail.text.toString(),
                binding.etLoginPassword.text.toString()
            )
        }

        binding.buttonLoginToRegister.setOnClickListener {
            fragmentViewModel.goToRegister()
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