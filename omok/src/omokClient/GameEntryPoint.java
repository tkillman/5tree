package omokClient;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//게임 
public class GameEntryPoint extends Application {
	Socket connSock = null;

	// 프로그레스바
	Task copyWorker;
	double progress = 1.0;

	final String chatStart = "chat:";
	final String pointStart = "point:";
	int[] point = new int[2]; // 마우스 클릭 좌표값 저장 배열
	GameStoneHandle sh = new GameStoneHandle(); // 돌 조작 알고리즘 생성자

	// 오목판과 관련된 클래스 정리
	GameOmokPanClass allPan = new GameOmokPanClass();

	// 사람 입장 관련된 클래스 정리
	GamepeopleEnterClass allpeople = new GamepeopleEnterClass();

	// 게임 메인 생성자
	public GameEntryPoint() {
		System.out.println("GameEntryPoint 생성자 콜");
	}

	// init 메소드 호출
	@Override
	public void init() {
		System.out.println("init 메소드 콜");
	}

	// start 호출
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("start 메소드 콜");

		// 닻. AnchorPane이 컨테이너 8개 중 하나임.
		AnchorPane root = new AnchorPane();
		root.setPrefWidth(830); // 가로크기
		root.setPrefHeight(660); // 세로크기

		// 닻을 정해서 scene을 띄워줘야 한다.
		Scene scene = new Scene(root);
		stage.setScene(scene);

		// 라벨
		final Label label = new Label();
		label.setPrefSize(100, 25);
		label.setLayoutX(20);
		label.setLayoutY(20);
		label.setText("제한시간30초");

		// 초세기 프로그레스 30초
		final ProgressBar pb = new ProgressBar(progress);
		pb.setPrefSize(700, 25); // 프로그레스 바. 가로, 세로
		pb.setLayoutX(95); // 프로그레스 바 위치 x값
		pb.setLayoutY(20); // 프로그레스 바 위치 y값

		root.getChildren().addAll(pb, label);

		// 오목판 네모 올리기
		Canvas omokPan = new Canvas(580, 580); // 오목판 그리는 캔버스
		GraphicsContext omokGC = omokPan.getGraphicsContext2D();
		allPan.drawPan(omokGC); // 만든 메소드 호출
		omokPan.setLayoutX(20); // 캔버스 x축 시작위치
		omokPan.setLayoutY(58); // 캔버스 y축 시작위치
		root.getChildren().add(omokPan);

		// 오목 돌 그리기
		Canvas omokStone = new Canvas(30, 30);
		GraphicsContext stoneGC = omokPan.getGraphicsContext2D();
		root.getChildren().add(omokStone);

		// 사람 입장 자리1
		Canvas peopleEnter1 = new Canvas(190, 100);
		GraphicsContext peopleGC1 = peopleEnter1.getGraphicsContext2D();
		allpeople.peopleEnter1(peopleGC1);
		peopleEnter1.setLayoutX(620); // 시작 x축
		peopleEnter1.setLayoutY(58); // 시작 y축
		root.getChildren().add(peopleEnter1);

		// 사람 입장 자리2
		Canvas peopleEnter2 = new Canvas(190, 100);
		GraphicsContext peopleGC2 = peopleEnter2.getGraphicsContext2D();
		allpeople.peopleEnter2(peopleGC2);
		peopleEnter2.setLayoutX(620); // 시작 x축
		peopleEnter2.setLayoutY(162); // 시작 y축
		root.getChildren().add(peopleEnter2);

		// 게임 채팅창 같은거....
		TextArea infoArea = new TextArea();
		infoArea.setPrefSize(190, 270); // 채팅창. 가로, 세로
		infoArea.setLayoutX(620); // 창 위치 x값
		infoArea.setLayoutY(275); // 창 위치 y값
		root.getChildren().add(infoArea);

		// 게임 채팅 쓰는 칸....
		TextField chatInput = new TextField();
		chatInput.setPrefSize(190, 30);
		chatInput.setLayoutX(620);
		chatInput.setLayoutY(550);
		root.getChildren().add(chatInput);

		// 게임시작 버튼을 만들어 준다.
		final Button startBtn = new Button("Start");
		startBtn.setText("게임 시작");
		startBtn.setPrefSize(80, 50); // 버튼 크기 정하기. 가로, 세로
		startBtn.setLayoutX(620); // 버튼 위치 x값
		startBtn.setLayoutY(590); // 버튼 위치 y값
		root.getChildren().add(startBtn);


