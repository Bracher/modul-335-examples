package ch.nyp.integrationtestdemo;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

	@Rule
	public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
			MainActivity.class);

	@Test
	public void testActionPerformend_correct() {
		String inputText = "Joel Holzer";

		// gültiger Text eingeben und dann Button klicken
		onView(withId(R.id.edittext_main_name))
				.perform(typeText(inputText), closeSoftKeyboard());
		onView(withId(R.id.button_main_save)).perform(click());

		// Überprüfen ob Toast eingeblendet wurde mit der korrekten Ausgabe
		onView(withText(inputText)).inRoot(withDecorView(not(mActivityRule.getActivity()
				.getWindow().getDecorView()))).check(matches(isDisplayed()));
	}

	@Test
	public void testActionPerformend_toLong() {
		String inputText =
				"sdkldfaskfjaslfkasdfklsdfksdfljkasdflasdfjasdflkdflasdfsdfjldfjasdfllkasdfldfklasdf";
		String errorMessage = "Name darf nicht länger als 50 Zeichen sein";

		// gültiger Text eingeben und dann Button klicken
		onView(withId(R.id.edittext_main_name))
				.perform(typeText(inputText), closeSoftKeyboard());
		onView(withId(R.id.button_main_save)).perform(click());

		// Überprüfen ob Toast eingeblendet wurde mit der korrekten Ausgabe
		onView(withText(errorMessage)).inRoot(withDecorView(not(mActivityRule.getActivity()
				.getWindow().getDecorView()))).check(matches(isDisplayed()));
	}

	@Test
	public void testActionPerformend_empty() {
		String inputText = "";
		String errorMessage = "Name darf nicht leer sein";

		// gültiger Text eingeben und dann Button klicken
		onView(withId(R.id.edittext_main_name))
				.perform(typeText(inputText), closeSoftKeyboard());
		onView(withId(R.id.button_main_save)).perform(click());

		// Überprüfen ob Toast eingeblendet wurde mit der korrekten Ausgabe
		onView(withText(errorMessage)).inRoot(withDecorView(not(mActivityRule.getActivity()
				.getWindow().getDecorView()))).check(matches(isDisplayed()));
	}
}