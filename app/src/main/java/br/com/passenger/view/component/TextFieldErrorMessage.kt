package br.com.passenger.view.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldErrorMessage(
    modifier: Modifier = Modifier,
    errorMessage: String,
) {
    Text(
        text = errorMessage,
        color = MaterialTheme.colorScheme.error,
    )
    Spacer(modifier = modifier.height(8.dp))
}
