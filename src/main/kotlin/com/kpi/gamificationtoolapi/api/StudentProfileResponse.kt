package com.kpi.gamificationtoolapi.api

import java.time.LocalDate

data class StudentProfileResponse(
    val name: String,
    val birthDate: LocalDate,
    val group: String,
    val pointSystems: Map<String, Int>
)