package uz.fayyoz.a1shop.domain.login

import uz.fayyoz.a1shop.data.repository.login.LoginRepository

class SaveAccessTokenUseCase(private val repository: LoginRepository) {

    suspend fun execute( accessToken: String?) {
        repository.saveAccessToken( accessToken)
    }
}