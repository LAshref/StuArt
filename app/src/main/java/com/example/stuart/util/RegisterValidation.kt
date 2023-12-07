package com.example.stuart.util

import android.provider.ContactsContract.CommonDataKinds.Email

sealed class RegisterValidation(){
    object Success: RegisterValidation()
    data class Failed(val message: String): RegisterValidation()
}
data class RegisterFieldsState(
    val email: RegisterValidation,
    val password: RegisterValidation
)