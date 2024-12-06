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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.passenger.view.component.Header
import br.com.passenger.view.screen.NewRideScreen
import br.com.passenger.view.theme.PassengercombrTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PassengercombrTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                        Header()
                        Box(
                            modifier =
                                Modifier
                                    .padding(innerPadding)
                                    .padding(16.dp),
                        ) {
                            NewRideScreen()
                        }
                    }
                }
            }
        }
    }
}
