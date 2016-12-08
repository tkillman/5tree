package com.omok.game;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//게임 
public class EntryPoint extends Application{

	int [] point = new int[2];	// 마우스 클릭 좌표값 저장 배열

	StoneHandle sh = new StoneHandle();	//돌 조작 알고리즘 생성자

	//오목판과 관련된 클래스 정리
	allOmokPanClass allPan = new allOmokPanClass();

	//사람 입장 관련된 클래스 정리
	peopleEnterClass allpeople = new peopleEnterClass();

	//게임 메인 생성자
	public EntryPoint(){
		System.out.println("AppMain 생성자 콜");
	}

	//init 메소드 호출
	@Override
	public void init(){
		System.out.println("init 메소드 콜");
	}	 

	//start 호출
	@Override
	public void start(Stage stage) throws Exception{
		System.out.println("start 메소드 콜");

		short secondCount = 0;

		//닻. AnchorPane이 컨테이너 8개 중 하나임.
		AnchorPane root = new AnchorPane();
		root.setPrefWidth(830); //가로크기
		root.setPrefHeight(660); //세로크기

		//닻을 정해서 scene을 띄워줘야 한다.
		Scene scene = new Scene(root);
		stage.setScene(scene);

		//초세기 프로그레스 30초
		ProgressBar minuteCount = new ProgressBar();
		minuteCount.setPrefSize(740, 25); //프로그레스 바. 가로, 세로
		minuteCount.setLayoutX(70); //프로그레스 바 위치 x값
		minuteCount.setLayoutY(20); //프로그레스 바 위치 y값
		minuteCount.setProgress(1.0d); //프로그레스 시작 값		
		root.getChildren().add(minuteCount);

		//오목판 네모 올리기
		Canvas omokPan = new Canvas(580, 580); //오목판 그리는 캔버스
		GraphicsContext omokGC = omokPan.getGraphicsContext2D();
		allPan.drawPan(omokGC); //만든 메소드 호출
		omokPan.setLayoutX(20); //캔버스 x축 시작위치
		omokPan.setLayoutY(58); //캔버스 y축 시작위치
		root.getChildren().add(omokPan);

		//오목 돌 그리기
		Canvas omokStone = new Canvas(30, 30);
		GraphicsContext stoneGC = omokPan.getGraphicsContext2D();   
		root.getChildren().add(omokStone);

		//사람 입장 자리
		Canvas peopleEnter = new Canvas(190, 200);
		GraphicsContext peopleGC = peopleEnter.getGraphicsContext2D();
		allpeople.peopleEnter(peopleGC);
		peopleEnter.setLayoutX(620); //시작 x축
		peopleEnter.setLayoutY(58); //시작 y축
		root.getChildren().add(peopleEnter);

		//게임 채팅창 같은거....
		TextArea infoArea = new TextArea();
		infoArea.setPrefSize(190, 300); //채팅창. 가로, 세로
		infoArea.setLayoutX(620); //창 위치 x값
		infoArea.setLayoutY(275); //창 위치 y값
		root.getChildren().add(infoArea);   
		infoArea.setText("▶은영님 입장하셨습니다.");

		//게임시작 버튼을 만들어 준다.         
		Button startBtn = new Button();
		startBtn.setText("게임 시작");
		startBtn.setPrefSize(190, 50); //버튼 크기 정하기. 가로, 세로
		startBtn.setLayoutX(620); //버튼 위치 x값
		startBtn.setLayoutY(590); //버튼 위치 y값
		root.getChildren().add(startBtn);   

		//버튼 누른 이벤트 설정
		startBtn.setOnAction((event) -> {
			if (startBtn.getText().equals("게임 시작")) {
				startBtn.setText("게임 기권");
			}
		});	

		//마우스 이벤트 핸들러
		omokPan.setOnMouseClicked((event) -> {         
			System.out.println(event.getX()+ ", "+event.getY());  

			//돌 놓기
			omokStone.setLayoutX(event.getX()-15); //캔버스 x축 시작위치
			omokStone.setLayoutY(event.getY()-15); //캔버스 y축 시작위치

			//오목 판 완전 가쪽에는 돌을 못놓도록 막아주어야 한다.
			if(!(event.getX() < 8 || event.getX() > 572 || event.getY() < 8 || event.getY() > 572)){

				point[0] = allPan.nearPointArrayValue(event.getY());
				point[1] = allPan.nearPointArrayValue(event.getX());

				if(sh.isCheck(point[0], point[1]) == 0){
					if(allPan.blackBool){ //처음은 무조건 흑돌 먼저													//
						allPan.stone(allPan.nearPoint(event.getX()), allPan.nearPoint(event.getY()), stoneGC);  //
					}else if(allPan.whiteBool){																	//   돌 그리는 메소드!
						allPan.stone(allPan.nearPoint(event.getX()), allPan.nearPoint(event.getY()), stoneGC);  //
					}
					sh.stone(point); // 콘솔창에 돌에대한 데이터를 입력해주는 메소드
				}
			}                  
		});	

		//화면 제목 설정
		stage.setTitle("오목게임");
		stage.show();
	}


	@Override
	public void stop(){
		System.out.println("stop 메소드 콜");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//메인 함수에서 자바fx를 띄운다.
		launch();      
		//StoneHandle.gameStart();
	}

}