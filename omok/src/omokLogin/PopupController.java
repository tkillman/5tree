package omokLogin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopupController{
	
	private Stage primaryStage;
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void idPopup(){ 
		try {
			Stage dialog = new Stage();
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(primaryStage);
			dialog.setTitle("오류");
			System.out.println("id popup fail~~");
			Parent parent = FXMLLoader.load(getClass().getResource("idPopUp.fxml"));
			Scene scene = new Scene(parent);
			
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("error: id popup");
		}		
	}	
	
	
	public void pwPopup(){ 
		try {
			Stage dialog = new Stage(StageStyle.UTILITY	);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(primaryStage);
			dialog.setTitle("오류");
			System.out.println("pass popup fail~~");
			Parent parent = FXMLLoader.load(getClass().getResource("pwPopUp.fxml"));
			Scene scene = new Scene(parent);
			
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("error: id popup");
		}		
	}	
	
	public void overlapIdPopup(){ 
		try {
			Stage dialog = new Stage(StageStyle.UTILITY	);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(primaryStage);
			dialog.setTitle("오류");
			
			Parent parent = FXMLLoader.load(getClass().getResource("overlapIdPopup.fxml"));
			Scene scene = new Scene(parent);
			
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}	
	
	public void joinIdPopup(){ 
		try {
			Stage dialog = new Stage(StageStyle.UTILITY	);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(primaryStage);
			dialog.setTitle("오류");
			
			Parent parent = FXMLLoader.load(getClass().getResource("joinIdPopup.fxml"));
			Scene scene = new Scene(parent);
			
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}	
	
	public void joinPwPopup(){ 
		try {
			Stage dialog = new Stage(StageStyle.UTILITY	);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(primaryStage);
			dialog.setTitle("오류");
			
			Parent parent = FXMLLoader.load(getClass().getResource("joinPwPopup.fxml"));
			Scene scene = new Scene(parent);
			
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	public void joinNicknamePopup(){ 
		try {
			Stage dialog = new Stage(StageStyle.UTILITY	);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(primaryStage);
			dialog.setTitle("오류");
			
			Parent parent = FXMLLoader.load(getClass().getResource("joinNicknamePopup.fxml"));
			Scene scene = new Scene(parent);
			
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	public void overlapNicknamePopup(){ 
		try {
			Stage dialog = new Stage(StageStyle.UTILITY	);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(primaryStage);
			dialog.setTitle("오류");
			
			Parent parent = FXMLLoader.load(getClass().getResource("overlapNicknamePopup.fxml"));
			Scene scene = new Scene(parent);
			
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
}
