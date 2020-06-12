package ics4ustart;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Text (console) based driver for testing purposes.
 * @author Hutchison
 *
 */
public class Driver {

	public static void main(String[] args) {

		// Setup constants for the Board
		final int ROWS = 6;
		final int COLS = 7;

		
		
		
		//turn counter
		int turnCount = 1;
		boolean player1 = false;
		boolean player2 = false;
		
			
		
		// create the board
		Board board = new Board(ROWS, COLS);
		board.display();
		
		// console input
		Scanner in = new Scanner(System.in);
		boolean done = false;
		String value = "";
		int column = 0;
		
		while (!done) {
			//displays player turn 
			
			player1 = turnCounter(turnCount,player1);
			
			
			if(player1 == true) {
				System.out.println("player 1's turn");
			}
			
				if(player1 == false) {
					System.out.println("player 2's turn");
				}
			column = getColumn(in, 1, COLS); // include min and max
			board.fillCol(column, player1);
			if(board.WinConditions(ROWS,COLS) == true && player1 == true) {
				System.out.println("player 1 wins");
				done = true;
			}
			if(board.WinConditions(ROWS,COLS) == true && player1 == false) {
				System.out.println("player 2 wins");
				done = true;
			}
			
			
			turnCount ++;
			board.display();
			
			
			
			
			}
			
	}	
		
/**		
 * Helper method to ensure column value is valid.
 * @param in
 * @return
 */		
	private static int getColumn(Scanner in, int min, int max) {
		boolean valid = false;
		int column = 0;
		
		while (!valid) {
			String prompt = String.format("Which column (%d,%d) :",min,max); 
			System.out.print(prompt);
			if (in.hasNextInt()) {
				column = in.nextInt();
				if (column >= min && column <= max) {
					valid = true;
				} 
				
				else {
					System.out.println("Invalid numeric value provided.");
				}
			} else {
				System.out.println("Not a number.");
			}
			in.nextLine();
		}
		return column;
	}
	
	public static boolean turnCounter(int turnCount, boolean player1){
		if(turnCount %2 != 0) {
			player1 = true;
			return player1;
			
		}
		else {
			player1= false;
			return player1;
			
		
		}
		
	}
	
		
	
	
	
}
