package ticTacToe_Ofri;

public class Computer {

	private char sign, enemySign;
	private Board board;

	public Computer(char sign1, char sign2, Board board) {
		this.enemySign = sign1;
		this.sign = sign2;
		this.board = board;
	}

	public int turn() {
		if (blockOrWin(sign))
			return 0;
		else if (blockOrWin(enemySign))
			return 0;

		move();

		return 0;
	}

	private int move() {
		if (board.board[1][1] == '-') {
			board.putSign(1, 1, sign);
			return 0;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board.board[i][j] == '-' && (Math.abs(i - j) == 2 || Math.abs(i - j) == 0)) {
					board.putSign(i, j, sign);
					return 0;
				}
			}
		}
		randomMove();
		return 0;
	}

	private void randomMove() {
		int n1 = (int) (Math.random() * 3), n2 = (int) (Math.random() * 3);

		while (board.board[n1][n2] != '-') {
			n1 = (int) (Math.random() * 3);
			n2 = (int) (Math.random() * 3);
		}
		board.putSign(n1, n2, sign);
	}

	private boolean blockOrWin(char s) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board.board[i][j] != '-')
					continue;

				board.putSign(i, j, s);
				if (board.checkBoard(s)) {
					board.eraseSign(i, j);
					board.putSign(i, j, sign);
					return true;
				} else
					board.eraseSign(i, j);
			}
		}

		return false;
	}
}
