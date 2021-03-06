/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run(){
		//marks first position
		putBeeper();
		
		//put beeper at opposite end of the world
		while(frontIsClear()) {
			move();
		}
		putBeeper();
		turnAround();
		
		//go back to find previous beeper
		while(frontIsClear()) {
			move();
			/*if find beeper, pick it up, start one position over and mark with beeper.
			  Loop is contained within to end walls of the world
			*/
			if(beepersPresent()){
				pickBeeper();
				turnAround();
				move();
				putBeeper();
			}
		}
		turnAround();
		
		/*the midpoint has 2 beepers
		  find beeper, pick it up and stop in position
		*/
		while(noBeepersPresent()) {
			move();
			if(beepersPresent()){
				pickBeeper();
			}
		}
	}

}
