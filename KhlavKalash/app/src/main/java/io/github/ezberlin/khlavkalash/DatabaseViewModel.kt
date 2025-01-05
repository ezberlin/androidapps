package io.github.ezberlin.khlavkalash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DatabaseViewModel(private val db: AppDatabase) : ViewModel() {
    val items = MutableStateFlow<List<Item>>(emptyList())

    init {
        fetchItems()
    }

    private fun fetchItems() = viewModelScope.launch {
        items.value = db.itemDao().getAll()
    }

    fun addItem(itemName: String, itemAnyLeft: Boolean) =
        viewModelScope.launch {
            db.itemDao().insert(
                Item(
                    id = null,
                    name = itemName,
                    anyLeft = itemAnyLeft
                )
            )
            fetchItems()
        }

    fun invertItemAnyLeft(item: Item) =
        viewModelScope.launch {
            val newItem = item.copy(anyLeft = !item.anyLeft)
            db.itemDao().update(newItem)
            fetchItems()
        }

    fun deleteItem(item: Item) =
        viewModelScope.launch {
            db.itemDao().delete(item)
            fetchItems()
        }
}