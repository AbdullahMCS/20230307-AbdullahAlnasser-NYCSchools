package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.api.HighSchoolResponse
import com.example.myapplication.api.models.LiveDataResource
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.MainActivity
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeFragmentViewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private  lateinit var schools: MutableList<HighSchoolResponse>
    private  lateinit var rvAdapter: SchoolsListAdapter

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        schools = mutableListOf()
        rvAdapter = SchoolsListAdapter(schools) {
            Log.d("tttt", "on item clicked")
            (activity as MainActivity).replaceFragment(it.dbn)
        }
        binding.rvSchoolsList.apply {
            adapter = rvAdapter
        }

        setObservers()
        lifecycleScope.launch {
            homeFragmentViewModel.getSchools()
        }
    }

    fun setObservers() {
        homeFragmentViewModel.schoolsData.observe(viewLifecycleOwner) {
            it?.let {
                when(it.status) {
                    LiveDataResource.Status.LOADING -> {
                        binding.clProgressbar.show()
                    }
                    LiveDataResource.Status.SUCCESS -> {
                        it.data?.successResponse?.let { it1 ->
                            binding.clProgressbar.hide()
                            schools.clear()
                            schools.addAll(it1)
                            rvAdapter.notifyDataSetChanged()
                        }
                    }
                    else -> {
                        it.data?.errorResponse
                    }
                }
            }
        }
    }
}