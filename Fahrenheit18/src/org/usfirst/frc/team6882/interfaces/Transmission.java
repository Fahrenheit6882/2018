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
		
		//drive left side if it has not gone far enough 
		if(leftEnc.getDistance() < inches) {
			left = speed;
		}
		
		//drive right side if it has not gone far enough
		if(rightEnc.getDistance() < inches) {
			right = speed;
		}
		
		//call to make motors move 
		this.drive(left, right);
		
		//if robot has not gone far enough set encoder to true 
		if(left == 0 && right == 0) {
			encReset = true;
		}
		
		//return encReset: true if gone far enough, false otherwise
		return(encReset);
	}
	/**
	 * The robot will change gears.
	 * 
	 * @param button
	 *            - indicates the state of the button
	 */
	public void changeGear(boolean button) {
		if (button && !gearButtonState) {
			fast = !fast;
		}

		gearButtonState = button;
	}
}
