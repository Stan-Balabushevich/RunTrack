package id.slava.nt.run.presentation.run_overview

sealed interface RunOverviewAction {
    data object OnStartClick: RunOverviewAction
    data object OnLogoutClick: RunOverviewAction
    data object OnAnalyticsClick: RunOverviewAction
//    data class DeleteRun(val runUi: RunUi): RunOverviewAction
}