package ticTacToe_Ofri;

import java.util.Scanner;

public class Board {

	private static Scanner in = new Scanner(System.in);

	public char[][] board = new char[3][3];

	/**
	 * Constructor creates an empty board.
	 */
	public Board() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				board[i][j] = '-';
		}
	}

	/**
	 * Returns whether the values are in range.
	 * 
	 * @param r
	 *            - Row
	 * @param c
	 *            - Column
	 */
	private boolean inRange(int r, int c) {
		if ((r >= 0 && r < 3) && (c >= 0 && c < 3))
			return true;
		else
			return false;
	}

	/**
	 * Prints the board.
	 */
	public void printBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(board[i][j] + " ");

			System.out.println("");
		}
	}

	/**
	 * Inserts a sign in a given position.
	 * 
	 * @param r
	 *            - Row
	 * @param c
	 *            - Column
	 * @param sign
	 *            - The sign to be inserted
	 */
	public void putSign(int r, int c, char sign) {
		// Checks whether position is out of bounds or taken
		while (!inRange(r, c) || !(board[r][c] == '-')) {
			System.out.println("Place taken or out of range. Enter a different location");
			r = in.nextInt() - 1;
			c = in.nextInt() - 1;
		}

		board[r][c] = sign; // Inserts the sign
	}

	/**
	 * Clears a given position.
	 * 
	 * @param r
	 *            - Row
	 * @param c
	 *            - Column
	 */
	public void eraseSign(int r, int c) {
		board[r][c] = '-';
	}

	/**
	 * Checks if an opponent won.
	 * 
	 * @param sign
	 *            - The sign of the tested opponent
	 */
	public boolean checkBoard(char sign) {
		int test;

		// Test for column win
		for (int i = 0; i < 3; i++) {
			test = 0;
			for (int j = 0; j < 3; j++)
				test += board[i][j];

			if (test/3 == sign)
				return true;
		}

		// Test for row win
		for (int i = 0; i < 3; i++) {
			test = 0;
			for (int j = 0; j < 3; j++)
				test += board[j][i];

			if (test/3 == sign)
				return true;
		}

		test = 0;
		for (int i = 0; i < 3; i++)
			test += board[i][i];

		if (test/3 == sign)
			return true;

		test = 0;
		for (int i = 0; i < 3; i++)
			test += board[i][2 - i];

		if (test/3 == sign)
			return true;

		return false;
	}
}