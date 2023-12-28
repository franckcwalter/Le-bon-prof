package com.devid_academy.projetfinal.ui.profile_learner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentAdCreateBinding
import com.devid_academy.projetfinal.databinding.FragmentProfileLearnerBinding
import com.devid_academy.projetfinal.databinding.FragmentTeacherProfileBinding
import com.devid_academy.projetfinal.ui.register.RegisterViewModel


class ProfileLearnerFragment : Fragment() {

    private val fragmentViewModel : ProfileLearnerViewModel by viewModels()

    private var _binding : FragmentProfileLearnerBinding? = null
    private val binding : FragmentProfileLearnerBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileLearnerBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}