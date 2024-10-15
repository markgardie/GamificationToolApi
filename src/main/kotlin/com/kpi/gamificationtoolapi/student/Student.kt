package com.kpi.gamificationtoolapi.student

import com.kpi.gamificationtoolapi.point_system.PointSystem
import jakarta.persistence.*

@Entity
@Table(name = "students")
data class Student(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    val age: Int,

    @Column(unique = true, nullable = false)
    val login: String,

    @Column(nullable = false)
    val password: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    val group: Group,

    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL], orphanRemoval = true)
    val pointSystems: List<PointSystem>,
)