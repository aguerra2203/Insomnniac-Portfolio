package guerrasandgame;

import edu.du.dudraw.DUDraw;

public class SandWorld {
    private int[][] sandArray;
    public static final int EMPTY = 0;
    public static final int SAND = 1;
    public static final int FLOOR = 2;
    public static final int WATER = 3;

    public SandWorld(int x, int y) {
	DUDraw.setCanvasSize(250, 250);
	DUDraw.setScale(0, 125);

	// creates an array of particles
	sandArray = new int[x][y];
	for (int row = 0; row < sandArray.length; row++) {
	    for (int col = 0; col < sandArray[0].length; col++) {
		sandArray[row][col] = EMPTY;
		sandArray[row][0] = FLOOR;
	    }
	}

    }

    public boolean placeParticle(int col, int row, int newType) {
	// Places new particle on an empty space
	int r = row;
	int c = col;
	if (sandArray[r][c] == EMPTY) {
	    sandArray[r][c] = newType;
	}

	return true;
    }

    public void displayToolName(int newType) {
	// displays the name of the tool being used
	DUDraw.setPenColor(DUDraw.WHITE);
	DUDraw.filledRectangle(15, 120, 10, 10);
	DUDraw.setPenColor(DUDraw.BLACK);
	if (newType == SAND) {

	    DUDraw.text(15, 120, "SAND");
	}
	if (newType == FLOOR) {
	    DUDraw.text(20, 120, "FLOOR");
	}
	if (newType == WATER) {
	    DUDraw.text(20, 120, "WATER");
	}
    }

    public void step() {
	// reads the array and moves particles into open spaces
	for (int row = 1; row < sandArray.length - 1; row++) {
	    for (int col = 1; col < sandArray[0].length - 1; col++) {
		// drop sand one unit down if space below is empty
		if (sandArray[row][col] == SAND && sandArray[row - 1][col] == EMPTY) {
		    sandArray[row][col] = EMPTY;
		    sandArray[row - 1][col] = SAND;
		}
		// drop sand one unit to the right if right is empty
		if (sandArray[row][col] == SAND && sandArray[row - 1][col] == SAND
			&& sandArray[row - 1][col + 1] == EMPTY) {
		    sandArray[row][col] = EMPTY;
		    sandArray[row - 1][col + 1] = SAND;
		}
		// drop sand one unit to the left if left is empty
		if (sandArray[row][col] == SAND && sandArray[row - 1][col] == SAND
			&& sandArray[row - 1][col - 1] == EMPTY) {
		    sandArray[row][col] = EMPTY;
		    sandArray[row - 1][col - 1] = SAND;
		}
		// drop water one unit below if empty
		if (sandArray[row][col] == WATER
			//&& (sandArray[row - 1][col] == SAND || sandArray[row - 1][col] == WATER)
			&& sandArray[row - 1][col] == EMPTY) {
		    sandArray[row][col] = EMPTY;
		    sandArray[row - 1][col] = WATER;
		}
		// drop water one unit diagonally to the right if right is empty
		if (sandArray[row][col] == WATER
			&& (sandArray[row - 1][col] == SAND || sandArray[row - 1][col] == WATER)
			&& sandArray[row - 1][col + 1] == EMPTY) {
		    sandArray[row][col] = EMPTY;
		    sandArray[row - 1][col + 1] = WATER;
		}
		// drop water one unit diagonally to the left if left is empty
		if (sandArray[row][col] == WATER
			&& (sandArray[row - 1][col] == SAND || sandArray[row - 1][col] == WATER)
			&& sandArray[row - 1][col - 1] == EMPTY) {
		    sandArray[row][col] = EMPTY;
		    sandArray[row - 1][col - 1] = WATER;
		}
		// drop water one unit directly to the left if left is empty and sand or water exists below
		 if (sandArray[row][col] == WATER
			&& (sandArray[row - 1][col] == SAND || sandArray[row - 1][col] == WATER)
			&& sandArray[row][col - 1] == EMPTY) {
		    sandArray[row][col] = EMPTY;
		    sandArray[row][col - 1] = WATER;
		}
		// drop water one unit directly to the right if right is empty and sand or water exists below
		if (sandArray[row][col] == WATER
			&& (sandArray[row - 1][col] == SAND || sandArray[row - 1][col] == WATER)
			&& sandArray[row][col + 1] == EMPTY) {
		    sandArray[row][col] = EMPTY;
		    sandArray[row][col + 1] = WATER;
		} 
	    }
	}
    }

    public void setSand() {
	// Edits the array and draws the particles of each type
	for (int row = 0; row < sandArray.length; row++) {
	    for (int col = 0; col < sandArray[0].length; col++) {
		if (sandArray[row][col] == EMPTY) {
		    DUDraw.setPenColor(DUDraw.WHITE);
		    DUDraw.filledRectangle(col, row, 0.5, 0.5);
		}
		if (sandArray[row][col] == SAND) {
		    DUDraw.setPenColor(DUDraw.YELLOW);
		    DUDraw.filledRectangle(col  + ((int)Math.random()*2), row, 0.5, 0.5);
		}
		if (sandArray[row][col] == FLOOR) {
		    DUDraw.setPenColor(DUDraw.BLACK);
		    DUDraw.filledRectangle(col, row, 0.5, 0.5);
		}
		if (sandArray[row][col] == WATER) {
		    DUDraw.setPenColor(DUDraw.BLUE);
		    DUDraw.filledRectangle(col, row, 0.5, 0.5);
		}
	    }
	}
    }

}
