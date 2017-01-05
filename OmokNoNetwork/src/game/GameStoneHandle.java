package game;

import java.util.Scanner;

public class GameStoneHandle {

	static boolean winlose = false;	// ���и� �Ǵ��ϴ� ����	
	static int[][] board = new int[19][19]; // �ٵ��� ����  
	static int[] point = new int[2];		// �ٵϵ��� ���� ��ǥ�� �����ϴ� �迭
	static int[] checkPoint = new int[2];	// �ֺ����� ��ǥ�� �־��ִ� �迭
	static int[] turnCnt = { 1 };// �� ī��Ʈ	

	public static void printGame(int[][] arr) { // �ٵ��� ���
		
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int stone(int[] point) { // ��ǥ���Է¹޾Ƶ��� �����ִ¸޼ҵ�
		return stone(point, board, turnCnt);
	}

	public static int stone(int[] point, int[][] board, int[] cnt) { // ��ǥ���Է¹޾Ƶ��� �����ִ¸޼ҵ�

		
		if(isCheck(point, board)== 0 ){ //�ʱ⿡ ���� �������� ���� ���¶�� 
				if (turn(cnt) == 2){ // �� ����
					
					board[point[0]][point[1]] = 1;
				}
				else if (turn(cnt) == 1){ // �� ����
					
					board[point[0]][point[1]] = 2;
				}
				
				printGame(board);
	
				DoubleThreeAlg three = new DoubleThreeAlg(point, checkPoint, board);
				if(three.checkThree(point, checkPoint, board))	// 33üũ    33�̸� �ߵ�
				{
					board[point[0]][point[1]] = 0;
					return 3;
				}
				
				cnt[0]++; // �� ����
	
				GameWinAlg wa = new GameWinAlg(point, checkPoint, board);	
				winlose = wa.compareStone(point, checkPoint, board);
				
				if (winlose){
					System.out.println("�¸�!!");
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

	//���� ���� �������ִ��� Ȯ���ϴ� �޼ҵ�
	public static int isCheck(int[] point, int[][] board) {

		switch (board[point[0]][point[1]]) {

		case 1:
			return 1; // �浹
		case 2:
			return 2; // �鵹

		default:
			return 0;
		}
	}

	public int isCheck(int y, int x) {

		switch (board[y][x]) {

		case 1:
			return 1; // �浹
		case 2:
			return 2; // �鵹

		default:
			return 0;
		}
	}

	public static int turn(int[] turnCnt) { // ¦������ Ȧ������ ����

		return (turnCnt[0] % 2) + 1;
	}
}
