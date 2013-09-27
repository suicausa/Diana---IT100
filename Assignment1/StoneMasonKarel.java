/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		
		while(frontIsClear()) {
			//check column at (1,1)
			colBuild();
			
			//build column on left
			colLeft();
			
			//travel to next column
			turnRight();
			while(frontIsClear()) {
				move();
			}
			turnRight();
			while(frontIsClear()) {
				move();
			}
			turnLeft();
			move();
		
		}
		//build last column
		turnAround();
		colBuild2();
		colRight();
		
	}
	
	//Beeper condition for left columns
	public void colBuild() {
		if(noBeepersPresent()) {
			putBeeper();
		}
		move();
		turnLeft();
	}
	//Build first column from right to left
	public void colLeft() {
		while(frontIsClear()) {
			if(frontIsClear()) {
				move();
			}
			if (leftIsClear()) {
				turnLeft();
				move();
				turnAround();
				colBuild();
			}
		}
	}
	
	//Beeper condition for right columns
	public void colBuild2() {
		if(noBeepersPresent()) {
			putBeeper();
		}
		move();
		turnRight();
	}
	//Build columns from left to right
	public void colRight() {
		while(frontIsClear()) {
			if(frontIsClear()) {
				move();
			}
			if (rightIsClear()) {
				turnRight();
				move();
				turnAround();
				colBuild2();
			}
		}
	}
}
