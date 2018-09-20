package _04_Maze_Maker;

import java.util.Random;

public class Checkpoint {

	public static void main(String[] args) {
			
	int[][]arr = new int[5][5];
	for(int i = 0; i<arr.length; i++) {
		for(int j = 0; j<arr[i].length; j++) {
			int rand = new Random().nextInt(25);
			arr[i][j] = rand;
		}
	}
	
	for(int i = 0; i<arr.length; i++) {
		
		for(int j = 0; j<arr[i].length; j++) {
			System.out.print(arr[i][j] + " ");
	
	}
		System.out.println(" ");
	
	}
	

	}

	
}
