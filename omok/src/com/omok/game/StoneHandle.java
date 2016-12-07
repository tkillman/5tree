package com.omok.game;

import java.util.Scanner;

public class StoneHandle {
	
	static boolean winlose = false;	// 승패를 판단하는 변수
	
	static int[][] board = new int[19][19]; // 바둑판 생성  

	static int[] point = new int[2];		// 바둑돌을 놓는 좌표를 저장하는 배열
	static int[] checkPoint = new int[2];	// 주변돌의 좌표를 넣어주는 배열
	static int[] turnCnt = { 1 };// 턴 카운트
	
	
	public static void gameStart() {

//		int[][] board = new int[19][19]; // 바둑판 생성  
//
//		int[] point = new int[2];		// 바둑돌을 놓는 좌표를 저장하는 배열
//		int[] checkPoint = new int[2];	// 주변돌의 좌표를 넣어주는 배열
//		int[] turnCnt = { 1 };// 턴 카운트
//
//		WinAlg wa = new WinAlg(point, checkPoint, board);
//		
//		while (winlose==false) {
//			stone(point, board,  turnCnt);
//			winlose = wa.compareStone(point, checkPoint, board);
//		}
//		
//		if(turn(turnCnt) == 1)
//			System.out.println("백돌 승리");
//		else
//			System.out.println("흑돌 승리");
	}
	
	
	
	
	
	

	public static void printGame(int[][] arr) { // 바둑판 출력
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	public void stone(int[] point) { // 좌표를입력받아돌을 놓아주는메소드
		stone(point, board,  turnCnt);
	}

	public static void stone(int[] point, int[][] board, int[] cnt) { // 좌표를입력받아돌을 놓아주는메소드
		Scanner input = new Scanner(System.in);
//		point[0] = input.nextInt();
//		point[1] = input.nextInt();

		if (isCheck(point, board) == 0 && turn(cnt) == 1)
			board[point[0]][point[1]] = 1;
		else if (turn(cnt) == 0)
			board[point[0]][point[1]] = 2;
		// else
		printGame(board);
		
		cnt[0]++; // 턴 증가
		
		WinAlg wa = new WinAlg(point, checkPoint, board);
		
	
		winlose = wa.compareStone(point, checkPoint, board);
		
		if (winlose)
		 System.out.println("승리!!");
//		if(turn(turnCnt) == 1)
//			System.out.println("백돌 승리");
//		else
//			System.out.println("흑돌 승리");
		
	}

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

	public static int turn(int[] turnCnt) { // 짝수인지 홀수인지 리턴

		if (turnCnt[0] % 2 == 0)
			return turnCnt[0] % 2; // 흑 차례
		else
			return turnCnt[0] % 2; // 백 차례
	}
}
