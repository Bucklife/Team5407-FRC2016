package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Inputs {
	
	Joystick joy_RightDriveStick; 
	Joystick joy_LeftWeaponsStick; 
	
	double d_TurnArcadeDrive;
	double d_PowerArcadeDrive; 
	double d_ShooterPower;
	double d_LiftWinchPower;
	
	boolean b_ShiftGears; 
	boolean b_ShooterKicker;
	boolean b_ShooterArm;
	boolean b_ShooterExtension; 
	boolean b_ScissorLift;
	boolean b_WinchBrake;
	
	
	public Inputs(int USBConnector_RightDriveStick, int USBConnector_joy_LeftWeaponsStick){
		
		joy_RightDriveStick = new Joystick(USBConnector_RightDriveStick); 
		joy_LeftWeaponsStick = new Joystick(USBConnector_joy_LeftWeaponsStick); 
		zeroInputs();
		
		
		
	}
	public void readValues() {
		
		 d_PowerArcadeDrive = joy_RightDriveStick.getX() * -1 * .50;
		 d_TurnArcadeDrive = joy_RightDriveStick.getY() * -1;
		 

		 
		 d_ShooterPower = joy_RightDriveStick.getZ() * -1;
		 b_ShiftGears = joy_RightDriveStick.getTrigger();
		 b_ShooterKicker = joy_RightDriveStick.getRawButton(2); 
		 b_ShooterArm = joy_RightDriveStick.getRawButton(3);
		 b_ShooterExtension = joy_RightDriveStick.getRawButton(4); 	
		 b_ScissorLift = joy_RightDriveStick.getRawButton(5);
		 b_WinchBrake = joy_LeftWeaponsStick.getRawButton(6);
		 d_LiftWinchPower = joy_LeftWeaponsStick.getZ() * -1;
		
	}
		
	public void zeroInputs(){  
		this.d_TurnArcadeDrive = 0.0;
		this.d_PowerArcadeDrive = 0.0;

	}
	
	

}
