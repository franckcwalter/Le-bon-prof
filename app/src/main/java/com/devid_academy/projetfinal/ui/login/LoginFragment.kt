package com.devid_academy.projetfinal.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentAdCreateBinding
import com.devid_academy.projetfinal.databinding.FragmentLoginBinding
import com.devid_academy.projetfinal.databinding.FragmentMainBinding
import com.devid_academy.projetfinal.ui.register.RegisterViewModel

class LoginFragment : Fragment() {

    private val fragmentViewModel : LoginViewModel by viewModels()

    private var _binding : FragmentLoginBinding? = null
    private val binding : FragmentLoginBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}