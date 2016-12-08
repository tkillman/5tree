package com.omok.game;

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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class rootController implements Initializable{
	@FXML private Button btn1;
	@FXML private Button btn2;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try{
					Parent joinus = FXMLLoader.load(getClass().getResource("joinUs.fxml"));
					Scene scene = new Scene(joinus);
					Stage primaryStage = (Stage)btn1.getScene().getWindow();
					primaryStage.setScene(scene);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});		
	}
}
