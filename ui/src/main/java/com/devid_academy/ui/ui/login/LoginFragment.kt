package com.devid_academy.ui.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devid_academy.projetfinal.util.toast
import com.devid_academy.ui.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val fragmentViewModel: LoginViewModel by viewModel()

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