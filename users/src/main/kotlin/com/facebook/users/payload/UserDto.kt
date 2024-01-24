package com.facebook.users.payload

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.sql.Timestamp
import java.util.*

data class UserDto(

    @NotBlank()
    val firstName: String,

    @NotBlank()
    val lastName: String,

    @Email()
    val email : String,

    @NotBlank()
    val password : String,

    @NotBlank()
    val birthdate : Date,

    @NotBlank()
    val registrationDate : Timestamp,
    )
