package uz.fayyoz.a1shop.network

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.*
import uz.fayyoz.a1shop.data.local.pref.UserPref
import uz.fayyoz.a1shop.model.Token
import uz.fayyoz.a1shop.model.User

interface LoginService {

    @POST("auth/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<Token>

    @POST("users")
    @FormUrlEncoded
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("avatar") avatar: String,
    ): Response<User>


    @GET("auth/profile")
    suspend fun getUserData(@Header("Authorization") token: String): Response<User>

}