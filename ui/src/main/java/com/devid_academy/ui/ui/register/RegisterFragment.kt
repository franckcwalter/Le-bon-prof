package com.devid_academy.ui.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devid_academy.domain.entities.Role
import com.devid_academy.domain.utils.toast
import com.devid_academy.ui.databinding.FragmentRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private val fragmentViewModel: RegisterViewModel by viewModel()

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