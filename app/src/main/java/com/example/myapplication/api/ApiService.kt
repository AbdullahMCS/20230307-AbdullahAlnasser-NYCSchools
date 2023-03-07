package com.example.myapplication.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/resource/s3k6-pzi2.json")
    suspend fun getSchoolsData(): Response<List<HighSchoolResponse>>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSatData(): Response<List<SatScoreResponse>>
}