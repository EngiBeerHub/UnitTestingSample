package com.example.unittestingsample

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Ignore
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.assertj.core.api.Assertions.*
import com.nhaarman.mockitokotlin2.*
import org.robolectric.AndroidMetadata
import org.robolectric.RobolectricTestRunner

@RunWith(AndroidJUnit4::class)
class JetpackTest {
    @Test
    fun gettingContextTest() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val appName = context.getString(R.string.app_name)
        assertThat(appName).isEqualTo("UnitTestingSample")

    }
}

@RunWith(RobolectricTestRunner::class)
class InputCheckerTest {
    private lateinit var target: InputChecker

    @Before
    fun setUp() {
        target = InputChecker()
    }

    @org.junit.jupiter.api.AfterEach
    fun tearDown() {
    }

    @Test
    fun isValid_givenLessThan3_returnsFalse() {
        val actual = target.isValid("ab")
        assertThat(actual, `is`(false))
    }

    @Test
    fun isValid_givenAlphabetic_returnsTrue() {
        val actual = target.isValid("abc")
        assertThat(actual, `is`(true))
    }

    @Test
    fun isValid_givenNumeric_returnsTrue() {
        val actual = target.isValid("123")
        assertThat(actual, `is`(true))
    }

    @Test
    fun isValid_givenAlphaNumeric_returnsTrue() {
        val actual = target.isValid("abc123")
        assertThat(actual, `is`(true))
    }

    @Test
    fun isValid_givenInvalidCharacter_returnsFalse() {
        val actual = target.isValid("abc@123")
        assertThat(actual, `is`(false))
    }

    @Test
    fun isValid_givenBlank_throwsIllegalArgumentException() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { target.isValid("") }
            .withMessage("Cannot be blank")
    }

    @Ignore("テスト対象が仮実装なので一時的にスキップ")
    @Test
    fun temporarilySkipThisTest() {
        // Do nothing
    }
}