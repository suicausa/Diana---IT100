/*
 * File: Breakout.java
 * -------------------
 * Name: Diana
 * 
 * This file will eventually implement the game of Breakout.
 * Referenced http://snipplr.com/view/62343/
 * Received help for setting up order of events for play and conditions for collisions.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	private GLabel gameStart;
	private GLabel gameWin;
	private GLabel gameOver;
	private int turnsLeft = NTURNS;
	public void run() { 
 		createGame();
 		gameStart = new GLabel("CLICK TO PLAY");
 		gameStart.setLocation((WIDTH - gameStart.getWidth()) / 2, HEIGHT / 2);
 		add(gameStart);
 		
	 	while(turnsLeft > 0) {//allow player to play while there are still turns
	 		play();//method breaks when ball exceeds window height, meaning paddle misses it
	 		turnsLeft --;//removes a turn each time play() breaks
 		}
 		//shows Game Over
 		GLabel gameOver = new GLabel("GAME OVER");
 		gameOver.setLocation((WIDTH - gameOver.getWidth()) / 2, HEIGHT / 2);
 		add(gameOver);
 		
 		gameWin = new GLabel("NO MORE BRICKS");
 		gameWin.setLocation((WIDTH - gameStart.getWidth()) / 2, HEIGHT / 2);
 		
	}
	
	private void createGame () {
		makeBricks();
		makePaddle();
 		addMouseListeners();
	}
	
	private void makeBricks(){
		//get brick row width
		double widthBrickRow = (BRICK_WIDTH + BRICK_SEP)*NBRICKS_PER_ROW;
		//starting x is midpoint minus half brick row plus brick space offset
		double posX = WIDTH/2 - widthBrickRow/2 + BRICK_SEP/2;
		
			for (int i = 0; i < NBRICK_ROWS; i++) {
				for (int j = 0; j < NBRICKS_PER_ROW; j++) {
					double x = posX + (j * (BRICK_WIDTH + BRICK_SEP));
					double y = BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP);
					GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
					brick.setFilled(true);
					if (i == 0 || i == 1) {//top 2 rows color
						brick.setColor(Color.RED);
						brick.setFillColor(Color.RED);
					}
					else if (i == 2 || i == 3) {//3rd and 4th rows color
						brick.setColor(Color.ORANGE);
						brick.setFillColor(Color.ORANGE);
					}
					else if (i == 4 || i == 5) {//5th and 6th rows color
						brick.setColor(Color.YELLOW);
						brick.setFillColor(Color.YELLOW);
					}
					else if (i == 6 || i == 7) {//7th and 8th rows color
						brick.setColor(Color.GREEN);
						brick.setFillColor(Color.GREEN);
					}
					else {//left over rows color
						brick.setColor(Color.CYAN);
						brick.setFillColor(Color.CYAN);
					}
					add(brick);
				}
			}
	}
	
	private GRect paddle;
	private void makePaddle(){
		double startX = WIDTH/2 - PADDLE_WIDTH/2; //x position is half of paddle width from center x
		double startY = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;//y position is paddle offset from bottom, minus its height
		paddle = new GRect(startX, startY, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}
	public void mouseMoved(MouseEvent e) {
		double posX = e.getX() - PADDLE_WIDTH/2;//position middle of paddle at cursor x
		double posY = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;//constant y position
		//set range of cursor motion
		double head = e.getX() - PADDLE_WIDTH/2;
		double tail = e.getX() + PADDLE_WIDTH/2;
		//reposition paddle within set range
		if (head > 0 && tail < WIDTH) {
			paddle.setLocation(posX, posY);
		}
	}
	
	private GOval ball;
	public void makeBall() {
		double posX = WIDTH/2 - BALL_RADIUS;//x position is half ball width from center x
		double posY = HEIGHT/2 - BALL_RADIUS;//y position is half ball width from center y
		ball = new GOval (posX,posY,BALL_RADIUS*2,BALL_RADIUS*2);
		ball.setFilled(true);
		add(ball);
	}
	/** Animation delay or pause time between ball moves */
	private static final int DELAY = 50;
	private double vx, vy;
	private RandomGenerator rgen = RandomGenerator.getInstance();

	/** Update and move ball */
	private void moveBall() {
		ball.move(vx,vy);
	}
	/** Determine if collision with walls, update velocities * and location as appropriate. */
	private void checkForCollision() {
 		if (ball.getX() + 2 * BALL_RADIUS > WIDTH || ball.getX() < 0) {
 			vx = -vx;
 		}
 		if (ball.getY() < 0) {
 			vy = -vy;
 		}
	}
	private GObject collidingObject; 
	private GObject getCollidingObject() {
		if (getElementAt(ball.getX(), ball.getY()) != null) {//get object that touches top left corner of ball object
 			collidingObject = getElementAt(ball.getX(), ball.getY());
 		}
		else if (getElementAt(ball.getX() + (2 * BALL_RADIUS), ball.getY()) != null) {//get object that touches top right corner of ball object
 			collidingObject = getElementAt(ball.getX() + (2 * BALL_RADIUS), ball.getY());
 		}
		else if (getElementAt(ball.getX(), ball.getY() + (2 * BALL_RADIUS)) != null) {//get object that touches bottom left corner of ball object
 			collidingObject = getElementAt(ball.getX(), ball.getY() + (2 * BALL_RADIUS));
 		}
		else if (getElementAt(ball.getX() + (2 * BALL_RADIUS), ball.getY() + (2 * BALL_RADIUS)) != null) {//get object that touches bottom right corner of ball object
 			collidingObject = getElementAt(ball.getX() + (2 * BALL_RADIUS), ball.getY() + (2 * BALL_RADIUS));
 		}
		else {
 			collidingObject = null;
 		}
 		return collidingObject;
	}
	
	private void play() {
		waitForClick();
		remove(gameStart);
		makeBall();
		vx = rgen.nextDouble(1.0, 5.0);
	    if (rgen.nextBoolean(0.5)) vx = -vx;
	    vy = 5.0;
	    int totalBricks = NBRICKS_PER_ROW * NBRICK_ROWS;
		moveBall();
	    while (true) {
 			moveBall();
 			checkForCollision();
			GObject collider = getCollidingObject();
			/*Increases velocity in both directions by 0.15 each time the ball collides with the paddle. */
			if (collider != null && collider == paddle) {
				vy = vy + 0.15;
				vx = vx + 0.15;
				/* If the ball hits the first fifth of the paddle while traveling in a positive x-direction, 
				 * or the last fifth of the paddle while traveling in a negative x-direction, x-velocity is doubled. 
				 * If the inverse of either of these is true, the ball will bounce in the opposite x-direction as it was received,
				 * and x-velocity is halved.
				 */
				if (vx > 0 && ball.getX() < (paddle.getX() + (PADDLE_WIDTH * 0.2))) {
					vx = 0.5 * vx;
					vx = -vx;
					vy = -vy;
				} else if (vx < 0 && ball.getX() < (paddle.getX() + (PADDLE_WIDTH * 0.2))) {
					vx = 2 * vx;
					vy = -vy;
				} else if (vx < 0 && ball.getX() > (paddle.getX() + (PADDLE_WIDTH * 0.8))) {
					vx = 0.5 * vx;
					vx = -vx;
					vy = -vy; 					
				} else if (vx > 0 && ball.getX() > (paddle.getX() + (PADDLE_WIDTH * 0.8))) {
					vx = 2 * vx;
					vy = -vy;
				} else vy = -vy;
			}
			
			if (collider != null && collider != paddle) {//if ball hits brick, remove brick
				remove(collider);
				vy = -vy;
				totalBricks --;
			}
			if (ball.getY() + 2 * BALL_RADIUS > HEIGHT) {//if y of bottom of ball goes beyond height of window, remove ball
				remove(ball);
				break;
			}
			if (totalBricks == 0) {//if no more bricks are left, player wins
				remove(ball);
				add(gameWin);
				turnsLeft = 0;//ends the game
				break;
			}
			pause(DELAY);
	    }	     
	}
}
