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

import edu.wpi.first.wpilibj.Joystick.AxisType;

import org.usfirst.frc.team6882.globals.constants;

/**
 * This class contains all of the user code for the Teleop part of the match,
 * namely, the Init and Periodic code
 */
public class Teleop {

	static double speedLeft = 0.0;
	static double speedRight = 0.0;
	static double speedLift = 0.0;

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

		if (Math.abs(hardware.gamePad1.getRawAxis(constants.gpRightY)) > constants.gamepadDeadZone) {
			speedLift = hardware.gamePad1.getRawAxis(constants.gpRightY);
		} else {
			speedLift = 0;
		}
		hardware.manipulators.moveLift(speedLift);

		// =================================================================
		// CAMERA CODE
		// =================================================================

		// =================================================================
		// Driving code
		// =================================================================

		// Change gears while driving

		hardware.driveBase.changeGear(hardware.rightStick.getRawButtonPressed(3));

		// Joystick speed controls for driving
		if (Math.abs(hardware.leftStick.getY()) > constants.joystickDeadZone) {
			// hardware.leftSpark1.set(hardware.leftStick.getY() * constants.fastSpeedFactor
			// * -1);
			// hardware.leftSpark2.set(hardware.leftStick.getY() * constants.fastSpeedFactor
			// * -1);
			speedLeft = hardware.leftStick.getY();
		} else {
			// hardware.leftSpark1.set(0);
			// hardware.leftSpark2.set(0);
			speedLeft = 0;
		}

		if (Math.abs(hardware.rightStick.getY()) > constants.joystickDeadZone) {
			// hardware.rightSpark1.set(hardware.rightStick.getY() *
			// constants.fastSpeedFactor * 1);
			// hardware.rightSpark2.set(hardware.rightStick.getY() *
			// constants.fastSpeedFactor * 1);
			speedRight = hardware.rightStick.getY();
		} else {
			// hardware.rightSpark1.set(0);
			// hardware.rightSpark2.set(0);
			speedRight = 0;
		}

		hardware.driveBase.drive(speedLeft, speedRight);

	}
	// end
	// Periodic

	/**
	 * stores print statements for future use in the print "bank", statements are
	 * commented out when not in use, when you write a new print statement,
	 * "deposit" the statement in the correct "bank" do not "withdraw" statements,
	 * unless directed to.
	 * 
	 * NOTE: Keep the groupings below, which correspond in number and order as the
	 * hardware declarations in the HARDWARE class
	 */
	public static void printStatements() {

		// =================================
		// Motor
		// Prints the value of motors
		// =================================

		// =================================
		// CAN items
		// prints value of the CAN controllers
		// =================================

		// =================================
		// Relay
		// =================================

		// =================================
		// Digital Inputs
		// =================================

		// ---------------------------------
		// Switches
		// prints state of switches
		// ---------------------------------

		// ---------------------------------
		// Encoders
		// ---------------------------------

		// ---------------------------------
		// Red Light/IR Sensors
		// prints the state of the sensor
		// ---------------------------------

		// =================================
		// Pneumatics
		// =================================

		// ---------------------------------
		// Compressor
		// prints information on the compressor
		// ---------------------------------

		// ---------------------------------
		// Solenoids
		// prints the state of solenoids
		// ---------------------------------

		// =================================
		// Analogs
		// =================================

		// ---------------------------------
		// pots
		// where the pot is turned to
		// ---------------------------------

		// --------------------------
		// Sonar/UltraSonic
		// --------------------------

		// =========================
		// Servos
		// =========================
		//
		// ================
		// GYRO
		// =================

		// =================================
		// Connection Items
		// =================================

		// ---------------------------------
		// Cameras
		// prints any camera information required
		// ---------------------------------

		// =================================
		// Driver station
		// =================================

		// ---------------------------------
		// Joysticks
		// information about the joysticks
		// ---------------------------------

		// ---------------------------------
		// timers
		// what time does the timer have now
		// ---------------------------------

	} // end printStatements

	/*
	 * ================================ Constants ================================
	 */

} // end class
