package uz.fayyoz.a1shop.domain.login

import retrofit2.Response
import uz.fayyoz.a1shop.data.repository.login.LoginRepository
import uz.fayyoz.a1shop.model.User

class GetUserDataUseCase(private val repository: LoginRepository) {

    suspend fun execute(token:String): User = repository.getUserData(token)

}