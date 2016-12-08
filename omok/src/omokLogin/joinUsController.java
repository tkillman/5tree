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
	@FXML private TextField id;
	@FXML private PasswordField pw;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//
		
		
		
		//초기화면으로 돌아가는 버튼
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
		
	}

}
