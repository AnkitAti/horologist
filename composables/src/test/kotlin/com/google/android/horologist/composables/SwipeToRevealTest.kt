/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.horologist.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import androidx.wear.compose.foundation.RevealState
import androidx.wear.compose.foundation.RevealValue
import androidx.wear.compose.foundation.createAnchors
import androidx.wear.compose.foundation.rememberRevealState
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.SwipeToRevealAction
import androidx.wear.compose.material.SwipeToRevealDefaults
import androidx.wear.compose.material.Text
import com.google.android.horologist.screenshots.ScreenshotBaseTest
import com.google.android.horologist.screenshots.ScreenshotTestRule
import com.google.android.horologist.screenshots.ScreenshotTestRule.Companion.screenshotTestRuleParams
import org.junit.Test

@OptIn(ExperimentalWearFoundationApi::class, ExperimentalWearMaterialApi::class)
class SwipeToRevealTest : ScreenshotBaseTest(
    screenshotTestRuleParams {
        record = ScreenshotTestRule.RecordMode.Record
        screenTimeText = {}
        enableA11y = true
    }
) {

    @Test
    fun covered() {
        screenshotTestRule.setContent(takeScreenshot = true, roundScreen = false) {
            val state = rememberRevealState(
                // Setting anchor to 0.4 since there is only one action.
                anchors = createAnchors(revealingAnchor = 0.4f),
                initialValue = RevealValue.Covered
            )

            SwipeToRevealChip(state)
        }
    }

    @Test
    fun revealing() {
        screenshotTestRule.setContent(takeScreenshot = true, roundScreen = false) {
            val state = rememberRevealState(
                // Setting anchor to 0.4 since there is only one action.
                anchors = createAnchors(revealingAnchor = 0.4f),
                initialValue = RevealValue.Revealing
            )

            SwipeToRevealChip(state)
        }
    }

    @Test
    fun revealed() {
        screenshotTestRule.setContent(takeScreenshot = true, roundScreen = false) {
            val state = rememberRevealState(
                // Setting anchor to 0.4 since there is only one action.
                anchors = createAnchors(revealingAnchor = 0.4f),
                initialValue = RevealValue.Revealed,
            )

            SwipeToRevealChip(state)
        }
    }

    @Test
    fun coveredWithAll() {
        screenshotTestRule.setContent(takeScreenshot = true, roundScreen = false) {
            val state = rememberRevealState(
                initialValue = RevealValue.Covered
            )

            SwipeToRevealChipWithActions(state)
        }
    }

    @Test
    fun revealingWithAll() {
        screenshotTestRule.setContent(takeScreenshot = true, roundScreen = false) {
            val state = rememberRevealState(
                initialValue = RevealValue.Revealing
            )

            SwipeToRevealChipWithActions(state)
        }
    }

    @Test
    fun revealedWithAll() {
        screenshotTestRule.setContent(takeScreenshot = true, roundScreen = false) {
            val state = rememberRevealState(
                initialValue = RevealValue.Revealed
            )

            SwipeToRevealChipWithActions(state)
        }
    }

    @Composable
    private fun SwipeToRevealChipWithActions(state: RevealState) {
        SwipeToRevealChip(
            state,
            additionalAction = SwipeToRevealDefaults.secondaryAction(
                icon = {
                    Icon(
                        SwipeToRevealDefaults.MoreOptions,
                        contentDescription = "More"
                    )
                }
            ),
            undoAction = SwipeToRevealDefaults.undoAction(
                label = { Text(text = "Undo") },
            ),
        )
    }

    @Composable
    private fun SwipeToRevealChip(
        state: RevealState,
        additionalAction: SwipeToRevealAction? = null,
        undoAction: SwipeToRevealAction? = null
    ) {
        androidx.wear.compose.material.SwipeToRevealChip(
            revealState = state,
            primaryAction = SwipeToRevealDefaults.primaryAction(
                icon = {
                    Icon(
                        SwipeToRevealDefaults.Delete,
                        contentDescription = "Delete"
                    )
                },
                label = { Text(text = "Delete") },
            ),
            secondaryAction = additionalAction,
            undoPrimaryAction = undoAction
        ) {
            Chip(
                onClick = { /*TODO*/ },
                colors = ChipDefaults.secondaryChipColors(),
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Try this") }
            )
        }
    }
}