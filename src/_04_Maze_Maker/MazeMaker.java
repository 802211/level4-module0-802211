package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		int r = new Random().nextInt(width);
		int r1 = new Random().nextInt(height);
		Cell rand = new Cell(r, r1);

		// 5. call selectNextPath method with the randomly selected cell
			selectNextPath(rand);
		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
			currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
			ArrayList <Cell> notVisited = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
			if(notVisited != null) {
		// C1. select one at random.
				int r3 = new Random().nextInt(notVisited.size());
		// C2. push it to the stack
				uncheckedCells.push(notVisited.get(r3));
				
		// C3. remove the wall between the two cells
				removeWalls(currentCell, notVisited.get(r3));
		// C4. make the new cell the current cell and mark it as visited
				currentCell = notVisited.get(r3);
				currentCell.setBeenVisited(true);
			}
		// D. if all neighbors are visited
			if(notVisited.size() == 0) {
		// D1. if the stack is not empty
				if(uncheckedCells.size() != 0) {
		// D1a. pop a cell from the stack
					//uncheckedCells.pop();
		// D1b. make that the current cell
					currentCell = uncheckedCells.pop();
				}
			}
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX() == c2.getX()) {
			if(c1.getY() == c2.getY()+1) {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}
			else if(c1.getY()+1 == c2.getY()) {
				c2.setSouthWall(false);
				c1.setNorthWall(false);
			}
		}
		else if(c1.getY() == c2.getY()) {
			if(c1.getX() == c2.getX()+1) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
			else if(c1.getX()+1 == c2.getX()) {
				c2.setWestWall(false);
				c1.setEastWall(false);
			}
		}
		
		
		
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		return null;
	}
}