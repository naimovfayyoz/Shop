package uz.fayyoz.a1shop.data.repository.login

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import uz.fayyoz.a1shop.data.local.db.UserDao
import uz.fayyoz.a1shop.data.local.pref.UserPref
import uz.fayyoz.a1shop.model.User
import uz.fayyoz.a1shop.network.LoginService
import uz.fayyoz.a1shop.utill.isNull

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

    override suspend fun insertUser(token: String?) = withContext(Dispatchers.IO) {
        userDao.insertUserData(loginService.getUserData("Bearer " + token).body()!!)

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

