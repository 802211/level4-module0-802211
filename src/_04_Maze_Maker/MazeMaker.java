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
		Cell rand = maze.getCell(r, r1);
		int rrr = new Random().nextInt();
		int RRR = new Random().nextInt(5);
		int RR = new Random().nextInt(5);
		if(rrr%2 == 0) {
		maze.getCell(0, RRR).setWestWall(false);
		maze.getCell(4, RR).setEastWall(false);
		}
		else {
			maze.getCell(RRR, 0).setSouthWall(false);
			maze.getCell(RR, 4).setNorthWall(false);
		}
		
		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(rand);
		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> notVisited = getUnvisitedNeighbors(currentCell);
		System.out.println(notVisited.size());
		// C. if has unvisited neighbors,
		if (notVisited.size() != 0) {
			// C1. select one at random.
			int r3 = new Random().nextInt(notVisited.size());
			// C2. push it to the stack
			uncheckedCells.push(notVisited.get(r3));

			// C3. remove the wall between the two cells
			removeWalls(currentCell, notVisited.get(r3));
			// C4. make the new cell the current cell and mark it as visited
			currentCell = notVisited.get(r3);
			//currentCell.setBeenVisited(true);
			selectNextPath(currentCell);
		}
		// D. if all neighbors are visited
		else {
			// D1. if the stack is not empty
			if (uncheckedCells.size() != 0) {
				// D1a. pop a cell from the stack
				// uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = uncheckedCells.pop();
				selectNextPath(currentCell);
			}
			
		}
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		// if(c1.getX() == c2.getX()) {
		// if(c1.getY() == c2.getY()+1) {
		// c1.setSouthWall(false);
		// c2.setNorthWall(false);
		// }
		// else if(c1.getY() == c2.getY()-1) {
		// c1.setNorthWall(false);
		// c2.setSouthWall(false);
		// }
		// }
		// else if(c1.getY() == c2.getY()) {
		// if(c1.getX() == c2.getX()+1) {
		// c1.setWestWall(false);
		// c2.setEastWall(false);
		// }
		// else if(c1.getX() == c2.getX()-1) {
		// c1.setEastWall(false);
		// c2.setWestWall(false);
		// }
		// }

		if (c1.getX() == c2.getX()) {
			if (c1.getY() > c2.getY()) {
				c2.setSouthWall(false);
				c1.setNorthWall(false);
			} else {
				c2.setNorthWall(false);
				c1.setSouthWall(false);
			}
		}
		else {
			if (c1.getX() > c2.getX()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			} else {
				c1.setEastWall(false);
				c2.setWestWall(false);
			}
		}
	}

	
	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();

		int XxX = c.getX();
		int YyY = c.getY();
		if (XxX != width - 1 && maze.getCell(XxX + 1, YyY).hasBeenVisited() == false) {
			unvisitedNeighbors.add(maze.getCell(XxX + 1, YyY));
		}
		if (XxX != 0 && maze.getCell(XxX - 1, YyY).hasBeenVisited() == false) {
			unvisitedNeighbors.add(maze.getCell(XxX - 1, YyY));
		}
		if (YyY != height - 1 && maze.getCell(XxX, YyY + 1).hasBeenVisited() == false) {
			unvisitedNeighbors.add(maze.getCell(XxX, YyY + 1));
		}
		if (YyY != 0 && maze.getCell(XxX, YyY - 1).hasBeenVisited() == false) {
			unvisitedNeighbors.add(maze.getCell(XxX, YyY - 1));
		}
		return unvisitedNeighbors;
	}
}