/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
// ====================================================================
// FILE NAME: Fahrenheit18.java (Team 6882 - Fahrenheit Robotics)
//
// CREATED ON: 15 January 2018
// CREATED BY: C. Robison
// MODIFIED ON:
// MODIFIED BY:
// ABSTRACT:
// This file is where almost all code for the robot will be
// written. All of these functions are functions that should
// override methods in the base class (IterativeRobot). The
// functions are as follows:
//
// -----------------------------------------------------
// autonomousInit() - Initialization code for autonomous mode
// should go here. Will be called each time the robot enters
// autonomous mode.
//
// disabledInit() - Initialization code for disabled mode should
// go here. This function will be called one time when the
// robot first enters disabled mode.
//
// robotInit() - Robot-wide initialization code should go here.
// It will be called exactly 1 time.
//
// teleopInit() - Initialization code for teleop mode should go here.
// Will be called each time the robot enters teleop mode.
// -----------------------------------------------------
// autonomousPeriodic() - Periodic code for autonomous mode should
// go here. Will be called periodically at a regular rate while
// the robot is in autonomous mode.
//
// disabledPeriodic() - Periodic code for disabled mode should go here.
// Will be called periodically at a regular rate while the robot
// is in disabled mode.
//
// teleopPeriodic() - Periodic code for teleop mode should go here.
// Will be called periodically at a regular rate while the robot
// is in teleop mode.
// -----------------------------------------------------
// autonomousContinuous() - Continuous code for autonomous mode should
// go here. Will be called repeatedly as frequently as possible
// while the robot is in autonomous mode.
//
// disabledContinuous() - Continuous code for disabled mode should go
// here. Will be called repeatedly as frequently as possible while
// the robot is in disabled mode.
//
// teleopContinuous() - Continuous code for teleop mode should go here.
// Will be called repeatedly as frequently as possible while the
// robot is in teleop mode.
// -----------------------------------------------------
// Other functions not normally used
// startCompetition() - This function is a replacement for the WPI
// supplied 'main loop'. This should not normally be written or
// used.
// -----------------------------------------------------
//
// NOTE: Please do not release this code without permission from
// Team 6882.
// ====================================================================

package org.usfirst.frc.team6882.robot;

import org.usfirst.frc.team6882.globals.constants;
import org.usfirst.frc.team6882.globals.hardware;
import org.usfirst.frc.team6882.robot.Autonomous.State;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

// -------------------------------------------------------

/**
 * declares all the code necessary to extend the IterativeRobot class. These are
 * all the methods needed to run the robot during a match
 */
public class Robot extends IterativeRobot {
	// =================================================
	// private data for the class
	// =================================================
	// -------------------------------------------------------

	/**
	 * Initialization code for autonomous mode should go here. Will be called once
	 * when the robot enters autonomous mode. *
	 */
	@Override
	public void autonomousInit() {
		// ---------------------------------------
		// start setup - tell the user we are beginning
		// setup
		// ---------------------------------------
		System.out.println("Started AutonousInit().");

		// =========================================================
		// User code goes below here
		// =========================================================
		Autonomous.init();
		// =========================================================
		// User code goes above here
		// =========================================================
		// ---------------------------------------
		// done setup - tell the user we are complete
		// setup
		// ---------------------------------------
		System.out.println("Completed AutonousInit().");
	} // end autonomousInit

	// -------------------------------------------------------
	/**
	 * Non-User Periodic code for autonomous mode should go here. Will be called
	 * periodically at a regular rate while the robot is in autonomous mode. This in
	 * turn calls the Autonomous class's Periodic function, which is where the user
	 * code should be placed.
	 *
	 * @author Bob Brown
	 * @written Jan 2, 2011 -------------------------------------------------------
	 */
	@Override
	public void autonomousPeriodic() {
		// System.out.println("Started AutonousPeriodic().");
		// =========================================================
		// User code goes below here
		// =========================================================
		Autonomous.periodic();
		// =========================================================
		// User code goes above here
		// =========================================================

	}// end autonomousPeriodic
		// -------------------------------------------------------

	/**
	 * Initialization code for disabled mode should go here. Will be called once
	 * when the robot enters disabled mode.
	 *
	 * @author Bob Brown
	 * @written Jan 2, 2011 -------------------------------------------------------
	 */
	@Override
	public void disabledInit() {
		// ---------------------------------------
		// start setup - tell the user we are beginning
		// setup
		// ---------------------------------------
		System.out.println("Started DisabledInit().");
		// =========================================================
		// User code goes below here
		// =========================================================

		// =========================================================
		// User code goes above here
		// =========================================================
		// ---------------------------------------
		// done setup - tell the user we are complete
		// setup
		// ---------------------------------------
		System.out.println("Completed DisabledInit().");
	} // end disabledInit

