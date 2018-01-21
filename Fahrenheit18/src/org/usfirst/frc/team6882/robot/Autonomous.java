package org.usfirst.frc.team6882.robot;

import org.usfirst.frc.team6882.globals.hardware;
import org.usfirst.frc.team6882.globals.constants;
import edu.wpi.first.wpilibj.Timer;

/**
 * An Autonomous class.
 * This class uses state machines in order to periodically
 * execute instructions during the Autonomous period.
 * 
 * This class contains all of the user code for the Autonomous part
 * of the match, namely, the Init and Periodic code 
 */

public class Autonomous
{
	/* Global variables for Autonomous only here */
	static Timer autoTimer;
	static boolean started = false;
	// Reading game data and storing as char
	static char ourSwitch;
	static char Scale;

	
/**
 * User Initialization code for autonomous mode should go here. Will run once
 * when the autonomous first starts, and will be followed immediately by
 * periodic().
 */
public static void init ()
{
	// initialize timer
	autoTimer = new Timer();
	
	//read game data
	
	

} // end Init

public static enum State
    {
		INIT, 
		CAPTURE,
		DRIVETURNRIGHT,
		DRIVETURNLEFT,
		FORWARD,
		RESET,
		BLOCK,
		STOP,
		FINISH
    }


public static State autoState = State.INIT;

/**
 * User Periodic code for autonomous mode should go here. Will be called
 * periodically at a regular rate while the robot is in autonomous mode. *
 */
public static void periodic ()
{
	// if Autonomous is not disabled (i.e. Autonomous is enabled) do some things
    if (hardware.disableAutoSwitch.get() == false)
        {
        switch (autoState)
            {
            case INIT:
            switch(ourSwitch) {
            	case 'r':
            	case 'R':
            		
            	break;
            	case 'l':
            	case 'L':
            	break;
            }
            	break;
            case CAPTURE:
            	lift(constants.fastSpeedFactor);
            	break;
            case DRIVETURNLEFT:
            	break;
            case DRIVETURNRIGHT:
            	break;
            case FORWARD:
            	hardware.driveBase.drive(1, 1);
            	break;
            case RESET:
            	break;
            case BLOCK:
            	break;
            case STOP:
            	break;
            case FINISH:


                break;

            default:
                break;
            }
        }
}

public static void lift(double speedLift)
{
	//hardware.liftTalon.set(speedLift);
}
}// end class