package com.facebook.users.controller
import com.facebook.users.entity.User
import com.facebook.users.payload.Result
import com.facebook.users.payload.UserDto
import com.facebook.users.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import uz.schoolapp.constants.AppConstants

@RestController
@RequestMapping("/api/v1/")
class UserController(val userService: UserService)     {

    @PostMapping("/add")
    fun add(@Validated @RequestBody dto: UserDto): ResponseEntity<Result> {

        val result = userService.add(dto)
        val httpStatus = if(result.success) HttpStatus.CREATED else HttpStatus.CONFLICT
        return ResponseEntity.status(httpStatus).body(result)
    }

    @PutMapping("/{id}")
    fun edit(@PathVariable id:Long, @RequestBody dto: UserDto): ResponseEntity<Result> {
        val result = userService.edit(id = id,dto)
        val httpStatus = if(result.success) HttpStatus.OK else HttpStatus.CONFLICT
        return ResponseEntity.status(httpStatus).body(result)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): ResponseEntity<User> {
        val result = userService.getOne(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/pageable")
    fun getAll(
        @RequestParam(value = "pageNo", defaultValue= AppConstants.DEFAULT_PAGE_NUMBER, required = false) pageNo : Int,
        @RequestParam(value = "pageSize", defaultValue= AppConstants.DEFAULT_PAGE_SIZE, required = false) pageSize : Int,
        @RequestParam(value = "sortBy", defaultValue= AppConstants.DEFAULT_SORT_BY, required = false) sortBy : String,
        @RequestParam(value = "sortDir", defaultValue= AppConstants.DEFAULT_SORT_DIRECTION, required = false) sortDir : String
    ): ResponseEntity<List<User>> {
        val result = userService.getAll(pageNo, pageSize, sortBy, sortDir)
        return ResponseEntity.ok(result)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long): ResponseEntity<Result> {
        val result = userService.delete(id = id)
        val httpStatus = if(result.success) HttpStatus.OK else HttpStatus.CONFLICT
        return ResponseEntity.status(httpStatus).body(result)
    }

}