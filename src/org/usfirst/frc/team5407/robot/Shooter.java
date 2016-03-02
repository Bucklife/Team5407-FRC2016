package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;

public class Shooter{
	
	Talon mot_ShooterPower; 
	
	VictorSP mot_ShooterWinch;
		
	double d_ShooterPower;	
	double d_ShooterWinch;
	
		public Shooter(int PWMConnector_ShooterPower, int PWMConnector_ShooterWinch){
			
		mot_ShooterPower = new Talon(PWMConnector_ShooterPower); 
		mot_ShooterWinch = new VictorSP(PWMConnector_ShooterWinch);
		
		//make sure all motors are stopped 
		mot_ShooterPower.set(0.0);
		mot_ShooterWinch.set(0.0);
		
		}
		public void update(Inputs inputs, Solenoids solenoids){
			
			d_ShooterPower = 0;
			
			if(inputs.b_ShooterPower == true){
				d_ShooterPower = -.60;
	    	}
			
			if(inputs.b_ShooterArm == false){
				d_ShooterPower = .40;
				solenoids.b_ShooterArm = true;
	    	} else {
	    		solenoids.b_ShooterArm = false;
	    	}
			
			mot_ShooterPower.set(d_ShooterPower);
			mot_ShooterWinch.set(d_ShooterWinch);
		}

	}
