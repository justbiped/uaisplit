package biped.works.locations.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.ViewList
import androidx.compose.ui.graphics.vector.ImageVector
import biped.works.locations.R

interface BottomNavItem {
    val title: Int
    val unselectedIcon: ImageVector
    val selectedIcon: ImageVector
}

object StatementNavItem : BottomNavItem {
    override val title: Int = R.string.statement_destination_label
    override val unselectedIcon: ImageVector = Icons.Outlined.ViewList
    override val selectedIcon: ImageVector = Icons.Filled.ViewList
}

object SettingsNavItem : BottomNavItem {
    override val title: Int = R.string.profile_destination_label
    override val unselectedIcon: ImageVector = Icons.Outlined.People
    override val selectedIcon: ImageVector = Icons.Filled.People
}