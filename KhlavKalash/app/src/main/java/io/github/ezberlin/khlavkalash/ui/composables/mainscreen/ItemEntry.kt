package io.github.ezberlin.khlavkalash.ui.composables.mainscreen

import android.content.res.Configuration
import android.os.Handler
import android.os.Looper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.ezberlin.khlavkalash.Item
import io.github.ezberlin.khlavkalash.databaseViewModel
import io.github.ezberlin.khlavkalash.ui.theme.KhlavKalashTheme

@Composable
fun ItemEntry(item: Item) {
    val cardColor =
        if (item.anyLeft)
            MaterialTheme.colorScheme.surface
        else
            MaterialTheme.colorScheme.errorContainer

    var visible by remember { mutableStateOf(true) }
    val scale by animateFloatAsState(if (visible) 1f else 0.8f, label = "")

    AnimatedVisibility(
        visible = visible,
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = 200, // Adjust duration for smoother exit
                easing = FastOutLinearInEasing
            )
        ),
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 200, // Adjust duration for smoother entry
                easing = LinearOutSlowInEasing
            )
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Add horizontal padding (margin effect)
                .scale(scale) // Use animated scale
                .clickable(
                    onClick = {
                        visible = false
                        // Delay the database operation to allow the animation to complete
                        Handler(Looper.getMainLooper()).postDelayed(
                            { databaseViewModel.invertItemAnyLeft(item) },
                            200 // Match this with the animation duration
                        )
                    }
                ),
            elevation = CardDefaults.elevatedCardElevation(20.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 3.dp) // Optional: Add vertical padding
            ) {
                Text(
                    item.name,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = {
                        visible = false
                        // Delay the database operation to allow the animation to complete
                        Handler(Looper.getMainLooper()).postDelayed(
                            { databaseViewModel.deleteItem(item) },
                            200 // Match this with the animation duration
                        )
                    },
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.Delete, "Delete item")
                }
            }
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ItemEntryPreviewSomeLeft() {
    KhlavKalashTheme {
        ItemEntry(Item(id = 0, name = "Salat", anyLeft = true))
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ItemEntryPreviewNoneLeft() {
    KhlavKalashTheme {
        ItemEntry(Item(id = 0, name = "Döner", anyLeft = false))
    }
}