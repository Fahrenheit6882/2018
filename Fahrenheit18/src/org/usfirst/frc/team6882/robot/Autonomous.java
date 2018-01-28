package org.usfirst.frc.team6882.robot;

import org.usfirst.frc.team6882.globals.hardware;
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
	static Timer autoTimer;
	static boolean started = false;
	// Reading game data and storing as char
	static char ourSwitch;
	static char Scale;
	static char startPosition;
	// tempTick variable is being used in lieu of the encoders ticks for now
	static int tempTick;

	/**
	 * User Initialization code for autonomous mode should go here. Will run once
	 * when the autonomous first starts, and will be followed immediately by
	 * periodic().
	 */
	public static void init() {
		// initialize timer
		autoTimer = new Timer();

		// read game data
		ourSwitch = ' ';
		Scale = ' ';
		
		//TODO read the switch
		// setting starting position to L for test
		startPosition = 'L';
		tempTick = 0;
	} // end Init
//Added CAPTURESWITCH state 
	public static enum State {
		START, LIFTSWITCH, CENTERAPPROACHSWITCH, FIELDSWITCHTURN, AUTOLINEFORWARD, RESETLIFT, DROPBLOCK, FINISH
	}

	public static State autoState = State.START;

	/**
	 * User Periodic code for autonomous mode should go here. Will be called
	 * periodically at a regular rate while the robot is in autonomous mode. *
	 */
	public static void periodic() {
		// if Autonomous is not disabled (i.e. Autonomous is enabled) do some things
		//TODO Put physical switch read back into the if statement
		if (true) {
			System.out.println(autoState.toString());
			switch (autoState) {
				case START:
					hardware.driveBase.stop();
					String rawData = hardware.driverStation.getInstance().getGameSpecificMessage();
					if(rawData != "") {
						ourSwitch = rawData.charAt(0);
						Scale = rawData.charAt(1);
						
						if(startPosition == 'R' || startPosition == 'L') {
							autoState = State.AUTOLINEFORWARD;
						}
						if(startPosition == 'C') {
							autoState = State.CENTERAPPROACHSWITCH;
						}
					}
					break;
				case AUTOLINEFORWARD:
//					tempTick++;
//					hardware.driveBase.drive(1, 1);
	
					//drive forward 168 inches before changing states
					if (hardware.driveBase.driveByInches(1.0, 168))
					{
						hardware.driveBase.stop();
						autoState = State.FIELDSWITCHTURN;
					}	
					break;
				case FIELDSWITCHTURN:
					// Tried to use if statements to measure a variable that constantly counts up. The flaw is that numbers keep getting higher
					// created a turn state for left and right turns the robot needs to
					tempTick++;
					/* if(startPosition == 'R' && ourSwitch == 'R') {
						hardware.driveBase.drive(-1, 1);
						//set encoder values to how many ticks we need to turn
						if(tempTick > 10 && tempTick < 15) {
							hardware.driveBase.stop();
							autoState = State.CAPTURESWITCH;
						}
						else if(tempTick > 15) {
							hardware.driveBase.drive(1, -1);
							
							autoState = State.FIELDSWITCHTURN;
						}
					} */
					
					if(startPosition == 'R' && ourSwitch == 'R') {
						while(tempTick > 15 && tempTick < 10) {
							hardware.driveBase.drive(-1, 1);
						}
							hardware.driveBase.stop();
							autoState = State.LIFTSWITCH;
					}
					break; 
				//TODO LIFTSWITCH need to write code for lifting block
				case LIFTSWITCH:
					hardware.liftTalon.set(1);
					//change tempTick to the ticks on the encoders 19 inches is the goal.
					if(tempTick > 19) {
						hardware.liftTalon.set(0);
						autoState = State.DROPBLOCK;
					}
					break;
				case DROPBLOCK:
				case RESETLIFT:
					hardware.driveBase.drive(-1, -1);
					//Need to reset lift motors at the same time
				
					
				case FINISH:
	
					break;
	
				default:
					break;
			}
		}
	}
}// end class