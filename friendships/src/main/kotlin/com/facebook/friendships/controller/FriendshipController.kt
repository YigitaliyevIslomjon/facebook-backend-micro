package com.facebook.friendships.controller

import com.facebook.friendships.entity.Friendship
import com.facebook.friendships.payload.FullResponse
import com.facebook.friendships.payload.FriendshipDto
import com.facebook.friendships.service.FriendshipService
import com.facebook.friendships.constants.AppConstants
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import com.facebook.friendships.payload.Result
import java.util.UUID

@RestController
@RequestMapping("/api/v1/friendship")
class FriendshipController(val friendshipService: FriendshipService)     {

    @PostMapping("/add")
    fun add(@Validated @RequestBody dto: FriendshipDto): ResponseEntity<Result> {

        val result = friendshipService.add(dto)
        return ResponseEntity.ok(result)
    }

    @PutMapping("/{id}")
    fun edit(@PathVariable id: UUID, @RequestBody dto: FriendshipDto): ResponseEntity<Result> {
        val result = friendshipService.edit(id = id,dto)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: UUID): ResponseEntity<FullResponse> {
        val result = friendshipService.getOne(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/pageable")
    fun getAll(
        @RequestParam(value = "pageNo", defaultValue= AppConstants.DEFAULT_PAGE_NUMBER, required = false) pageNo : Int,
        @RequestParam(value = "pageSize", defaultValue= AppConstants.DEFAULT_PAGE_SIZE, required = false) pageSize : Int,
        @RequestParam(value = "sortBy", defaultValue= AppConstants.DEFAULT_SORT_BY, required = false) sortBy : String,
        @RequestParam(value = "sortDir", defaultValue= AppConstants.DEFAULT_SORT_DIRECTION, required = false) sortDir : String
    ): ResponseEntity<List<Friendship>> {
        val result = friendshipService.getAll(pageNo, pageSize, sortBy, sortDir)
        return ResponseEntity.ok(result)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Result> {
        val result = friendshipService.delete(id = id)
        return ResponseEntity.ok(result)
    }

}