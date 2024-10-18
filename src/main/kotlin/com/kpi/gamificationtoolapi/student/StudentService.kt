package com.kpi.gamificationtoolapi.student

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository,
): UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        val student = studentRepository.findByLogin(login)
            ?: throw UsernameNotFoundException("Student not found")

        return User(
            student.login,
            student.password,
            listOf(SimpleGrantedAuthority("ROLE_STUDENT"))
        )
    }

    fun findByLogin(login: String): Student {
        return studentRepository.findByLogin(login)
            ?: throw UsernameNotFoundException("Student not found")
    }
}