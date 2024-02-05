package com.facebook.friendships.service.impl

import com.facebook.friendships.entity.Friendship
import com.facebook.friendships.feignclients.UserService
import com.facebook.friendships.payload.*
import com.facebook.friendships.repository.FriendshipRepository
import com.facebook.friendships.service.FriendshipService
import feign.FeignException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.util.*

@Service
class FriendshipServiceImpl(
    private  val friendshipRepository: FriendshipRepository,
    private  val userService: UserService,
) : FriendshipService {
    override fun add(dto: FriendshipDto): Result {
        try {
            val user1: User = userService.getOne(dto.userId1)
            val user2: User = userService.getOne(dto.userId2)

            friendshipRepository.save(
                Friendship(
                    userId1 = user1.id,
                    userId2 = user2.id,
                    status = dto.status,
                    requestDate = Timestamp(System.currentTimeMillis()),
                    acceptDate = Timestamp(System.currentTimeMillis()))
            )
        } catch (e: FeignException.NotFound) {
            throw IllegalArgumentException(e.message)
        }

        return Result(message = "data are saved successfully")
    }

    override fun getAll(pageNo: Int, pageSize:Int, sortBy:String, sortDir:String): List<Friendship> {
        val sort  = if (sortDir.equals(Sort.Direction.ASC.name, ignoreCase = true)){
            Sort.by(sortBy).ascending()
        } else {
            Sort.by(sortBy).descending()
        }
        val pageable = PageRequest.of(pageNo,pageSize,sort)
        val existingComment = friendshipRepository.findAll(pageable)
        if (existingComment.hasContent()){
            return existingComment.content.toList()
        }
        return emptyList()
    }

    override fun getOne(id: UUID): FullResponse {
        try {
            val existingFriendship = friendshipRepository.findById(id)
            val user1: User = userService.getOne(existingFriendship.get().userId1)
            val user2: User = userService.getOne(existingFriendship.get().userId2)

            if (existingFriendship.isEmpty) {
                throw IllegalArgumentException("id is not found")
            }

            return FullResponse(
                user1 = user1,
                user2 = user2,
                status = existingFriendship.get().status,
                requestDate = existingFriendship.get().requestDate,
                acceptDate = existingFriendship.get().acceptDate,
            )
        } catch (e: FeignException.NotFound) {
            throw IllegalArgumentException(e.message)
        }
    }

    override fun edit(id: UUID, dto:FriendshipDto): Result {
        try {
            val existingFriendship = friendshipRepository.findById(id)
            if(existingFriendship.isEmpty){
                throw NoSuchElementException("id of comment is not exist")
            }

            val user1: User = userService.getOne(existingFriendship.get().userId1)
            val user2: User = userService.getOne(existingFriendship.get().userId2)

            val updateFriendship = existingFriendship.get()
                .copy( userId1 = user1.id,
                    userId2 = user2.id,
                    status = dto.status,
                    requestDate = Timestamp(System.currentTimeMillis()),
                    acceptDate = Timestamp(System.currentTimeMillis())
                )

            friendshipRepository.save(updateFriendship)
            return Result(message = "Data are edited", success = true)
        } catch (e: FeignException.NotFound) {
            throw IllegalArgumentException(e.message)
        }
    }

    override fun delete(id: UUID): Result {
        val existingFriendship = friendshipRepository.findById(id)
        if(existingFriendship.isEmpty){
            throw NoSuchElementException("id of comment is not exist")
        }

        friendshipRepository.deleteById(id)
        return  Result(message = "Data are deleted", success = true)
    }
}