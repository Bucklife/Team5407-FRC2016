package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class Solenoids {
	
	Solenoid s_robotbase;
	Inputs inputs; 

	public Solenoids(int s0,int s1,int s2,int s3){
		
		s_robotbase = new Solenoid(s0);
		
	}
	public void update(){
		
		s_robotbase.set(inputs.b_ShiftGears);	
		
	}
	
	
	
	
	

}
