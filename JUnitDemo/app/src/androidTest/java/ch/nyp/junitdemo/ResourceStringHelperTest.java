package ch.nyp.junitdemo;

import android.content.Context;
import android.support.test.annotation.UiThreadTest;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Testklasse für {@link ResourceStringHelper}.
 * Instrumentation Test
 */
public class ResourceStringHelperTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public ResourceStringHelperTest() {
		super(MainActivity.class);
	}

	/**
	 * Testet, ob die Methode {@link ResourceStringHelper#getRestErrorMessage(Context, String, int)} korrekt
	 * funktioniert.
	 *
	 * @throws Exception Exception, welche die Methode
	 * {@link ResourceStringHelper#getRestErrorMessage(Context, String, int)} werfen könnte
	 */
	@UiThreadTest
	public void testGetRestErrorMessage() throws Exception {

		MainActivity mainActivity = getActivity();
		String restUrl = "/lernenden";
		int errorCode = 400;

		String expectedString = "eine der gemachten Angaben ist fehlerhaft oder es " +
				"wurden mehr als 2 Fotos zum Upload angegeben.";
		String stringFromLanguageFile = ResourceStringHelper.getRestErrorMessage(mainActivity,
				restUrl, errorCode);
		assertEquals(expectedString,stringFromLanguageFile );
	}
}