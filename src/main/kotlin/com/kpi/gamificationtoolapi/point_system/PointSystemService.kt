package com.kpi.gamificationtoolapi.point_system

import org.springframework.stereotype.Service

@Service
class PointSystemService(
    private val pointSystemRepository: PointSystemRepository,
) {

    fun getPointSystems(studentId: Long): List<PointSystem> {
        return pointSystemRepository.findByStudentId(studentId)
    }
}