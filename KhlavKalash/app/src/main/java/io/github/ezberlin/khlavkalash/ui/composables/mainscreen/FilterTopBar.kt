package io.github.ezberlin.khlavkalash.ui.composables.mainscreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.ezberlin.khlavkalash.ui.theme.KhlavKalashTheme

@Composable
fun FilterTopBar(
    newItemMenuContent: String,
    bindContent: (newItemMenuContent: String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            TextField(
                value = newItemMenuContent,
                onValueChange = { bindContent(it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = { Text("Filter for items...") }
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun FilterTopBarPreview() {
    KhlavKalashTheme {
        FilterTopBar(
            newItemMenuContent = "Bak",
            bindContent = { _ -> },
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun FilterTopBarPreviewNoContent() {
    KhlavKalashTheme {
        FilterTopBar(
            newItemMenuContent = "",
            bindContent = { _ -> },
        )
    }
}