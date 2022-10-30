package uz.fayyoz.a1shop.data.repository.login

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.fayyoz.a1shop.model.Token
import uz.fayyoz.a1shop.model.User

interface LoginRepository {

    suspend fun login(email: String, password: String): Response<Token>

    fun getAccessTokens(): Flow<String?>

    suspend fun clearAccessToken()

    suspend fun insertUser(token: String?)

    suspend fun deleteUser()

    suspend fun saveAccessToken(accessToken: String?)

    suspend fun getUserData(token: String): User
}