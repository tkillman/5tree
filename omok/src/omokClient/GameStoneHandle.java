package omokClient;

public class GameStoneHandle {
	private int[][] board = new int[19][19];
	private boolean blackTurn = true;

	public void putStoneOnBoard(int[] point) {
		if(blackTurn){
			board[point[1]][point[0]] = 1;
		} else{
			board[point[1]][point[0]] = 2;
		}
		blackTurn = !blackTurn;
	}

	public boolean checkStoneExist(int y, int x) {
		switch (board[y][x]) {
			case 1:
			case 2:
				return true;
			default:
				return false;
		}
	}

	public void clearBoard(){
		for(int xIndex = 0 ; xIndex < board.length ; xIndex++){
			for(int yIndex = 0 ; yIndex < board[xIndex].length ; yIndex++){
				board[xIndex][yIndex] = 0;
			}
		}
	}

	public int[][] getBoard(){
		return board;
	}
}
