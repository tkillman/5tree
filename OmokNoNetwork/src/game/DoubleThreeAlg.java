package game;

public class DoubleThreeAlg {	
	static final int[] UP = { -1, 0 };
	static final int[] RIGHT_UP = { -1, 1 };
	static final int[] RIGHT = { 0, 1 };
	static final int[] RIGHT_DOWN = { 1, 1 };
	static final int[] DOWN = { 1, 0 };
	static final int[] LEFT_DOWN = { 1, -1 };
	static final int[] LEFT = { 0, -1 };
	static final int[] LEFT_UP = { -1, -1 };

	int[][] board; // �ٵ��� ���� 	
	int[] point;		// �ٵϵ��� ���� ��ǥ�� �����ϴ� �迭
	int[] checkPoint;	// �ֺ����� ��ǥ�� �־��ִ� �迭
	static int[] endBoard = {0};
	static int[] threeCnt = {0};
//	static boolean pointCheck = true;	// Ž���Ҷ� ������ ���ϴ��� �ľ��ϴ� ����

	public DoubleThreeAlg(int[] point, int[] checkPoint, int[][] board){
		this.point = point;		// �ٵϵ��� ���� ��ǥ�� �����ϴ� �迭
		this.checkPoint = checkPoint;	// �ֺ����� ��ǥ�� �־��ִ� �迭
		this.board = board; // �ٵ��� ����  		
	}

	public static void clonePoint(int[] point, int[] checkPoint) {	// ����Ʈ ��ǥ�� üũ ����Ʈ�� ����
		for(int i = 0 ; i < 2 ; i++)
			checkPoint[i] = point[i];
	}

