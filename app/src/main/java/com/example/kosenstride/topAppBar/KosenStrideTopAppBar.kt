package com.example.kosenstride.topAppBar

import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kosenstride.navigation.BottomBarScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KosenStrideTopAppBar(navController: NavController) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Kosen Stride",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(700),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.7.sp,
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate("MyAccount") }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "アカウントアイコン",
                    modifier = Modifier.width(24.dp),
                )
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate("setting") }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "設定")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
        )
    )
}