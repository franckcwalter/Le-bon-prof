package com.devid_academy.ui.ui.ad_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.devid_academy.projetfinal.util.toast
import com.devid_academy.ui.R
import com.devid_academy.ui.databinding.FragmentAdDetailsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class AdDetailsFragment : Fragment() {

    private val fragmentViewModel : AdDetailsViewModel by viewModel()

    private val args : AdDetailsFragmentArgs by navArgs()

    private var _binding : FragmentAdDetailsBinding? = null
    private val binding : FragmentAdDetailsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAdDetailsBinding.inflate(inflater, container,false)

        args.idAd.let {
            fragmentViewModel.fetchAd(it)
        }

        binding.viewmodel = fragmentViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.buttonAdDetailsToggleFavorite.setOnClickListener{
           fragmentViewModel.toggleFav(args.idAd)
        }

        fragmentViewModel.adLiveData.observe(viewLifecycleOwner){
            Picasso.get()
                .load(it.photo.ifEmpty { "noImg" })
                .placeholder(R.drawable.baseline_favorite_border_24)
                .error(R.drawable.logo_small)
                .into(binding.ivItemPhoto)

            (if (it.isFav == 1) R.drawable.baseline_favorite_24
            else R.drawable.baseline_favorite_border_24)
                .let { binding.buttonAdDetailsToggleFavorite.setImageResource(it) }
        }

        fragmentViewModel.userMessageLiveData.observe(viewLifecycleOwner){
            it.getContentIfNotHandeled()?.let {
                requireContext().toast(it)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}