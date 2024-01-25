package com.facebook.posts.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
data class Post(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    val id: UUID? = null,

    @Column(nullable = false)
    val userId : UUID? = null,

    @Column(nullable = false)
    val content: String? = null,

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    val postDate: Date? = null,
)