package com.facebook.comments.payload


import jakarta.validation.constraints.NotBlank
import java.util.*

data class CommentDto(

    @NotBlank()
    val userId : UUID,

    @NotBlank()
    val postId : UUID,

    @NotBlank()
    val contentText: String,

 )
