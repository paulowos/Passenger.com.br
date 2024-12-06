package br.com.passenger.view.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    hint: String,
    onValueChange: (String) -> Unit = {},
) {
    TextField(
        value = "",
        onValueChange = onValueChange,
        modifier =
            modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
        label = { Text(hint) },
    )
    Spacer(modifier = Modifier.height(8.dp))
}