package id.slava.nt.plrun

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import id.slava.nt.core.presentation.designsystem.PlrunTheme
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel<MainViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.state.isCheckingAuth
            }
        }

        enableEdgeToEdge()
        setContent {
            PlrunTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {  innerPadding ->

                    if(!viewModel.state.isCheckingAuth) {
                        val navController = rememberNavController()
                        NavigationRoot(
                            navController = navController,
                            isLoggedIn = viewModel.state.isLoggedIn,
//                            onAnalyticsClick = {
//                                installOrStartAnalyticsFeature()
//                            }
                        )

//                        if(viewModel.state.showAnalyticsInstallDialog) {
//                            Dialog(onDismissRequest = {}) {
//                                Column(
//                                    modifier = Modifier
//                                        .clip(RoundedCornerShape(15.dp))
//                                        .background(MaterialTheme.colorScheme.surface)
//                                        .padding(16.dp),
//                                    verticalArrangement = Arrangement.Center,
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    CircularProgressIndicator()
//                                    Spacer(modifier = Modifier.height(8.dp))
//                                    Text(
//                                        text = stringResource(id = R.string.installing_module),
//                                        color = MaterialTheme.colorScheme.onSurface
//                                    )
//                                }
//                            }
//                        }
                    }

                }
            }
        }
    }
}
