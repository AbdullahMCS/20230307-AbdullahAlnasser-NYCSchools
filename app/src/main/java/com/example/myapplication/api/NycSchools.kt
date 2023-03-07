package com.example.myapplication.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HighSchoolResponse(
    @field:SerializedName("dbn")
    @Expose
    val dbn: String,

    @field:SerializedName("school_name")
    @Expose
    val schoolName: String? = null,

    @field:SerializedName("overview_paragraph")
    @Expose
    val overviewParagraph: String? = null,
    val schoolSeats: Int? = null,
    val academicOpp1:String? = null,
    val academicOpp2: String? = null
)

data class SatScoreResponse(
    @field:SerializedName("dbn")
    @Expose
    val dbn: String,

    @field:SerializedName("school_name")
    @Expose
    val schoolName: String? = null,
    val numSatTestTaker: String? = null,
    val readingAvg: String? = null,
    @field:SerializedName("sat_math_avg_score")
    @Expose
    val mathAvg: String? = null,
    val writingAvg: String? = null
)