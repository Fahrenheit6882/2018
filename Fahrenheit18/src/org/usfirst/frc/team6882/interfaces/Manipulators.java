package org.usfirst.frc.team6882.interfaces;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

import org.usfirst.frc.team6882.globals.constants;
import org.usfirst.frc.team6882.globals.hardware;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Manipulators {
	private WPI_TalonSRX liftMotor;
	private DoubleSolenoid flipper1;
	private DoubleSolenoid flipper2;
	private Spark intakeSparkLeft;
	private Spark intakeSparkRight;
	
	private boolean pushBtnState = false;
	private boolean pullBtnState = false;

	/**
	 * Constructor for the Manipulator class
	 * @param liftMotor - Talon for the elevator controls
	 * @param f1 - DoubleSolenoid for one flipper
	 * @param f2 - DoubleSolenoid for other flipper
	 */
	public Manipulators(WPI_TalonSRX liftMotor, DoubleSolenoid f1, DoubleSolenoid f2, Spark L, Spark R) {
		this.liftMotor = liftMotor;
		this.flipper1 = f1;
		this.flipper2 = f2;
		this.intakeSparkLeft = L;
		this.intakeSparkRight = R;
	}

	/**
	 * Move the elevator (and everything else attached to it) according to the speed
	 * @param speedlift - positive = upward movement; negative = downward movement
	 */
	public void moveLift(double speedlift) {
		// TODO - add logic to prevent driving the motor beyond full extension or full lowered
		liftMotor.set(speedlift * constants.liftSpeedFactor);
	}
	
	/**
	 * Suck the cube in
	 */
	public void intake()
	{
		// TODO - add logic to stop intake when limit switch indicating cube held is hit
		intakeSparkLeft.set(0.4);
		intakeSparkRight.set(0.4);
	}
	
	/**
	 * Spit the cube out
	 */
	public void outtake()
	{
		intakeSparkLeft.set(-0.4);
		intakeSparkRight.set(-0.4);
	}
	
	/**
	 * Stop the intake
	 */
	public void stoptake()
	{
		intakeSparkLeft.set(0.0);
		intakeSparkRight.set(0.0);
	}
	
	/**
	 * Push the pneumatic pistons out (forward) to PUSH the manipulator down
	 */
	public void stowManipulator(boolean button) {
		if(button && !pushBtnState)
		{
			flipper1.set(DoubleSolenoid.Value.kForward);
			flipper2.set(DoubleSolenoid.Value.kForward);
		}
		
		pushBtnState = button;
	}
	
	/**
	 * Pull the pneumatic pistons back (reverse) to PULL to manipulator up into the working position
	 */
	public void extendManipulator(boolean button) {
		if(button && !pullBtnState)
		{
			flipper1.set(DoubleSolenoid.Value.kReverse);
			flipper2.set(DoubleSolenoid.Value.kReverse);
		}
		
		pullBtnState = button;
	}
}