	public static void checkThreeUpDown(int[] point, int[] checkPoint, int[][] board) {
		int sCnt = 1;
		int eCnt = 0;

		boolean pointCheck = true;
		
		clonePoint(point, checkPoint);// üũ����Ʈ ����
		
		while(pointCheck){

			pointCheck = checkThreeUp(point, checkPoint); 
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("up ������: "+sCnt+" ");
				}
				
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("up ��ĭ: "+eCnt+" ");
				}
				else 
					break;
			}
			
			if(sCnt>4 || eCnt >2)
				pointCheck = false; 
		} // ���� ���� ������ üũ

		pointCheck = true;

		
		if(eCnt>2) 
			eCnt = 1;
		else if(eCnt!=0)
			endBoard[0] = 0;

		
		
		clonePoint(point, checkPoint);// üũ����Ʈ ����

		while(pointCheck){

			pointCheck = checkThreeDown(point, checkPoint);
			
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("down ������: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("down ��ĭ: "+eCnt+" ");
				}
				else 
					break;
			}
		
			if(sCnt > 4 || eCnt >2)
				pointCheck = false;
			
		} // �Ʒ��� ���� ������ üũ
		
		//		clonePoint(point, checkPoint);// üũ����Ʈ ����
		
		
		System.out.println("���庸��" + endBoard[0]);

		
		if (sCnt == 3&&endBoard[0]==0){
			threeCnt[0]++;
			System.out.println("find");
		
		}
		endBoard[0] = 0;

	}

	
	public static boolean checkThreeUp(int[] point, int[] checkPoint) {

		if(checkPoint[0] == 0){ 
			endBoard[0] = 1; // ���������� Ȯ���ߴ��� ǥ�����ִ� �迭
			return false;
		}
			
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += UP[i];
		}
		
		return true;
	}

	
	public static boolean checkThreeDown(int[] point, int[] checkPoint) {

		if(checkPoint[0] == 18){
			endBoard[0] = 1;
			return false;
		}
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += DOWN[i];
		}
		return true;
	}
	
	
	public static void checkThreeRightLeft(int[] point, int[] checkPoint, int[][] board) {
		int sCnt = 1;
		int eCnt = 0;

		boolean pointCheck = true;
		
		clonePoint(point, checkPoint);// üũ����Ʈ ����
		while(pointCheck){

			pointCheck = checkThreeRight(point, checkPoint);
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("right ������: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("right ��ĭ: "+eCnt+" ");
				}
				else 
					break;
			}
			
			if(sCnt>4 || eCnt >2)
				pointCheck = false;
		} // �¿�� ���� ������ üũ

		pointCheck = true;

		if(eCnt>2)
			eCnt = 1;
		else if(eCnt!=0)
			endBoard[0] = 0;

		
		clonePoint(point, checkPoint);// üũ����Ʈ ����

		while(pointCheck){

			pointCheck = checkThreeLeft(point, checkPoint);
			
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("left ������: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("left ��ĭ: "+eCnt+" ");
				}
				else 
					break;
			}
			if(sCnt > 4 || eCnt >2)
				pointCheck = false;
		} // �¿�� ���� ������ üũ
		
		System.out.println("���庸��" + endBoard[0]);

		if (sCnt == 3 && endBoard[0]==0){
			threeCnt[0]++;
			System.out.println("find");
		}
		endBoard[0] = 0;

	}

	
	public static boolean checkThreeRight(int[] point, int[] checkPoint) {

		if(checkPoint[1] == 18){
			endBoard[0] = 1;
			return false;
		}
			
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += RIGHT[i];
		}
		return true;
	}

	public static boolean checkThreeLeft(int[] point, int[] checkPoint) {

		if(checkPoint[1] == 0){
			endBoard[0] = 1;
			return false;
		}
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += LEFT[i];
		}
		return true;
	}
	
	
	public static void checkThreeDiagonalL(int[] point, int[] checkPoint, int[][] board) {
		int sCnt = 1;
		int eCnt = 0;

		boolean pointCheck = true;
		
		clonePoint(point, checkPoint);// üũ����Ʈ ����
		while(pointCheck){

			pointCheck = checkThreeRightDown(point, checkPoint);
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("rightdown ������: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("rightdown ��ĭ: "+eCnt+" ");
				}
				else 
					break;
			}
			if(sCnt>4 || eCnt >2)
				pointCheck = false;
		} // �¿�� ���� ������ üũ

		pointCheck = true;

		if(eCnt>2)
			eCnt = 1;
		else if(eCnt!=0)
			endBoard[0] = 0;

		clonePoint(point, checkPoint);// üũ����Ʈ ����

		while(pointCheck){

			pointCheck = checkThreeLeftUp(point, checkPoint);
			
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("leftup ������: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("leftup ��ĭ: "+eCnt+" ");
				}
				else 
					break;
			}
			if(sCnt > 4 || eCnt >2)
				pointCheck = false;
		} // �¿�� ���� ������ üũ
		System.out.println("���庸��" + endBoard[0]);

		if (sCnt == 3&&endBoard[0]==0){
			threeCnt[0]++;
			System.out.println("find");
		}
		endBoard[0] = 0;

	}

	public static boolean checkThreeRightDown(int[] point, int[] checkPoint) {

		if(checkPoint[0] == 18||checkPoint[1] == 18){
			endBoard[0] = 1;
			return false;
		}
			
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += RIGHT_DOWN[i];
		}
		return true;
	}

	
	public static boolean checkThreeLeftUp(int[] point, int[] checkPoint) {

		if(checkPoint[0] == 0||checkPoint[1] == 0){
			endBoard[0] = 1;
			return false;
		}
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += LEFT_UP[i];
		}
		return true;
	}
	
	public static void checkThreeDiagonalR(int[] point, int[] checkPoint, int[][] board) {
		int sCnt = 1;
		int eCnt = 0;

		boolean pointCheck = true;
		
		clonePoint(point, checkPoint);// üũ����Ʈ ����
		while(pointCheck){

			pointCheck = checkThreeRightUp(point, checkPoint);
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("rightup ������: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("rightup ��ĭ: "+eCnt+" ");
				}
				else 
					break;
			}
			if(sCnt>4 || eCnt >2)
				pointCheck = false;
		} // ���� ���� ������ üũ

		pointCheck = true;

		if(eCnt>2)
			eCnt = 1;
		else if(eCnt!=0)
			endBoard[0] = 0;

		clonePoint(point, checkPoint);// üũ����Ʈ ����

		while(pointCheck){

			pointCheck = checkThreeLeftDown(point, checkPoint);
			
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("leftDown ������: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("leftDown ��ĭ: "+eCnt+" ");
				}
				else 
					break;
			}
			if(sCnt > 4 || eCnt >2)
				pointCheck = false;
		} // �Ʒ��� ���� ������ üũ
		//		clonePoint(point, checkPoint);// üũ����Ʈ ����
		System.out.println("���庸��" + endBoard[0]);

		if (sCnt == 3&&endBoard[0]==0){
			threeCnt[0]++;
			System.out.println("find");
		}
		endBoard[0] = 0;

	}

	public static boolean checkThreeRightUp(int[] point, int[] checkPoint) {

		if(checkPoint[0] == 0||checkPoint[1] == 18){
			endBoard[0] = 1;
			return false;
		}
			
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += RIGHT_UP[i];
		}
		return true;
	}

	public static boolean checkThreeLeftDown(int[] point, int[] checkPoint) {

		if(checkPoint[0] == 18||checkPoint[1] == 0){
			endBoard[0] = 1;
			return false;
		}
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += LEFT_DOWN[i];
		}
		return true;
	}
	
	public boolean checkThree(int[] point, int[] checkPoint, int[][] board) {
		checkThreeUpDown(point, checkPoint, board);
		checkThreeRightLeft(point, checkPoint, board);
		checkThreeDiagonalL(point, checkPoint, board);
		checkThreeDiagonalR(point, checkPoint, board);
	
		if( threeCnt[0] >= 2){				//33�϶�
			System.out.println("33!!!");
			threeCnt[0] = 0;
			return true;
		}
		else{
			threeCnt[0] = 0;
			return false;			//33�ƴҶ�!
		}
		
	}

}
