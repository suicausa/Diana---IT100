/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy_test extends GraphicsProgram {
	/** Width of label in pixels */
	private static final int labelWidth = 120;

	/** Height of label in pixels */
	private static final int labelHeight = 30;
	

	//get window center position
	private int centerx = getWidth()/2;
	private int centery = getHeight()/2;
	
	private GLabel makeLabel(GLabel label) {
		//center label
		double posx1 = centerx - labelWidth/2;
		double posy1 = centery;
		
		GRect labelBox1 = new GRect(posx1, posy1, labelWidth,labelHeight);
		add(labelBox1);//draws rectangle
		GLabel labelVal1 = label;
		double i1 = (posx1 + labelWidth/2);
		double j1 = (posy1 + labelHeight/2);
		double posA1 = i1 - (labelVal1.getWidth()/2);
		double posB1 = j1 + (labelVal1.getAscent()/2);
		GLabel labelName1 = new GLabel("ConsoleProgram",posA1,posB1);
		add(labelName1);//prints text
		return labelName1;
	}
	
	public void run() {
		
		makeLabel(new GLabel("ConsoleProgram"));
		
	}
}
