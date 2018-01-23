package org.usfirst.frc.team6882.interfaces;

import org.usfirst.frc.team6882.globals.constants;
import org.usfirst.frc.team6882.globals.hardware;

import edu.wpi.first.wpilibj.Spark;

public class Transmission {

	private static Spark left1, left2, right1, right2;
	private boolean fast = true;
	private boolean gearButtonState = false;

	/**
	 * Creates a transmission for a 6 wheel drop center robot with 2 motors per
	 * side.
	 * 
	 * @param left1
	 * @param left2
	 * @param right1
	 * @param right2
	 */
	public Transmission(Spark left1, Spark left2, Spark right1, Spark right2) {
		this.left1 = left1;
		this.left2 = left2;
		this.right1 = right1;
		this.right2 = right2;
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

		if (fast) {
			currentSpeed = constants.fastSpeedFactor;
		} else {
			currentSpeed = constants.slowSpeedFactor;
		}

		left1.set(currentSpeed * speedLeft);
		left2.set(currentSpeed * speedLeft);
		right1.set(currentSpeed * speedRight);
		right2.set(currentSpeed * speedRight);
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
