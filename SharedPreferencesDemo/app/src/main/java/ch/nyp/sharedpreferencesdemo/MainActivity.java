package ch.nyp.sharedpreferencesdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button buttonWrite = findViewById(R.id.button_main_write);
		buttonWrite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
						"demo_preferences", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putString("KEY_VORNAME", "Fritz");
				editor.putString("KEY_NACHNAME", "Müller");
				editor.commit();
			}
		});

		Button buttonRead = findViewById(R.id.button_main_read);
		buttonRead.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
						"demo_preferences", Context.MODE_PRIVATE);
				String firstName = sharedPref.getString("KEY_VORNAME", "");
				String lastName = sharedPref.getString("KEY_NACHNAME", "");

				String message = "vorname: " + firstName + "\n" + "nachname: " + lastName;
				Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
			}
		});
	}
}
