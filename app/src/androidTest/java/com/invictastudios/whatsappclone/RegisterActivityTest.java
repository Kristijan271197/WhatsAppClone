package com.invictastudios.whatsappclone;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterActivityTest {

    @Rule
    public ActivityScenarioRule<RegistrationActivity> activityScenarioRule
            = new ActivityScenarioRule<>(RegistrationActivity.class);

    @Test
    public void wrongUsernameRegisterScreen() throws InterruptedException {
        Espresso.onView(withId(R.id.username_edit_text)).perform(typeText("Kristijan$$"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.password_edit_text)).perform(typeText("CorrectPassword1234"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.email_edit_text)).perform(typeText("kristijanstojanoski@yahoo.com"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.registration_button)).perform(click());
        Thread.sleep(3000);

    }

    @Test
    public void wrongPasswordRegisterScreen() throws InterruptedException {
        Espresso.onView(withId(R.id.username_edit_text)).perform(typeText("Kristijan2711"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.password_edit_text)).perform(typeText("123456"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.email_edit_text)).perform(typeText("kristijanstojanoski@yahoo.com"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.registration_button)).perform(click());
        Thread.sleep(3000);

    }


    @Test
    public void wrongEmailRegisterScreen() throws InterruptedException {
        Espresso.onView(withId(R.id.username_edit_text)).perform(typeText("Kristijan2711"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.password_edit_text)).perform(typeText("CorrectPassword1234"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.email_edit_text)).perform(typeText("kristijanstojanoski@yahoo"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.registration_button)).perform(click());
        Thread.sleep(3000);

    }

    @Test
    public void emailInUseRegisterScreen() throws InterruptedException {
        Espresso.onView(withId(R.id.username_edit_text)).perform(typeText("Kristijan2711"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.password_edit_text)).perform(typeText("CorrectPassword1234"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.email_edit_text)).perform(typeText("kristijanstojanoski@yahoo.com"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.registration_button)).perform(click());
        Thread.sleep(3000);

    }

    @Test
    public void correctRegisterScreen() throws InterruptedException {
        Espresso.onView(withId(R.id.username_edit_text)).perform(typeText("Kristijan271197"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.password_edit_text)).perform(typeText("CorrectPassword1234"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.email_edit_text)).perform(typeText("kristijan_stojanoski@yahoo.com"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.registration_button)).perform(click());
        Thread.sleep(3000);

    }


}
