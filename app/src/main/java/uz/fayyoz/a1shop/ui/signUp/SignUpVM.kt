package uz.fayyoz.a1shop.ui.signUp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.fayyoz.a1shop.domain.SignUpUseCase
import uz.fayyoz.a1shop.model.User
import uz.fayyoz.a1shop.ui.BaseViewModel


class SignUpVM(private val useCase: SignUpUseCase) : BaseViewModel() {

    private val _userData: MutableLiveData<User> = MutableLiveData()
    private val userData: LiveData<User> get() = _userData


    fun signUp(name: String, email: String, password: String): LiveData<User> {
        viewModelScope.launch(exceptionHandler) {
            val signUpResponse = useCase.execute(name,
                email,
                password,
                "https://api.lorem.space/image/face?w=640&h=480")
            if (signUpResponse.isSuccessful) {
                _userData.postValue(signUpResponse.body())
                Log.d("TAG525", "signup:ssss " + signUpResponse.body())
            }
        }
        return userData
    }
}