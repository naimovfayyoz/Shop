package uz.fayyoz.a1shop.domain.login

import kotlinx.coroutines.flow.Flow
import uz.fayyoz.a1shop.data.repository.login.LoginRepository

class GetAccessTokenUseCase(private val repository: LoginRepository) {

     fun execute(): Flow<String?> =repository.getAccessTokens()

}