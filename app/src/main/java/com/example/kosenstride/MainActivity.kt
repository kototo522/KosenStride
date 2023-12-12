package com.example.kosenstride

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.kosenstride.navigation.BottomNavigationBar
import com.example.kosenstride.topAppBar.KosenStrideTopAppBar
import com.example.kosenstride.ui.theme.KosenStrideTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KosenStrideTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column {
                        KosenStrideTopAppBar()
                        BottomNavigationBar()
                    }
                }
            }
        }
    }
}
