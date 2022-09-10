package uz.fayyoz.a1shop.network

import android.provider.ContactsContract
import retrofit2.Response
import retrofit2.http.*
import uz.fayyoz.a1shop.model.Products
import uz.fayyoz.a1shop.model.Token
import uz.fayyoz.a1shop.model.User
import uz.fayyoz.a1shop.utill.Resource

interface ShopService {

    @GET("products")
    suspend fun getAllProducts(): List<Products>

    @GET("categories/{categoryID}/products")
    suspend fun getByCategory(@Path("categoryID") id: Int): List<Products>

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


//    @GET("categories/{categoryID}/products")
//    suspend fun getBy(@Path("categdoryID") id: Int): Flow<PagingData<Products>>


    //@GET("categories")
//    suspend fun getByCategory()
}