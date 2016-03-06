package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter{
	
	Talon mot_ShooterPower; 
	AnalogInput ana_WinchPotentiometer; 
	AnalogInput ana_ShooterHallEffectSensor; 
	
	VictorSP mot_ShooterWinch;
		
	double d_ShooterPower;	
	double d_ShooterWinch;
	
	// PID System Variables //
    // final double setPoints[] = {0.1, 0.25}; // low and high setpoints
    PIDController pidControllerShooter;
    PIDController pidControllerWinch;
    	//proportional, integral, and derivative speed constants; motor inverted 
    	//DANGER: when tuning PID constants, high/inappropriate values for pGain, iGain,
    	//and dGain may cause dangerous, uncontrollable, or undesired behavior!
    final double pGain_S = 0.25, iGain_S = 0.0, dGain_S = 0.0; //these may need to be positive for a non-inverted motor
    final double pGain_W = 0.5, iGain_W = 0.0, dGain_W = 0.0; //these may need to be positive for a non-inverted motor
  	
    double d_WinchPotentiometer;
    double d_ShooterHallEffectSensor;
	
	public Shooter(int PWMConnector_ShooterPower, int PWMConnector_ShooterWinch, int ANAConnector_WinchPotentiometer, int ANAConnector_ShooterHallEffectSensor){
			
		mot_ShooterPower = new Talon(PWMConnector_ShooterPower); 
		mot_ShooterWinch = new VictorSP(PWMConnector_ShooterWinch);
		ana_WinchPotentiometer = new AnalogInput(ANAConnector_WinchPotentiometer);
		ana_ShooterHallEffectSensor = new AnalogInput(ANAConnector_ShooterHallEffectSensor);
		
		//make sure all motors are stopped 
		mot_ShooterPower.set(0.0);
		mot_ShooterWinch.set(0.0);
		
		// PID Shooter
		pidControllerShooter = new PIDController(pGain_S, iGain_S, dGain_S, ana_ShooterHallEffectSensor, mot_ShooterPower);
		pidControllerShooter.setContinuous();
		pidControllerShooter.setInputRange(0, 5);
		pidControllerShooter.setOutputRange(-1, 1);
		pidControllerShooter.setAbsoluteTolerance(0.2);
		
		// PID Winch
		pidControllerWinch = new PIDController(pGain_W, iGain_W, dGain_W, ana_WinchPotentiometer, mot_ShooterWinch);
		pidControllerWinch.setContinuous();
		pidControllerWinch.setInputRange(0, 5);
		pidControllerWinch.setOutputRange(-1, 1);
		pidControllerWinch.setAbsoluteTolerance(0.2);
	}
		
	public void readValues(){
		d_WinchPotentiometer = ana_WinchPotentiometer.getAverageVoltage();
		d_ShooterHallEffectSensor = ana_ShooterHallEffectSensor.getAverageVoltage();
		SmartDashboard.putNumber("Winch PID", d_WinchPotentiometer);
		SmartDashboard.putNumber("Hall Effect Sensor", d_ShooterHallEffectSensor);
	}
		
	public void update(Inputs inputs, Solenoids solenoids){
		
		d_ShooterPower = 0;
		
		// Test Low and High Shot Buttons
		if(inputs.b_LowShot == true){
			mot_ShooterPower.setInverted(true);
			pidControllerShooter.enable(); //begin PID control
			pidControllerShooter.setSetpoint(3);
			SmartDashboard.putNumber("Hall Effect PID Low", pidControllerShooter.get());
			
			pidControllerWinch.enable(); //begin PID control
			pidControllerWinch.setSetpoint(4);
			SmartDashboard.putNumber("Winch PID Low", pidControllerWinch.get());
			
		} else if(inputs.b_HighShot == true){
			mot_ShooterPower.setInverted(true);
			pidControllerShooter.enable(); //begin PID control
			pidControllerShooter.setSetpoint(3);
			SmartDashboard.putNumber("Hall Effect PID High", pidControllerShooter.get());
			
			pidControllerWinch.enable(); //begin PID control
			pidControllerWinch.setSetpoint(1);
			SmartDashboard.putNumber("Winch PID High", pidControllerWinch.get());
			
		} else {
			//mot_ShooterPower.setInverted(false);
			// pidControllerShooter.disable(); //begin PID control
			// pidControllerWinch.disable(); //begin PID control
		}
		
		// Test spinning up shooter wheel
		if(inputs.b_ShooterPower == true){
			d_ShooterPower = -0.65;
    	}
		
		// Intake mechanism
		if(inputs.b_ShooterArm == false){
			d_ShooterPower = 0.50;
			solenoids.b_ShooterArm = true;
    	} else {
    		solenoids.b_ShooterArm = false;
    	}
		
		//portcullis opener 
		if(inputs.b_PortcullisOpener == true){
			d_ShooterPower = -0.50;
			solenoids.b_ShooterArm = true;
    	}
		
		mot_ShooterPower.set(d_ShooterPower);
		mot_ShooterWinch.set(d_ShooterWinch);
		

	}

}
