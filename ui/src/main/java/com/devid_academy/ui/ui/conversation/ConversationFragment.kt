package com.devid_academy.ui.ui.conversation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devid_academy.ui.databinding.FragmentConversationBinding

class ConversationFragment : Fragment() {

    private val fragmentViewModel : ConversationViewModel by viewModels()

    private var _binding : FragmentConversationBinding? = null
    private val binding : FragmentConversationBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentConversationBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}