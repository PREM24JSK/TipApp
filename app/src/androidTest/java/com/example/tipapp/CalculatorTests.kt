package com.example.tipapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.core.StringContains
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith (AndroidJUnit4::class)
class CalculatorTests {

    @get:Rule()
    val activity=ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip(){
        onView(withId(R.id.costOfServiceEditText)).perform(ViewActions.typeText("50.00"))
            .perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.calculateBtn)).perform(ViewActions.click())
        onView(withId(R.id.tipAmount)).check(matches(withText(StringContains("Tip Amount: $10.00"))))

    }

}