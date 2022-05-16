package com.invictastudios.whatsappclone;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule
            = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void wrongEmailLoginScreen() throws InterruptedException {
        Espresso.onView(withId(R.id.editText)).perform(typeText("kristijan.stojanoski@yahoo"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.editText3)).perform(typeText("somesomepassword"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonLogin)).perform(click());
        Thread.sleep(3000);
    }

    @Test
    public void wrongPasswordLoginScreen() throws InterruptedException {
        Espresso.onView(withId(R.id.editText)).perform(typeText("kristijan.stojanoski@yahoo.com"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.editText3)).perform(typeText("password"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonLogin)).perform(click());
        Thread.sleep(3000);
    }

    @Test
    public void correctTestLoginScreen() throws InterruptedException {
        Espresso.onView(withId(R.id.editText)).perform(typeText("kristijan-stojanoski@yahoo.com"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.editText3)).perform(typeText("Kikimiki123"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonLogin)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.recycler_view2)).check(matches(isDisplayed()));

    }


}
