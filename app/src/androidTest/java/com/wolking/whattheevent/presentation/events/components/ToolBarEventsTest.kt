package com.wolking.whattheevent.presentation.events.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.wolking.whattheevent.domain.event.entities.Event
import com.wolking.whattheevent.presentation.theme.WhatTheEventTheme
import org.junit.Rule
import org.junit.Test

class ToolBarEventsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalMaterialApi
    @Test
    fun testShowTitle() {
        val title = "Title 01"
        composeTestRule.setContent {
            WhatTheEventTheme {
                ToolBarEvents(
                    title
                )
            }
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }
}