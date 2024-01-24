package com.facebook.friendships

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FriendshipsApplication

fun main(args: Array<String>) {
	runApplication<FriendshipsApplication>(*args)
}
