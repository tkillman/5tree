package game;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//���� 
public class GameMain extends Application{

	boolean standby = false;

	// ���α׷�����
	Task copyWorker;
	double progress = 1.0;
	
	Thread copyTh;

	static int[] point = new int[2]; // ���콺 Ŭ�� ��ǥ�� ���� �迭

	// �����ǰ� ���õ� Ŭ���� ����
	GUIOmokPanClass allPan = new GUIOmokPanClass();

	// ��� ���� ���õ� Ŭ���� ����
	GUIPeopleEnterClass allpeople = new GUIPeopleEnterClass();

	// ���� ���� ������
	public GameMain() {		
		System.out.println("GUIMainClass ������ ��");
	}

	// init �޼ҵ� ȣ��
	@Override
	public void init() {
		System.out.println("init �޼ҵ� ��");
	}

	// start ȣ��

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start �޼ҵ� ��");	

		PopupController puc = new PopupController();
		puc.setPrimaryStage(primaryStage);

		GameStoneHandle gsh = new GameStoneHandle();


		// ��. AnchorPane�� �����̳� 8�� �� �ϳ���.
		AnchorPane root = new AnchorPane();
		root.setPrefWidth(830); // ����ũ��
		root.setPrefHeight(660); // ����ũ��

		// ���� ���ؼ� scene�� ������ �Ѵ�.
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		// ��
		final Label label = new Label();
		label.setPrefSize(100, 25);
		label.setLayoutX(20);
		label.setLayoutY(20);
		label.setText("10��");

		// �ʼ��� ���α׷��� 30��
		final ProgressBar pb = new ProgressBar(progress);
		pb.setPrefSize(700, 25); // ���α׷��� ��. ����, ����
		pb.setLayoutX(95); // ���α׷��� �� ��ġ x��
		pb.setLayoutY(20); // ���α׷��� �� ��ġ y��
		root.getChildren().addAll(pb, label);

		// ������ �׸� �ø���
		Canvas omokPan = new Canvas(580, 580); // ������ �׸��� ĵ����
		GraphicsContext omokGC = omokPan.getGraphicsContext2D();
		allPan.drawPan(omokGC); // ���� �޼ҵ� ȣ��
		omokPan.setLayoutX(20); // ĵ���� x�� ������ġ
		omokPan.setLayoutY(58); // ĵ���� y�� ������ġ
		root.getChildren().add(omokPan);

		// ���� �� �׸���
		Canvas omokStone = new Canvas(30, 30);
		GraphicsContext stoneGC = omokPan.getGraphicsContext2D();
		root.getChildren().add(omokStone);


		// ��� ���� �ڸ�1
		Canvas peopleEnter1 = new Canvas(190, 100);
		GraphicsContext peopleGC1 = peopleEnter1.getGraphicsContext2D();
		allpeople.peopleEnter1(peopleGC1);
		peopleEnter1.setLayoutX(620); // ���� x��
		peopleEnter1.setLayoutY(58); // ���� y��
		root.getChildren().add(peopleEnter1);

		// ��� ���� �ڸ�2
		Canvas peopleEnter2 = new Canvas(190, 100);
		GraphicsContext peopleGC2 = peopleEnter2.getGraphicsContext2D();
		allpeople.peopleEnter2(peopleGC2);
		peopleEnter2.setLayoutX(620); // ���� x��
		peopleEnter2.setLayoutY(162); // ���� y��
		root.getChildren().add(peopleEnter2);

		// ���� ä��â ������....
		TextArea infoArea = new TextArea();
		infoArea.setPrefSize(190, 270); // ä��â. ����, ����
		infoArea.setLayoutX(620); // â ��ġ x��
		infoArea.setLayoutY(275); // â ��ġ y��
		root.getChildren().add(infoArea);

		// ���� ä�� ���� ĭ....
		TextField chatInput = new TextField();
		chatInput.setPrefSize(190, 30);
		chatInput.setLayoutX(620);
		chatInput.setLayoutY(550);
		root.getChildren().add(chatInput);

		// ���ӽ��� ��ư�� ����� �ش�.
		final Button startBtn = new Button("Start");
		startBtn.setText("���ӽ���");
		startBtn.setPrefSize(90, 50); // ��ư ũ�� ���ϱ�. ����, ����
		startBtn.setLayoutX(620); // ��ư ��ġ x��
		startBtn.setLayoutY(590); // ��ư ��ġ y��
		root.getChildren().add(startBtn);

