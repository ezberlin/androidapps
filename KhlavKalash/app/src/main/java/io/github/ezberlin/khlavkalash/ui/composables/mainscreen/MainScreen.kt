package io.github.ezberlin.khlavkalash.ui.composables.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.github.ezberlin.khlavkalash.DatabaseViewModel

@Composable
fun MainScreen(databaseViewModel: DatabaseViewModel) {
    val items by databaseViewModel.items.collectAsState(initial = emptyList())
    var newItemMenuContent by remember { mutableStateOf("") }
    var newItemMenuAnyLeft by remember { mutableStateOf(false) }
    var newItemMenuActivated by remember { mutableStateOf(false) }
    var filterMenuContent by remember { mutableStateOf("") }

    fun showNewItemMenu() {
        newItemMenuActivated = true
    }

    fun hideNewItemMenu() {
        newItemMenuActivated = false
        newItemMenuContent = ""
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            NewItemFab(
                { showNewItemMenu() },
                { hideNewItemMenu() },
                newItemMenuActivated
            )
        },
        topBar = {
            FilterTopBar(filterMenuContent) { filterMenuContent = it }
        },
        bottomBar = {
            NewItemBottomBar(
                newItemMenuContent,
                newItemMenuAnyLeft,
                newItemMenuActivated,
                { newItemMenuAnyLeft = it },
                { newItemMenuContent = it }
            ) { hideNewItemMenu() }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            ItemEntries(items.filter { it.name.contains(filterMenuContent, ignoreCase = true) })
        }
    }
}