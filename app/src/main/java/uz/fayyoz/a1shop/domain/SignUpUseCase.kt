package uz.fayyoz.a1shop.domain

import uz.fayyoz.a1shop.data.repository.signUp.SignUpRepository

class SignUpUseCase(private val signUpRepository: SignUpRepository) {

    suspend fun execute(name: String, email: String, password: String, image: String) =
        signUpRepository.register(name, email, password, image)

}