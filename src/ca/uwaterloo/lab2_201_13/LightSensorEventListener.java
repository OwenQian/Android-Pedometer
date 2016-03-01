package ca.uwaterloo.lab2_201_13;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

class LightSensorEventListener implements SensorEventListener {
    TextView output;

     public LightSensorEventListener(TextView outputView){
      output = outputView;
     }
     
     public void onAccuracyChanged(Sensor s, int i) {}
     public void onSensorChanged(SensorEvent se) {
       if (se.sensor.getType() == Sensor.TYPE_LIGHT) {
         
    	   float currentLightReading = se.values[0];
    	   output.setText(String.format( "Light Sensor: %.3f", currentLightReading ));
    	   
    	   
       }
       
     }
}
