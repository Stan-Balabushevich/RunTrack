package id.slava.nt.analitycs.presentation

sealed interface AnalyticsAction {
    data object OnBackClick: AnalyticsAction
}