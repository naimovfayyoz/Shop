package uz.fayyoz.a1shop.domain.login

import uz.fayyoz.a1shop.data.repository.login.LoginRepository

class InsertUserDataUseCase (private val repository: LoginRepository) {

    suspend fun execute(token:String?) = repository.insertUser(token)

}