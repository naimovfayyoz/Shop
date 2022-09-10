package uz.fayyoz.a1shop.data.repository.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.fayyoz.a1shop.model.User
import uz.fayyoz.a1shop.network.ShopService

class SignUpRepoImpl(private val shopService: ShopService) : SignUpRepository {

    private val _userData: MutableLiveData<User> = MutableLiveData()
    private val userData: LiveData<User> get() = _userData

    override suspend fun register(name: String, email: String, password: String, image: String) =
        shopService.register(name, email, password, image)
}
