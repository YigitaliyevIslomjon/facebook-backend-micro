package com.facebook.comments.service

import com.facebook.comments.entity.Comment
import com.facebook.comments.payload.FullResponse
import com.facebook.comments.payload.CommentDto
import com.facebook.comments.payload.Result
import java.util.UUID

interface CommentService {

    fun add(dto: CommentDto) : Result
    fun edit(id: UUID, dto: CommentDto) : Result
    fun delete(id: UUID): Result
    fun getOne(id: UUID) : FullResponse
    fun getAll(pageNo: Int, pageSize:Int, sortBy:String, sortDir:String): List< Comment>




}