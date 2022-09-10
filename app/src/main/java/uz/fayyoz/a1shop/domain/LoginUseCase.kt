package uz.fayyoz.a1shop.domain

import uz.fayyoz.a1shop.data.repository.login.LoginRepository

class LoginUseCase(private val repository: LoginRepository) {

    suspend fun execute(email: String, password: String) =
        repository.login(email, password)

}