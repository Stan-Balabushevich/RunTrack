package id.slava.nt.run.presentation.run_overview

import id.slava.nt.run.presentation.run_overview.model.RunUi


data class RunOverviewState(
    val runs: List<RunUi> = emptyList()
)
