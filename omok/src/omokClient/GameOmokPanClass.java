package omokClient;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameOmokPanClass {
	//맞물리는 부분 좌표 저장
	ArrayList<GameLineCrossPoint> lineCrossArray = new ArrayList<GameLineCrossPoint>();

	//검은 돌, 흰 돌
	boolean blackBool = true;
	boolean whiteBool = false;

	//바둑판 가로선, 세로선 시작점
	final short horLine = 20;
	final short verLine = 560;
	final short lineBetweenCount = 30;
	final short PanLineCount = 19;

	//바둑판 선이 맞물리는 지점 구하기
	void lineCrossPoint(){      

		//30을 간격으로 바둑판 좌표를 저장한다.
		short plusVerLine = 0;
		short plusHorLine = 0;
		for(int a=0; a<PanLineCount; a++){      
			plusHorLine = 0;
			for(int b=0; b<PanLineCount; b++){                        
				lineCrossArray.add(new GameLineCrossPoint(horLine+plusHorLine, horLine+plusVerLine));
				plusHorLine += lineBetweenCount;
			}         
			plusVerLine += lineBetweenCount;
		}

		//리스트에 들어간 목록 빼보기
		/*for(LineCrossPoint line:lineCrossArray){
	         System.out.println(line.x+", "+line.y);
	    }*/
	}
	
	//오목판 그리는 메소드
	void drawPan(GraphicsContext gc) {   
		gc.setFill(Color.CHOCOLATE);
		gc.fillRect(0, 0, 580, 580); //가로, 세로, 가로크기, 세로크기

		//선긋기
		short Count = 0;      
		for(short a = 0; a<PanLineCount; a++){         
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(2);
			gc.strokeLine(horLine+Count, horLine, horLine+Count, verLine); //가로줄 그어주기. x1, y1, x2, y2 
			gc.strokeLine(horLine, horLine+Count, verLine, horLine+Count); //세로줄 그어주기. x1, y1, x2, y2  

			Count += lineBetweenCount;
		}      

		//오목판 줄 겹치는 부분 좌표 저장 함수 호출해주기
		lineCrossPoint();
	}

	//클릭했을때 돌을 놓는다.
	void stone(int lineX, int lineY, GraphicsContext gc){

		//검은 돌 놓기
		if(blackBool == true){
			gc.setFill(Color.BLACK);
			whiteBool = true;
			blackBool = false;

		//흰 돌 놓기
		}else if(whiteBool == true){
			gc.setFill(Color.WHITE);  
			blackBool = true;
			whiteBool = false;
		}
		gc.fillOval(lineX-15, lineY-15, 30, 30); //마우스 클릭한 부분의 중간에 돌을 놓기위해 -15해주고, 돌 크기는 30정도
	}

	//보류 : 가까운 점 구해서 돌 놓기
	/*int[][] arr = new int[PanLineCount][PanLineCount];
	int yy = 0;
	int xx = 0;
	void nearbyPoint(){
		for(LineCrossPoint line:lineCrossArray){
			yy = ((line.y)-20)/30;
			xx = ((line.x)-20)/30; 

			System.out.println(yy+", "+xx);
		}   
	}*/

	//가까운 축 돌 놓는 식
	final int nearPoint(double xy){
		int pointXY = (int)(Math.round((xy-20)/30)*30+20);
		
		System.out.println("xy좌표 : " + pointXY);
		return pointXY;
	}
	
	final int nearPointArrayValue(double xy){
		int pointXY = (int)(Math.round((xy-20)/30));
		return pointXY;
	}
}
