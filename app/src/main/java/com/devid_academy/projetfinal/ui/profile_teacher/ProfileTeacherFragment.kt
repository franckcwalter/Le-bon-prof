package com.devid_academy.projetfinal.ui.profile_teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devid_academy.projetfinal.databinding.FragmentTeacherProfileBinding
import com.devid_academy.projetfinal.ui.register.RegisterViewModel


class ProfileTeacherFragment : Fragment() {

    private val fragmentViewModel : ProfileTeacherViewModel by viewModels()

    private var _binding : FragmentTeacherProfileBinding? = null
    private val binding : FragmentTeacherProfileBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTeacherProfileBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}