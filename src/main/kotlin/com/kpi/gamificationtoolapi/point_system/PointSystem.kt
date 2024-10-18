package com.kpi.gamificationtoolapi.point_system

import com.kpi.gamificationtoolapi.student.Student
import jakarta.persistence.*

@Entity
@Table(name = "points")
data class PointSystem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    var value: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    val student: Student,
)