package io.github.ezberlin.khlavkalash.ui.composables.mainscreen

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import io.github.ezberlin.khlavkalash.ui.theme.KhlavKalashTheme

@Composable
fun NewItemFab(
    showNewItemMenu: () -> Unit,
    hideNewItemMenu: () -> Unit,
    newItemMenuActivated: Boolean
) {
    val scale by animateFloatAsState(if (newItemMenuActivated) 1.2f else 1f, label = "")

    FloatingActionButton(
        onClick = {
            if (newItemMenuActivated) hideNewItemMenu()
            else showNewItemMenu()
        },
        modifier = Modifier.scale(scale)
    ) {
        Icon(
            if (newItemMenuActivated) Icons.Default.Close else Icons.Default.Create,
            "Add new item to shopping list"
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun NewItemFabPreview() {
    KhlavKalashTheme {
        NewItemFab(
            showNewItemMenu = { },
            hideNewItemMenu = { },
            newItemMenuActivated = false
        )
    }
}