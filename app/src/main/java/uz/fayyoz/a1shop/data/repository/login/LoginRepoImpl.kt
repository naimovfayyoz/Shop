package uz.fayyoz.a1shop.data.repository.login

import uz.fayyoz.a1shop.data.local.pref.UserPref
import uz.fayyoz.a1shop.network.ShopService

class LoginRepoImpl (
    private val shopService: ShopService,
    private val preferences: UserPref,
) : LoginRepository {


    override suspend fun login(email: String, password: String) = shopService.login(email, password)

    override suspend fun saveAccessTokens(accessToken: String) {
        preferences.saveAccessTokens(accessToken)
    }

}