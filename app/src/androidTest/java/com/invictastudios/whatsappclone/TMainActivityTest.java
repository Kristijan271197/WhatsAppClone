package com.invictastudios.whatsappclone;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anyOf;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;

public class TMainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void checkChatList() throws InterruptedException {
        Thread.sleep(2000);
        onView(withId(R.id.recycler_view2)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_view2))
                .perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.username)).check(matches(withText("krasla")));

    }

    @Test
    public void testSignOut() {
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(anyOf(withText("Logout"), withId(R.id.logout))).perform(click());
    }


}
