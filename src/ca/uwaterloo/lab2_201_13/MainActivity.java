package ca.uwaterloo.lab2_201_13;

import java.util.Arrays;

import android.app.Activity;
import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import ca.uwaterloo.sensortoy.LineGraphView;

public class MainActivity extends Activity {

	
	 
		  
		    
		  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			TextView tv = (TextView) rootView.findViewById(R.id.label1);
			tv.setText("I've actually done it this time!?");
			
			LinearLayout l1 = (LinearLayout) rootView.findViewById(R.id.label2);
			
			
			TextView tv_Light = new TextView(rootView.getContext());
			TextView tv_Accel = new TextView(rootView.getContext());
			TextView tv_Mag = new TextView(rootView.getContext());
			TextView tv_Rot = new TextView(rootView.getContext());
			//TextView test = new TextView(rootView.getContext());

			
			LineGraphView graph = new LineGraphView ( rootView.getContext(), 100, Arrays.asList("x", "y", "z"));
			l1.addView(graph);
		    graph.setVisibility(View.VISIBLE);
		    
			
			l1.addView(tv_Light);
			l1.addView(tv_Accel);
			l1.addView(tv_Mag);
			l1.addView(tv_Rot);
			
			
			l1.setOrientation(LinearLayout.VERTICAL);
			
			
			SensorManager sensorManager = (SensorManager) rootView.getContext().getSystemService(SENSOR_SERVICE);
			Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
			SensorEventListener l2 = new LightSensorEventListener(tv_Light);
			sensorManager.registerListener(l2, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);

			
			Sensor AccelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
			SensorEventListener l3 = new AccelSensorEventListener(tv_Accel, graph);
			sensorManager.registerListener(l3, AccelSensor, SensorManager.SENSOR_DELAY_FASTEST);

			
			Sensor MagSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
			SensorEventListener l4 = new MagSensorEventListener(tv_Mag);
			sensorManager.registerListener(l4, MagSensor, SensorManager.SENSOR_DELAY_NORMAL);
			
			
			Sensor RotSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
			SensorEventListener l5 = new RotSensorEventListener(tv_Rot);
			sensorManager.registerListener(l5, RotSensor, SensorManager.SENSOR_DELAY_NORMAL);
			
			
			
			
			
	

			
			return rootView;
		}
	}
}
