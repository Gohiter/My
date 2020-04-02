package com.co.my.data.repositories

import com.co.my.data.db.AppDatabase
import com.co.my.data.db.entities.User
import com.co.my.data.network.MiApi
import com.co.my.data.network.SafeApiRequest
import com.co.my.data.network.responses.AuthResponse

class UserRepository(
    private val api: MiApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String) : AuthResponse{
        return apiRequest{ api.userLogin(email,password)}
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()
}