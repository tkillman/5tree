package omokLogin;

import java.net.InetSocketAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		primaryStage.setTitle("Omok Game");
		Parent root = FXMLLoader.load(getClass().getResource("root.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setWidth(300);
		primaryStage.setHeight(500);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	/*public static void main(String[] args) {
		launch(args);		
	}*/
}
