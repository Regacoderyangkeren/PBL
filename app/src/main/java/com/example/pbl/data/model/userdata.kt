package com.example.pbl.data.model

data class userData(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val alias: String = "",
    val email: String = "",
    val role: String = "member",
    val status: userStatus = userStatus.OFFLINE
)

enum class userStatus {
    OFFLINE,
    ONLINE,
    STANDBY
}