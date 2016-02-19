package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class Solenoids {
	
	Solenoid s_robotbase;
	Inputs inputs;
	
	boolean b_ShiftGears;

	public Solenoids(int s0,int s1,int s2,int s3){
		s_robotbase = new Solenoid(s0);
	}
	
	public void update(){
		
//		if (inputs.b_ShiftGears == true){
//			s_robotbase.set(true);
//		} else if (inputs.b_ShiftGears == false){
//			s_robotbase.set(false);	
//		}
		
		s_robotbase.set(b_ShiftGears);
		
	}

}
