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

		//top parent label
		double posx0 = centerx - labelWidth/2;
		double posy0 = centery - 2*labelHeight;
		makeLabel(posx0, posy0, "Program");

		//center child label
		double posx1 = centerx - labelWidth/2;
		//posy defined one time since all children have the same y position
		double posy = centery;
		makeLabel(posx1, posy, "ConsoleProgram");
		makeLine(posx1, posy, centerx, centery);
		
		//left child label
		double posx2 = centerx - 1.75*labelWidth;
		makeLabel(posx2, posy, "GraphicsProgram");
		makeLine(posx2, posy, centerx, centery);
		
		//right child label
		double posx3 = centerx + .75*labelWidth;
		makeLabel(posx3, posy, "DialogProgram");
		makeLine(posx3, posy, centerx, centery);

	}
	
	private void makeLabel(double x, double y, String text){
		//draws rectangle
		GRect labelBox = new GRect(x, y, labelWidth,labelHeight);
		add(labelBox);
		//creates label
		GLabel labelVal = new GLabel(text);
		//gets center position of rectangle
		double i = (x + labelWidth/2);
		double j = (y + labelHeight/2);
		//gets bottom left position to center label text
		double posA = i - (labelVal.getWidth()/2);
		double posB = j + (labelVal.getAscent()/2);
		labelVal.setLocation(posA,posB);
		//outputs label
		add(labelVal);
	}
	
	private void makeLine(double c, double d, double a, double b){
		//defines starting x and y as bottom center of top label
		double startx = a;
		double starty = b - labelHeight;
		//defines ending x and y as top center of children labels
		double endx = (c + labelWidth/2);
		double endy = d;
		//draws the line
		GLine line = new GLine(startx,starty,endx,endy);
		add(line);
	}
}