		//������ ��ư 
		final Button cancleBtn = new Button("cancle");
		cancleBtn.setText("������");
		cancleBtn.setPrefSize(90, 50);
		cancleBtn.setLayoutX(720); // ��ư ��ġ x��
		cancleBtn.setLayoutY(590); // ��ư ��ġ y��
		root.getChildren().add(cancleBtn);		

		// ���� ��ư ������ ���α׷����� �پ��. ���ѽð� 30��.
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				standby = true;
				startBtn.setDisable(true); //��ư ��Ȱ��ȭ
				pb.setProgress(progress); //�⺻����
				cancleBtn.setDisable(false); //��ǹ�ư Ȱ��ȭ
				copyWorker = createWorker();
				pb.progressProperty().unbind();
				pb.progressProperty().bind(copyWorker.progressProperty());
				copyWorker.messageProperty().addListener(new ChangeListener<String>() {
					
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						//System.out.println(newValue);
					}
				});				
				copyTh = new Thread(copyWorker);
				copyTh.start();

			}
		});

		//������ ��ư ������ ���α׷����� ����.
		cancleBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startBtn.setDisable(false);
				cancleBtn.setDisable(true);
				copyWorker.cancel(true);
				pb.progressProperty().unbind();
				pb.setProgress(progress); //���α׷��� �⺻���� 1.0
				System.out.println("cancelled.");
				primaryStage.close(); //â����
			}
		});

		// ���콺 �̺�Ʈ �ڵ鷯
		omokPan.setOnMouseClicked((event) -> {
			System.out.println(event.getX() + ", " + event.getY());

			// �� ����
			omokStone.setLayoutX(event.getX()); // ĵ���� x�� ������ġ
			omokStone.setLayoutY(event.getY()); // ĵ���� y�� ������ġ
			
			if(standby){ //���� ���� �� standby = true �� �ȴ�.
				// ���� �� ���� ���ʿ��� ���� �������� �����־�� �Ѵ�.
				if (!(event.getX() < 8 || event.getX() > 572 || event.getY() < 8 || event.getY() > 572)) {


					point[0] = allPan.nearPointArrayValue(event.getY());
					
					point[1] = allPan.nearPointArrayValue(event.getX());
					
					System.out.println(point[1]+","+point[0]);
					
					
					
						if(gsh.stone(point) == 1) 	// 1 : �¸�������
						{
							if(gsh.turn(gsh.turnCnt)==1){ //¦���� �������� �����鼭 Cnt �� 1 �����ؼ�
								infoArea.setText("�浹 �¸�!!!");
							}
							else if(gsh.turn(gsh.turnCnt)==2){
								infoArea.setText("�鵹 �¸�!!!");
							}
							puc.gameEndPopup();	
							standby = false;
							copyTh.interrupt(); //task ����
							//startBtn.setDisable(false); //��ư Ȱ��ȭ
							allPan.stone(point[1] * 30 + 20, point[0] * 30 + 20, stoneGC);// ���׸��� ��ɹ�
						}
						
						else if (gsh.stone(point) == 2)
							allPan.stone(point[1] * 30 + 20, point[0] * 30 + 20, stoneGC);// ���׸��� ��ɹ�
						
						else if (gsh.stone(point) == 3)
							System.out.println("33 �˾�   ���ߞ�������������������������");
						
						
				}
			}
		});
		

		//ä��â ����ġ��~
		chatInput.setOnKeyPressed((event) -> {
			//������ ������ ��.
			if(event.getCode()==KeyCode.ENTER){
				chatInput.clear();				
			}
		});

		// ȭ�� ���� ����
		primaryStage.setTitle("�������");
		primaryStage.show();
	}

	//���α׷����� ���� ��Ű�� Task	
	public Task createWorker() {
		return new Task() {
			
			@Override
			protected Object call() throws Exception {
				for (int i = 1; i <= 600; i++) {
					Thread.sleep(1000);
					updateMessage("���ѽð� 10�����κ��� :" + (600-i) +"�� ����" );
					updateProgress(600-i, 600);
				}
				return true;
			}
		};
	}


	@Override
	public void stop() {
		System.out.println("stop �޼ҵ� ��");
	}


	public static void main(String args[]) {
		launch();
	}

}

