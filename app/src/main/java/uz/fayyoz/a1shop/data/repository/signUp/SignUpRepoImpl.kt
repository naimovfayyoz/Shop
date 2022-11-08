package uz.fayyoz.a1shop.data.repository.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.fayyoz.a1shop.data.local.db.UserDao
import uz.fayyoz.a1shop.model.User
import uz.fayyoz.a1shop.network.LoginService

class SignUpRepoImpl(private val loginService: LoginService, provideUserDao: UserDao) :
    SignUpRepository {

    private val _userData: MutableLiveData<User> = MutableLiveData()
    private val userData: LiveData<User> get() = _userData

    override suspend fun register(name: String, email: String, password: String, image: String) =
        withContext(Dispatchers.IO) {
            loginService.register(name, email, password, image)
        }
}
