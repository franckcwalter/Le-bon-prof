package com.devid_academy.projetfinal.ui.ad_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentAdCreateBinding
import com.devid_academy.projetfinal.databinding.FragmentAdDetailsBinding
import com.devid_academy.projetfinal.databinding.FragmentAdUpdateBinding
import com.devid_academy.projetfinal.ui.admin.AdminViewModel
import com.devid_academy.projetfinal.ui.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdDetailsFragment : Fragment() {

    private val fragmentViewModel : AdDetailsViewModel by viewModels()

    private var _binding : FragmentAdDetailsBinding? = null
    private val binding : FragmentAdDetailsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdDetailsBinding.inflate(inflater, container,false)



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}