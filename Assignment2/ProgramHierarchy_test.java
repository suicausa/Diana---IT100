/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This draws the diagram for Program Hierarchy.
 * Each label's rectangle is defined by posx and posy as the top left corner of the GRect.
 * Variables i and j are the values for the center of the rectangle based on posx and posy.
 * Variables posA and posB define the bottom left position of the GLabel.
 * The connecting lines all start at the top label's bottom middle position
 * and connect to the child labels' top middle positions.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {
	/** Width of label in pixels */
	private static final int labelWidth = 120;

	/** Height of label in pixels */
	private static final int labelHeight = 30;

	public void run() {
		//get window center position
		int centerx = getWidth()/2;
		int centery = getHeight()/2;

		//center label
		double posx1 = centerx - labelWidth/2;
		double posy1 = centery;

		makeLabel(posx1, posy1, "ConsoleProgram");

		//top label
		double posx0 = centerx - labelWidth/2;
		double posy0 = centery - 2*labelHeight;
		makeLabel(posx0, posy0, "Program");

		//center label left
		double posx2 = centerx - 1.75*labelWidth;
		double posy2 = centery;
		makeLabel(posx2, posy2, "GraphicsProgram");


		//center label right
		double posx3 = centerx + .75*labelWidth;
		double posy3 = centery;
		
		makeLabel(posx3, posy3, "DialogProgram");


		//draw connecting lines
		/* Modify this part the same as the make Label, create a new method called makeLine with 4 parameters
		 * It doesn't shorten a lot of code here, but can show you a clear structure and how to make your own methods
		 */
		/*
		GLine line1 = new GLine(i0,(j0+labelHeight/2),i1,(j1-labelHeight/2));
		add(line1);//top label connects to bottom center label
		GLine line2 = new GLine(i0,(j0+labelHeight/2),i2,(j2-labelHeight/2));
		add(line2);//top label connects to bottom left label
		GLine line3 = new GLine(i0,(j0+labelHeight/2),i3,(j3-labelHeight/2));
		add(line3);//top label connects to bottom right label
		*/
	}
	
	private void makeLabel(double x, double y, String text){
		
		GRect labelBox = new GRect(x, y, labelWidth,labelHeight);
		add(labelBox);
		GLabel labelVal = new GLabel(text);
		double i = (x + labelWidth/2);
		double j = (y + labelHeight/2);
		double posA = i - (labelVal.getWidth()/2);
		double posB = j + (labelVal.getAscent()/2);
		labelVal.setLocation(posA,posB);
		add(labelVal);
	}
}
