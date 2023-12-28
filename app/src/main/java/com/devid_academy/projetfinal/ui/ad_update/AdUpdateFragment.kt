package com.devid_academy.projetfinal.ui.ad_update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentAdCreateBinding
import com.devid_academy.projetfinal.databinding.FragmentAdUpdateBinding
import com.devid_academy.projetfinal.databinding.FragmentAdminBinding
import com.devid_academy.projetfinal.ui.register.RegisterViewModel


class AdUpdateFragment : Fragment() {

    private val fragmentViewModel : AdUpdateViewModel by viewModels()

    private var _binding : FragmentAdUpdateBinding? = null
    private val binding : FragmentAdUpdateBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdUpdateBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}