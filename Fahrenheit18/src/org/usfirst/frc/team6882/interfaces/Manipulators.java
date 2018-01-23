package org.usfirst.frc.team6882.interfaces;

import edu.wpi.first.wpilibj.Talon;

public class Manipulators {
	private Talon liftMotor;

	public Manipulators(Talon liftMotor) {
		this.liftMotor = liftMotor;
	}

	public void moveLift(double speedlift) {
		liftMotor.set(speedlift);
	}
}