	// -------------------------------------------------------
	/**
	 * Periodic code for disabled mode should go here. Will be called periodically
	 * at a regular rate while the robot is in disabled mode. Code that can be
	 * "triggered" by a joystick button can go here. This can set up configuration
	 * things at the driver's station for instance before a match.
	 *
	 * @author Bob Brown
	 * @written Jan 2, 2011 -------------------------------------------------------
	 */
	@Override
	public void disabledPeriodic() {
		// -------------------------------------
		// Watch dog code used to go here.
		// -------------------------------------
		// =========================================================
		// User code goes below here
		// =========================================================

		// =========================================================
		// User code goes above here
		// =========================================================
	} // end disabledPeriodic
		// -------------------------------------------------------

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code for the robot.
	 *
	 * @author Bob Brown
	 * @written Jan 2, 2011 -------------------------------------------------------
	 */
	@Override
	public void robotInit() {
		// ---------------------------------------
		// start setup - tell the user we are beginning
		// setup
		// ---------------------------------------
		System.out.println("Started robotInit()");

		// =========================================================
		// User code goes below here
		
		// Invert motor controllers for right side of drive so positive is forward
		hardware.leftSpark1.setInverted(true);
		hardware.leftSpark2.setInverted(true);
		
		// Invert motor controller for one side of intake
		hardware.intakeSparkRight.setInverted(true);
		
		// Set drive encoders distance per pulse
		hardware.leftDriveEncoder.setDistancePerPulse(constants.inchesPerPulse);
		hardware.rightDriveEncoder.setDistancePerPulse(constants.inchesPerPulse);
		
		

		// User code goes above here
		// =========================================================
		// ---------------------------------------
		// done setup - tell the user we are complete
		// setup
		// ---------------------------------------
		System.out.println("Fahrenheit-18 is started.  All hardware items created.");
	} // end
		// robotInit

	// -------------------------------------------------------
	/**
	 * Non-User initialization code for teleop mode should go here. Will be called
	 * once when the robot enters teleop mode, and will call the Teleop class's Init
	 * function, where the User code should be placed.
	 *
	 * @author Bob Brown
	 * @written Jan 2, 2011 -------------------------------------------------------
	 */
	@Override
	public void teleopInit() {
		// ---------------------------------------
		// start setup - tell the user we are beginning
		// setup
		// ---------------------------------------
		System.out.println("Started teleopInit().");
		// =========================================================
		// User code goes below here

		Teleop.init();

		// User code goes above here
		// =========================================================
		// ---------------------------------------
		// done setup - tell the user we are complete
		// setup
		// ---------------------------------------
		System.out.println("Completed TeleopInit().");
	} // end teleopInit

	// -------------------------------------------------------
	/**
	 * Non-User Periodic code for teleop mode should go here. Will be called
	 * periodically at a regular rate while the robot is in teleop mode, and will in
	 * turn call the Teleop class's Periodic function.
	 *
	 * @author Bob Brown
	 * @written Jan 2, 2011 -------------------------------------------------------
	 */
	@Override
	public void teleopPeriodic() {
		// -------------------------------------
		// Call the Teleop class's Periodic function,
		// which contains the user code.
		// -------------------------------------

		// =========================================================
		// User code goes below here

		Teleop.periodic();

		// User code goes above here
		// =========================================================

	} // end teleopPeriodic
		// -------------------------------------------------------

	/**
	 * Initialization code for test mode should go here. Will be called once when
	 * the robot enters test mode.
	 *
	 * @author Bob Brown
	 * @written Jan 2, 2015 -------------------------------------------------------
	 */
	@Override
	public void testInit() {
		// =========================================================
		// User code goes below here
		// =========================================================
//		boolean leftPos = hardware.leftPosition.get();
//		boolean rightPos = hardware.rightPosition.get();
//		
//		System.out.println("Left position = " + !leftPos);
//		System.out.println("Right position = " + !rightPos);
		
		//testing encoders
		//hardware.leftDriveEncoder.reset();
		//hardware.rightDriveEncoder.reset();
		
		//System.out.println("Encoders reset. Left: " + hardware.leftDriveEncoder.getDistance() + " Right: " + hardware.rightDriveEncoder.getDistance());
		
		//testing auto under here
		
		// =========================================================
		// User code goes above here
		// =========================================================

	} // end testInit
		// -------------------------------------------------------

