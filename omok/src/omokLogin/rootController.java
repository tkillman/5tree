package omokLogin;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class rootController implements Initializable{
	
	@FXML private Button btn1;
	@FXML private Button btn2;
	@FXML private TextField logId;
	@FXML private PasswordField logPw;
	@FXML private HBox loginPU;
	@FXML private ImageView imgMessage;
	
	Socket connSock = null;

	String [] splitMsg;
	String Nick; //닉네임 
	String WIN; //승
	String LOSE;//패 
	


	public String getNick() {
		return Nick;
	}

	public void setNick(String nick) {
		Nick = nick;
	}
	public String getWIN() {
		return WIN;
	}
	public void setWIN(String wIN) {
		WIN = wIN;
	}
	public String getLOSE() {
		return LOSE;
	}

	public void setLOSE(String lOSE) {
		LOSE = lOSE;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		
		//회원가입 버튼
		btn1.setOnAction(new EventHandler<ActionEvent>() {		
	
			@Override
			public void handle(ActionEvent event) {
				try{
					connSock.close(); //회원가입하고 취소
					
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
					//String loginIDPW = "login:"+strLogId+","+strLogPw;//왜 구분자가 여러가지야 -_-;  split메소드 사용하는게 좋음
					String loginIDPW = "login:"+strLogId+":"+strLogPw;
					byte[] bt = loginIDPW.getBytes("UTF-8");
					OutputStream sender = connSock.getOutputStream();
					sender.write(bt);		
					
					//서버에서 받는거
					byte[] recv = new byte[100]; //수신된 데이터 저장할 변수(recv)-->!!(꿀팁)나중을 대비하면 ArrayList
		            InputStream receiver = connSock.getInputStream();// 소켓에서 데이터를 가져오려면 꼭 필요함.
		            int size = receiver.read(recv);
		            String readMsg = new String (recv, 0, size, "UTF-8");
		            System.out.println("readMsg="+readMsg);//그냥 잘 받았나 출력해보는곳
		            
		            splitMsg = readMsg.split(":"); // 서버가 보내준 메시지를 콜론을 기준으로 잘라서 배열에 담는다.
		            		            
		            PopupController puc = new PopupController(); 
		            		            
		            if(splitMsg[0].equals("failID")){
		            	System.out.println("클라이언트 아이디 실패");
		        		puc.idPopup();
		            }else if(splitMsg[0].equals("failPW")){
		            	System.out.println("클라이언트 비번 실패");
		            	puc.pwPopup();
		            	System.out.println("pass fail~~");
		            }else if(splitMsg[0].equals("welcome")){
		            	System.out.println("클라이언트 성공");
		            	System.out.println(splitMsg[0]);
		            	System.out.println(splitMsg[1]);
		            	System.out.println(splitMsg[2]);
		            	//로그인창 꺼지고 게임 창 실행
		            }
									
				} catch (Exception e) {
					System.out.println("예외발생"+e);
				}
				
			}
		});	
	}
}
