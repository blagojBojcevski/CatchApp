package com.test.testapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testapplication.data.model.User
import com.test.testapplication.data.persistence.UserDao
import kotlinx.coroutines.launch
import com.test.testapplication.data.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val userDao: UserDao
): ViewModel() {
    private val userLiveData = MutableLiveData<User>()
    private val isAuthenticatedLiveData = MutableLiveData<Boolean>()


    fun login(email: String, password: String) {
        viewModelScope.launch {
         val login =   repository.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            )
            when (login.isSuccessful){
                true -> {
                    with(login.body()){
                        this?.user?.token = login.body()?.token
                        isAuthenticatedLiveData.postValue(true)
                        login.body()?.user?.let { userDao.insertUser(it) }
                        userLiveData.postValue(login.body()?.user)
                    }
                }

                else -> {
                    isAuthenticatedLiveData.postValue(false)
                }
            }
        }
    }

    fun getIsAuthenticatedLiveData(): LiveData<Boolean> = isAuthenticatedLiveData

    fun setIsAuthenticatedLiveData(value: Boolean?) {
        isAuthenticatedLiveData.value = value
    }
}