package id.slava.nt.auth.presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.slava.nt.auth.domain.AuthRepository
import id.slava.nt.auth.domain.UserDataValidator
import id.slava.nt.auth.presentation.R
import id.slava.nt.core.domain.util.DataError
import id.slava.nt.core.domain.util.Result
import id.slava.nt.core.presentation.ui.UiText
import id.slava.nt.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userDataValidator: UserDataValidator
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val _stateFlow = MutableStateFlow(LoginState())
    val stateFlow: StateFlow<LoginState> = _stateFlow

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    init {


        // Can not use  state flow because of compose  TextFieldState works with snapshotFlow, we need to use compose state
        //The TextFieldState class integrates tightly with Compose's snapshot system, which explains why it works well with snapshotFlow.
        // To make StateFlow work effectively in your use case, you need to explicitly propagate changes or combine it with snapshotFlow.

        // Combine the email and password values from the stateFlow
//        combine(
//            _stateFlow.map { it.email.text }, // Map stateFlow to emit the current email text as flow
//            _stateFlow.map { it.password.text } // Map stateFlow to emit the current password text as flow
//        ) { email, password ->
//            _stateFlow.value = _stateFlow.value.copy(
//                canLogin = userDataValidator.isValidEmail(
//                    email.toString().trim()
//                ) && password.isNotEmpty()
//            )
//
//            Log.d("LoginViewModel", "canLogin: ${_stateFlow.value.canLogin}")
//        }.launchIn(viewModelScope)


        combine(

            snapshotFlow { state.email.text },
            snapshotFlow { state.password.text }

        ) { email, password ->
            state = state.copy(
                canLogin = userDataValidator.isValidEmail(
                    email = email.toString().trim()
                ) && password.isNotEmpty()
            )

            Log.d("LoginViewModel", "canLogin: ${_stateFlow.value.canLogin}")
        }.launchIn(viewModelScope)


}

//    fun onAction(action: LoginAction) {
//        when(action) {
//            LoginAction.OnLoginClick -> login()
//            LoginAction.OnTogglePasswordVisibility -> {
//                _stateFlow.value = _stateFlow.value.copy(
//                    isPasswordVisible = !_stateFlow.value.isPasswordVisible
//                )
//            }
//            else -> Unit
//        }
//    }
//
//        private fun login() {
//        viewModelScope.launch {
//            _stateFlow.value = _stateFlow.value.copy(isLoggingIn = true)
//            val result = authRepository.login(
//                email = _stateFlow.value.email.text.toString().trim(),
//                password = _stateFlow.value.password.text.toString()
//            )
//            _stateFlow.value = _stateFlow.value.copy(isLoggingIn = false)
//
//            when(result) {
//                is Result.Error -> {
//                    if(result.error == DataError.Network.UNAUTHORIZED) {
//                        eventChannel.send(
//                            LoginEvent.Error(
//                            UiText.StringResource(R.string.error_email_password_incorrect)
//                        ))
//                    } else {
//                        eventChannel.send(LoginEvent.Error(result.error.asUiText()))
//                    }
//                }
//                is Result.Success -> {
//                    eventChannel.send(LoginEvent.LoginSuccess)
//                }
//            }
//        }
//    }


fun onAction(action: LoginAction) {
        when(action) {
            LoginAction.OnLoginClick -> login()
            LoginAction.OnTogglePasswordVisibility -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }
            else -> Unit
        }
    }


    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)
            val result = authRepository.login(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString()
            )
            state = state.copy(isLoggingIn = false)

            when(result) {
                is Result.Error -> {
                    if(result.error == DataError.Network.UNAUTHORIZED) {
                        eventChannel.send(
                            LoginEvent.Error(
                            UiText.StringResource(R.string.error_email_password_incorrect)
                        ))
                    } else {
                        eventChannel.send(LoginEvent.Error(result.error.asUiText()))
                    }
                }
                is Result.Success -> {
                    eventChannel.send(LoginEvent.LoginSuccess)
                }
            }
        }
    }

}