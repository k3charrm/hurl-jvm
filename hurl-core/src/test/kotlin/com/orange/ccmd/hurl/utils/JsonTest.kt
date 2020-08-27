/*
 * Copyright (C) 2020 Orange
 *
 * Hurl JVM (JVM Runner for https://hurl.dev)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.orange.ccmd.hurl.utils

import com.orange.ccmd.hurl.core.utils.parseJsonString
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class JsonTest {

    @Test
    fun `parse a single string as a json string`() {
        val text = "\"helloworld\""
        assertEquals("helloworld", parseJsonString(text))
    }
}