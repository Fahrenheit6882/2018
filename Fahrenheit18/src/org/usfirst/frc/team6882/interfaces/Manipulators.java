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
	 * @param speedLift - positive = upward movement; negative = downward movement
	 */
	public void moveLift(double speedLift) {
		// if we are at the bottom of the elevator (liftMin = false)
		//		if rising, use speedLift
		//		if lowering, holdLift
		if(!hardware.liftMin.get())
		{
			if(speedLift < 0)
			{
				liftMotor.set(speedLift * constants.liftSpeedFactor);
			}
			else
			{
				holdLift();
			}
		}
		
		// if we are at the top of the elevator (liftMax = false) then holdLift
		//		if lowering, use speedLift
		//		if rising, holdLift
		else if(!hardware.liftMax.get())
		{
			if(speedLift > 0)
			{
				liftMotor.set(speedLift * constants.liftSpeedFactor);
			}
			else
			{
				holdLift();
			}
		}
		
		//if not at top or bottom 
		//		if not moving hold lift 
		//		else speedlift
		else
		{
			if(speedLift == 0)
			{
				holdLift();
			}
			else 
			{
				liftMotor.set(speedLift * constants.liftSpeedFactor);
			}
		}
	}
	
	/**
	 * holds the lift in place against gravity
	 */
	private void holdLift() {
		//TODO - add logic to prevent elevator from falling when not actively rising and not at bottom of lift
		// 		or to stop moving at bottom of lift
		
		// if at bottom of elevator (liftMin = false) then set 0
		// else set gravityCounter
		if(!hardware.liftMin.get()) 
		{
			hardware.liftTalon.set(0);
		}
		else 
		{
			hardware.liftTalon.set(constants.gravityCounter);
		}
		
	}
	
	/**
	 * automates climbing 
	 */
	public void climb()
	{
		if(!hardware.liftMin.get())
		{
			hardware.liftTalon.set(constants.climbHold);
		}
		else
		{
			hardware.liftTalon.set(constants.climbActive);
		}
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
	public void pullUpManipulator(boolean button) {
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
	public void pushDownManipulator(boolean button) {
		if(button && !pullBtnState)
		{
			flipper1.set(DoubleSolenoid.Value.kReverse);
			flipper2.set(DoubleSolenoid.Value.kReverse);
		}
		
		pullBtnState = button;
	}
}
