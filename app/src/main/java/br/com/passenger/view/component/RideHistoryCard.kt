package br.com.passenger.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.passenger.model.RideHistory
import java.util.Date

@Composable
fun RideHistoryCard(
    modifier: Modifier = Modifier,
    ride: RideHistory,
) {
    PrimaryCard {
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = ride.driver.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = ride.date,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Origem: ${ride.origin}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Destino: ${ride.destination}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Distância: ${ride.distance} km",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Duração: ${ride.duration}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Valor: R$ ${ride.value}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RideHistoryCardPreview() {
    RideHistoryCard(
        ride =
            RideHistory(
                date = Date().toString(),
                destination = "destination",
                distance = 10.0,
                driver =
                    RideHistory.Driver(
                        id = 1,
                        name = "Carlos",
                    ),
                duration = "duration",
                id = 1,
                origin = "origin",
                value = 10.0,
            ),
    )
}
