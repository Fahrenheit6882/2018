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
		//startPosition = 'L';
		tempTick = 0;
	} // end Init

	public static enum State {
		START, LIFTSWITCH, CENTERAPPROACHSWITCH, FIELDSWITCHTURN, AUTOLINEFORWARD, RESETLIFT, DROPBLOCK, REVERSE, REVERSEDRIVE, EXCHANGEGIVE, AFTERREVERSETURN, APPROACHLASTEXCHANGETURN, RESET, BLOCK, STOP, FINISH
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
					String rawData = hardware.driverStation.getGameSpecificMessage();
					
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
					//drive forward 168 inches before changing states
					if (hardware.driveBase.driveByInches(1.0, 168))
					{
						hardware.driveBase.stop();
						
						// TODO add logic for when to turn towards the switch
						autoState = State.FIELDSWITCHTURN;
					}	
					break;
				
				case FIELDSWITCHTURN:
					// TODO turn 90 degrees towards switch
					
					break; 
					
				case LIFTSWITCH:
					//TODO LIFTSWITCH need to write code for lifting block
					
					hardware.liftTalon.set(1);
					//change tempTick to the ticks on the encoders 19 inches is the goal.
					if(hardware.driveBase.driveByInches(0.2, 39.25)) {
						hardware.liftTalon.set(0);
						autoState = State.DROPBLOCK;
					}
					break;
					
				case DROPBLOCK:
					// TODO code DROPBLOCK
					break;
					
				case RESETLIFT:
					// TODO back up and lower the lift
					hardware.driveBase.driveByInches(-0.3, 39.25);
					//Need to reset lift motors at the same time
					hardware.liftTalon.set(-1);
					break;
					
				case REVERSE:
					if(hardware.driveBase.driveByInches(-1, 80)) {
						autoState = State.AFTERREVERSETURN;
					}
					break;
									
				case AFTERREVERSETURN:
					if(startPosition == 'R' & ourSwitch == 'R') {
						hardware.driveBase.stop();
						autoState = State.APPROACHLASTEXCHANGETURN;
					}
					
					break;

				case REVERSEDRIVE:
					tempTick++;
					hardware.driveBase.drive (-1, -1);
					//Encoder will also go here
				//set condition gone far enough, which is -168 in.
					if (tempTick > 100)
						//I don not know if the encoder can count backwards, this is if it cannot cout backwards
					{
						hardware.driveBase.stop();
						autoState = State.EXCHANGEGIVE; 
					}
					//End curly bracket of condition here
					//The state: EXCHANGEGIVE will make sure that the arm is lowered and insert the cube into the exchange
					break;
				case FINISH:
					hardware.driveBase.stop();
					break;
	
				default:
					break;
			}
		}
	}
}// end class