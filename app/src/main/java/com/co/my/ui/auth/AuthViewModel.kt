package com.co.my.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.co.my.data.repositories.UserRepository
import com.co.my.util.ApiException
import com.co.my.util.Coroutines
import com.co.my.util.NoInternetExeption

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel(){

    var email: String? = null
    var password: String? = null

    var authListener:AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonOnclick(view: View){

        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){

            authListener?.onFailure("Email o password invalidado")
            return
        }


        Coroutines.main {

            try {
                val autResponse = repository.userLogin(email!!, password!!)

                autResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(autResponse.message!!)
            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e: NoInternetExeption){
                authListener?.onFailure(e.message!!)
            }
        }
    }
}