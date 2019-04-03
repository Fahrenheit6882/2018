// ====================================================================
// FILE NAME: constants.java (Team 6882 - Fahrenheit)
//
// CREATED ON: 15 January 2018
// CREATED BY: C. Robison
// MODIFIED ON:
// MODIFIED BY:
// ABSTRACT:
// This file contains all of the global definitions for the
// hardware objects in the system
//
// NOTE: Please do not release this code without permission from
// Team 6882.
// ====================================================================
package org.usfirst.frc.team6882.globals;



// -------------------------------------------------------
/**
 * puts all of the hardware declarations into one place. In addition, it makes
 * them available to both autonomous and teleop.
 */

public class constants {
	// ------------------------------------
	// Gamepad Controller Constants
	// ------------------------------------
	// Game Pad Axis Indexes
	public static final int gpLeftX = 0;
	public static final int gpLeftY = 1;
	public static final int gpLeftTrig = 2;
	public static final int gpRightTrig = 3;
	public static final int gpRightX = 4;
	public static final int gpRightY = 5;

	// Game Pad Button Indexes
	public static final int gpBtnA = 1;
	public static final int gpBtnB = 2;
	public static final int gpBtnX = 3;
	public static final int gpBtnY = 4;
	public static final int gpBtnLB = 5;
	public static final int gpBtnRB = 6;
	public static final int gpBtnBack = 7;
	public static final int gpBtnStart = 8;
	public static final int gpBtnLStick = 9;
	public static final int gpBtnRStick = 10;

	// ---------------------------------------
	// Hardware Tunables
	// ---------------------------------------

	// Speed Factors
	// changed fast  speed factor for STEM summit demo
	public static double fastSpeedFactor = 0.7;
	public static double slowSpeedFactor = 0.4;
	public static double liftSpeedFactor = 0.7;
	public static double intakeSpeedFactor = 0.4;
	
	//adjustment for on carpet
	public static double rightAdjust = 1.005;
//	public static double leftAdjust = 1.5;
	public static double leftAdjust = 0.0;
	
	// Gravity Adjustment for Lift
	//changing hold point to zero for demo 
	//gravity counter was -0.125
	public static double gravityCounter = -0.165;
	public static double climbHold = -0.2;
	public static double climbActive = 0.7;

	// Dead Zones
	public static double joystickDeadZone = 0.2;
	public static double gamepadDeadZone = 0.05;
		
	//Forward Motion Calculations
	public static double distanceMeasured = 30; //inches
	public static double pulsesCountedLeft = 572.0; //pulses
	public static double pulsesCountedRight = 572.0;
	public static double inchesPerPulseLeft = distanceMeasured / pulsesCountedLeft;
	public static double inchesPerPulseRight = distanceMeasured / pulsesCountedRight;
	
	//Turn calculations
	public static double turnRadius = 13.75;//inches
	public static double turnCirc = Math.PI * 2 * turnRadius;
	public static double inchesPerDegree = (turnCirc/360);
	
	//Autonomous variables
	public static double stoppingDistance = 6.5; //inches at 0.6 speed
	
} // end class
