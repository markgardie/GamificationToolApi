package com.kpi.gamificationtoolapi.point_system

import org.springframework.data.jpa.repository.JpaRepository

interface PointSystemRepository : JpaRepository<PointSystem, Long> {
    fun findByStudentId(studentId: Long): List<PointSystem>
}