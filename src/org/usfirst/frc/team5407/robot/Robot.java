package org.usfirst.frc.team5407.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	// RobotDrive myRobot;
	// Joystick joy_RightDriveStick;
	// Joystick joy_LeftWeaponsStick; 
	int autoLoopCounter;
	RobotBase robotbase; 
	Inputs inputs; 
	Solenoids solenoids; 
	Shooter shooter; 
	Winch winch;
	Auton auton;
	
	CameraServer server;

    public Robot() {
        server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam0");
    }

	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	robotbase = new RobotBase(0,1);
    	inputs = new Inputs(0,1,2);
    	shooter = new Shooter(2,5,1,2);
    	winch = new Winch(3,7,6);
    	
    	// Instructions to add a new solenoid:
    	// 1) Declare solenoids below.
    	// 2) Add the inputs to set the solenoid variables
    	// 3) Setup the solenoids objects in the Solenoids class
    	// 4) Set the solenoid variables to equal the input variables in RobotThink below
    	
    	solenoids = new Solenoids(0,	// Drive train shift gears
    							  1,	// Shooter kicker 
    							  2,	// Shooter arm 
    							  3,	// Shooter Extension
    							  4		// Scissors Lift
    							  );
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	autoLoopCounter = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if(autoLoopCounter < 125) //Check if we've completed 100 loops (approximately 2 seconds)
		{
			// myRobot.drive(-0.5, 0.0); 	// drive forwards half speed
        	robotbase.mot_LeftDriveMotor.set(0.75);
        	robotbase.mot_RightDriveMotor.set(0.75);
        	autoLoopCounter++;
			} else {
			// myRobot.drive(0.0, 0.0); 	// stop robot
	    	robotbase.mot_LeftDriveMotor.set(0);
	    	robotbase.mot_RightDriveMotor.set(0);
		}
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    	// robotbase.reset();
    	// Timer.delay(2);
    	winch.zeroInputs();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        // myRobot.arcadeDrive(joy_RightDriveStick);
    	
    	//robotbase.reset();	// resets gyro
    	//Timer.delay(2);		// waits for gyro to reset
        
        while (isOperatorControl() && isEnabled()) {
            /** robot code here! **/
            inputs.readValues();
            robotbase.update();
            solenoids.update();
            shooter.update(inputs, solenoids);
            shooter.readValues();
            winch.update(inputs, solenoids);
            robotThink();
            
            // robotbase.gyroUpdate();
                        
            // Timer.delay(0.005);		// wait for a motor update time
        }

    }
    
    public void robotThink() {
    	robotbase.d_LeftDrivePower = inputs.d_PowerArcadeDrive - inputs.d_TurnArcadeDrive;
    	robotbase.d_RightDrivePower = inputs.d_PowerArcadeDrive + inputs.d_TurnArcadeDrive;
    	solenoids.b_ShiftGears = inputs.b_ShiftGears;
    	solenoids.b_ShooterKicker = inputs.b_ShooterKicker;
    	solenoids.b_ShooterExtension = inputs.b_ShooterExtension;
    	
    	// testing using potentiometer as limit switch
    	if(shooter.d_WinchPotentiometer > 4.4 && inputs.d_ShooterWinch > 0.1){
    		shooter.d_ShooterWinch = 0;
    	} else if (shooter.d_WinchPotentiometer < 0.5 && inputs.d_ShooterWinch < -0.1){
    		shooter.d_ShooterWinch = 0;
    	} else {
    		shooter.d_ShooterWinch = inputs.d_ShooterWinch;
    	}
    	
    	// Obsolete:
    	// solenoids.b_ShooterArm = inputs.b_ShooterArm; <-- not needed - using method inside shooter class instead
    	// solenoids.b_ScissorLift = inputs.b_ScissorLift; <-- not needed - using method inside winch class instead
    	
    }
    
    
    /**
     *
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
