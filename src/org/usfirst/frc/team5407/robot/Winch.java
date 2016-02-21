package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.Servo;

public class Winch {
	
	Servo serv_WinchBrake;
	
	double d_WinchBrake;
	
	
	public Winch(int PWMConnector_WinchBrake){
		
		serv_WinchBrake = new Servo(PWMConnector_WinchBrake); 
		
		//make sure all motors are stopped 
		serv_WinchBrake.set(0.0);
		
		}
	
		public void update(Inputs inputs){
			
	    	if(inputs.b_WinchBrake == true){
	    		d_WinchBrake = .5;
	    	} else {
	    		d_WinchBrake = 0;
	    	}
	    	
			serv_WinchBrake.set(d_WinchBrake);

		}

}
