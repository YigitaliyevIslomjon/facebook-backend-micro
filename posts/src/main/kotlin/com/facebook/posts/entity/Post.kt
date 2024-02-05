package com.facebook.posts.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.sql.Timestamp
import java.util.*

@Entity
data class Post(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    val id: UUID? = null,

    @Column(nullable = false)
    val userId : UUID? = null,

    @Column(nullable = false)
    val content: String? = null,

    @Column(nullable = false)
    val postDate: Timestamp = Timestamp(System.currentTimeMillis()),
)