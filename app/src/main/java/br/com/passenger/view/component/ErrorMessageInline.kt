package br.com.passenger.view.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ErrorMessageInline(
    modifier: Modifier = Modifier,
    errorMessage: String,
) {
    Text(
        text = errorMessage,
        color = MaterialTheme.colorScheme.error,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier,
        textAlign = TextAlign.Center,
    )
}
