/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 * 
 * Referenced https://github.com/NatashaTheRobot/Stanford-CS-106A/blob/master/Assignment4/HangmanCanvas.java
 * for if statement of displayWord()
 */

import acm.graphics.*;
import acm.util.ErrorException;

public class HangmanCanvas extends GCanvas {

	private double centerX;
	private double centerY;
	private double startX;
	private double startY;
	private double lastX;
	private double lastY;
	private GLabel wordLabel;

	private int count = 0;
	private String str = "";
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		drawScaffold();
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		startX = centerX - BEAM_LENGTH;
		startY = centerY + SCAFFOLD_HEIGHT/2;
		
		wordLabel = new GLabel(word, startX, startY);
		wordLabel.setFont("Halvetica-24");
		if (getElementAt(startX,startY) != null){
			remove(getElementAt(startX,startY));
		}
		add(wordLabel);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {	
		drawMan(count);
		displayChar(letter);
		count++;
	}
	public void displayChar(char ch) {
		startX = centerX - BEAM_LENGTH;
		startY = centerY + 3*SCAFFOLD_HEIGHT/5;
		str += ch;
		GLabel charLabel = new GLabel(str, startX, startY);
		add(charLabel);
	}
	
	public void drawScaffold() {
		centerX = getWidth()/2;
		centerY = getHeight()/2;
		//rope
		startY = centerY - 2*SCAFFOLD_HEIGHT/3;
		lastY = startY + ROPE_LENGTH;
		GLine rope = new GLine(centerX, startY, centerX, lastY);
		add(rope);
		//beam
		startX = centerX - BEAM_LENGTH;
		GLine beam = new GLine(startX, startY, centerX, startY);
		add(beam);
		//sacffold
		lastY = startY + SCAFFOLD_HEIGHT;
		GLine scaffold = new GLine(startX, startY, startX, lastY);
		add(scaffold);
	}
	public void drawMan(int count) {
		//head
		startX = centerX - HEAD_RADIUS;
		startY = centerY - 2*SCAFFOLD_HEIGHT/3 + ROPE_LENGTH;
		lastY = startY + 2*HEAD_RADIUS;
		GOval head = new GOval(startX, startY, 2*HEAD_RADIUS, 2*HEAD_RADIUS);
		//body
		startY = lastY;
		lastY = startY + BODY_LENGTH;
		double startHip = lastY;
		GLine body = new GLine (centerX, startY, centerX, lastY);
		//left arm
		startY = startY + ARM_OFFSET_FROM_HEAD;
		startX = centerX - UPPER_ARM_LENGTH;
		GLine upperLeftArm = new GLine (startX, startY, centerX, startY);
		lastY = startY + LOWER_ARM_LENGTH;
		GLine lowerLeftArm = new GLine (startX, startY, startX, lastY);
		//right arm
		lastX = centerX + UPPER_ARM_LENGTH;
		GLine upperRightArm = new GLine (centerX, startY, lastX, startY);
		GLine lowerRightArm = new GLine (lastX, startY, lastX, lastY);
		//hips
		startX = centerX - HIP_WIDTH;
		startY = startHip;
		GLine leftHip = new GLine(startX, startY, centerX, startY);
		lastX = centerX + HIP_WIDTH;
		GLine rightHip = new GLine(centerX, startY, lastX, startY);
		lastY = startHip + LEG_LENGTH;
		//legs
		GLine leftLeg = new GLine (startX, startY, startX, lastY);
		GLine rightLeg = new GLine (lastX, startY, lastX, lastY);
		//feet
		double leftstartX = startX - FOOT_LENGTH;
		GLine leftFoot = new GLine (leftstartX, lastY, startX, lastY);
		double rightlastX = lastX + FOOT_LENGTH;
		GLine rightFoot = new GLine (lastX, lastY, rightlastX, lastY);
		
		switch (count) {
			case 0: add(head); count++;
			break;
			case 1: add(body); count++;
			break;
			case 2: add(upperLeftArm); add(lowerLeftArm); count++;
			break;
			case 3: add(upperRightArm); add(lowerRightArm); count++;
			break;
			case 4: add(leftHip); add(leftLeg); count++;
			break;
			case 5: add(rightLeg); add(rightHip); count++;
			break;
			case 6: add(leftFoot); count++;
			break;
			case 7: add(rightFoot); count++;
			break;
		}
	}
/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
