package com.facebook.likes.service

import com.facebook.likes.entity.Like
import com.facebook.likes.payload.FullResponse
import com.facebook.likes.payload.LikeDto
import com.facebook.likes.payload.Result
import java.util.UUID

interface LikeService {

    fun add(dto: LikeDto) : Result
    fun edit(id: UUID, dto: LikeDto) : Result
    fun delete(id: UUID): Result
    fun getOne(id: UUID) : FullResponse
    fun getAll(pageNo: Int, pageSize:Int, sortBy:String, sortDir:String): List< Like>

}