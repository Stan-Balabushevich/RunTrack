package id.slava.nt.wear.run.presentation

import id.slava.nt.core.presentation.ui.UiText


sealed interface TrackerEvent {
    data object RunFinished: TrackerEvent
    data class Error(val message: UiText): TrackerEvent
}