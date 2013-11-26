/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file reads two user input values as "a" and "b" and yields a value "c" from the pythagorean theorem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter values to compute Pythagorean theorem."); 		
		int a = readInt("a: "); 
		int b = readInt("b: "); 
		double c = Math.sqrt((a*a)+(b*b));
		println("c = " + c); 
	}
}
