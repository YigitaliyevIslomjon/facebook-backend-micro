package com.facebook.likes.controller

import com.facebook.likes.constants.AppConstants
import com.facebook.likes.entity.Like
import com.facebook.likes.payload.FullResponse
import com.facebook.likes.payload.LikeDto
import com.facebook.likes.service.LikeService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import com.facebook.likes.payload.Result
import java.util.UUID

@RestController
@RequestMapping("/api/v1/like")
class LikeController(val likeService: LikeService)     {

    @PostMapping("/add")
    fun add(@Validated @RequestBody dto: LikeDto): ResponseEntity<Result> {

        val result = likeService.add(dto)
        return ResponseEntity.ok(result)
    }

    @PutMapping("/{id}")
    fun edit(@PathVariable id: UUID, @RequestBody dto: LikeDto): ResponseEntity<Result> {
        val result = likeService.edit(id = id,dto)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: UUID): ResponseEntity<FullResponse> {
        val result = likeService.getOne(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/pageable")
    fun getAll(
        @RequestParam(value = "pageNo", defaultValue= AppConstants.DEFAULT_PAGE_NUMBER, required = false) pageNo : Int,
        @RequestParam(value = "pageSize", defaultValue= AppConstants.DEFAULT_PAGE_SIZE, required = false) pageSize : Int,
        @RequestParam(value = "sortBy", defaultValue= AppConstants.DEFAULT_SORT_BY, required = false) sortBy : String,
        @RequestParam(value = "sortDir", defaultValue= AppConstants.DEFAULT_SORT_DIRECTION, required = false) sortDir : String
    ): ResponseEntity<List<Like>> {
        val result = likeService.getAll(pageNo, pageSize, sortBy, sortDir)
        return ResponseEntity.ok(result)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Result> {
        val result = likeService.delete(id = id)
        return ResponseEntity.ok(result)
    }

}