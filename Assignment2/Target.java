/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file draws three circles of different size from the center of the user window to output a target graphic.
 * It first determines the center coordinates of the window.
 * Then each circle is drawn from the center coordinates. 
 * The start position of each circle is determined by subtracting the radius of each circle from the center x and y values.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {
	
	
	public void run() {
		//determine window's center
		double centerx = getWidth()/2;
		double centery = getHeight()/2;
		
		//first outer circle
		double firstRad = 72;//circle radius
		double firstDiam = firstRad * 2;//circle diameter
		double posx1 = centerx - 72;//circle x position
		double posy1 = centery - 72;//circle y position
		GOval firstCircle = new GOval(posx1,posy1,firstDiam,firstDiam);
		firstCircle.setFilled(true);
		firstCircle.setFillColor(Color.red);
		add(firstCircle);
		
		//second middle circle
		double secondRad = 0.65 * 72;
		double secondDiam = secondRad * 2;
		double posx2 = centerx - secondRad;
		double posy2 = centery - secondRad;
		GOval secondCircle = new GOval (posx2,posy2,secondDiam,secondDiam);
		secondCircle.setFilled(true);
		secondCircle.setFillColor(Color.white);
		add(secondCircle);
		
		//third inner circle
		double thirdRad = 0.3 * 72;
		double thirdDiam = thirdRad * 2;
		double posx3 = centerx - thirdRad;
		double posy3 = centery - thirdRad;
		GOval thirdCircle = new GOval (posx3,posy3,thirdDiam,thirdDiam);
		thirdCircle.setFilled(true);
		thirdCircle.setFillColor(Color.red);
		add(thirdCircle);
	}
}
