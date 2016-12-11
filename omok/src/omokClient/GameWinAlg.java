package omokClient;

public class GameWinAlg {
/*	private final int[] UP = {-1, 0};
	private final int[] RIGHT_UP = {-1, 1};
	private final int[] RIGHT = {0, 1};
	private final int[] RIGHT_DOWN = {1, 1};
	private final int[] DOWN = {1, 0};
	private final int[] LEFT_DOWN = {1, -1};
	private final int[] LEFT = {0, -1};
	private final int[] LEFT_UP = {-1, -1};*/

	private int countSameStoneOnVertical(int[] point, int[][] board) {
		int cnt = 0;
		int[] nextCheckUpPoint = point.clone();
		while (nextCheckUpPoint[0] >= 0 && (board[nextCheckUpPoint[1]][nextCheckUpPoint[0]] == board[point[1]][point[0]])) {
			cnt++;
			nextCheckUpPoint[0]--;
		}

		int[] nextCheckDownPoint = point.clone();
		while (nextCheckDownPoint[0] < board.length && (board[nextCheckDownPoint[1]][nextCheckDownPoint[0]] == board[point[1]][point[0]])) {
			cnt++;
			nextCheckDownPoint[0]++;
		}
		return --cnt;
	}

	private int countSameStoneOnHorizon(int[] point, int[][] board) {
		int cnt = 0;
		int[] nextCheckRightPoint = point.clone();
		while (nextCheckRightPoint[1] < board.length && (board[nextCheckRightPoint[1]][nextCheckRightPoint[0]] == board[point[1]][point[0]])) {
			cnt++;
			nextCheckRightPoint[1]++;
		}
		int[] nextCheckLeftPoint = point.clone();
		while (nextCheckLeftPoint[1] >= 0 && (board[nextCheckLeftPoint[1]][nextCheckLeftPoint[0]] == board[point[1]][point[0]])) {
			cnt++;
			nextCheckLeftPoint[1]--;
		}
		return --cnt;
	}

	private int countSameStoneOnDiagonalRight(int[] point, int[][] board) {
		int cnt = 0;
		int[] nextCheckDiagonalRightUpPoint = point.clone();
		while ((nextCheckDiagonalRightUpPoint[0] >= 0 && nextCheckDiagonalRightUpPoint[1] < board.length)
				&& (board[nextCheckDiagonalRightUpPoint[1]][nextCheckDiagonalRightUpPoint[0]] == board[point[1]][point[0]])) {
			cnt++;
			nextCheckDiagonalRightUpPoint[0]--;
			nextCheckDiagonalRightUpPoint[1]++;
		}

		int[] nextCheckDiagonalLeftDownPoint = point.clone();
		while ((nextCheckDiagonalLeftDownPoint[0] < board.length && nextCheckDiagonalLeftDownPoint[1] >= 0)
				&& (board[nextCheckDiagonalLeftDownPoint[1]][nextCheckDiagonalLeftDownPoint[0]] == board[point[1]][point[0]])) {
			cnt++;
			nextCheckDiagonalLeftDownPoint[0]++;
			nextCheckDiagonalLeftDownPoint[1]--;
		}
		return --cnt;
	}

	private int countSameStoneDiagonalLeft(int[] point, int[][] board) {
		int cnt = 0;
		int[] nextCheckDiagonalRightDownPoint = point.clone();
		while ((nextCheckDiagonalRightDownPoint[0] < board.length && nextCheckDiagonalRightDownPoint[1] < board.length)
				&& (board[nextCheckDiagonalRightDownPoint[1]][nextCheckDiagonalRightDownPoint[0]] == board[point[1]][point[0]])) {
			cnt++;
			nextCheckDiagonalRightDownPoint[0]++;
			nextCheckDiagonalRightDownPoint[1]++;
		}

		int[] nextCheckDiagonalLeftUpPoint = point.clone();
		while ((nextCheckDiagonalLeftUpPoint[0] >= 0 && nextCheckDiagonalLeftUpPoint[1] >= 0)
				&& (board[nextCheckDiagonalLeftUpPoint[1]][nextCheckDiagonalLeftUpPoint[0]] == board[point[1]][point[0]])) {
			cnt++;
			nextCheckDiagonalLeftUpPoint[0]--;
			nextCheckDiagonalLeftUpPoint[1]--;
		}
		return --cnt;
	}

	public boolean compareStone(int[] point, int[][] board) {
		System.out.println("vertical" + countSameStoneOnVertical(point, board));
		System.out.println("horizon" + countSameStoneOnHorizon(point, board));
		System.out.println("DiagnolRight" + countSameStoneOnDiagonalRight(point, board));
		System.out.println("DiagnolLeft" + countSameStoneDiagonalLeft(point, board));
		return Math.max(
				Math.max(countSameStoneOnVertical(point, board), countSameStoneOnHorizon(point, board)),
				Math.max(countSameStoneOnDiagonalRight(point, board), countSameStoneDiagonalLeft(point, board))) >= 5;
	}
}
