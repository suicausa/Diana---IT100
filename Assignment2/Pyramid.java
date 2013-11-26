/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file generates a 2D pyramid of bricks in the center of the window.
 * With the constants of the brick dimensions (width and height), and the number of bricks in the base,
 * this program determines the bottom left point of the pyramid and begins piling bricks horizontally.
 * Once the base has been completed, its position shifts up 1 brick height, and over half brick width
 * as the starting point of the second line of bricks.
 * The number of bricks per line decreases by one until there is only one brick, the peak of the pyramid.
 * At this point the program stops.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
		private static final int BRICK_WIDTH = 30;

	/** Height of each brick in pixels */
		private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
		private static final int BRICKS_IN_BASE = 14;

		   
		public void run() {
			//initial x: center minus half the number of bricks in base
			int posx = (getWidth()/2) - (BRICK_WIDTH*(BRICKS_IN_BASE/2));
			//initial y: window height minus the height of a brick, as the GRect draws from upper left corner
			int posy = getHeight()-BRICK_HEIGHT;
			//counter value for number of bricks to output
			int count = BRICKS_IN_BASE;
			
			//while the counter is not 0
			while (count > 0) {
				for (int j = 0; j < count; j++) {
					int x = posx + (j * BRICK_WIDTH);
					int y = posy;
					GRect sq = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
					add(sq);
				}
				posy -= BRICK_HEIGHT; //y position of brick output at the next level
				posx += (BRICK_WIDTH/2); //x position of brick output at half a brick to the right
				count --; //decrease the number of bricks per row by 1
			}
		}
	}

