package com.facebook.posts.payload

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.sql.Timestamp
import java.util.*

data class User(
    val id : UUID,
    val firstName: String,
    val lastName: String,
    val email : String,
    val birthdate : Date,
    val registrationDate : Timestamp,
    )
