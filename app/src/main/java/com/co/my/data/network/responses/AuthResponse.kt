package com.co.my.data.network.responses

import com.co.my.data.db.entities.User

data class AuthResponse (
    val isSuccessful : Boolean?,
    val message : String?,
    val user : User?
)