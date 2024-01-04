package com.devid_academy.projetfinal.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentAdCreateBinding
import com.devid_academy.projetfinal.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val fragmentViewModel : SplashViewModel by viewModels()

    private var _binding : FragmentSplashBinding? = null
    private val binding : FragmentSplashBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSplashBinding.inflate(inflater, container,false)

        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        fragmentViewModel.navDirLiveData
            .observe(viewLifecycleOwner){
                findNavController().navigate(it)
            }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

    }

}