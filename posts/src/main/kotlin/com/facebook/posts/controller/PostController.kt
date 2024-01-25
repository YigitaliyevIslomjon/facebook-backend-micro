package com.facebook.posts.controller
import com.facebook.posts.entity.Post
import com.facebook.posts.payload.PostDto
import com.facebook.posts.service.PostService
import com.facebook.users.constants.AppConstants
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import com.facebook.posts.payload.Result
import java.util.UUID

@RestController
@RequestMapping("/api/v1/users")
class PostController(val postService: PostService)     {

    @PostMapping("/add")
    fun add(@Validated @RequestBody dto: PostDto): ResponseEntity<Result> {

        val result = postService.add(dto)
        val httpStatus = if(result.success) HttpStatus.CREATED else HttpStatus.CONFLICT
        return ResponseEntity.status(httpStatus).body(result)
    }

    @PutMapping("/{id}")
    fun edit(@PathVariable id: UUID, @RequestBody dto: PostDto): ResponseEntity<Result> {
        val result = postService.edit(id = id,dto)
        val httpStatus = if(result.success) HttpStatus.OK else HttpStatus.CONFLICT
        return ResponseEntity.status(httpStatus).body(result)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: UUID): ResponseEntity<Post> {
        val result = postService.getOne(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/pageable")
    fun getAll(
        @RequestParam(value = "pageNo", defaultValue= AppConstants.DEFAULT_PAGE_NUMBER, required = false) pageNo : Int,
        @RequestParam(value = "pageSize", defaultValue= AppConstants.DEFAULT_PAGE_SIZE, required = false) pageSize : Int,
        @RequestParam(value = "sortBy", defaultValue= AppConstants.DEFAULT_SORT_BY, required = false) sortBy : String,
        @RequestParam(value = "sortDir", defaultValue= AppConstants.DEFAULT_SORT_DIRECTION, required = false) sortDir : String
    ): ResponseEntity<List<Post>> {
        val result = postService.getAll(pageNo, pageSize, sortBy, sortDir)
        return ResponseEntity.ok(result)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Result> {
        val result = postService.delete(id = id)
        val httpStatus = if(result.success) HttpStatus.OK else HttpStatus.CONFLICT
        return ResponseEntity.status(httpStatus).body(result)
    }

}