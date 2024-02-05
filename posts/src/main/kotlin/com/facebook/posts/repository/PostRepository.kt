package com.facebook.posts.repository

import com.facebook.posts.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PostRepository : JpaRepository<Post, UUID> {

}