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
	// Public Constants
	// ------------------------------------
	// Game Pad Axis Indexes
	public static final int gpLeftX = 0;
	public static final int gpLeftY = 1;
	public static final int gpLeftTrig = 2;
	public static final int gpRightTrig = 3;
	public static final int gpRightX = 4;
	public static final int gpRightY = 5;

	// Game Pad Button Indexes
	public static final int gpBtnA = 0;
	public static final int gpBtnB = 1;
	public static final int gpBtnX = 2;
	public static final int gpBtnY = 3;
	public static final int gpBtnLB = 4;
	public static final int gpBtnRB = 5;
	public static final int gpBtnBack = 6;
	public static final int gpBtnStart = 7;
	public static final int gpBtnLStick = 8;
	public static final int gpBtnRStick = 9;

	// -------------------------------------
	// Private Constants
	// -------------------------------------

	// ---------------------------------------
	// Hardware Tunables
	// ---------------------------------------

	// Speed Factors
	public static double fastSpeedFactor = 0.6;
	public static double slowSpeedFactor = 0.2;
	public static double liftSpeedFactor = 0.5;

	// Dead Zones
	public static double joystickDeadZone = 0.2;
	public static double gamepadDeadZone = 0.1;

} // end class
