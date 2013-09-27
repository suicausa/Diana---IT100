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
		while(frontIsClear()) {
			checkRowOdd();
			checkRowEven();
		}
	}
	
	public void checkRowOdd() {
		while(frontIsClear()) {
			putBeeper();
			if(frontIsClear()){
				move();
				if(frontIsClear()){
					move();
				}
			}
		}
		turnAround();
		move();
		//even grid
		if(beepersPresent()) {
			turnRight();

			if(frontIsClear()){
				move();
				turnRight();
				move();
				putBeeper();
				turnAround();
			}
		}
		//odd grid
		if(noBeepersPresent()) {
			turnAround();
			move();
			putBeeper();
			turnLeft();
			if(frontIsClear()){
				move();
				turnLeft();
			}
		}
	}
	public void checkRowEven() {
		if(beepersPresent()) {
			move();
		}
		move();
		while(frontIsClear()) {
			putBeeper();
			if(frontIsClear()){
				move();
				if(frontIsClear()){
					move();
				}
			}
		}
		turnRight();
		if(frontIsClear()){
			move();
			turnRight();
		}
	}
	
}
