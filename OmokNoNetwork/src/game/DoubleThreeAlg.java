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

	int[][] board; // 바둑판 생성 	
	int[] point;		// 바둑돌을 놓는 좌표를 저장하는 배열
	int[] checkPoint;	// 주변돌의 좌표를 넣어주는 배열
	static int[] endBoard = {0};
	static int[] threeCnt = {0};
//	static boolean pointCheck = true;	// 탐색할때 방향이 변하는지 파악하는 변수

	public DoubleThreeAlg(int[] point, int[] checkPoint, int[][] board){
		this.point = point;		// 바둑돌을 놓는 좌표를 저장하는 배열
		this.checkPoint = checkPoint;	// 주변돌의 좌표를 넣어주는 배열
		this.board = board; // 바둑판 생성  		
	}

	public static void clonePoint(int[] point, int[] checkPoint) {	// 포인트 좌표를 체크 포인트에 복사
		for(int i = 0 ; i < 2 ; i++)
			checkPoint[i] = point[i];
	}

	public static void checkThreeUpDown(int[] point, int[] checkPoint, int[][] board) {
		int sCnt = 1;
		int eCnt = 0;

		boolean pointCheck = true;
		
		clonePoint(point, checkPoint);// 체크포인트 복사
		while(pointCheck){

			pointCheck = checkThreeUp(point, checkPoint);
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("up 같은돌: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("up 빈칸: "+eCnt+" ");
				}
				else 
					break;
			}
			if(sCnt>4 || eCnt >2)
				pointCheck = false;
		} // 위로 같은 돌인지 체크

		pointCheck = true;

		if(eCnt>2)
			eCnt = 1;
		else if(eCnt!=0)
			endBoard[0] = 0;

		clonePoint(point, checkPoint);// 체크포인트 복사

		while(pointCheck){

			pointCheck = checkThreeDown(point, checkPoint);
			
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("down 같은돌: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("down 빈칸: "+eCnt+" ");
				}
				else 
					break;
			}
			if(sCnt > 4 || eCnt >2)
				pointCheck = false;
		} // 아래로 같은 돌인지 체크
		//		clonePoint(point, checkPoint);// 체크포인트 복사
		System.out.println("엔드보드" + endBoard[0]);

		if (sCnt == 3&&endBoard[0]==0){
			threeCnt[0]++;
			System.out.println("find");
		}
		endBoard[0] = 0;

	}

	public static boolean checkThreeUp(int[] point, int[] checkPoint) {

		if(checkPoint[0] == 0){
			endBoard[0] = 1;
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
		
		clonePoint(point, checkPoint);// 체크포인트 복사
		while(pointCheck){

			pointCheck = checkThreeRight(point, checkPoint);
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("up 같은돌: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("up 빈칸: "+eCnt+" ");
				}
				else 
					break;
			}
			if(sCnt>4 || eCnt >2)
				pointCheck = false;
		} // 좌우로 같은 돌인지 체크

		pointCheck = true;

		if(eCnt>2)
			eCnt = 1;
		else if(eCnt!=0)
			endBoard[0] = 0;

		clonePoint(point, checkPoint);// 체크포인트 복사

		while(pointCheck){

			pointCheck = checkThreeLeft(point, checkPoint);
			
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("down 같은돌: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("down 빈칸: "+eCnt+" ");
				}
				else 
					break;
			}
			if(sCnt > 4 || eCnt >2)
				pointCheck = false;
		} // 좌우로 같은 돌인지 체크
		System.out.println("엔드보드" + endBoard[0]);

		if (sCnt == 3&&endBoard[0]==0){
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
		
		clonePoint(point, checkPoint);// 체크포인트 복사
		while(pointCheck){

			pointCheck = checkThreeRightDown(point, checkPoint);
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("up 같은돌: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("up 빈칸: "+eCnt+" ");
				}
				else 
					break;
			}
			if(sCnt>4 || eCnt >2)
				pointCheck = false;
		} // 좌우로 같은 돌인지 체크

		pointCheck = true;

		if(eCnt>2)
			eCnt = 1;
		else if(eCnt!=0)
			endBoard[0] = 0;

		clonePoint(point, checkPoint);// 체크포인트 복사

		while(pointCheck){

			pointCheck = checkThreeLeftUp(point, checkPoint);
			
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("down 같은돌: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("down 빈칸: "+eCnt+" ");
				}
				else 
					break;
			}
			if(sCnt > 4 || eCnt >2)
				pointCheck = false;
		} // 좌우로 같은 돌인지 체크
		System.out.println("엔드보드" + endBoard[0]);

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
		
		clonePoint(point, checkPoint);// 체크포인트 복사
		while(pointCheck){

			pointCheck = checkThreeRightUp(point, checkPoint);
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("up 같은돌: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("up 빈칸: "+eCnt+" ");
				}
				else 
					break;
			}
			if(sCnt>4 || eCnt >2)
				pointCheck = false;
		} // 위로 같은 돌인지 체크

		pointCheck = true;

		if(eCnt>2)
			eCnt = 1;
		else if(eCnt!=0)
			endBoard[0] = 0;

		clonePoint(point, checkPoint);// 체크포인트 복사

		while(pointCheck){

			pointCheck = checkThreeLeftDown(point, checkPoint);
			
			if(pointCheck){
				if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]]) {
					sCnt++;
					System.out.print("down 같은돌: "+sCnt+" ");
				}
				else if(board[checkPoint[0]][checkPoint[1]] == 0){
					eCnt++;
					System.out.print("down 빈칸: "+eCnt+" ");
				}
				else 
					break;
			}
			if(sCnt > 4 || eCnt >2)
				pointCheck = false;
		} // 아래로 같은 돌인지 체크
		//		clonePoint(point, checkPoint);// 체크포인트 복사
		System.out.println("엔드보드" + endBoard[0]);

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
	
		if( threeCnt[0] >= 2){				//33일때
			System.out.println("33!!!");
			threeCnt[0] = 0;
			return true;
		}
		else{
			threeCnt[0] = 0;
			return false;			//33아닐때!
		}
		
	}

}
