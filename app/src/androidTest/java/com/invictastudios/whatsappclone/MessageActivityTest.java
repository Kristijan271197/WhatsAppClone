package com.invictastudios.whatsappclone;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MessageActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void sendEmptyMessage() throws InterruptedException {
        Matcher<View> matcher = allOf(withText("USERS"),
                isDescendantOfA(withId(R.id.tabLayout)));
        onView(matcher).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(2000);
        onView(withId(R.id.btn_send)).perform(click());
    }

    @Test
    public void sendCorrectMessage() throws InterruptedException {

        Matcher<View> matcher = allOf(withText("USERS"),
                isDescendantOfA(withId(R.id.tabLayout)));
        onView(matcher).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(2000);
        onView(withId(R.id.text_send)).perform(typeText("hello"), closeSoftKeyboard());
        onView(withId(R.id.btn_send)).perform(click());
        onView(withId(R.id.recycler_view))
                .check(matches(hasDescendant(withText("hello"))));

    }


    @Test
    public void sendLongMessage() throws InterruptedException {

        Matcher<View> matcher = allOf(withText("USERS"),
                isDescendantOfA(withId(R.id.tabLayout)));
        onView(matcher).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerView))
                .perform(actionOnItemAtPosition(0, click()));
        Thread.sleep(2000);
        onView(withId(R.id.text_send)).perform(typeText("HelloHelloHelloHelloHello" +
                "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello" +
                "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello" +
                "HelloHelloHelloHelloHelloHelloHelloHelloHelloHello"), closeSoftKeyboard());
        onView(withId(R.id.btn_send)).perform(click());

    }
}
