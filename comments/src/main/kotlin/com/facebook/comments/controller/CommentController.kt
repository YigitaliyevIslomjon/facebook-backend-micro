package com.facebook.comments.controller
import com.facebook.comments.entity.Comment
import com.facebook.comments.payload.FullResponse
import com.facebook.comments.payload.CommentDto
import com.facebook.comments.service.CommentService
import com.facebook.comments.constants.AppConstants
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import com.facebook.comments.payload.Result
import java.util.UUID

@RestController
@RequestMapping("/api/v1/comment")
class CommentController(val commentService: CommentService)     {

    @PostMapping("/add")
    fun add(@Validated @RequestBody dto: CommentDto): ResponseEntity<Result> {

        val result = commentService.add(dto)
        return ResponseEntity.ok(result)
    }

    @PutMapping("/{id}")
    fun edit(@PathVariable id: UUID, @RequestBody dto: CommentDto): ResponseEntity<Result> {
        val result = commentService.edit(id = id,dto)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: UUID): ResponseEntity<FullResponse> {
        val result = commentService.getOne(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/pageable")
    fun getAll(
        @RequestParam(value = "pageNo", defaultValue= AppConstants.DEFAULT_PAGE_NUMBER, required = false) pageNo : Int,
        @RequestParam(value = "pageSize", defaultValue= AppConstants.DEFAULT_PAGE_SIZE, required = false) pageSize : Int,
        @RequestParam(value = "sortBy", defaultValue= AppConstants.DEFAULT_SORT_BY, required = false) sortBy : String,
        @RequestParam(value = "sortDir", defaultValue= AppConstants.DEFAULT_SORT_DIRECTION, required = false) sortDir : String
    ): ResponseEntity<List<Comment>> {
        val result = commentService.getAll(pageNo, pageSize, sortBy, sortDir)
        return ResponseEntity.ok(result)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Result> {
        val result = commentService.delete(id = id)
        return ResponseEntity.ok(result)
    }

}