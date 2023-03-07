package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.api.HighSchoolResponse
import com.example.myapplication.api.models.LiveDataResource
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.databinding.FragmentHomeBinding
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeFragmentViewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private var _binding: FragmentDetailBinding? = null
    val binding get() = _binding!!

    private var receivedDbn = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        receivedDbn = arguments?.getString("SAT") ?: ""
        lifecycleScope.launch {
            homeFragmentViewModel.getSatScore()
        }
    }

    fun setObservers() {
        homeFragmentViewModel.satData.observe(viewLifecycleOwner) {
            it?.let {
                when(it.status) {
                    LiveDataResource.Status.LOADING -> {
                        binding.clProgressbar.show()
                    }
                    LiveDataResource.Status.SUCCESS -> {
                        it.data?.successResponse?.let { it1 ->
                            binding.clProgressbar.hide()
                            val score =  it1.find { it.dbn == receivedDbn }?.mathAvg
                            if(score.isNullOrEmpty())
                                binding.tvSatScore.text = "No data found"
                            else
                                binding.tvSatScore.text = score
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