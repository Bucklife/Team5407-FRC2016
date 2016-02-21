package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.Servo;

public class Winch {
	
	Servo serv_WinchBrake;
	
	double d_WinchBrake;
	
	
	public Winch(){
		
		serv_WinchBrake = new Servo(2); 
		
		//make sure all motors are stopped 
		serv_WinchBrake.set(0.0);
		
		}
		public void update(){
			serv_WinchBrake.set(d_WinchBrake);
		}

}
