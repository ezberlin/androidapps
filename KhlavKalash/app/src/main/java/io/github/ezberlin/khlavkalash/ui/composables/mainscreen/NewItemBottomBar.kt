package io.github.ezberlin.khlavkalash.ui.composables.mainscreen

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.ezberlin.khlavkalash.databaseViewModel
import io.github.ezberlin.khlavkalash.ui.theme.KhlavKalashTheme

@Composable
fun NewItemBottomBar(
    newItemMenuContent: String,
    newItemMenuAnyLeft: Boolean,
    newItemMenuActivated: Boolean,
    bindAnyLeft: (newItemMenuAnyLeft: Boolean) -> Unit,
    bindContent: (newItemMenuContent: String) -> Unit,
    hideNewItemMenu: () -> Unit
) {
    AnimatedVisibility(
        visible = newItemMenuActivated,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
    ) {
        // Background for the bottom bar
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = ShapeDefaults.Small
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                TextField(
                    value = newItemMenuContent,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                        .fillMaxWidth(),
                    onValueChange = { bindContent(it) },
                    singleLine = true,
                    placeholder = { Text("Enter name...") },
                    supportingText = { Text("New item name") }
                )
                Column(
                    modifier = Modifier
                        .padding(end = 8.dp)
                ) {
                    Checkbox(
                        checked = newItemMenuAnyLeft,
                        onCheckedChange = bindAnyLeft,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        "Any left?",
                        fontSize = 12.sp
                    )
                }
                Button(
                    onClick = {
                        databaseViewModel.addItem(newItemMenuContent, newItemMenuAnyLeft)
                        hideNewItemMenu()
                    },
                    modifier = Modifier
                        .size(64.dp) // Increase the size of the button
                        .align(Alignment.CenterVertically), // Center the button vertically
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp) // Remove default padding
                ) {
                    Icon(
                        Icons.Default.Create,
                        contentDescription = "Create new Item",
                        modifier = Modifier.size(24.dp) // Set the size of the icon
                    )
                }

            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun NewItemBottomBarPreview() {
    KhlavKalashTheme {
        NewItemBottomBar(
            newItemMenuContent = "Baklava",
            newItemMenuAnyLeft = false,
            newItemMenuActivated = true,
            bindAnyLeft = { _ -> },
            bindContent = { _ -> },
            hideNewItemMenu = {  }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun NewItemBottomBarPreviewNoEntry() {
    KhlavKalashTheme {
        NewItemBottomBar(
            newItemMenuContent = "",
            newItemMenuAnyLeft = true,
            newItemMenuActivated = true,
            bindAnyLeft = { _ -> },
            bindContent = { _ -> },
            hideNewItemMenu = {  }
        )
    }
}