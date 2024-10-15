package com.kpi.gamificationtoolapi.student

import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, Long> {

    fun findByLogin(username: String): Student?
}