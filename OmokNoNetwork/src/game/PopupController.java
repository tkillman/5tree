package game;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopupController implements Initializable{
	@FXML private Button btn;
	
	public Stage primaryStage;
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		
		//게임 종료 버튼
		btn.setOnAction(new EventHandler<ActionEvent>() {		
			@Override
			public void handle(ActionEvent event) {
				try {
					Parent root = FXMLLoader.load(getClass().getResource("gameEndPopUp.fxml"));
					Scene scene = new Scene(root);
					Stage primaryStage = (Stage)btn.getScene().getWindow();				
					primaryStage.close();		
				} catch (Exception e) {
					// TODO: handle exception
				}
						
			}
		});		
	}
	
	public void gameEndPopup(){ 
		try {
			Stage dialog = new Stage(StageStyle.UTILITY	);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(primaryStage);
			dialog.setTitle("게임 종료");
			Parent parent = FXMLLoader.load(getClass().getResource("gameEndPopUp.fxml"));
			Scene scene = new Scene(parent);
			
			dialog.setScene(scene);
			dialog.setResizable(true);
			dialog.show();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}
	
}
