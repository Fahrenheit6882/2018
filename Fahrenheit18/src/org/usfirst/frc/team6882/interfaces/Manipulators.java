package org.usfirst.frc.team6882.interfaces;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;

public class Manipulators {
	private Talon liftMotor;
	private DoubleSolenoid flipper1;
	private DoubleSolenoid flipper2;

	/**
	 * Constructor for the Manipulator class
	 * @param liftMotor - Talon for the elevator controls
	 * @param f1 - DoubleSolenoid for one flipper
	 * @param f2 - DoubleSolenoid for other flipper
	 */
	public Manipulators(Talon liftMotor, DoubleSolenoid f1, DoubleSolenoid f2) {
		this.liftMotor = liftMotor;
		this.flipper1 = f1;
		this.flipper2 = f2;
	}

	/**
	 * Move the elevator (and everything else attached to it) according to the speed
	 * @param speedlift - positive = upward movement; negative = downward movement
	 */
	public void moveLift(double speedlift) {
		// TODO - add logic to prevent driving the motor beyond full extension or full lowered
		liftMotor.set(speedlift);
	}
	
	/**
	 * Push the pneumatic pistons out (forward) to PUSH the manipulator down
	 */
	public void stowManipulator() {
		flipper1.set(DoubleSolenoid.Value.kForward);
		flipper2.set(DoubleSolenoid.Value.kForward);
	}
	
	/**
	 * Pull the pneumatic pistons back (reverse) to PULL to manipulator up into the working position
	 */
	public void extendManipulator() {
		flipper1.set(DoubleSolenoid.Value.kReverse);
		flipper2.set(DoubleSolenoid.Value.kReverse);
	}
}
