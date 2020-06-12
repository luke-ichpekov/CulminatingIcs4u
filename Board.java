package ics4ustart;

import java.util.Random;

/**
 * Represents a 2 dimensional Board for Grid based games.
 * 
 * @author Hutchison
 * @version 1.0
 *
 */
public class Board {

	private Cell[][] board;
	private int rows;
	private int cols;

	/**
	 * Constructor for Boards.
	 * 
	 * @param aRows number of rows in board
	 * @param aCols number of columns in board
	 */
	public Board(int aRows, int aCols) {
		board = new Cell[aRows][aCols];
		rows = aRows;
		cols = aCols;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new Cell(CellState.EMPTY); // no color
			}
		}
	}

	/**
	 * Obtain the current number of rows.
	 * 
	 * @return number of rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Obtain the current number of columns.
	 * 
	 * @return number of columns
	 */
	public int getCols() {
		return cols;
	}

	public void fillCol(int column, boolean player1) {

		int bottomRow = rows - 1;

		if (player1 == true) {
			if (board[bottomRow][column - 1].getState() == CellState.EMPTY) {
				board[bottomRow][column - 1].setState(CellState.P1);
			} else {
				RowCheck(column, player1);
			}
		}

		if (player1 == false) {
			if (board[bottomRow][column - 1].getState() == CellState.EMPTY) {
				board[bottomRow][column - 1].setState(CellState.P2);

			} else {
				RowCheck(column, player1);
			}
		}

		// else {
		// RowCheck(column);
		// }

	}

	public void RowCheck(int column, boolean player1) {
		int bottomRow = rows - 1;

		boolean spotTaken = true;

		while (spotTaken == true) {

			bottomRow--;

			if (bottomRow == -1) {
				System.out.println("That column has been filled");
				break;

			}

			if (player1 == true) {

				if (board[bottomRow][column - 1].getState() == CellState.EMPTY) {
					board[bottomRow][column - 1].setState(CellState.P1);
					spotTaken = false;

				}
			}
			if (player1 == false) {

				if (board[bottomRow][column - 1].getState() == CellState.EMPTY) {
					board[bottomRow][column - 1].setState(CellState.P2);
					spotTaken = false;

				}
			}

		}

	}

	public boolean WinConditions(int numRows, int numCols) {

		boolean done = false;
		int winCountVp1 = 0;
		int winCountVp2 = 0;
		int winCountHp1 = 0;
		int winCountHp2 = 0;

		// Check Vertical win

		for (int j = 0; j < numCols; j++) {
			for (int i = 0; i < numRows; i++) {
				if (board[i][j].getState() == CellState.P1) {
					winCountVp1 += 1;
				} else {
					winCountVp1 = 0;
				}
				if (board[i][j].getState() == CellState.P2) {
					winCountVp2 += 1;
				} else {
					winCountVp2 = 0;
				}

				if (winCountVp1 == 4) {
					done = true;
				}

				if (winCountVp2 == 4) {
					done = true;
				}

			}
			winCountVp1 = 0;
			winCountVp2 = 0;
		}

		// Check horizontal
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (board[i][j].getState() == CellState.P1) {
					winCountHp1 += 1;
				} else {
					winCountHp1 = 0;
				}
				if (board[i][j].getState() == CellState.P2) {
					winCountHp2 += 1;
				} else {
					winCountHp2 = 0;
				}

				if (winCountHp1 == 4) {
					done = true;
				}
				if (winCountHp2 == 4) {
					done = true;
				}
			}
			winCountHp1 = 0;
			winCountHp2 = 0;

		}

		// diagnol bot right to top left

		int winCountDiagBrP1;
		int winCountDiagBrP2;
		int winCountDiagBrP1Second;
		int winCountDiagBrP2Second;

		// first
		for (int i = 0; i < numRows; i++) {
			int rowCounter;
			int colCounter;
			winCountDiagBrP1 = 0;
			winCountDiagBrP2 = 0;

			for (rowCounter = i, colCounter = 0; rowCounter < numRows
					&& colCounter < numCols; rowCounter++, colCounter++) {
				if (board[rowCounter][colCounter].getState() == CellState.P1) {
					winCountDiagBrP1++;

				}
				if (board[rowCounter][colCounter].getState() == CellState.P2) {
					winCountDiagBrP2++;

				}

			}

			if (winCountDiagBrP1 == 4) {
				done = true;
			}
			if (winCountDiagBrP2 == 4) {
				done = true;
			}

		}

		// second
		for (int i = 0; i < numCols; i++) {
			winCountDiagBrP1Second = 0;
			winCountDiagBrP2Second = 0;
			int rowCounter;
			int colCounter;
			for (rowCounter = 0, colCounter = i; rowCounter < numRows
					&& colCounter < numCols; rowCounter++, colCounter++) {
				if (board[rowCounter][colCounter].getState() == CellState.P1) {
					winCountDiagBrP1Second++;

				}
				if (board[rowCounter][colCounter].getState() == CellState.P2) {
					winCountDiagBrP2Second++;

				}

			}

			if (winCountDiagBrP1Second == 4) {
				done = true;
			}
			if (winCountDiagBrP2Second == 4) {
				done = true;
			}

		}

		// diagnol top right to bot left

		int winCountDiagBlP1;
		int winCountDiagBlP2;
		int winCountDiagBlP1Second;
		int winCountDiagBlP2Second;

		// first
		for (int i = 0; i < numRows; i++) {
			int rowCounter;
			int colCounter;
			winCountDiagBlP1 = 0;
			winCountDiagBlP2 = 0;
			for (rowCounter = i, colCounter = numCols - 1; rowCounter < numRows
					&& colCounter > 0; rowCounter++, colCounter--) {
				if (board[rowCounter][colCounter].getState() == CellState.P1) {
					winCountDiagBlP1++;

				}
				if (board[rowCounter][colCounter].getState() == CellState.P2) {
					winCountDiagBlP2++;

				}

			}

			if (winCountDiagBlP1 == 4) {
				done = true;
			}
			if (winCountDiagBlP2 == 4) {
				done = true;

			}

		}

		// second
		for (int i = numCols - 2; i >= 0; i--) {
			winCountDiagBlP1Second = 0;
			winCountDiagBlP2Second = 0;
			int rowCounter;
			int colCounter;
			for (rowCounter = 0, colCounter = i; rowCounter < numRows && colCounter >= 0; rowCounter++, colCounter--) {
				if (board[rowCounter][colCounter].getState() == CellState.P1) {
					winCountDiagBlP1Second++;

				}
				if (board[rowCounter][colCounter].getState() == CellState.P2) {
					winCountDiagBlP2Second++;

				}

			}

			if (winCountDiagBlP1Second == 4) {
				done = true;
			}
			if (winCountDiagBlP2Second == 4) {
				done = true;

			}

		}

		return done;

	}

	public void display() {
		// System.out.println("BOARD");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%s ", board[i][j]);
			}
			System.out.println();
		}
	}
}
