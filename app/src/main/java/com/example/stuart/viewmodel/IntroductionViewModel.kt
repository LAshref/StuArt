package com.example.stuart.viewmodel

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stuart.R
import com.example.stuart.util.Constants.INTRODUCTION_KEY
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class IntroductionViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _navigate = MutableStateFlow(0)
    val navigate : StateFlow<Int> = _navigate

    companion object{

        const val SHOPPING_ACTIVITY =23  // anynumber just to track it

        val ACCOUNT_OPTIONS_FRAGMENT = R.id.action_introductionFragment_to_accountOptionsFragment

    }
    init{
        val isButtonClicked = sharedPreferences.getBoolean(INTRODUCTION_KEY,false)
        val user =firebaseAuth.currentUser

        if ( user != null){
        /* when we have a user logged in ==> we navigate to the shopping Activities */
            viewModelScope.launch {
                _navigate.emit(SHOPPING_ACTIVITY)
            }
        }else if (isButtonClicked){
            /* navigate to the account options fragment */
            viewModelScope.launch {
                _navigate.emit(ACCOUNT_OPTIONS_FRAGMENT)
            }
        }else{
            /* we wont do anything */
            Unit
        }
    }
     fun startButtonClicked(){
         sharedPreferences.edit().putBoolean(INTRODUCTION_KEY,true).apply()
     }
}

/*
Package and Imports:
    The code is organized in the "com.example.stuart.viewmodel" package, and it includes necessary imports for classes and libraries used in the ViewModel.
Class Declaration:
    The "IntroductionViewModel" class is declared, extending the "ViewModel" class provided by the Android Architecture Components.
Constructor:
    The class has a constructor that takes two dependencies: "SharedPreferences" and "FirebaseAuth". These dependencies are injected into the ViewModel using the "@Inject" annotation, which is commonly used in dependency injection frameworks like Dagger or Hilt.
State Management:
    The ViewModel uses a "MutableStateFlow" to emit navigation events. This allows the ViewModel to communicate changes in the navigation state to the UI components. The "StateFlow" is then exposed as a read-only property to the UI.
Constants:
    The class includes some constants to represent different activities and fragments. This is a good practice for maintaining a centralized place for such identifiers.
Initialization:
    In the "init" block, the ViewModel checks if a user is logged in and if a specific button is clicked. Depending on these conditions, it emits navigation events using coroutines. This logic is executed when the ViewModel is created.
startButtonClicked Function:
    This function is called when the start button is clicked. It updates the SharedPreferences to indicate that the button has been clicked.

In summary, the "IntroductionViewModel" is responsible for managing the navigation logic and user preferences related to the introduction flow of the application

Now let's go to the introduction Fragment
@Anis

when logging in , use :
test@account.com
test12

once you are connected, you should when closing the app and reopen it be connected and brought directly to the shoppingPage

because of :
 if ( user != null){
        /* when we have a user logged in ==> we navigate to the shopping Activities */
            viewModelScope.launch {
                _navigate.emit(SHOPPING_ACTIVITY)
            }

 and then inside the introduction Fragment we will recieve that  and we will directly navigate and go to the 2nd main activity which is shoppingActivity
  when(it){
                    //when "it" equal to shopping activity we will navigate to shopping Activity.
                    SHOPPING_ACTIVITY ->{
                        /* code copied from LoginFragment*/
                        Intent(requireActivity(), ShoppingActivity::class.java).also { intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)}
                    }
 */
