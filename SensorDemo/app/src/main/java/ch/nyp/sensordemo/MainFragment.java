package ch.nyp.sensordemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * MainFragment der Sensor Demo App
 */
public class MainFragment extends Fragment {

	private SensorManager mSensorManager;

	private SensorEventListener mBarometerSensorEventListener = new SensorEventListener() {
		@Override
		public void onSensorChanged(SensorEvent event) {
			float[] sensorValues = event.values;
			String airPressure = sensorValues[0] + " mbar";
			Toast toast = Toast.makeText(getContext(), airPressure, Toast.LENGTH_LONG);
			toast.show();
			mSensorManager.unregisterListener(this);
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
	};

	private View.OnClickListener mButtonBarometerOnclickListener = new View.OnClickListener() {
		@Override
		public void onClick(View button) {
			//Abfragen, ob ein Sensor zur Messung des Luftdrucks verfügbar ist und falls Ja,
			// einen SensorEventListener registrieren (für den SensorEventListener siehe
			// Instanzvariable mBarometerSensorEventListener
			Sensor airPressureSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
			if (airPressureSensor != null) {
				mSensorManager.registerListener(mBarometerSensorEventListener, airPressureSensor,
						SensorManager.SENSOR_DELAY_NORMAL);
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView =  inflater.inflate(R.layout.fragment_main, container, false);

		TextView textViewAvailableSensors = (TextView) rootView.findViewById(R.id
				.main_textview_available_sensors);
		Button buttonBarometer = (Button) rootView.findViewById(R.id.main_button_barometer);
		buttonBarometer.setOnClickListener(mButtonBarometerOnclickListener);

		//Alle verfügbaren Sensoren des Geräts abfragen und deren Namen auf dem GUI anzeigen
		mSensorManager = (SensorManager) getActivity().getSystemService(Context
				.SENSOR_SERVICE);
		List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

		String sensorString = "";
		for (Sensor deviceSensor : deviceSensors) {
			sensorString += deviceSensor.getName() + "\n";
		}

		textViewAvailableSensors.setText(sensorString);

		return rootView;
	}
}
