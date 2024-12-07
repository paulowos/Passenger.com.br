package br.com.passenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.com.passenger.view.component.Header
import br.com.passenger.view.route.NewRideScreenRoute
import br.com.passenger.view.route.RideOptionsScreenRoute
import br.com.passenger.view.screen.NewRideScreen
import br.com.passenger.view.screen.RideOptionsScreen
import br.com.passenger.view.theme.PassengercombrTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PassengercombrTheme {
                val navController = rememberNavController()
                Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    Header()
                    Box(
                        modifier =
                            Modifier
                                .safeDrawingPadding()
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                    ) {
                        NavHost(navController = navController, startDestination = NewRideScreenRoute) {
                            composable<NewRideScreenRoute> {
                                NewRideScreen(navController)
                            }
                            composable<RideOptionsScreenRoute> {
                                val args = it.toRoute<RideOptionsScreenRoute>()
                                RideOptionsScreen(
                                    passengerId = args.passengerId,
                                    origin = args.origin,
                                    destination = args.destination,
                                    nav = navController,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
