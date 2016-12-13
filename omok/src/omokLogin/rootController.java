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
					
		
		//로그인 버튼 -> 서버로 넘겨줘야 한다.
		btn2.setOnAction(new EventHandler<ActionEvent>() {	
			//서버 접속 소켓 생성
			Socket connSock = null;
			
			@Override
			public void handle(ActionEvent event) {					
				
				try {
					//서버에 접속할 때 사용할 소켓 생성
					connSock = new Socket(); 
					System.out.println("클라이언트 소켓을 생성하였습니다.");

					InetSocketAddress connAddr = new InetSocketAddress("127.0.0.1", 1818);
					connSock.connect(connAddr); // 위에서 결정한 주소로 연결 
					System.out.println("서버에 접속하였습니다.");
					
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
				
				
				//db연동 파일 호출하기
				/*DBLoginCheck dbLogin = new DBLoginCheck();
				int loginRe = dbLogin.DBlogin(strLogId, strLogPw);
				
				//팝업
				Popup popup = new Popup();
				
				if(loginRe == 0){
					System.out.println("아이디를 다시 입력해주세요.");
					
					try{
						Parent loginPU = FXMLLoader.load(getClass().getResource("loginPopUp.fxml"));
						ImageView imageView = (ImageView) loginPU.lookup("#imgMessage");
						imageView.setImage(new Image(getClass().getResource("image/exclamation_mark.png").toString()));
						
						Label lblMessage = (Label)loginPU.lookup("#imgMessage");
						lblMessage.setText("아이디를 다시 입력하세요");
						
						popup.getContent().add(loginPU);
						popup.setAutoHide(true);
						
						
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}else if(loginRe == 1){
					System.out.println("비밀번호를 다시 입력해주세요.");
				}else if(loginRe == 2){
					System.out.println("로그인 성공하였습니다아아악!!!!!!!! 드디어억!!!!!!!");
					((Stage)btn2.getScene().getWindow()).close();
				}*/		
				
			}
		});	
		
	}
}
