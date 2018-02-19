// ====================================================================
// FILE NAME: Hardware.java (Team 6882 - Fahrenheit)
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

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

import org.usfirst.frc.team6882.interfaces.Transmission;
import org.usfirst.frc.team6882.interfaces.Manipulators;

// -------------------------------------------------------
/**
 * puts all of the hardware declarations into one place. In addition, it makes
 * them available to both autonomous and teleop.
 */

public class hardware {
	// **********************************************************
	// DIGITAL I/O CLASSES
	// **********************************************************
	
	// ====================================
	// PWM classes
	// ====================================

	// ------------------------------------
	// Spark Classes
	// ------------------------------------
	public static Spark leftSpark1 = new Spark(3);
	public static Spark leftSpark2 = new Spark(2);
	public static Spark rightSpark1 = new Spark(1);
	public static Spark rightSpark2 = new Spark(0);
	
	public static Spark intakeSparkL = new Spark(4);
	public static Spark intakeSparkR = new Spark(5);
	
	// ------------------------------------
	// Encoders
	// ------------------------------------
	public static Encoder leftDriveEncoder = new Encoder(6, 7);
	public static Encoder rightDriveEncoder = new Encoder(8, 9);

	// ====================================
	// CAN classes
	// ====================================
	
	// ------------------------------------
	// Talon classes
	// ------------------------------------
	public static WPI_TalonSRX liftTalon = new WPI_TalonSRX(0);

	// ====================================
	// Digital Inputs
	// ====================================
	// ------------------------------------
	// Single and double throw switches
	// ------------------------------------
	public static DigitalInput disableAutoSwitch = new DigitalInput(0);
	
	public static DigitalInput leftPosition = new DigitalInput(1);
	public static DigitalInput rightPosition = new DigitalInput(2);


	// **********************************************************
	// SOLENOID I/O CLASSES
	// **********************************************************
	// ====================================
	// Compressor class - runs the compressor
	// ====================================

	// ====================================
	// Pneumatic Control Module
	// ====================================
	

	// ====================================
	// Solenoids
	// ====================================
	// ------------------------------------
	// Double Solenoids
	// ------------------------------------
	// Forward = extended = down or stowed; Backward = retracted = up in working position
	public static DoubleSolenoid flipper1 = new DoubleSolenoid(0,1);
	public static DoubleSolenoid flipper2 = new DoubleSolenoid(2,3);

	
	

	// **********************************************************
	// roboRIO CONNECTIONS CLASSES
	// **********************************************************
	// -------------------------------------
	// Axis/USB Camera class
	// -------------------------------------
	public static UsbCamera POVCamera = CameraServer.getInstance().startAutomaticCapture(0);


	// **********************************************************
	// DRIVER STATION CLASSES
	// **********************************************************

	// ------------------------------------
	// DriverStations class
	// ------------------------------------
	public static DriverStation driverStation = DriverStation.getInstance();

	// ------------------------------------
	// Joystick classes
	// ------------------------------------
	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick = new Joystick(1);
	public static Joystick gamePad1 = new Joystick(2);
	

	
	// **********************************************************
	// DRIVE AND MANIPULATOR CLASSES
	// **********************************************************
	public static Manipulators manipulators = new Manipulators(liftTalon, flipper1, flipper2);
	public static Transmission driveBase = new Transmission(leftSpark1, leftSpark2, rightSpark1, rightSpark2, leftDriveEncoder, rightDriveEncoder);

} // end class
