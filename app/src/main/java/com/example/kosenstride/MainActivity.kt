package com.example.kosenstride

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.kosenstride.navigation.Navigation
import com.example.kosenstride.ui.theme.KosenStrideTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KosenStrideTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column {
//                        KosenStrideTopAppBar()
                        Navigation()
                    }
                }
            }
        }
    }
}
