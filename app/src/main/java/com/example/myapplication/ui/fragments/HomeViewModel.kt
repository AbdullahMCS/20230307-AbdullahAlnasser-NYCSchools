package com.example.myapplication.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.AppRepository
import com.example.myapplication.api.models.BaseResponse
import com.example.myapplication.api.models.BaseResponse2
import com.example.myapplication.api.models.LiveDataResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
): ViewModel() {

    private val _resultData: MutableLiveData<LiveDataResource<BaseResponse>> = MutableLiveData()
    val schoolsData = _resultData

    private val _resultSatData: MutableLiveData<LiveDataResource<BaseResponse2>> = MutableLiveData()
    val satData = _resultSatData

    fun getSchools() {
        viewModelScope.launch {
            _resultData.value = LiveDataResource.loading(null)
            val response = withContext(Dispatchers.IO) {
                appRepository.getNycSchools()
            }
            if(response.isSuccessful) {
                response.body()?.let {
                    _resultData.value = LiveDataResource.success(BaseResponse(it, null))
                }
            } else {
                response.errorBody()?.let {
                    _resultData.value = LiveDataResource.error(BaseResponse(null, it.toString()))
                }
            }
        }
    }

    fun getSatScore() {
        viewModelScope.launch {
            _resultSatData.value = LiveDataResource.loading(null)
            val response = withContext(Dispatchers.IO) {
                appRepository.getSatData()
            }
            if(response.isSuccessful) {
                response.body()?.let {
                    _resultSatData.value = LiveDataResource.success(BaseResponse2(it, null))
                }
            } else {
                response.errorBody()?.let {
                    _resultSatData.value = LiveDataResource.error(BaseResponse2(null, it.toString()))
                }
            }
        }
    }
}