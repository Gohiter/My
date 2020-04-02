package com.co.my.ui.auth

import androidx.lifecycle.LiveData
import com.co.my.data.db.entities.User

interface AuthListener {

    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)

}