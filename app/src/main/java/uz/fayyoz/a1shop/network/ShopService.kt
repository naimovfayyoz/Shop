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


}