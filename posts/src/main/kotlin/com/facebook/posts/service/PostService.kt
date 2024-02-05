package com.facebook.posts.service

import com.facebook.posts.entity.Post
import com.facebook.posts.payload.FullPostResponse
import com.facebook.posts.payload.PostDto
import com.facebook.posts.payload.Result
import java.util.UUID

interface PostService {

    fun add(dto: PostDto) : Result
    fun edit(id: UUID, dto: PostDto) : Result
    fun delete(id: UUID): Result
    fun getOne(id: UUID) : Post
    fun getAll(pageNo: Int, pageSize:Int, sortBy:String, sortDir:String): List< Post>




}