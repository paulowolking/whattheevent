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

class EventRowTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalMaterialApi
    @Test
    fun testShowTitleAndDescription() {
        val title = "Title 01"
        val description = "Description 01"
        composeTestRule.setContent {
            WhatTheEventTheme {
                EventRow(
                    event = Event(
                        id = "",
                        date = 0,
                        description = description,
                        image = "",
                        longitude = 0.0,
                        latitude = 0.0,
                        price = 0.0,
                        title = title
                    )
                ) {

                }
            }
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(description).assertIsDisplayed()
        composeTestRule.onNodeWithText(title).performClick()
        composeTestRule.onNodeWithText(description).performClick()
    }
}