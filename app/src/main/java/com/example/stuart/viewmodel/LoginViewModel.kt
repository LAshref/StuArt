package com.example.stuart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stuart.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
):
    ViewModel() {
        private val _login = MutableSharedFlow<Resource<FirebaseUser>>()
        val login = _login.asSharedFlow()
        private val _resetPassword = MutableSharedFlow<Resource<String>>()
        val resetPassword = _resetPassword.asSharedFlow()
    fun login(email: String, password: String){
        viewModelScope.launch { _login.emit(Resource.Loading()) }
        firebaseAuth.signInWithEmailAndPassword(
            email, password).addOnSuccessListener {
                viewModelScope.launch {
                    it.user?.let {
                        _login.emit(Resource.Success(it))
                    }
                }
        }.addOnFailureListener{
            viewModelScope.launch {
                _login.emit(Resource.Error(it.message.toString()))
            }
        }
    }
    fun resetPassword(email: String){
        viewModelScope.launch {
            _resetPassword.emit(Resource.Loading())
            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnSuccessListener{
                    viewModelScope.launch {
                        _resetPassword.emit(Resource.Success(email))
                    }
                }
                .addOnFailureListener {
                    viewModelScope.launch {
                        _resetPassword.emit(Resource.Error(it.message.toString()))
                    }
                }
        }
    }
}


    /* 

ViewModel Scope:
        The LoginViewModel utilizes the viewModelScope provided by the androidx.lifecycle library. 
        This scope is used to launch coroutines that are tied to the lifecycle of the ViewModel. 
        It ensures that coroutines are cancelled when the ViewModel is no longer in use, preventing potential memory leaks.

Dependency Injection:
        The LoginViewModel uses constructor injection with the @Inject annotation to receive an instance of FirebaseAuth. 
        This is made possible by the integration of Hilt, the dependency injection library for Android, as indicated by the @HiltViewModel annotation.

State Management:
        The ViewModel uses MutableSharedFlow to manage the state of the login process. 
        It emits instances of Resource to represent different states, such as loading, success, and error. The login and resetPassword properties expose these states
        as shared flows, allowing other components to observe and react to state changes.

Login Functionality:
        The login function initiates the login process by calling firebaseAuth.signInWithEmailAndPassword with the provided email and password. 
        It emits a loading state before making the authentication request and then emits success or error states based on the outcome of the authentication operation.

Password Reset Functionality:
        The resetPassword function handles the password reset process by calling FirebaseAuth.getInstance().sendPasswordResetEmail with the provided email. 
        Similar to the login function, it emits loading, success, or error states based on the outcome of the password reset operation.
        
Coroutines and Flow:
        The use of coroutines and flows allows for asynchronous and non-blocking handling of the login and password reset operations. 
        This ensures that the UI remains responsive while these operations are in progress.


    */
