package uz.fayyoz.a1shop.data.repository.login

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


    override suspend fun login(email: String, password: String) =
        loginService.login(email, password)

    override fun getAccessTokens(): Flow<String?> {
        return preferences.accessToken
    }

    override suspend fun clearAccessToken() {
        preferences.clearToken()
    }

    override suspend fun insertUser(token: String?) {
        if (!token.isNull())
            userDao.insertUserData(loginService.getUserData("Bearer " + token).body()!!)
    }

    override suspend fun deleteUser() {
        userDao.deleteUser()
    }

    override suspend fun saveAccessToken(accessToken: String?) {
        if (accessToken != null) {
            preferences.saveAccessTokens(accessToken)
        }
    }

    override suspend fun getUserData(token: String): User = withContext(Dispatchers.IO) {
        coroutineScope {
            return@coroutineScope if (loginService.getUserData(token).isSuccessful) {
                userDao.insertUserData(loginService.getUserData("Bearer " + token).body()!!)
                userDao.getUser()
            } else {
                val refreshToken =
                    loginService.login(userDao.getUser().email, userDao.getUser().password).body()
                userDao.insertUserData(loginService.getUserData("Bearer " + refreshToken!!.access_token)
                    .body()!!)
                userDao.getUser()
            }
        }

    }

}