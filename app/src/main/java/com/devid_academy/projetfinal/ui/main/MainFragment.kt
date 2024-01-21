package com.devid_academy.projetfinal.ui.main

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.FragmentAdCreateBinding
import com.devid_academy.projetfinal.databinding.FragmentMainBinding
import com.devid_academy.projetfinal.databinding.FragmentProfileLearnerBinding
import com.devid_academy.projetfinal.network.SubjectDto
import com.devid_academy.projetfinal.ui.register.RegisterViewModel
import com.devid_academy.projetfinal.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val fragmentViewModel : MainViewModel by viewModels()

    private var _binding : FragmentMainBinding? = null
    private val binding : FragmentMainBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container,false)


        binding.btnMainToProfile.setOnClickListener{
            fragmentViewModel.goToProfile()
        }

        val articleAdapter = AdAdapter().apply {
            onItemClick = { articleId ->
                fragmentViewModel.goToDetail(articleId) }
        }.also {
            binding.rvMainAds.adapter = it
        }

        val swipeRefreshLayout = binding.swipeRefreshLayout

        binding.swipeRefreshLayout.setOnRefreshListener {
            fragmentViewModel.fetchAds()
            if(swipeRefreshLayout.isRefreshing) swipeRefreshLayout.isRefreshing = false
        }


        fragmentViewModel.adList.observe(viewLifecycleOwner){
            articleAdapter.submitList(it)
            // binding.rvMainAds.smoothScrollToPosition(0)

            /*
            binding.progressBar.visibility = View.GONE
            if(swipeRefreshLayout.isRefreshing) swipeRefreshLayout.isRefreshing = false
             */
        }



        fragmentViewModel.navDirLiveData
            .observe(viewLifecycleOwner){
                it.getContentIfNotHandeled()?.let {
                    findNavController().navigate(it)
                }
            }

        fragmentViewModel.userMessageLiveData.observe(viewLifecycleOwner){
            it.getContentIfNotHandeled()?.let {
                requireContext().toast(it)
            }
        }

        fragmentViewModel.fetchCategories()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        fragmentViewModel.fetchAds()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

/*
fragmentViewModel.subjectsList.observe(viewLifecycleOwner){

    val subjectsAdapter = CustomArrayAdapter(this.requireContext(), android.R.layout.simple_list_item_1, it)
        .apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }.also {
            binding.spinnerMainSubjetSelector.adapter = it
            binding.spinnerMainSubjetSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    /*TODO : faire le filtre  */
                    val article : SubjectDto = parent.getItemAtPosition(position) as SubjectDto
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
}


class CustomArrayAdapter(
    context: Context,
    resource: Int,
    objects: List<SubjectDto>
) : ArrayAdapter<SubjectDto>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getView(position, convertView, parent).apply {
            getItem(position)?.let {
                this.findViewById<TextView>(android.R.id.text1).text = it.name
            }
        }
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getDropDownView(position, convertView, parent).apply {
            getItem(position)?.let {
                this.findViewById<TextView>(android.R.id.text1).text = it.name
            }
        }
    }
}
 */