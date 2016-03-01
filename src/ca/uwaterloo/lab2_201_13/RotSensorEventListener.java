package ca.uwaterloo.lab2_201_13;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

class RotSensorEventListener implements SensorEventListener {
    TextView output;
     public RotSensorEventListener(TextView outputView){
      output = outputView;
     }
     
     double max_x = 0;
     double max_y = 0;
     double max_z = 0;

     
     public void onAccuracyChanged(Sensor s, int i) {}
     public void onSensorChanged(SensorEvent se) {
    	 

    	 if (Math.abs(se.values[0]) > max_x){
    		 max_x = Math.abs(se.values[0]);
    	 }
    	 if (Math.abs(se.values[1]) > max_y){
    		 max_y = Math.abs(se.values[1]);
    	 }
    	 if (Math.abs(se.values[2]) > max_z){
    		 max_z = Math.abs(se.values[2]);
    	 }

    	 String s = String.format("Rotation Sensor: \n"+"X_Value: %.3f max_X: %.3f\n" + "Y_Value: %.3f max_Y: %.3f\n" + "Z_Value: %.3f  max_Z: %.3f\n"  , se.values[0], max_x, se.values[1], max_y, se.values[2], max_z);
    	 
    	 output.setText(s);

       
     }
}
