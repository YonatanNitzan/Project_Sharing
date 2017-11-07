package ticTacToe_Ofri;

import java.util.Scanner;

public class TicTacToe {

	private static Board board;
	public static int turns = 1;
	private static char currentSign = 'O';
	private static char sign1 = 'X', sign2 = 'O';

	private static Scanner in = new Scanner(System.in);

	/**
	 * Player versus Player game mode
	 */
	public static void PVP() {
		while (true) {
			if (currentSign == sign1)
				currentSign = sign2;
			else
				currentSign = sign1;

			System.out.println("<---------------------------->");
			System.out.println("turn " + turns);
			System.out.println(currentSign + "'s turn");
			System.out.println("");
			System.out.println("Enter row and column number");
			board.printBoard();

			board.putSign(in.nextInt() - 1, in.nextInt() - 1, currentSign);

			if (board.checkBoard(currentSign))
				break;

			turns++;

			if (turns > 9)
				break;
		}
	}

	/**
	 * Player versus Computer game mode
	 */
	public static void PVC() {
		Computer computer = new Computer(sign1, sign2, board);
		
		while (true) {
			if (currentSign == sign1)
				currentSign = sign2;
			else
				currentSign = sign1;

			System.out.println("<---------------------------->");
			System.out.println("turn " + turns);
			System.out.println(currentSign + "'s turn \n");
			System.out.println("Enter row and column number");
			board.printBoard();

			if (currentSign == sign1)
				board.putSign(in.nextInt() - 1, in.nextInt() - 1, currentSign);
			else
				computer.turn();

			if (board.checkBoard(currentSign))
				break;

			turns++;

			if (turns > 9)
				break;
		}
	}

	public static void main(String[] args) {
		board = new Board();

		System.out.println("Do you want the normal X and O signs? (y/n)");
		
		if (in.next().charAt(0) == 'n') {
			System.out.print("Enter the first sign: ");
			sign1 = in.next().charAt(0);
			System.out.print("Enter the second sign: ");
			sign2 = in.next().charAt(0);
		}
			
		System.out.println("Enter preffered game mode: "
				+ "(1 - PVP/2 - PVC)");
		if (in.nextInt() == 1)
			PVP();
		else
			PVC();

		System.out.println("");
		board.printBoard();
		System.out.println("");

		if (turns > 9)
			System.out.println("TIE");
		else
			System.out.println(currentSign + " WON");
	}
}
