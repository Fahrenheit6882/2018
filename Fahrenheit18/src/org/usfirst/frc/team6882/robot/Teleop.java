/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/
// ====================================================================
// FILE NAME: Teleop.java (Team 6882 - Fahrenheit)
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
// -----------------------------------------------------
// Init() - Initialization code for teleop mode
// should go here. Will be called each time the robot enters
// teleop mode.
// -----------------------------------------------------
// Periodic() - Periodic code for teleop mode should
// go here. Will be called periodically at a regular rate while
// the robot is in teleop mode.
// -----------------------------------------------------
//
// NOTE: Please do not release this code without permission from
// Team 6882.
// ====================================================================
package org.usfirst.frc.team6882.robot;

import org.usfirst.frc.team6882.globals.hardware;

import org.usfirst.frc.team6882.globals.constants;

/**
 * This class contains all of the user code for the Teleop part of the match,
 * namely, the Init and Periodic code
 */
public class Teleop {

	// Initialize variables for reading joystick inputs - will be used later
	static double speedLeft = 0.0;
	static double speedRight = 0.0;
	static double speedLift = 0.0;
	static boolean climb = false;

	/**
	 * User Initialization code for teleop mode should go here. Will be called once
	 * when the robot enters teleop mode.
	 */
	public static void init() {

	} // end Init

	/**
	 * User Periodic code for teleop mode should go here. Will be called
	 * periodically at a regular rate while the robot is in teleop mode.
	 */
	public static void periodic() {
		// =================================================================
		// OPERATOR CONTROLS
		// =================================================================
		
		//------------------------------------------------------------------
		// LIFT CONTROLS
		//------------------------------------------------------------------
		// joystick speed control for elevator
		if (Math.abs(hardware.gamePad1.getRawAxis(constants.gpRightY)) > constants.gamepadDeadZone) {
			speedLift = hardware.gamePad1.getRawAxis(constants.gpRightY);
		} else {
			speedLift = 0;
		}
		
		// move the lift
		hardware.manipulators.moveLift(speedLift);
		
		//increase lift speed
		if(hardware.gamePad1.getRawButtonPressed(constants.gpBtnBack))
		{
			constants.liftSpeedFactor = 1.0;
		}
		
		//triggering climb to a start button
		if(hardware.gamePad1.getRawButtonPressed(constants.gpBtnStart))
		{
			climb = true;
		}
		
		if(climb)
		{
			hardware.manipulators.climb();
		}
		
		if(hardware.leftStick.getRawButtonPressed(7))
		{
			System.out.println("disable = "  + hardware.autoSwitch.get());
//			System.out.println("Left = " + hardware.leftPosition.get());
//			System.out.println("Right = " + hardware.rightPosition.get());
			System.out.println("liftMax = " + hardware.liftMax.get());
			System.out.println("liftMin = " + hardware.liftMin.get());
			hardware.rightDriveEncoder.reset();
			hardware.leftDriveEncoder.reset();
		}
		
		
		//------------------------------------------------------------------
		// INTAKE CONTROLS
		//------------------------------------------------------------------
		if(hardware.gamePad1.getRawAxis(constants.gpLeftTrig) > 0.1)
		{
			hardware.manipulators.outtake();
		}
		else if(hardware.gamePad1.getRawAxis(constants.gpRightTrig) > 0.1)
		{
			hardware.manipulators.intake();
		}
		else
		{
			hardware.manipulators.stoptake();
		}
		

		//increase intake speed on RB
		if(hardware.gamePad1.getRawButtonPressed(constants.gpBtnRB))
		{
			constants.intakeSpeedFactor = 0.7;
		}
		
		//------------------------------------------------------------------
		// MANIPULATOR ACTUATION
		//------------------------------------------------------------------
		hardware.manipulators.pushDownManipulator(hardware.gamePad1.getRawButtonPressed(constants.gpBtnA));
		hardware.manipulators.pullUpManipulator(hardware.gamePad1.getRawButtonPressed(constants.gpBtnB));	

		

		// =================================================================
		// DRIVER CONTROLS
		// =================================================================

		//------------------------------------------------------------------
		// GEAR CHANGES
		//------------------------------------------------------------------
		// Change gears if the button is pressed and it has not already changed gears.
		hardware.driveBase.changeGear(hardware.rightStick.getRawButtonPressed(3));

		//------------------------------------------------------------------
		// DIRECTIONAL CONTROLS
		//------------------------------------------------------------------
		
		// Joystick speed controls for driving
		if (Math.abs(hardware.leftStick.getY()) > constants.joystickDeadZone) {
			speedLeft = hardware.leftStick.getY();
		} else {
			speedLeft = 0;
		}

		if (Math.abs(hardware.rightStick.getY()) > constants.joystickDeadZone) {
			speedRight = hardware.rightStick.getY();
		} else {
			speedRight = 0;
		}
		
		// Drive the robot according to Joystick input
		hardware.driveBase.drive(speedLeft, speedRight);

	}
	// end
	// Periodic

} // end class
