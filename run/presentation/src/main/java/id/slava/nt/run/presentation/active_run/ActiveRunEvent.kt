package id.slava.nt.run.presentation.active_run

import id.slava.nt.core.presentation.ui.UiText


sealed interface ActiveRunEvent {
    data class Error(val error: UiText): ActiveRunEvent
    data object RunSaved: ActiveRunEvent
}