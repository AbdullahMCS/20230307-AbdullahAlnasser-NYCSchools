package com.example.myapplication.api

import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private  val apiService: ApiService) {

    suspend fun getNycSchools(): Response<List<HighSchoolResponse>> {
        return apiService.getSchoolsData()
    }

    suspend fun getSatData(): Response<List<SatScoreResponse>> {
        return apiService.getSatData()
    }
}