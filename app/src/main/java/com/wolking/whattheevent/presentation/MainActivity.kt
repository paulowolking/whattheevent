package com.wolking.whattheevent.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.rememberNavController
import com.wolking.whattheevent.presentation.shared.navigation.NavigationHost
import com.wolking.whattheevent.presentation.theme.WhatTheEventTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatTheEventTheme {
                val navController = rememberNavController()
                NavigationHost(
                    navController = navController,
                )
            }
        }
    }
}