package com.facebook.posts.payload


import jakarta.validation.constraints.NotBlank
import java.sql.Timestamp
import java.util.*

data class PostDto(
    @NotBlank()
    val userId : UUID,

    @NotBlank()
    val content: String,

    @NotBlank()
    val postDate: Timestamp,
    )
