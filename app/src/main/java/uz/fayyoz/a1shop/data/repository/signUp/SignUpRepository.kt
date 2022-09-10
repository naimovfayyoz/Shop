package uz.fayyoz.a1shop.data.repository.signUp

import retrofit2.Response
import uz.fayyoz.a1shop.model.User

interface SignUpRepository {

    suspend fun register(name: String, email: String, password: String,image:String): Response<User>

}