package com.co.my.data.network

import com.co.my.data.network.responses.AuthResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MiApi {

    @FormUrlEncoded
    @POST ("login")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<AuthResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MiApi{

            val okkHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit .Builder()
                .client(okkHttpClient)
                .baseUrl("https://freyblengohitermarrugo.000webhostapp.com/loginapp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MiApi::class.java)
        }

    }
}
