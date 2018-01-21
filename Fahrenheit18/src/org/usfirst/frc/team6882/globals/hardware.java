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
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

import org.usfirst.frc.team6882.interfaces.Transmission;


// -------------------------------------------------------
/**
 * puts all of the hardware declarations into one place. In addition, it makes
 * them available to both autonomous and teleop.
 */

public class hardware
{
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
	
	public static Transmission driveBase = new Transmission (leftSpark1, leftSpark2, rightSpark1, rightSpark2);
	
// ------------------------------------
// Jaguar classes
// ------------------------------------

// ------------------------------------
// Talon classes
// ------------------------------------
	//public static Talon liftTalon = new Talon(0);
	

// ------------------------------------
// Victor Classes
// ------------------------------------

// ====================================
// CAN classes
// ====================================

// ====================================
// Relay classes
// ====================================

// ====================================
// Digital Inputs
// ====================================
// ------------------------------------
// Single and double throw switches
// ------------------------------------
public static DigitalInput disableAutoSwitch = new DigitalInput(20);
	
// ------------------------------------
// Gear Tooth Sensors
// ------------------------------------

// ------------------------------------
// Encoders
// ------------------------------------
//public static Encoder leftEncoder = new Encoder(0, 1);
//public static Encoder rightEncoder = new Encoder(2, 3);

// -------------------------------------
// Red Light/IR Sensor class
// -------------------------------------

// ====================================
// I2C Classes
// ====================================

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


// ------------------------------------
// Single Solenoids
// ------------------------------------

// **********************************************************
// ANALOG I/O CLASSES
// **********************************************************
// ====================================
// Analog classes
// ====================================
// ------------------------------------
// Gyro class
// ------------------------------------

// -------------------------------------
// Potentiometers
// -------------------------------------
	
// -------------------------------------
// Sonar/Ultrasonic
// -------------------------------------

// **********************************************************
// roboRIO CONNECTIONS CLASSES
// **********************************************************
// -------------------------------------
// Axis/USB Camera class
// -------------------------------------

// -------------------------------------
// declare the USB camera server and the
// USB camera it serves
// -------------------------------------

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


} // end class
