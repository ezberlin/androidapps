package io.github.ezberlin.khlavkalash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import io.github.ezberlin.khlavkalash.ui.composables.mainscreen.MainScreen
import io.github.ezberlin.khlavkalash.ui.theme.KhlavKalashTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "items"
        ).build()

        databaseViewModel = ViewModelProvider(
            this,
            DatabaseViewModelFactory(db)
        )[DatabaseViewModel::class.java]

        setContent {
            KhlavKalashTheme {
                MainScreen(databaseViewModel)
            }
        }
    }
}
