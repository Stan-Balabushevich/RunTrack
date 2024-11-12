@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package id.slava.nt.run.presentation.run_overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.slava.nt.core.presentation.designsystem.AnalyticsIcon
import id.slava.nt.core.presentation.designsystem.LogoIcon
import id.slava.nt.core.presentation.designsystem.LogoutIcon
import id.slava.nt.core.presentation.designsystem.PlrunTheme
import id.slava.nt.core.presentation.designsystem.RunIcon
import id.slava.nt.core.presentation.designsystem.components.PlrunFloatingActionButton
import id.slava.nt.core.presentation.designsystem.components.PlrunScaffold
import id.slava.nt.core.presentation.designsystem.components.PlrunToolbar
import id.slava.nt.core.presentation.designsystem.components.util.DropDownItem
import id.slava.nt.run.presentation.R

@Composable
fun RunOverviewScreenRoot(
    onStartRunClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onAnalyticsClick: () -> Unit,
//    viewModel: RunOverviewViewModel = koinViewModel(),
) {
    RunOverviewScreen(
//        state = viewModel.state,
        onAction = { action ->
            when(action) {
                RunOverviewAction.OnAnalyticsClick -> onAnalyticsClick()
                RunOverviewAction.OnStartClick -> onStartRunClick()
                RunOverviewAction.OnLogoutClick -> onLogoutClick()
                else -> Unit
            }
//            viewModel.onAction(action)
        }
    )
}

@Composable
private fun RunOverviewScreen(
//    state: RunOverviewState,
    onAction: (RunOverviewAction) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )
    PlrunScaffold(
        topAppBar = {
            PlrunToolbar(
                showBackButton = false,
                title = stringResource(id = R.string.plrun),
                scrollBehavior = scrollBehavior,
                menuItems = listOf(
                    DropDownItem(
                        icon = AnalyticsIcon,
                        title = stringResource(id = R.string.analytics)
                    ),
                    DropDownItem(
                        icon = LogoutIcon,
                        title = stringResource(id = R.string.logout)
                    ),
                ),
                onMenuItemClick = { index ->
                    when (index) {
                        0 -> onAction(RunOverviewAction.OnAnalyticsClick)
                        1 -> onAction(RunOverviewAction.OnLogoutClick)
                    }
                },
                startContent = {
                    Icon(
                        imageVector = LogoIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            PlrunFloatingActionButton(
                icon = RunIcon,
                onClick = {
                    onAction(RunOverviewAction.OnStartClick)
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(horizontal = 16.dp),
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
//            items(
//                items = state.runs,
//                key = { it.id }
//            ) {
//                RunListItem(
//                    runUi = it,
//                    onDeleteClick = {
//                        onAction(RunOverviewAction.DeleteRun(it))
//                    },
//                    modifier = Modifier
//                        .animateItemPlacement()
//                )
//            }
        }
    }
}

@Preview
@Composable
private fun RunOverviewScreenPreview() {
    PlrunTheme  {
        RunOverviewScreen(
//            state = RunOverviewState(),
            onAction = {}
        )
    }
}