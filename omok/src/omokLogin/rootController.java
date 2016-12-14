package omokLogin;

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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class rootController implements Initializable{
	@FXML private Button btn1;
	@FXML private Button btn2;
	@FXML private TextField logId;
	@FXML private PasswordField logPw;
	@FXML private HBox loginPU;
	@FXML private ImageView imgMessage;
	
	Socket connSock = null;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		
		//회원가입 버튼
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
		
		
		//서버에 접속할 때 사용할 소켓 생성		
		try {
			connSock = new Socket(); 
			System.out.println("클라이언트 소켓을 생성하였습니다.");

			InetSocketAddress connAddr = new InetSocketAddress("127.0.0.1", 1818);
			connSock.connect(connAddr); // 위에서 결정한 주소로 연결 
			System.out.println("서버에 접속하였습니다.");
			
			//
			ClientThreadLoginResult loginResult = new ClientThreadLoginResult(connSock);
			loginResult.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}					
		
		//로그인 버튼 -> 서버로 넘겨줘야 한다.
		btn2.setOnAction(new EventHandler<ActionEvent>() {	
			
			@Override
			public void handle(ActionEvent event) {		
				try {
					//로그인창에서 쓴 id와 pw를 받는다.
					String strLogId = logId.getText();
					String strLogPw = logPw.getText();
					
					//받아낸 string을 login을 붙여서 logID, logPW를 넘긴다.
					String loginIDPW = "login:"+strLogId+","+strLogPw;
					byte []bt = loginIDPW.getBytes("UTF-8");
					OutputStream sender = connSock.getOutputStream();
					sender.write(bt);
										
				} catch (Exception e) {
					System.out.println("예외발생"+e);
				}
				
			}
		});	
		
	}
}
