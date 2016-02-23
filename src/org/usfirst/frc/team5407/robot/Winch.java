package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;

public class Winch {
	
	Servo serv_WinchBrake;
	
	VictorSP mot_LiftWinchPower; 
	
	double d_WinchBrake;
	
	double d_LiftWinchPower;
	
	
	public Winch(int PWMConnector_WinchBrake, int PWMConnector_WinchPower){
		
		serv_WinchBrake = new Servo(PWMConnector_WinchBrake); 
		mot_LiftWinchPower = new VictorSP(PWMConnector_WinchPower);
		
		//make sure all motors are stopped 
		serv_WinchBrake.set(0.0);
		mot_LiftWinchPower.set(0.0);
		
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
