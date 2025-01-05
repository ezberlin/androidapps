package io.github.ezberlin.khlavkalash

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

lateinit var db: AppDatabase
lateinit var databaseViewModel: DatabaseViewModel

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    @ColumnInfo(name = "any_left") val anyLeft: Boolean
)

@Dao
interface ItemDao {
    @Query("SELECT * FROM item")
    suspend fun getAll(): List<Item>

    @Query("SELECT * FROM item WHERE name LIKE :nameSnippet")
    suspend fun getByName(nameSnippet: String): List<Item>

    @Insert
    suspend fun insert(vararg items: Item)

    @Update
    suspend fun update(vararg items: Item)

    @Delete
    suspend fun delete(vararg items: Item)
}

@Database(entities = [Item::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}

