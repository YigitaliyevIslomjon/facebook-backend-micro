package com.facebook.posts.payload


import jakarta.validation.constraints.NotBlank
import java.util.*

data class FullPostResponse(

    val user : User,
    val content: String,
    val postDate: Date,
    )
