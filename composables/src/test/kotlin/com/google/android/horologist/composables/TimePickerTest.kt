/*
 * Copyright 2022 The Android Open Source Project
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

import androidx.compose.ui.text.font.FontWeight
import androidx.wear.compose.material.MaterialTheme
import com.google.android.horologist.compose.tools.copy
import com.google.android.horologist.screenshots.rng.WearLegacyScreenTest
import org.junit.Test
import org.robolectric.annotation.Config
import java.time.LocalTime

class TimePickerTest : WearLegacyScreenTest() {

    @Test
    fun initial() {
        runTest {
            TimePicker(
                time = LocalTime.of(10, 10, 0),
                onTimeConfirm = {},
            )
        }
    }

    @Test
    fun midnight() {
        runTest {
            TimePicker(
                time = LocalTime.of(0, 0, 0),
                onTimeConfirm = {},
                showSeconds = false,
            )
        }
    }

    @Test
    @Config(
        fontScale = 1.24f,
    )
    fun largestFontScaling() {
        runTest(applyDeviceConfig = false) {
            TestScaffold {
                TimePicker(
                    time = LocalTime.of(10, 10, 0),
                    onTimeConfirm = {},
                )
            }
        }
    }

    @Test
    fun noSeconds() {
        runTest {
            TimePicker(
                time = LocalTime.of(10, 10, 0),
                onTimeConfirm = {},
                showSeconds = false,
            )
        }
    }

    @Test
    @Config(
        qualifiers = "+w192dp-h192dp",
        fontScale = 1.24f,
    )
    fun smallDeviceLargeFontBold() {
        runTest(applyDeviceConfig = false) {
            MaterialTheme(typography = MaterialTheme.typography.copy { this.copy(fontWeight = FontWeight.Bold) }) {
                TimePicker(
                    time = LocalTime.of(10, 10, 0),
                    onTimeConfirm = {},
                )
            }
        }
    }
}
