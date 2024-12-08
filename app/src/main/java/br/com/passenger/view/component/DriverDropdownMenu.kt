package br.com.passenger.view.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.passenger.viewmodel.RidesHistoryViewModel

@Composable
fun DriverDropdownMenu(
    modifier: Modifier = Modifier,
    viewModel: RidesHistoryViewModel = hiltViewModel(),
) {
    Card(modifier = modifier.clickable { viewModel.toggleExpanded() }) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            Text(
                text = viewModel.selectedDriver.value,
            )
            Icon(
                Icons.Outlined.ArrowDropDown,
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
                    text = { Text(driver) },
                    onClick = { viewModel.selectDriver(driver) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
