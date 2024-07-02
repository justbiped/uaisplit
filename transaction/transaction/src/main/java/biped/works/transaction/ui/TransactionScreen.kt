package biped.works.transaction.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.biped.locations.theme.components.SmallTitle

@Composable
fun TransactionScreen() {
    TransactionPanel()
}

@Composable
fun TransactionPanel() {
    TopAppbar({}, {})
    Column {
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppbar(
    onNavigateUp: () -> Unit, onSave: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Transaction") },
        navigationIcon = {
            IconButton(onClick = onNavigateUp) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = onSave) {
                SmallTitle(text = "Save", color = MaterialTheme.colorScheme.tertiary)
            }
        }
    )
}

@Preview
@Composable
fun TransactionPane_Preview() {
    TransactionPanel()
}