	/**
	 * Periodic code for test mode should go here. Will be called periodically at a
	 * regular rate while the robot is in test mode.
	 *
	 * @author Bob Brown
	 * @written Jan 2, 2015 -------------------------------------------------------
	 */
	@Override
	public void testPeriodic() {
		// =========================================================
		// User code goes below here
		// =========================================================
		
		//Test Switches
		if(hardware.leftStick.getRawButtonPressed(7))
		{
			System.out.println("disable = "  + hardware.disableAutoSwitch.get());
			System.out.println("Left = " + hardware.leftPosition.get());
			System.out.println("Right = " + hardware.rightPosition.get());
			System.out.println("liftMax = " + hardware.liftMax.get());
			System.out.println("liftMin = " + hardware.liftMin.get());
			System.out.println("cubeIn = " + hardware.cubeIn.get());
		}
		
		
		//Test drive by inches and turn by degrees
		
//		if (hardware.driveBase.driveByInches(-0.3, 168))
//		{
//			hardware.rightDriveEncoder.reset();
//			hardware.driveBase.stop();
//		}
//		if(hardware.leftStick.getRawButtonPressed(7))
//		{
//			tempLeft = hardware.leftDriveEncoder.get();
//			tempRight = hardware.rightDriveEncoder.get();
//		
//			temp++;
//			System.out.println("Temp = " + temp + " Left = " + tempLeft + " Right = " + tempRight);
//		}
		
//		if(!d)
//		{
//			if(hardware.driveBase.driveByInches(-0.5, 30))
//			{
//				System.out.println("30 inches done.");
//				d = true;
//			}
//		}
//		
//		if(!d)
//		{
//			if(hardware.driveBase.turnDegrees(90, false, 0.5))
//			{
//				System.out.println("90 degress done.");
//				d = true;
//			}
//		}		
		
		//Test button outputs
//		boolean btn1 = hardware.gamePad1.getRawButtonPressed(1);
//		boolean btn2 = hardware.gamePad1.getRawButtonPressed(2);
//		boolean btn3 = hardware.gamePad1.getRawButtonPressed(3);
//		boolean btn4 = hardware.gamePad1.getRawButtonPressed(4);
//		boolean btn5 = hardware.gamePad1.getRawButtonPressed(5);
//		boolean btn6 = hardware.gamePad1.getRawButtonPressed(6);
//		boolean btn7 = hardware.gamePad1.getRawButtonPressed(7);
//		boolean btn8 = hardware.gamePad1.getRawButtonPressed(8);
//		boolean btn9 = hardware.gamePad1.getRawButtonPressed(9);
//		boolean btn10 = hardware.gamePad1.getRawButtonPressed(10);
//		boolean btn11 = hardware.gamePad1.getRawButtonPressed(11);
//		boolean btn12 = hardware.gamePad1.getRawButtonPressed(12);
//		boolean btn13 = hardware.gamePad1.getRawButtonPressed(13);
//		boolean btn14 = hardware.gamePad1.getRawButtonPressed(14);
//		boolean btn15 = hardware.gamePad1.getRawButtonPressed(15);
//		boolean btn16 = hardware.gamePad1.getRawButtonPressed(16);
//		
//		if(btn1 || btn2 || btn3 || btn4 || btn5 || btn6 || btn7 || btn8 || btn9 || btn10 || btn11 || btn12 || btn13 || btn14 || btn15 || btn16)
//		{
//			System.out.println("Btn 1: " + btn1 + " Btn 2: " + btn2 + " Btn 3: " + btn3 + " Btn 4: " + btn4);
//			System.out.println("Btn 5: " + btn5 + " Btn 6: " + btn6 + " Btn 7: " + btn7 + " Btn 8: " + btn8);
//			System.out.println("Btn 9: " + btn9 + " Btn 10: " + btn10 + " Btn 11: " + btn11 + " Btn 12: " + btn12);
//			System.out.println("Btn 13: " + btn13 + " Btn 14: " + btn14 + " Btn 15: " + btn15 + " Btn 16: " + btn16);
//		}
		
		
		// =========================================================
		// User code goes above here
		// =========================================================

	} // end testPeriodic

	// ==========================================
	// GLOBAL TEST VARIABLES
	// ==========================================
	int temp = 0;
	double tempLeft = 0;
	double tempRight = 0;
	boolean d = false;

} // end class
