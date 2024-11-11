package id.slava.nt.auth.presentation.login

import id.slava.nt.core.presentation.ui.UiText


sealed interface LoginEvent {
    data class Error(val error: UiText): LoginEvent
    data object LoginSuccess: LoginEvent
}