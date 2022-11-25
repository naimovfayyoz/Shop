package uz.fayyoz.a1shop.data.repository.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import uz.fayyoz.a1shop.data.local.db.UserDao
import uz.fayyoz.a1shop.data.local.pref.UserPref
import uz.fayyoz.a1shop.model.User
import uz.fayyoz.a1shop.network.LoginService

class LoginRepoImpl(
    private val loginService: LoginService,
    private val preferences: UserPref,
    private val userDao: UserDao,
) : LoginRepository {


    override suspend fun login(email: String, password: String) = withContext(Dispatchers.IO) {
        loginService.login(email, password)
    }

    override fun getAccessTokens(): Flow<String?> {
        return preferences.accessToken
    }

    override suspend fun clearAccessToken() {
        preferences.clearToken()
    }

    //TODO is this a best practice to give a value for a field of object that is not comming from retrofit in this manner?
    override suspend fun insertUser(token: String?) = withContext(Dispatchers.IO) {
        userDao.insertUserData(loginService.getUserData("Bearer " + token).body()!!
            .copy(money = 1000.0)
        )

    }

    override suspend fun deleteUser() {
        userDao.deleteUser()
    }

    override suspend fun saveAccessToken(accessToken: String?) = withContext(Dispatchers.IO) {
        if (accessToken != null) {
            preferences.saveAccessTokens(accessToken)
        }
    }

    override suspend fun getUserData(): User = withContext(Dispatchers.IO) {
        userDao.getUser()
    }
}

