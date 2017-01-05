package game;

public class GameWinAlg {	
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
	static boolean pointCheck = true;	// Ž���Ҷ� ������ ���ϴ��� �ľ��ϴ� ����

	public GameWinAlg(int[] point, int[] checkPoint, int[][] board){
		this.point = point;		// �ٵϵ��� ���� ��ǥ�� �����ϴ� �迭
		this.checkPoint = checkPoint;	// �ֺ����� ��ǥ�� �־��ִ� �迭
		this.board = board; // �ٵ��� ����  		
	}

	public static void clonePoint(int[] point, int[] checkPoint, int[][] board) {	// ����Ʈ ��ǥ�� üũ ����Ʈ�� ����
		for (int i = 0; i < 2; i++) {
			checkPoint[i] = point[i];
		}
	}

	public static boolean compareUpDown(int[] point, int[] checkPoint, int[][] board) {
		int cnt = 1;

		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointUP(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // ���� ���� ������ üũ

		
		pointCheck = true;
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointDOWN(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // �Ʒ��� ���� ������ üũ
		
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		if (cnt == 5)
			return true;
		else{
			cnt = 1;
			return false;
		}
	}

	public static boolean pointUP(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[0] == 0)
			return false;
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += UP[i];
		}
		return true;
	}

	public static boolean pointDOWN(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[0] == 18)
			return false;
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += DOWN[i];
		}
		return true;
	}

	public static boolean compareRightLeft(int[] point, int[] checkPoint, int[][] board) {
		int cnt = 1;

		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointRight(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // ��� ���� ������ üũ

		pointCheck = true;
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointLeft(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // �·� ���� ������ üũ
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		if (cnt == 5)
			return true;
		else{
			cnt = 1;
			return false;
		}
	}

	public static boolean pointRight(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[1] == 18)
			return false;
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += RIGHT[i];
		}
		return true;
	}

	public static boolean pointLeft(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[1] == 0)
			return false;
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += LEFT[i];
		}
		return true;
	}

	public static boolean compareDiagonalR(int[] point, int[] checkPoint, int[][] board) {
		int cnt = 1;

		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointRightUp(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // ������ ���� ������ üũ

		pointCheck = true;
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointLeftDown(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // �¾Ʒ��� ���� ������ üũ
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		if (cnt == 5)
			return true;
		else{
			cnt = 1;
			return false;
		}
	}

	public static boolean pointRightUp(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[0] == 0||checkPoint[1] == 18){
			return false;
		}
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += RIGHT_UP[i];
		}
		return true;
	}

	public static boolean pointLeftDown(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[0] == 18||checkPoint[1] == 0)
			return false;
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += LEFT_DOWN[i];
		}
		return true;
	}

	public static boolean compareDiagonalL(int[] point, int[] checkPoint, int[][] board) {

		int cnt = 1;

		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointRightDown(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // ������ ���� ������ üũ

		pointCheck = true;
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointLeftUp(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // �¾Ʒ��� ���� ������ üũ
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		if (cnt == 5)
			return true;
		else{
			cnt = 1;
			return false;
		}
	}

	public static boolean pointRightDown(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[0] == 18||checkPoint[1] == 18){
			return false;
		}
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += RIGHT_DOWN[i];
		}
		return true;
	}

	public static boolean pointLeftUp(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[0] == 0||checkPoint[1] == 0)
			return false;
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += LEFT_UP[i];
		}
		return true;
	}

	public boolean compareStone(int[] point, int[] checkPoint, int[][] board) {
		if(compareUpDown(point, checkPoint, board)||compareRightLeft(point, checkPoint, board)||compareDiagonalR(point, checkPoint, board)||compareDiagonalL(point, checkPoint, board))
			return true;
		return false;
	}

}
