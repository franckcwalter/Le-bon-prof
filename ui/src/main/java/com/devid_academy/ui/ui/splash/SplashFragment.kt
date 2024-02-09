package com.devid_academy.ui.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devid_academy.ui.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private val fragmentViewModel: SplashViewModel by viewModel()

    private var _binding : FragmentSplashBinding? = null
    private val  binding : FragmentSplashBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    { _binding = FragmentSplashBinding.inflate(inflater, container,false)

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