		//기권 버튼 
		final Button cancleBtn = new Button("calcle");
		cancleBtn.setText("기권");
		cancleBtn.setPrefSize(80, 50);
		cancleBtn.setLayoutX(730); // 버튼 위치 x값
		cancleBtn.setLayoutY(590); // 버튼 위치 y값
		root.getChildren().add(cancleBtn);


		// 시작 버튼 누르면 프로그레스바 줄어듦. 제한시간 30초.
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				startBtn.setDisable(true);
				pb.setProgress(progress); //기본설정
				cancleBtn.setDisable(false);
				copyWorker = createWorker();
				pb.progressProperty().unbind();
				pb.progressProperty().bind(copyWorker.progressProperty());
				copyWorker.messageProperty().addListener(new ChangeListener<String>() {
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						System.out.println(newValue);
					}
				});
				new Thread(copyWorker).start();
			}
		});


		//기권 버튼 누르면 프로그레스바 멈춤.
		cancleBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startBtn.setDisable(false);
				cancleBtn.setDisable(true);
				copyWorker.cancel(true);
				pb.progressProperty().unbind();
				pb.setProgress(progress); //프로그레스 기본세팅 1.0
				System.out.println("cancelled.");
			}
		});


		// 마우스 이벤트 핸들러
		omokPan.setOnMouseClicked((event) -> {
			System.out.println(event.getX() + ", " + event.getY());

			// 돌 놓기
			omokStone.setLayoutX(event.getX() - 15); // 캔버스 x축 시작위치
			omokStone.setLayoutY(event.getY() - 15); // 캔버스 y축 시작위치

			// 오목 판 완전 가쪽에는 돌을 못놓도록 막아주어야 한다.
			if (!(event.getX() < 8 || event.getX() > 572 || event.getY() < 8 || event.getY() > 572)) {

				point[0] = allPan.nearPointArrayValue(event.getY());
				point[1] = allPan.nearPointArrayValue(event.getX());

				if (sh.isCheck(point[0], point[1]) == 0) {
					try {
						String xyPoint = "point:" + String.valueOf(point[0]) + "," + String.valueOf(point[1]);
						byte[] bt = xyPoint.getBytes("UTF-8");
						OutputStream sender = connSock.getOutputStream();
						sender.write(bt);

					} catch (Exception e) {
						System.out.println(e);
					}

//					if (allPan.blackBool) { // 처음은 무조건 흑돌 먼저 //
//						allPan.stone(allPan.nearPoint(event.getX()), allPan.nearPoint(event.getY()), stoneGC); //
//					} else if (allPan.whiteBool) { // 돌 그리는 메소드!
//						allPan.stone(allPan.nearPoint(event.getX()), allPan.nearPoint(event.getY()), stoneGC); //
//					}
//					sh.stone(point); // 콘솔창에 돌에대한 데이터를 입력해주는 메소드
				}
			}
		});

		// --------------------------------------------
		// 서버 접속 소켓 생성
		try {
			//클라이언트 채팅 읽는 스레드 콜
			ClientThreadRead read = new ClientThreadRead(connSock, infoArea, allPan,  stoneGC, sh);
			read.start();

		} catch (Exception e) {
			System.out.println(e);
		}	

		//채팅창 엔터치면~
		chatInput.setOnKeyPressed((event) -> {
			//서버에 보내는 것.
			if(event.getCode()==KeyCode.ENTER){
				try {				
					byte []bt = chatStart.concat(chatInput.getText()).getBytes("UTF-8");
					OutputStream sender = connSock.getOutputStream();
					sender.write(bt);
					System.out.println("엔터 계속 치고있따.");

				} catch (Exception e) {
					System.out.println(e);
				}

				chatInput.clear();				
			}
		});


		// 화면 제목 설정
		stage.setTitle("오목게임");
		stage.show();
	}

	//프로그레스바 진행 시키는 Task 
	public Task createWorker() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				for (int i = 1; i <= 30; i++) {
					Thread.sleep(1000);
					updateMessage("제한시간 30초로부터 :" + (30-i) +"초 남음" );
					updateProgress(30-i, 30);
				}
				return true;
			}
		};
	}
	

	@Override
	public void stop() {
		System.out.println("stop 메소드 콜");
	}

	public void gameMain(Socket connSock) {
		this.connSock = connSock;
		
		// 메인 함수에서 자바fx를 띄운다.
		System.out.println("GUI를 돌립니다.");
		launch();

		// StoneHandle.gameStart();
	}

}