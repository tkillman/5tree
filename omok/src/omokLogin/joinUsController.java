package omokLogin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class joinUsController implements Initializable{
	@FXML private Button btn1;
	@FXML private Button btn2;
	@FXML private TextField joinId;
	@FXML private PasswordField joinPw;
	@FXML private TextField joinNick;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//�����ϴ� ��ư
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {						
				//db�� id�� �н������ �г��� �Ѱ��ֱ�
				String strJoinId = joinId.getText();
				String strJoinPw = joinPw.getText();
				String strJoinNick = joinNick.getText();
				
				//db���� ���� ȣ���ϱ�
				DBMemberJoin dbJoin = new DBMemberJoin();
				dbJoin.DBJoin(strJoinId, strJoinPw, strJoinNick);	
			}
		});				
		
		//�ʱ�ȭ������ ���ư��� ��ư
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try{
					Parent root = FXMLLoader.load(getClass().getResource("root.fxml"));
					Scene scene = new Scene(root);
					Stage PrimaryStage = (Stage)btn2.getScene().getWindow();
					PrimaryStage.setScene(scene);
				
					}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
		});
		
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			
			@Override
			public void handle(ActionEvent event) {
				
				try{
					Parent root = FXMLLoader.load(getClass().getResource("root.fxml"));
					Scene scene = new Scene(root);
					Stage PrimaryStage = (Stage)btn1.getScene().getWindow();
					PrimaryStage.setScene(scene);
				
					}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
			
		});
		
	}

}