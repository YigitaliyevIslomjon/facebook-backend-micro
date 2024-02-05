package com.facebook.likes.payload


import jakarta.validation.constraints.NotBlank
import java.util.*

data class LikeDto(

    @NotBlank()
    val userId : UUID,

    @NotBlank()
    val postId : UUID,
 )
