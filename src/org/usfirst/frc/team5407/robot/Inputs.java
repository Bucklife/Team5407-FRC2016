package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Inputs {
	
	Joystick joy_RightDriveStick; 
	//Joystick joy_LeftWeaponsStick; 
	
	double d_TurnArcadeDrive;
	double d_PowerArcadeDrive; 
	

	
	
	public Inputs( int USBConnector_RightDriveStick /*, int USBConnector_joy_LeftWeaponsStick*/){
		
		joy_RightDriveStick = new Joystick(USBConnector_RightDriveStick); 
		zeroInputs();
		//joy_LeftWeaponsStick = new Joystick(USBConnector_joy_LeftWeaponsStick); 
		
		
	}
	public void readValues() {
		
		 d_PowerArcadeDrive = joy_RightDriveStick.getX() * -1 * .50;
		 d_TurnArcadeDrive = joy_RightDriveStick.getY() * -1;
		
		
		
	}
		
	public void zeroInputs(){  
		this.d_TurnArcadeDrive = 0.0;
		this.d_PowerArcadeDrive = 0.0;

	}
	
	
	

}
