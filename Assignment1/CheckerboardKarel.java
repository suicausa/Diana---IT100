/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		while(frontIsClear()){
			if (facingEast()) {
				makeRow();
				if(leftIsClear()) {
					checkRowOdd();
				}
			}
			if (facingWest()) {
				makeRow();
				if(rightIsClear()) {
					checkRowEven();
				}
			}
		}
		//check final position whether to place beeper (odd) or not (even)
		if(frontIsBlocked()){
			turnAround();
			move();
			turnAround();
			if (noBeepersPresent()){
				move();
				putBeeper();
			}
		}
	}
	//places beepers in a row at every other position
	public void makeRow() {
		while(frontIsClear()) {
			putBeeper();
			if(frontIsClear()){
				move();
				if(frontIsClear()){
					move();
				}
			}
		}
	}
	
	
	public void checkRowOdd() {
		/*if there is beeper in previous position then row is even because (1,1) has beeper
		  if there is NO beeper in previous position then row is odd
		*/
		turnAround();
		move();
		turnAround();
		//check if row is even; if yes then move to next row
		if (beepersPresent()){
			move();
			if(leftIsClear()){
				turnLeft();
				move();
				turnLeft();
				putBeeper();
				move();
			}
		}
		//check if row is odd; if yes then adds a beeper before moving to next row
		else if (noBeepersPresent()){
			move();
			putBeeper();
			if(leftIsClear()){
				turnLeft();
				move();
				turnLeft();
			}
		}
		move();
	}
	public void checkRowEven() {
		turnAround();
		move();
		turnAround();
		//check if row is even
		if (beepersPresent()){
			move();
			if(rightIsClear()){
				turnRight();
				move();
				turnRight();
				putBeeper();
				move();
			}
		}
		//check if row is odd
		else if (noBeepersPresent()){
			move();
			putBeeper();
			if(rightIsClear()){
				turnRight();
				move();
				turnRight();
			}
		}
		move();
	}
	
}
