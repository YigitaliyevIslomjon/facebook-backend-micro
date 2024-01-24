package com.facebook.users.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.Id
import java.sql.Timestamp
import java.util.*

@Entity(name = "users")
data class User(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    val id: UUID? = null,

    @Column(nullable = false)
    val firstName: String? = null,

    @Column(nullable = false)
    val lastName: String? = null,

    @Column(nullable = false, unique = true)
    val email : String? = null,

    @Column(nullable = false)
    val password : String? = null,

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    val birthdate : Date? = null,

    @CreationTimestamp
    val registrationDate : Timestamp? = null,

)
