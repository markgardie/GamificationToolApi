package com.kpi.gamificationtoolapi.api

data class StudentProfileResponse(
    val name: String,
    val age: Int,
    val group: String,
    val pointSystems: Map<String, Int>
)