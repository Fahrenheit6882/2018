package org.usfirst.frc.team6882.robot;

import org.usfirst.frc.team6882.globals.hardware;
import org.omg.PortableServer.POAManagerPackage.State;
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

	// how far to travel in simple auto
	static double travelDistance = 0;

	/**
	 * User Initialization code for autonomous mode should go here. Will run once
	 * when the autonomous first starts, and will be followed immediately by
	 * periodic().
	 */
	public static void init() {
		// initialize timer
//		autoTimer = new Timer();

		// read game data
		ourSwitch = ' ';
		Scale = ' ';
		
		//TODO read the switch
		
		if(!hardware.leftPosition.get())
		{
			startPosition = 'L';
		}
		else if (!hardware.rightPosition.get())
		{
			startPosition = 'R';
		}
		else
		{
			startPosition = 'C';
		}
		
	} // end Init

	public static enum State {
		START, LIFTSWITCH, CENTERAPPROACHSWITCH, FIELDSWITCHTURN, AUTOLINEFORWARD, PAUSE, RESETLIFT, DROPBLOCK, REVERSE, REVERSEDRIVE, EXCHANGEGIVE, AFTERREVERSETURN, APPROACHLASTEXCHANGETURN, RESET, BLOCK, STOP, FINISH
	}

	public static State autoState = State.START;
	
	private static boolean lifted = false;
	private static int cnt = 0;
	private static int maxCnt = 10;

	/**
	 * User Periodic code for autonomous mode should go here. Will be called
	 * periodically at a regular rate while the robot is in autonomous mode. *
	 */
	public static void periodic() {
		// if Autonomous is not disabled (i.e. Autonomous is enabled) do some things
		//TODO Put physical switch read back into the if statement
		if (hardware.autoSwitch.get()) {
			if(autoState != State.FINISH)
			{
				System.out.println(autoState.toString());
			}
			
			switch (autoState) {
				case START:
					hardware.driveBase.stop();
					String rawData = hardware.driverStation.getGameSpecificMessage();
					
					if(rawData != "") {
						ourSwitch = rawData.charAt(0);
						Scale = rawData.charAt(1);
					}
					
					if(cnt < maxCnt)
					{
						//intake cube
						hardware.manipulators.intake();
						hardware.manipulators.moveLift(0);
						cnt++;
					}
					else
					{
						autoState = State.AUTOLINEFORWARD;
//						autoState = State.FINISH;
						cnt = 0;
					}

					if(!lifted && cnt == 2)
					{
						//putting manipulator in upright position
						hardware.manipulators.pullUpManipulator(true);
						lifted = true;
					}
					
					break;
				
				case AUTOLINEFORWARD:
					//turn off intake first
					hardware.manipulators.intake();
					
					//if L or R, drive forward 168 inches before changing states
					switch(startPosition)
					{
						case 'r':
						case 'R':
						case 'l':
						case 'L':
							if (hardware.driveBase.driveByInches(-0.6, (168 - constants.stoppingDistance)))
							{
								hardware.driveBase.stop();
								
								cnt = 0;
								
								// TODO add logic for when to turn towards the switch
								autoState = State.PAUSE;
//								autoState = State.FINISH;
							}	
							break;
						case 'c':
						case 'C':
							if (hardware.driveBase.driveByInches(-0.6, 30))
							{
								hardware.driveBase.stop();
								
								autoState = State.FINISH;
							}
							break;
						
					}
					
					break;
				case PAUSE:
				{
					if (cnt < 10)
					{
						hardware.driveBase.stop();
						cnt++;
					}
					else
					{
//						hardware.leftDriveEncoder.reset();
						hardware.rightDriveEncoder.reset();
						autoState = State.FIELDSWITCHTURN;
//						autoState = State.FINISH;
						cnt = 0;
					}
				}
				case FIELDSWITCHTURN:
					switch (startPosition)
					{
						case 'r':
						case 'R':
							if(hardware.driveBase.turnDegrees(65, false, 0.5))
							{
//								autoState = State.FINISH;
								autoState = State.LIFTSWITCH;
							}
							break;
						case 'l':
						case 'L':
							if(hardware.driveBase.turnDegrees(95, true, 0.5))
							{
								autoState = State.FINISH;
								autoState = State.LIFTSWITCH;
							}
							break;
					}
					break; 
					
				case LIFTSWITCH:
					//TODO LIFTSWITCH need to write code for lifting block
					
					hardware.manipulators.moveLift(-1.0);
					//change tempTick to the ticks on the encoders 19 inches is the goal.
					if(hardware.driveBase.driveByInches(-0.3, 15 - constants.stoppingDistance)) {
						hardware.manipulators.moveLift(0);
						autoState = State.DROPBLOCK;
//						autoState = State.FINISH;
					}
					break;
					
				case DROPBLOCK:
					// TODO code DROPBLOCK
					if(cnt < maxCnt)
					{
						hardware.manipulators.outtake();
						cnt++;
					}
					else
					{
						cnt = 0;
						hardware.manipulators.stoptake();
						autoState = State.FINISH;
					}
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
					hardware.driveBase.drive (-1, -1);
					
					//End curly bracket of condition here
					//The state: EXCHANGEGIVE will make sure that the arm is lowered and insert the cube into the exchange
					break;
					
				case FINISH:
					hardware.driveBase.stop();
					hardware.manipulators.moveLift(0);
					break;
	
				default:
					break;
			}
		}
		else
		{
			switch (autoState) 
			{
				case START:
					if(!lifted)
					{
						//putting manipulator in upright position
						hardware.manipulators.pullUpManipulator(true);
						lifted = true;
						if (startPosition == 'C' || startPosition == 'c')
						{
							travelDistance = 95;
						}
						else
						{
							travelDistance = 168;
						}
					}
					
					break;
				
				case AUTOLINEFORWARD:
					if (hardware.driveBase.driveByInches(-0.6, (travelDistance - constants.stoppingDistance)))
						{
							hardware.driveBase.stop();
							
							cnt = 0;
							
							// TODO add logic for when to turn towards the switch
							autoState = State.FINISH;
						}						
					break;
					
				case FINISH:
					hardware.driveBase.stop();
					hardware.manipulators.moveLift(0);
					break;
	
				default:
					break;				
				
			}
		}
	}
}
// end class