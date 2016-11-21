package ch.nyp.sensordemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * MainActivity der Sensor Demo App
 */
public class MainActivity extends AppCompatActivity {

	/**
	 *
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		getSupportFragmentManager().beginTransaction().add(R.id.activity_main_root_container,
				new MainFragment())
				.commitAllowingStateLoss();
	}
}
