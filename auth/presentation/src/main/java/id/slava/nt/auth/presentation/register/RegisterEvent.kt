package id.slava.nt.auth.presentation.register

import id.slava.nt.core.presentation.ui.UiText


sealed interface RegisterEvent {
    data object RegistrationSuccess: RegisterEvent
    data class Error(val error: UiText): RegisterEvent
}