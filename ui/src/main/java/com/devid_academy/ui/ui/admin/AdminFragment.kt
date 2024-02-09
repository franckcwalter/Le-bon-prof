package com.devid_academy.ui.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devid_academy.ui.databinding.FragmentAdminBinding

class AdminFragment : Fragment() {

    private val fragmentViewModel : AdminViewModel by viewModels()

    private var _binding : FragmentAdminBinding? = null
    private val binding : FragmentAdminBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdminBinding.inflate(inflater, container,false)

        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}