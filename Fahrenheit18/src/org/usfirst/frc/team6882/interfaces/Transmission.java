package org.usfirst.frc.team6882.interfaces;

import org.usfirst.frc.team6882.globals.constants;
import org.usfirst.frc.team6882.globals.hardware;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

public class Transmission {

	private Spark left1, left2, right1, right2;
	private Encoder leftEnc, rightEnc;
	private boolean fast = true;
	private boolean gearButtonState = false;
	private boolean encReset = true;

	/**
	 * Creates a transmission for a 6 wheel drop center robot with 2 motors per
	 * side.
	 * 
	 * @param left1
	 * @param left2
	 * @param right1
	 * @param right2
	 */
	public Transmission(Spark left1, Spark left2, Spark right1, Spark right2, Encoder leftEnc, Encoder rightEnc) {
		this.left1 = left1;
		this.left2 = left2;
		this.right1 = right1;
		this.right2 = right2;
		this.leftEnc = leftEnc;
		this.rightEnc = rightEnc;
	}

	/**
	 * Stop the robot.
	 */
	public void stop() {
		left1.set(0);
		left2.set(0);
		right1.set(0);
		right2.set(0);
	}

	/**
	 * Drive the robot.
	 * 
	 * @param speedLeft
	 *            Range of values: -1 (full reverse) to +1 (full forward)
	 * @param speedRight
	 *            Range of values: -1 (full reverse) to +1 (full forward)
	 */
	public void drive(double speedLeft, double speedRight) {
		double currentSpeed;
		
		// determine speed factor based on current gear
		if (fast) {
			currentSpeed = constants.fastSpeedFactor;
		} else {
			currentSpeed = constants.slowSpeedFactor;
		}
		// set motors to ratio of current gear speed based on given desired speed
		left1.set(currentSpeed * speedLeft);
		left2.set(currentSpeed * speedLeft);
		right1.set(currentSpeed * speedRight);
		right2.set(currentSpeed * speedRight);
	}
	
	/**
	 * Drive the robot forward by a variable number of inches
	 * 
	 * @param speed - speed the robot should drive forward
	 * @param inches - desired number of inches to travel
	 * @return - true if traveled desired distance; false otherwise
	 */
	public boolean driveByInches(double speed, double inches) {
		// doubles to hold speed for left and right side
		double left = 0.0;
		double right = 0.0;
		
		// only reset encoders the first time called per step
		if(encReset) {
			encReset = false;
			leftEnc.reset();
			rightEnc.reset();
		}
		
//		//drive left side if it has not gone far enough 
//		if(leftEnc.getDistance() < inches) {
//			left = speed;
//		}
		
		//drive right side if it has not gone far enough
		if(rightEnc.getDistance() < inches) {
			right = speed;
			left = speed;
		}
		
		//call to make motors move 
		this.drive(left, right);
		
		//if robot has gone far enough set encoder to true 
		if(left == 0 && right == 0) {
			encReset = true;
		}
		
		//return encReset: true if gone far enough, false otherwise
		return(encReset);
	}
	
	/**
	 * Turn the robot about its center line a variable number of degrees
	 * 
	 * @param degrees - desired number of degrees to turn
	 * @param rightTurn - true if turning right; false otherwise
	 * @param speed - speed to turn
	 * @return - true when turned far enough; false otherwise
	 */
	public boolean turnDegrees(double degrees, boolean rightTurn, double speed) {
		// variables for storing driving speed on the left and right
		double left = 0.0;
		double right = 0.0;
		
		// convert from degrees to inches each wheel will travel
		double inchesToTravel = degrees * constants.inchesPerDegree;
		
		//Reseting the encoder
		if(encReset) {
			leftEnc.reset();
			rightEnc.reset();
			encReset = false;
		}
		
		// if turning right, left wheel goes forward and right wheel goes backwards
		if (rightTurn){
			//Left forward
//			if(leftEnc.getDistance() < inchesToTravel) {
//				left = speed;
//			}
			
			//Right backward
			if(rightEnc.getDistance() > -(inchesToTravel)) {
				right = speed;
				left = -speed;
			}
		}
		// turning left, so left wheel goes backwards and right wheel goes forwards
		else {
			//Left backward
//			if(leftEnc.getDistance() > -(inchesToTravel)) {
//				left = -speed;
//			}
			
			//Right forward
			if(rightEnc.getDistance() < inchesToTravel) {
				right = -speed;
				left = speed;
			}
		}
		

		// Move the wheels
		this.drive(left, right);
		
		//If turned far enough, prepare for next call via encReset
		if(left == 0 && right == 0) {
			encReset = true;
		}
		
		// return state of resetEnc - true if turned far enough; false if turned not far enough
		return(encReset);
	}
		
	/**
	 * The robot will change gears.
	 * 
	 * @param button
	 *            - indicates the state of the button
	 */
	public void changeGear(boolean button) {
		// if the button is pressed and the gearButtonState indicates that it has not been changed, change the gear
		if (button && !gearButtonState) {
			fast = !fast;
		}

		// set the gearButtonState to match the button state
		gearButtonState = button;
	}
}
