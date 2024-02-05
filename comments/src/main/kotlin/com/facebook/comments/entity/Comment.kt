package com.facebook.comments.entity

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.sql.Timestamp
import java.util.*

@Entity
data class Comment(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    val id: UUID? = null,

    @Column(nullable = false)
    val userId : UUID,

    @Column(nullable = false)
    val postId : UUID,

    @Column(nullable = false)
    val contentText: String,

    @Column(nullable = false)
    val createdDate: Timestamp = Timestamp(System.currentTimeMillis()),
)