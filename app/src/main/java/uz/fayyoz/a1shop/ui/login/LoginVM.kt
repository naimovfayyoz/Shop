package uz.fayyoz.a1shop.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import uz.fayyoz.a1shop.domain.login.*
import uz.fayyoz.a1shop.model.Token
import uz.fayyoz.a1shop.model.User
import uz.fayyoz.a1shop.ui.BaseViewModel

class LoginVM(
    private val loginUseCase: LoginUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val clearTokenUseCase: ClearAccessTokenUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,

    ) : BaseViewModel() {
    private val _loginToken: MutableLiveData<Token> = MutableLiveData()
    private val loginToken: LiveData<Token> get() = _loginToken

    suspend fun login(email: String, password: String): LiveData<Token> {
        viewModelScope.launch(exceptionHandler) {
            val loginResponse = loginUseCase.execute(email, password)
            if (loginResponse.isSuccessful) {
                _loginToken.postValue(loginResponse.body())
            }
        }
        return loginToken
    }

    suspend fun saveAccessTokens( accessToken: String?) {
        saveAccessTokenUseCase.execute(accessToken)
    }

    suspend fun refreshToken(token: String?) {
        clearTokenUseCase.execute()
        saveAccessTokenUseCase.execute(token)
    }

    suspend fun clearAccessToken() {
        clearTokenUseCase.execute()
    }

    fun getAccessTokens(): Flow<String?> {
        return getAccessTokenUseCase.execute()
    }

    suspend fun getUserData(token: String): User {
        return getUserDataUseCase.execute(token)
    }
}
