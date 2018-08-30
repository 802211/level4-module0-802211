package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;

	private Timer timer;

	// 1. Create a 2D array of Cells. Do not initialize it.

	Cell[][] cells;

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;
System.out.println(cpr);
		// 2. Calculate the cell size.
		cellSize = w/cpr;
		// 3. Initialize the cell array to the appropriate size.
		cells = new Cell[cpr][cpr];
		// 3. Iterate through the array and initialize each cell.
		// Don't forget to consider the cell's dimensions when
		// passing in the location.
		for (int j = 0; j < cells.length; j++) {
		for (int i = 0; i < cells[j].length; i++) {
				cells[j][i] = new Cell(j*cellSize,i*cellSize, cellSize);
			}
		}
	}

	public void randomizeCells() {
		// 4. Iterate through each cell and randomly set each
		// cell's isAlive memeber to true or false
		for (int j = 0; j < cells.length; j++) {
		for (int i = 0; i < cells[j].length; i++) {
				int r = new Random().nextInt();
				if (r % 2 == 0) {
					cells[j][i].isAlive = false;
				} else {
					cells[j][i].isAlive = true;
				}
			}
		}
		repaint();
	}

	public void clearCells() {
		// 5. Iterated through the cells and set them all to dead.
		for (int j = 0; j < cells.length; j++) {
		for (int i = 0; i < cells[j].length; i++) {
				cells[j][i].isAlive = false;
			}
		}
		repaint();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
		// 6. Iterate through the cells and draw them all
//System.out.println("paint");
		for (int j = 0; j < cells.length; j++) {
		for (int i = 0; i < cells[j].length; i++) {
				cells[j][i].draw(g);
			}
		}

		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	// advances world one step
	public void step() {
		// 7. iterate through cells and get their neighbors
		//
//System.out.println("step");
		int[][] neighbors = new int[cellsPerRow][cellsPerRow];
		for (int j = 0; j < cells.length; j++) {
			for (int i = 0; i < cells[j].length; i++) {
				neighbors[j][i] = getLivingNeighbors(j, i);
			}
		}

		// 8. check if each cell should live or die
		for (int j = 0; j < cells.length; j++) {
		for (int i = 0; i < cells[j].length; i++) {
				cells[j][i].liveOrDie(neighbors[j][i]);
			}
		}
	}
	//

	// repaint();

	// 9. Complete the method.
	// It returns an array list of the 8 or less neighbors of the
	// cell identified by x and y
	
	///////*(above) no it doesn't, return an int.

	public int getLivingNeighbors(int x, int y) {
		int neighbors = 0;
		
		if(x!=0) {
			if (cells[x - 1][y].isAlive = true) {
				neighbors++;
			}
		}
		 if(x!=cellsPerRow-1) {
			if (cells[x + 1][y].isAlive = true) {
				neighbors++;
			}
		}
		if(y!=0) {
			if (cells[x][y-1].isAlive = true) {
				neighbors++;
			}
		}
		 if(y!=cellsPerRow-1) {
			if (cells[x][y+1].isAlive = true) {
				neighbors++;
			}
		}
		if(x!=0 && y != 0) {
			if (cells[x-1][y - 1].isAlive = true) {
				neighbors++;
			}
			
		}

		if(x!=0 && y!=cellsPerRow-1) {
			
			if (cells[x - 1][y+1].isAlive = true) {
				neighbors++;
			}
			
		}
		if(x!=cellsPerRow-1 && y!=cellsPerRow-1) {
			
			if (cells[x + 1][y+1].isAlive = true) {
				neighbors++;
			}
		}
		if(x!=cellsPerRow-1 && y!=0) {
		
			if (cells[x + 1][y-1].isAlive = true) {
				neighbors++;
			}
			
		}
		return neighbors;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 10. Use e.getX() and e.getY() to determine
		// which cell is clicked. Then toggle
		// the isAlive variable for that cell.
		
		if(cells[e.getX()/cellSize][e.getY()/cellSize].isAlive == true) {
			cells[e.getX()/cellSize][e.getY()/cellSize].isAlive = false;
		}
		if(cells[e.getX()/cellSize][e.getY()/cellSize].isAlive == false) {
			cells[e.getX()/cellSize][e.getY()/cellSize].isAlive = true;
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}
