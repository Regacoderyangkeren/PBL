package com.example.pbl.data.remote.model

data class UserData(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val alias: String = "",
    val email: String = "",
    val role: String = "member",
    val status: UserStatus = UserStatus.OFFLINE
)

enum class UserStatus {
    OFFLINE,
    ONLINE,
    STANDBY
}