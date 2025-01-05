package io.github.ezberlin.khlavkalash.ui.composables.mainscreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.ezberlin.khlavkalash.Item
import io.github.ezberlin.khlavkalash.ui.theme.KhlavKalashTheme

@Composable
fun ItemEntries(items: List<Item>,  modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            Spacer(Modifier.size(16.dp))

            Text(
                "Still left: ",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )

            Spacer(Modifier.size(8.dp))
        }

        items(items.filter { it.anyLeft }, key = { it.id!! }) { item ->
            ItemEntry(item)
            Spacer(Modifier.size(8.dp))
        }
        item {
            Spacer(Modifier.size(16.dp))

            Text(
                "None left: ",
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )

            Spacer(Modifier.size(8.dp))
        }

        items(items.filterNot { it.anyLeft }, key = { it.id!! }) { item ->
            ItemEntry(item)
            Spacer(Modifier.size(8.dp))
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ItemEntriesPreview() {
    KhlavKalashTheme {
        ItemEntries(
            mutableListOf(
                Item(id = 0, name = "Salat", anyLeft = true),
                Item(id = 1, name = "Öl", anyLeft = false),
                Item(id = 2, name = "Baguette", anyLeft = true),
                Item(id = 3, name = "Döner", anyLeft = false)
            )
        )
    }
}