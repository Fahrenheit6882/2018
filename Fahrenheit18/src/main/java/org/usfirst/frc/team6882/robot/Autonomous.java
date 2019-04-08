package org.usfirst.frc.team6882.robot;

import org.usfirst.frc.team6882.globals.hardware;

import org.usfirst.frc.team6882.globals.autoMap;
import org.usfirst.frc.team6882.globals.constants;
import edu.wpi.first.wpilibj.Timer;

/**
 * An Autonomous class. This class uses state machines in order to periodically
 * execute instructions during the Autonomous period.
 * 
 * This class contains all of the user code for the Autonomous part of the
 * match, namely, the Init and Periodic code
 */

public class Autonomous {
	/* Global variables for Autonomous only here */
	static autoMap autos = new autoMap();
	static int autoMode = -1;
	static int currStep = -1;
	static double arg;
	static boolean ready = false;
	static boolean back = false;
	static int cnt = 0;

	/**
	 * User Initialization code for autonomous mode should go here. Will run once
	 * when the autonomous first starts, and will be followed immediately by
	 * periodic().
	 */
	public static void init() {
		hardware.leftDriveEncoder.reset();
		hardware.rightDriveEncoder.reset();
		
	} // end Init


	/**
	 * User Periodic code for autonomous mode should go here. Will be called
	 * periodically at a regular rate while the robot is in autonomous mode. *
	 */
	public static void periodic() {
//		System.out.println(autoMode);
		
		//select which autonomous to run
		if(autoMode < 0)
		{
			// for on/simple switch: simple = false; on = true
			if(!hardware.autoSwitch.get())
			{
				if(hardware.rightStick.getZ() >= 0.5 || hardware.rightStick.getZ() <= -0.5)
				{
					autoMode = 0;
				}
				else
				{
					autoMode = 1;
				}
			}
			else
			{
				if(hardware.rightStick.getZ() <= -0.5) //left side!
				{
					if(hardware.rightStick.getZ() <= -0.5) //switch!
					{
						// switch is on left
						if(hardware.driverStation.getGameSpecificMessage().charAt(0) == 'L' || hardware.driverStation.getGameSpecificMessage().charAt(0) == 'l')
						{
							autoMode = 2;
						}
						else // switch is on the right
						{
							autoMode = 3;
						}
					}
					else if(hardware.rightStick.getZ() >= 0.5) //scale!
					{
						// scale is on left
						if(hardware.driverStation.getGameSpecificMessage().charAt(1) == 'L' || hardware.driverStation.getGameSpecificMessage().charAt(1) == 'l')
						{
							autoMode = 4;
						}
						else // scale is on the right
						{
							autoMode = 5;
						}
					}
				}
				else if(hardware.rightStick.getZ() >= 0.5) // right side
				{
					if(hardware.rightStick.getZ() <= -0.5) //switch!
					{
						// switch is on left
						if(hardware.driverStation.getGameSpecificMessage().charAt(0) == 'R' || hardware.driverStation.getGameSpecificMessage().charAt(0) == 'r')
						{
							autoMode = 6;
						}
						else // switch is on the right
						{
							autoMode = 7;
						}
					}
					else if(hardware.rightStick.getZ() >= 0.5) //scale!
					{
						// scale is on left
						if(hardware.driverStation.getGameSpecificMessage().charAt(1) == 'R' || hardware.driverStation.getGameSpecificMessage().charAt(1) == 'r')
						{
							autoMode = 8;
						}
						else // scale is on the right
						{
							autoMode = 9;
						}
					}
				}
				else //center
				{
					// switch is on left
					if(hardware.driverStation.getGameSpecificMessage().charAt(0) == 'L' || hardware.driverStation.getGameSpecificMessage().charAt(0) == 'l')
					{
						autoMode = 10;
					}
					else // switch is on the right
					{
						autoMode = 11;
					}
				}
			}
		
			// ready to start at our first step of our desired auto
			currStep = 0;
			hardware.autoTimer.reset();
			hardware.autoTimer.start();
//			autoMode = 2;
			System.out.println(autoMode);
		}
		else //ready to go through the states as laid out in autos
		{
			arg = autos.autoArgs[autoMode][currStep];
						
			switch(autos.autoPaths[autoMode][currStep])
			{
				case START:	
					if(hardware.autoTimer.get() >= 3)
					{
						if(cnt == 16)
						{
							currStep++;
							cnt = 0;
							hardware.manipulators.stoptake();
						}
						else if(cnt == 5)
						{
							hardware.manipulators.pullUpManipulator(true);
							cnt++;
							hardware.manipulators.intake();
						}
						else
						{
							cnt++;
							hardware.manipulators.intake();
						}
					}
					break;
				case FORWARD:
					if(hardware.driveBase.driveByInches(-0.8, arg))
					{
						hardware.driveBase.stop();
						currStep++;
						//autoState = State.Finish						// Use this to test
					}
					break;
				case RIGHT:
					if(hardware.driveBase.turnDegrees(arg, true, 0.5))
					{
						hardware.driveBase.stop();
						currStep++;
					}
					
					break;
				case LEFT:
					if(hardware.driveBase.turnDegrees(arg, false, 0.5))
					{
						hardware.driveBase.stop();
						currStep++;
					}
					
					break;
				case SCALE:
					if(!ready && hardware.driveBase.driveByInches(-0.3, arg))
					{
						hardware.driveBase.stop();
						ready = true;
					}
					
					if(!hardware.liftMax.get())
					{
						if(ready)
						{
							hardware.manipulators.outtake();
							ready = false;
							currStep++;
						}
					}
					else
					{
						hardware.manipulators.moveLift(-0.7);
					}
					
					
					break;
				case SWITCH:
					if(!ready && hardware.driveBase.driveByInches(-0.3, arg))
					{
						hardware.driveBase.stop();
						hardware.manipulators.intake();
						//might need to change whether this is true or false
//						if(hardware.cubeIn.get())
//						{
//							hardware.manipulators.stoptake();
//							currStep++;
//						}
//						else
//						{
//							hardware.manipulators.outtake();
//						}
						
					}
					else
					{
						hardware.manipulators.moveLift(-0.7);
					}
					
					if(ready)
					{
						if(cnt < 16)
						{
							hardware.manipulators.outtake();
							cnt++;
						}
						else
						{
							hardware.manipulators.stoptake();
							ready = false;
							cnt = 0;
							currStep++;
						}
					}
					break;
				case RESET:
					if(hardware.driveBase.driveByInches(0.3, arg) || back == true)
					{
						hardware.driveBase.stop();
						hardware.manipulators.moveLift(0.7);
						back = true;
						if(!hardware.liftMin.get())
						{
							hardware.manipulators.moveLift(0);
							currStep++;
						}
					}
					
					break;
				case FINISH:
					hardware.driveBase.stop();
					hardware.manipulators.moveLift(0.0);
					hardware.manipulators.stoptake();
					break;
				default:
					break;
			}
		}
	}
}
// end class