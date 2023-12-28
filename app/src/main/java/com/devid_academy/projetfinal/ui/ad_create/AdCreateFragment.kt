package com.devid_academy.projetfinal.ui.ad_create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentAdCreateBinding
import com.devid_academy.projetfinal.databinding.FragmentAdDetailsBinding
import com.devid_academy.projetfinal.ui.register.RegisterViewModel


class AdCreateFragment : Fragment() {

    private val fragmentViewModel : AdCreateViewModel by viewModels()

    private var _binding : FragmentAdCreateBinding? = null
    private val binding : FragmentAdCreateBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdCreateBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}