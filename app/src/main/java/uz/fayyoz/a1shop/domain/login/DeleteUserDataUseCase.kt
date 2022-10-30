package uz.fayyoz.a1shop.domain.login

import uz.fayyoz.a1shop.data.repository.login.LoginRepository

class DeleteUserDataUseCase (private val repository: LoginRepository) {

    suspend fun execute() = repository.deleteUser()

}