package ch.nyp.instrumentedunittestdemo;

import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static junit.framework.Assert.assertEquals;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

	@Rule
	public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity
			.class);

	@Test
	public void testValidateFormFields_empty() throws Throwable {
		final MainActivity activity = rule.getActivity();

		final EditText editText = activity.findViewById(R.id.edittext_main_name);

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				editText.setText("");
				String errorMessage = activity.validateFormFields();
				assertEquals("Name darf nicht leer sein", errorMessage);
			}
		});
	}

	@Test
	public void testValidateFormFields_toLong() throws Throwable {
		final MainActivity activity = rule.getActivity();

		final EditText editText = activity.findViewById(R.id.edittext_main_name);

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				editText.setText("dassssssssssssssssssssadsasddfdassssssssssssssssssssadsasddfdassssssssssssssssssssadsasddfdassssssssssssssssssssadsasddf");
				String errorMessage = activity.validateFormFields();
				assertEquals("Name darf nicht länger als 50 Zeichen sein", errorMessage);
			}
		});
	}
}