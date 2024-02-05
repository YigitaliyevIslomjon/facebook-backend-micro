package com.facebook.comments.service.impl

import com.facebook.comments.entity.Comment
import com.facebook.comments.feignclients.PostService
import com.facebook.comments.feignclients.UserService
import com.facebook.comments.payload.*
import com.facebook.comments.repository.CommentRepository
import com.facebook.comments.service.CommentService
import feign.FeignException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class CommentServiceImpl(
    private  val commentRepository: CommentRepository,
    private  val userService: UserService,
    private  val postService: PostService
) : CommentService {
    override fun add(dto: CommentDto): Result {
        try {
            val user: User = userService.getOne(dto.userId)
            val post: Post = postService.getOne(dto.postId)
            commentRepository.save(
                Comment(userId = user.id, postId = post.id, contentText = dto.contentText)
            )

            return Result(message = "data are saved successfully")

        } catch (e: FeignException.NotFound) {
            throw IllegalArgumentException(e.message)
        } catch (e: RuntimeException) {
            throw IllegalArgumentException(e.message)
        }
    }

    override fun getAll(pageNo: Int, pageSize:Int, sortBy:String, sortDir:String): List<Comment> {
        val sort  = if(sortDir.equals(Sort.Direction.ASC.name, ignoreCase = true)){
            Sort.by(sortBy).ascending()
        }else{
            Sort.by(sortBy).descending()
        }
        val pageable = PageRequest.of(pageNo,pageSize,sort)
        val existingComment = commentRepository.findAll(pageable)
        if(existingComment.hasContent()){
            return existingComment.content.toList()
        }
        return emptyList()
    }

        override fun getOne(id: UUID): FullResponse {
            try {
                val existingComment = commentRepository.findById(id)
                val user = userService.getOne(existingComment.get().userId)
                val post: Post = postService.getOne(existingComment.get().postId)

                if(existingComment.isEmpty){
                    throw IllegalArgumentException("id is not found")
                }
                return FullResponse(user= user, post = post, contentText = existingComment.get().contentText, createdDate = existingComment.get().createdDate)
            } catch (e: FeignException.NotFound) {
                throw IllegalArgumentException(e.message)
            }
        }

    override fun edit(id: UUID, dto:CommentDto): Result {
        try {
            val existingComment = commentRepository.findById(id)
            if(existingComment.isEmpty){
                throw NoSuchElementException("id of comment is not exist")
            }

            val user = userService.getOne(existingComment.get().userId)
            val post: Post = postService.getOne(existingComment.get().postId)

            val updateComment = existingComment.get()
                .copy(userId = user.id, postId = post.id, contentText = dto.contentText)

            commentRepository.save(updateComment)
            return Result(message = "Data are edited", success = true)
        } catch (e: FeignException.NotFound) {
            throw IllegalArgumentException(e.message)
        }
    }

    override fun delete(id: UUID): Result {
        val existingComment = commentRepository.findById(id)
        if(existingComment.isEmpty){
            throw NoSuchElementException("id of comment is not exist")
        }

        commentRepository.deleteById(id)
        return  Result(message = "Data are deleted", success = true)
    }
}