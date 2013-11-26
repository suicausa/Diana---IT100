/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file applies the Hailstone sequence and outputs each step of
 * when the value is odd or even until the value equals 1.
 * If the value is odd, then it re-calculates the new value as "3n + 1", with n being the value.
 * If the value is even, then it divides the value by 2.
 * The new value is then re-calculated either with the odd condition formula or even condition formula.
 * Each time the value is re-calculated, there is a counter.
 * Once the value reaches 1, the program outputs the number of steps it took to reach 1.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int val = readInt("Enter a number: ");
		//store val in a temporary variable to calculate it in the method
		int tempVal = val;
		int count = 0;
		while (val != 1) {
			//if val is odd
			if (val%2 != 0) {
				/*store new calculated value in tempVal so we can
				print the values before and after calculation
				*/
				tempVal = 3*val + 1;
				println(val + " is odd, so I make 3n + 1: " + tempVal);
				/*assign calculated value to val so we can 
				continue the loop with new value
				*/
				val = tempVal;
				//counts each time we output a new line
				count++;
			}
			//if val is even
			else if (val%2 == 0) {
				tempVal = val/2;
				println(val + " is even so I take half: " + tempVal);
				val = tempVal;
				count++;
			}
		}
		println("The process took " + count + " to reach 1");
	}
}

