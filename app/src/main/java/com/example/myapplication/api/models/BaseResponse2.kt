package com.example.myapplication.api.models

import com.example.myapplication.api.HighSchoolResponse
import com.example.myapplication.api.SatScoreResponse

data class BaseResponse2(
    val successResponse: List<SatScoreResponse>? = null,
    val errorResponse: String? = null
)
