package guerrasandgame;
/* Aidan Guerra
 * 1/14/2022
 * Create a game of falling sand
 */

import edu.du.dudraw.DUDraw;
import java.util.Scanner;

public class SandGame {

    public static void main(String[] args) {
	Scanner keyboard = new Scanner(System.in);

	DUDraw.enableDoubleBuffering();
	int type = 1;
	char userInput = 's';

	// Create SandWorld object and bring array to life
	SandWorld sand = new SandWorld(130, 130);

	while (true) {

	    // controls the user keyboard input
	    if (DUDraw.hasNextKeyTyped()) {
		userInput = DUDraw.nextKeyTyped();

		if (userInput == 'f') {
		    type = SandWorld.FLOOR;
		    System.out.println("Floor Selected");
		}

		if (userInput == 's') {
		    type = SandWorld.SAND;
		    System.out.println("Sand Selected");
		}

		if (userInput == 'w') {
		    type = 3;
		    System.out.println("Water Selected");
		}
	    }
	    // creates particles of different types when the mouse is pressed
	    if (DUDraw.isMousePressed()) {
		sand.placeParticle((int) DUDraw.mouseX() + ((int)Math.random()*2), (int) DUDraw.mouseY(), type);
	    }

	    sand.step();

	    sand.setSand();

	    sand.displayToolName(type);

	    DUDraw.pause(5);
	    DUDraw.show();

	}
    }

}
