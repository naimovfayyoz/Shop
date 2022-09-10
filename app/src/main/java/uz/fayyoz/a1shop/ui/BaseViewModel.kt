package uz.fayyoz.a1shop.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import uz.fayyoz.a1shop.utill.log

abstract class BaseViewModel : ViewModel() {

    private val _exception = MutableSharedFlow<String>()
    val exception = _exception.asSharedFlow()

    protected val exceptionHandler = CoroutineExceptionHandler { context, error ->
        log("err ${error.message.toString()}")
        viewModelScope.launch() {
            _exception.emit(error.message.toString())
        }
    }
}