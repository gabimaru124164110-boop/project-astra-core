package com.projectastra.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.projectastra.ui.AstraTheme
import com.projectastra.ui.SkyMapScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AstraTheme {
                SkyMapScreen()
            }
        }
    }
}
