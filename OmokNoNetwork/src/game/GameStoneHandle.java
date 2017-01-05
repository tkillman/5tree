package game;

import java.util.Scanner;

public class GameStoneHandle {

	static boolean winlose = false;	// 승패를 판단하는 변수	
	static int[][] board = new int[19][19]; // 바둑판 생성  
	static int[] point = new int[2];		// 바둑돌을 놓는 좌표를 저장하는 배열
	static int[] checkPoint = new int[2];	// 주변돌의 좌표를 넣어주는 배열
	static int[] turnCnt = { 1 };// 턴 카운트	

	public static void printGame(int[][] arr) { // 바둑판 출력
		
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int stone(int[] point) { // 좌표를입력받아돌을 놓아주는메소드
		return stone(point, board, turnCnt);
	}

	public static int stone(int[] point, int[][] board, int[] cnt) { // 좌표를입력받아돌을 놓아주는메소드

		
		if(isCheck(point, board)== 0 ){ //초기에 돌이 놓여있지 않은 상태라면 
				if (turn(cnt) == 2){ // 흑 차례
					
					board[point[0]][point[1]] = 1;
				}
				else if (turn(cnt) == 1){ // 백 차례
					
					board[point[0]][point[1]] = 2;
				}
				
				printGame(board);
	
				DoubleThreeAlg three = new DoubleThreeAlg(point, checkPoint, board);
				if(three.checkThree(point, checkPoint, board))	// 33체크    33이면 발동
				{
					board[point[0]][point[1]] = 0;
					return 3;
				}
				
				cnt[0]++; // 턴 증가
	
				GameWinAlg wa = new GameWinAlg(point, checkPoint, board);	
				winlose = wa.compareStone(point, checkPoint, board);
				
				if (winlose){
					System.out.println("승리!!");
					return 1;
				}
				else{
					return 2;
				}
			}
			else{
				return 2;
			}
		
	}

	//무슨 돌이 놓아져있는지 확인하는 메소드
	public static int isCheck(int[] point, int[][] board) {

		switch (board[point[0]][point[1]]) {

		case 1:
			return 1; // 흑돌
		case 2:
			return 2; // 백돌

		default:
			return 0;
		}
	}

	public int isCheck(int y, int x) {

		switch (board[y][x]) {

		case 1:
			return 1; // 흑돌
		case 2:
			return 2; // 백돌

		default:
			return 0;
		}
	}

	public static int turn(int[] turnCnt) { // 짝수인지 홀수인지 리턴

		return (turnCnt[0] % 2) + 1;
	}
}
