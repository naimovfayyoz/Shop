package uz.fayyoz.a1shop.data.repository.login

import retrofit2.Response
import uz.fayyoz.a1shop.model.Token

interface LoginRepository {

    suspend fun login(email: String, password: String): Response<Token>

    suspend fun saveAccessTokens(accessToken: String)
}