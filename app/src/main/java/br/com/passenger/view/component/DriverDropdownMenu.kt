package br.com.passenger.view.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowDropDown
import androidx.compose.material.icons.sharp.Face
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.passenger.util.FieldNames
import br.com.passenger.viewmodel.RidesHistoryViewModel

@Composable
fun DriverDropdownMenu(
    modifier: Modifier = Modifier,
    viewModel: RidesHistoryViewModel = hiltViewModel(),
) {
    val containsDriverError = viewModel.fieldErrorNames.value.contains(FieldNames.DRIVER)

    Card(
        modifier =
            modifier
                .clickable { viewModel.toggleExpanded() }
                .border(
                    BorderStroke(
                        1.dp,
                        color = if (containsDriverError) MaterialTheme.colorScheme.error else Color.Transparent,
                    ),
                    shape = MaterialTheme.shapes.medium,
                ),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            Row {
                Icon(
                    Icons.Sharp.Face,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 13.dp),
                )
                Text(
                    text =
                        viewModel.drivers.value
                            .find { it.id.toString() == viewModel.selectedDriver.value }
                            ?.name ?: "Selecione o motorista",
                    color = if (containsDriverError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                )
            }
            Icon(
                Icons.Sharp.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = viewModel.isExpanded.value,
            onDismissRequest = { viewModel.toggleExpanded() },
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            offset = DpOffset(0.dp, 4.dp),
        ) {
            viewModel.drivers.value.forEach { driver ->
                DropdownMenuItem(
                    text = { Text(driver.name) },
                    onClick = { viewModel.selectDriver(driver.id) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
    if (containsDriverError) {
        ErrorMessageInline(
            errorMessage = "Selecione um motorista",
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}
