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
	} // end Init

	public static enum State {
		START, CAPTURESWITCH, CENTERAPPROACHSWITCH, DRIVETURNRIGHT, DRIVETURNLEFT, AUTOLINEFORWARD, RESET, BLOCK, STOP, FINISH
	}

	public static State autoState = State.START;

	/**
	 * User Periodic code for autonomous mode should go here. Will be called
	 * periodically at a regular rate while the robot is in autonomous mode. *
	 */
	public static void periodic() {
		// if Autonomous is not disabled (i.e. Autonomous is enabled) do some things
		if (hardware.disableAutoSwitch.get() == false) {
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
					hardware.driveBase.drive(1, 1);
					//Encoder Code will go here
					//set condition gone far enough which is 168 inches
						hardware.driveBase.stop();
						autoState = State.CAPTURESWITCH;
					//end curly bracket of condition goes here	
					break;
				case CAPTURESWITCH:
					
					
					break;
				case FINISH:
	
					break;
	
				default:
					break;
			}
		}
	}
}// end class