package com.example.stuart.viewmodel

import androidx.lifecycle.ViewModel
import com.example.stuart.data.User
import com.example.stuart.util.Constants.USER_COLLECTION
import com.example.stuart.util.RegisterFieldsState
import com.example.stuart.util.RegisterValidation
import com.example.stuart.util.Resource
import com.example.stuart.util.validateEmail
import com.example.stuart.util.validatePassword
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val db: FirebaseFirestore
): ViewModel() {

    private val _register= MutableStateFlow<Resource<User>>(Resource.Unspecified())
    val register: Flow<Resource<User>> = _register
    private val _validation = Channel<RegisterFieldsState>()
    val validation = _validation.receiveAsFlow()
    fun createAccountWithEmailAndPassword(user: User, password: String){
        if (checkValidation(user, password)) {

        runBlocking {
            _register.emit(Resource.Loading())
        }
        firebaseAuth.createUserWithEmailAndPassword(user.email, password)
            .addOnSuccessListener{
                it.user?.let {
                saveUserInfo(it.uid,user)
                }
            }.addOnFailureListener{
                _register.value = Resource.Error(it.message.toString())
            }
        }
        else{
            val registerFieldsState =   RegisterFieldsState(
                validateEmail(user.email),validatePassword(password)
            )
            runBlocking {
                _validation.send(registerFieldsState)
            }
        }
    }

    private fun saveUserInfo(userUid: String, user: User) {
        db.collection(USER_COLLECTION)
            .document(userUid)
            .set(user)
            .addOnSuccessListener {
                _register.value = Resource.Success(user)
            }
            .addOnFailureListener {
                _register.value = Resource.Error(it.message.toString())
            }
    }

    private fun checkValidation(user: User, password: String): Boolean {
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)
        val shouldRegister =
            emailValidation is RegisterValidation.Success && passwordValidation is RegisterValidation.Success

        return shouldRegister
    }
}


/* 

##Dependencies Injection:
        The ViewModel is annotated with @HiltViewModel to indicate that it's a ViewModel component that should be provided by Hilt, which is a dependency injection library for Android. 
        The ViewModel FirebaseAuth and FirebaseFirestore instances, which are used for user authentication and data storage.

## State Management:

     The ViewModel uses MutableStateFlow to manage the state of the registration process. 
    It emits different states such as Resource.Loading(), Resource.Success(user), and Resource.Error(errorMessage) to represent the loading state, 
    successful registration, and error conditions, respectively.       

    **MutableStateFlow**:

            MutableStateFlow is a part of Kotlin's coroutines library and is used to represent a state that can be observed and updated asynchronously.
            It's similar to a LiveData in Android, but it's designed to work seamlessly with coroutines and provides a flow-based API for state management.

    \**State Representation\** :
    
            In the RegisterViewModel, MutableStateFlow is used to represent the different states of the registration process.
            It emits instances of the Resource class, which is a common pattern used in Android development to represent different states of an asynchronous operation.
   
    ** Resource Class **:
            The Resource class typically contains the data related to the operation's result, such as the loading state, the success state with the user data,
            or the error state with an error message. It's a way to encapsulate the different outcomes of an asynchronous operation into a single class.

    ** Emitted States **:
            When the registration process is initiated, the MutableStateFlow emits a Resource.Loading() state to indicate that the registration is in progress. 
            Upon successful registration, it emits a Resource.Success(user) state with the user data. In case of an error during registration, it emits a Resource.
            Error(errorMessage) state with the error message.
            
    ** Observing the State **:
            Other parts of the app, such as the UI components (activities or fragments), can observe the MutableStateFlow to react to the changes 
            in the registration process. For example, the UI can display a loading indicator when the Resource.Loading() state is emitted and show a success 
            message or an error message based on the emitted state.
            
    ** Validation Flow **:
            The ViewModel uses a Channel to communicate the validation state of the registration fields. 
            It sends instances of RegisterFieldsState through the channel to represent the validation status of the email and password fields.
    
    ** createAccountWithEmailAndPassword Function **:
            This function is responsible for creating a new user account with the provided email, password, and user information. 
            It first checks the validation of the user input using the checkValidation function. If the input is valid, 
            it emits a loading state and then attempts to create the user account using Firebase Authentication. Upon success, 
            it saves the user information to Firestore. If there are any errors, it emits an error state.

    ** saveUserInfo Function:**
            This function is responsible for saving the user information to Firestore. It interacts with the FirebaseFirestore instance to add a new document 
            to the USER_COLLECTION with the user's information. It emits success or error states based on the outcome of the Firestore operation.

    ** checkValidation Function **:
            This function checks the validation status of the user's email and password. It uses the validateEmail and validatePassword functions to validate 
            the email and password input. If both validations succeed, it returns true to indicate that the registration process should proceed.

*/
