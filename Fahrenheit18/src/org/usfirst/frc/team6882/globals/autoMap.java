package org.usfirst.frc.team6882.globals;

public class autoMap {
	public static enum State
	{
		START, FORWARD, RIGHT, LEFT, SWITCH, SCALE, RESET, FINISH
	}
	public State autoPaths[][] = new State[12][10];
	public double autoArgs[][] = new double[12][10];
	
	public autoMap() {
		autoPaths[0][0] = State.START;
		autoPaths[0][1] = State.FORWARD;
		autoArgs[0][1] = 168.0;
		autoPaths[0][2] = State.FINISH;
		
		autoPaths[1][0] = State.START;
		autoPaths[1][1] = State.FORWARD;
		autoArgs[1][1] = 122.0;
		autoPaths[1][2] = State.FINISH;
		
		autoPaths[2][0] = State.START;
		autoPaths[2][1] = State.FORWARD;
		autoArgs[2][1] = 168.0;
		autoPaths[2][2] = State.RIGHT;
		autoArgs[2][2] = 90.0;
		autoPaths[2][3] = State.SWITCH;
		autoArgs[2][3] = 15.0;
		autoPaths[2][4] = State.FINISH;
		
		autoPaths[3][0] = State.START;
		autoPaths[3][1] = State.FORWARD;
		autoArgs[3][1] = 185.0;
		autoPaths[3][2] = State.RIGHT;
		autoArgs[3][2] = 90.0;
		autoPaths[3][3] = State.FORWARD;
		autoArgs[3][3] = 166.0;
		autoPaths[3][4] = State.RIGHT;
		autoArgs[3][4] = 90.0;
		autoPaths[3][5] = State.SWITCH;
		autoArgs[3][5] = 15.0;
		autoPaths[3][6] = State.FINISH;
		
		autoPaths[4][0] = State.START;
		autoPaths[4][1] = State.FORWARD;
		autoArgs[4][1] = 324.0;
		autoPaths[4][2] = State.RIGHT;
		autoArgs[4][2] = 90.0;
		autoPaths[4][3] = State.SCALE;
		autoArgs[4][3] = 15.0;
		autoPaths[4][4] = State.RESET;
		autoArgs[4][4] = 15.0;
		autoPaths[4][5] = State.FINISH;
		
		autoPaths[5][0] = State.START;
		autoPaths[5][1] = State.FORWARD;
		autoArgs[5][1] = 216.0;
		autoPaths[5][2] = State.RIGHT;
		autoArgs[5][2] = 90.0;
		autoPaths[5][3] = State.FORWARD;
		autoArgs[5][3] = 264.0;
		autoPaths[5][4] = State.LEFT;
		autoArgs[5][4] = 90.0;
		autoPaths[5][5] = State.FORWARD;
		autoArgs[5][5] = 108.0;
		autoPaths[5][6] = State.LEFT;
		autoArgs[5][6] = 90.0;
		autoPaths[5][7] = State.SCALE;
		autoArgs[5][7] = 15.0;
		autoPaths[5][8] = State.RESET;
		autoArgs[5][8] = 15.0;
		autoPaths[5][9] = State.FINISH;
		
		autoPaths[6][0] = State.START;
		autoPaths[6][1] = State.FORWARD;
		autoArgs[6][1] = 168.0;
		autoPaths[6][2] = State.LEFT;
		autoArgs[6][2] = 90.0;
		autoPaths[6][3] = State.SWITCH;
		autoArgs[6][3] = 15.0;
		autoPaths[6][4] = State.FINISH;
		
		autoPaths[7][0] = State.START;
		autoPaths[7][1] = State.FORWARD;
		autoArgs[7][1] = 185.0;
		autoPaths[7][2] = State.LEFT;
		autoArgs[7][2] = 90.0;
		autoPaths[7][3] = State.FORWARD;
		autoArgs[7][3] = 166.0;
		autoPaths[7][4] = State.LEFT;
		autoArgs[7][4] = 90.0;
		autoPaths[7][5] = State.FORWARD;
		autoArgs[7][5] = 17.0;
		autoPaths[7][6] = State.LEFT;
		autoArgs[7][6] = 90.0;
		autoPaths[7][7] = State.SWITCH;
		autoArgs[7][7] = 15.0;
		autoPaths[7][8] = State.FINISH;
		
		autoPaths[8][0] = State.START;
		autoPaths[8][1] = State.FORWARD;
		autoArgs[8][1] = 324.0;
		autoPaths[8][2] = State.LEFT;
		autoArgs[8][2] = 90.0;
		autoPaths[8][3] = State.SCALE;
		autoArgs[8][3] = 15.0;
		autoPaths[8][4] = State.RESET;
		autoArgs[8][4] = 15.0;
		autoPaths[8][5] = State.FINISH;
		
		autoPaths[9][0] = State.START;
		autoPaths[9][1] = State.FORWARD;
		autoArgs[9][1] = 185.0;
		autoPaths[9][2] = State.LEFT;
		autoArgs[9][2] = 90.0;
		autoPaths[9][3] = State.FORWARD;
		autoArgs[9][3] = 166.0;
		autoPaths[9][4] = State.RIGHT;
		autoArgs[9][4] = 90.0;
		autoPaths[9][5] = State.FORWARD;
		autoArgs[9][5] = 17.0;
		autoPaths[9][6] = State.RIGHT;
		autoArgs[9][6] = 90.0;
		autoPaths[9][7] = State.SCALE;
		autoArgs[9][7] = 15.0;
		autoPaths[9][8] = State.RESET;
		autoArgs[9][8] = 15.0;
		autoPaths[9][9] = State.FINISH;
		
		autoPaths[10][0] = State.START;
		autoPaths[10][1] = State.FORWARD;
		autoArgs[10][1] = 89.0;
		autoPaths[10][2] = State.LEFT;
		autoArgs[10][2] = 90.0;
		autoPaths[10][3] = State.FORWARD;
		autoArgs[10][3] = 132.0;
		autoPaths[10][4] = State.RIGHT;
		autoArgs[10][4] = 90.0;
		autoPaths[10][5] = State.FORWARD;
		autoArgs[10][5] = 98.0;
		autoPaths[10][6] = State.RIGHT;
		autoArgs[10][6] = 90.0;
		autoPaths[10][7] = State.SWITCH;
		autoArgs[10][7] = 15.0;
		autoPaths[10][8] = State.FINISH;
		
		autoPaths[11][0] = State.START;
		autoPaths[11][1] = State.FORWARD;
		autoArgs[11][1] = 89.0;
		autoPaths[11][2] = State.RIGHT;
		autoArgs[11][2] = 90.0;
		autoPaths[11][3] = State.FORWARD;
		autoArgs[11][3] = 132.0;
		autoPaths[11][4] = State.LEFT;
		autoArgs[11][4] = 90.0;
		autoPaths[11][5] = State.FORWARD;
		autoArgs[11][5] = 98.0;
		autoPaths[11][6] = State.LEFT;
		autoArgs[11][6] = 90.0;
		autoPaths[11][7] = State.SWITCH;
		autoArgs[11][7] = 15.0;
		autoPaths[11][8] = State.FINISH;
	}
}

