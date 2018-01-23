package org.usfirst.frc.team6882.interfaces;

import org.usfirst.frc.team6882.globals.hardware;

import edu.wpi.first.wpilibj.Talon;

public class Manipulators {
	private static Talon liftMotor;

	public Manipulators(Talon liftMotor) {
		this.liftMotor = liftMotor;
	}

	public static void moveLift(double speedlift) {
		liftMotor.set(speedlift);
	}
}
