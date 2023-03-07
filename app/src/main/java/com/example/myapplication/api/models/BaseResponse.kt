package com.example.myapplication.api.models

import com.example.myapplication.api.HighSchoolResponse

data class BaseResponse(
    val successResponse: List<HighSchoolResponse>? = null,
    val errorResponse: String? = null
)
