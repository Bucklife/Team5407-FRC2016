package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter{
	
	Talon mot_ShooterPower; 
	AnalogInput ana_WinchPotentiometer; 
	
	VictorSP mot_ShooterWinch;
		
	double d_ShooterPower;	
	double d_ShooterWinch;
	
	int i_WinchPotentiometer;
	
		public Shooter(int PWMConnector_ShooterPower, int PWMConnector_ShooterWinch, int ANAConnector_WinchPotentiometer){
			
		mot_ShooterPower = new Talon(PWMConnector_ShooterPower); 
		mot_ShooterWinch = new VictorSP(PWMConnector_ShooterWinch);
		ana_WinchPotentiometer = new AnalogInput(ANAConnector_WinchPotentiometer);
		
		//make sure all motors are stopped 
		mot_ShooterPower.set(0.0);
		mot_ShooterWinch.set(0.0);
		
		}
		
		public void readValues(){
			i_WinchPotentiometer = ana_WinchPotentiometer.getAverageValue();
			SmartDashboard.putNumber("Winch PID", i_WinchPotentiometer);
		}
		
		public void update(Inputs inputs, Solenoids solenoids){
			
			d_ShooterPower = 0;
			
			if(inputs.b_ShooterPower == true){
				d_ShooterPower = -.55;
	    	}
			
			if(inputs.b_ShooterArm == false){
				d_ShooterPower = .50;
				solenoids.b_ShooterArm = true;
	    	} else {
	    		solenoids.b_ShooterArm = false;
	    	}
			
			mot_ShooterPower.set(d_ShooterPower);
			mot_ShooterWinch.set(d_ShooterWinch);
		}

	}
