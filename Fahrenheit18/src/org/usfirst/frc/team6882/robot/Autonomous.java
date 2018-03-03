package org.usfirst.frc.team6882.robot;

import org.usfirst.frc.team6882.globals.hardware;
import org.omg.PortableServer.POAManagerPackage.State;
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

	/**
	 * User Initialization code for autonomous mode should go here. Will run once
	 * when the autonomous first starts, and will be followed immediately by
	 * periodic().
	 */
	public static void init() {
		
		
	} // end Init


	/**
	 * User Periodic code for autonomous mode should go here. Will be called
	 * periodically at a regular rate while the robot is in autonomous mode. *
	 */
	public static void periodic() {
		//select which autonomous to run
		if(autoMode < 0)
		{
			// for on/simple switch: simple = false; on = true
			if(!hardware.autoSwitch.get())
			{
				if(!hardware.rightPosition.get() || !hardware.leftPosition.get())
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
				if(!hardware.leftPosition.get()) //left side!
				{
					if(hardware.leftStick.getZ() <= -0.5) //switch!
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
					else if(hardware.leftStick.getZ() >= 0.5) //scale!
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
				else if(!hardware.rightPosition.get()) // right side
				{
					if(hardware.leftStick.getZ() <= -0.5) //switch!
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
					else if(hardware.leftStick.getZ() >= 0.5) //scale!
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
		}
		else //ready to go through the states as laid out in autos
		{
		arg = autos.autoArgs[autoMode][currStep];
			
			switch(autos.autoPaths[autoMode][currStep])
			{
				case START:
					
					break;
				case FORWARD:
					if(hardware.driveBase.driveByInches(-0.6, arg))
					{
						hardware.driveBase.stop();
						currStep++;
					}
					break;
				case RIGHT:
					if(hardware.driveBase.turnDegrees(arg, true, 0.8))
					{
						hardware.driveBase.stop();
						currStep++;
					}
					
					break;
				case LEFT:
					if(hardware.driveBase.turnDegrees(arg, false, 0.8))
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
					if(hardware.driveBase.driveByInches(-0.3, arg))
					{
						hardware.driveBase.stop();
						hardware.manipulators.outtake();
						//might need to change whether this is true or false
						if(hardware.cubeIn.get())
						{
							hardware.manipulators.stoptake();
							currStep++;
						}
					}
					else
					{
						hardware.manipulators.moveLift(-0.7);
					}
					break;
				case RESET:
					if(hardware.driveBase.driveByInches(0.3, arg))
					{
						hardware.driveBase.stop();
						hardware.manipulators.moveLift(0.7);
						if(!hardware.liftMin.get())
						{
							hardware.manipulators.moveLift(0);
							currStep++;
						}
					}
					
					if(!hardware.liftMin.get())
					{
					}
					else
					{
						hardware.manipulators.moveLift(-0.7);
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