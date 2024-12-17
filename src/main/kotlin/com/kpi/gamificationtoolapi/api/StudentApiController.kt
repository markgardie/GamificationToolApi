package com.kpi.gamificationtoolapi.api

import com.kpi.gamificationtoolapi.point_system.PointSystemService
import com.kpi.gamificationtoolapi.security.JwtUtil
import com.kpi.gamificationtoolapi.student.StudentService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class StudentRestController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val studentService: StudentService,
    private val pointSystemService: PointSystemService
) {

    @PostMapping("/auth/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Map<String, String>> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.login, loginRequest.password)
        )

        SecurityContextHolder.getContext().authentication = authentication
        val userDetails = authentication.principal as UserDetails
        val jwt = jwtUtil.generateToken(userDetails)

        return ResponseEntity.ok(mapOf("token" to jwt))
    }

    @GetMapping("/student/profile")
    fun getStudentProfile(): ResponseEntity<StudentProfileResponse> {
        val login = SecurityContextHolder.getContext().authentication.name
        val student = studentService.findByLogin(login)
        val pointSystems = pointSystemService.getPointSystems(student.id)

        return ResponseEntity.ok(StudentProfileResponse(
            name = student.name,
            birthDate = student.birthDate,
            group = student.group.name,
            pointSystems = pointSystems.associate { it.name to it.value }
        ))
    }

}