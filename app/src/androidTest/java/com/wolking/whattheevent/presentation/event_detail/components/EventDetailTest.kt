package com.wolking.whattheevent.presentation.event_detail.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.wolking.whattheevent.domain.event.entities.Event
import com.wolking.whattheevent.extensions.toMoneyReal
import com.wolking.whattheevent.presentation.theme.WhatTheEventTheme
import org.junit.Rule
import org.junit.Test

class EventDetailTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalMaterialApi
    @Test
    fun testShowInfoDetail() {
        val title = "Title 01"
        val description = "Description 01"
        val price = 12.0
        composeTestRule.setContent {
            WhatTheEventTheme {
                EventDetail(
                    event = Event(
                        id = "",
                        date = 0,
                        description = description,
                        image = "",
                        longitude = 0.0,
                        latitude = 0.0,
                        price = price,
                        title = title
                    )
                )
            }
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(description).assertIsDisplayed()
        composeTestRule.onNodeWithText(price.toMoneyReal()).assertIsDisplayed()
    }
}