package ca.uwaterloo.lab2_201_13;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import ca.uwaterloo.sensortoy.LineGraphView;

class AccelSensorEventListener implements SensorEventListener {

	TextView output;
    LineGraphView graph;
    
     public AccelSensorEventListener(TextView outputView, LineGraphView g){
	      output = outputView;
	      graph = g;
	     // test = te;
     }
     
     double maxX = 0;
     double maxY = 0;
     double maxZ = 0;
     //float[] pastValues = new float[]{0, 0, 0};
     float[] smoothedAccel = new float[]{1, 1, 1};
     
     double totalAccel = 0.0;
     double totalAccelPast = 0.0;
     
//     boolean rising = false;
//     boolean falling = false;
     int state = 0;
     int step = 0;
     int filterCounter = 0;
     int setCounter = 0;
     
//     public enum State {
//    	 STATE_RISING, STATE_FALLING
//     }
//    State walkingState;
     
     public void onAccuracyChanged(Sensor s, int i) {}
     public void onSensorChanged(SensorEvent se) {
    	 
    	 
    	 
    	 for (int i=0; i < 3;i++){
    		 smoothedAccel[i] += (se.values[i] - smoothedAccel[i]) / 1470;
    		 
    	 }
    	 
    	 totalAccel = Math.sqrt(smoothedAccel[0]*smoothedAccel[0] + smoothedAccel[1]*smoothedAccel[1] + smoothedAccel[2]*smoothedAccel[2]);
    	 
    	 
// We have to look at the graph and try to put the curve in FSM, meaning a series of loops and if statements, such that after each iteration in the main while loop
// we would have counted a step.
// looking at the curves while in walking gait, we see that each step happens with a maximum followed by a minimum.
   
    	 
    	 ////////////////////////////////////FINITE_STATE_MACHINE
    	 
    	 if (filterCounter > 500 ) {
    	 
    		 while(setCounter++ % 10 == 0){
		    	 switch (state) {
		    	 	case 0://IF IT's RISING
		    	 		if ( totalAccel < totalAccelPast ){
		    	 			//walkingState = STATE_FALLING;
		    	 			state = 1;
		    	 		}
		    	 		break;
		    	 	case 1://IF IT'S FALLING
		    	 		if ( totalAccel > totalAccelPast ){
		    	 			//walkingState = STATE_RISING;
		    	 			state = 2;
		    	 			
		    	 		}
		    	 		break;
		    	 	case 2://IF IT'S FALLING
		    	 		if ( totalAccel < totalAccelPast ){
		    	 			//walkingState = STATE_RISING;
		    	 			state = 3;
		    	 			
		    	 		}
		    	 		break;
		    	 	case 3://IF IT'S FALLING
		    	 		if ( totalAccel > totalAccelPast ){
		    	 			//walkingState = STATE_RISING;
		    	 			state = 0;
		    	 			step++;
		    	 		}
		    	 		break;
		    	 	default:
		    	 		break;
		    	 }
    		} 
    	 } else {filterCounter++;}
    	 
//    	 pastValues[1] = smoothedAccel[1];
//    	 pastValues[2] = smoothedAccel[2];
    	 
    	 totalAccelPast = totalAccel;
    	 //////////////////////////////////////
    	 
    	 if (Math.abs(se.values[1]) > maxY){
    		 maxY = Math.abs(se.values[1]);
    	 }
    	 if (Math.abs(se.values[2]) > maxZ){
    		 maxZ = Math.abs(se.values[2]);
    	 }
    	 if (Math.abs(se.values[0]) > maxX){
    		 maxX = Math.abs(se.values[0]);
    	 }
    	 String s = String.format("Accelometer: \n"+"X_Value: %.3f maxX: %.3f\n" + "Y_Value: %.3f maxY: %.3f\n" + "Z_Value: %.3f  maxZ: %.3f\n" + "TOTAL ACCEL = %.3f\n", se.values[0], maxX, se.values[1], maxY, se.values[2], maxZ, totalAccel);
    	 
    	 String steps = String.format("\nNumber of Steps: %d", step);
    	 
    	 
    	 
    	 output.setText(s + steps);


       graph.addPoint(smoothedAccel);
       
     
     }
}
