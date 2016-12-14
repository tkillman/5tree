package omokServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//메시지 읽고 쓰고 하는 스레드
class ServerThreadUser extends Thread{	
	Socket cliSock;	
	ArrayList<ServerClientInfo> clientInfo;
	
	ServerThreadUser(Socket cliSock, ArrayList<ServerClientInfo> clientInfo){
		this.cliSock = cliSock;
		this.clientInfo = clientInfo;
	}
	
	@Override
	public void run(){
		try {
			InetAddress cliAddr = cliSock.getInetAddress();
			System.out.println(cliAddr.getHostAddress() + "접속");

			//어떤 클라이언트 들어왔는지 확인해야함.
			for(ServerClientInfo ci:clientInfo){
				System.out.println(ci);
			}				
			
			//클라이언트에서 보낸 글 받기
			while(true){
				InputStream receiver = cliSock.getInputStream();
				byte []bt = new byte[256];
				int size = receiver.read(bt);
				String readMsg = new String (bt, 0, size, "UTF-8");		
				System.out.println("받아쏘");
				if(readMsg.startsWith("chat:")||readMsg.startsWith("point:")){
					send(cliAddr, readMsg); //메소드 콜     
				}else if(readMsg.startsWith("login:")){
					loginCheck(cliAddr, readMsg);
				}
			}		
			
		} catch (Exception e) {
			System.out.println("error : " + e);
		}
	}
	
	//메시지 뿌리기
	public void send(InetAddress cliAddr, String readMsg) {
		try {
			
			for (int a = 0; a < clientInfo.size(); a++) {
				ServerClientInfo cliSocket = clientInfo.get(a);				
				byte []br = readMsg.getBytes("UTF-8");
				OutputStream os = cliSocket.ss.getOutputStream();
				os.write(br);
				System.out.println("다시 보내쏘");
			}	
			
		} catch (IOException e) {
			System.out.println("error : " + e);
		}

	}	
	public void loginCheck(InetAddress cliAddr, String readMsg){
		
		readMsg = readMsg.substring(6, readMsg.length());
		String [] logCheck = readMsg.split(",");
		
		try {			
			//1. 드라이버 로딩
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver); //class명을 집어 넣어주면 자동으로 객체를 만들어주는 역할을 함.
			System.out.println("드라이버 로딩하였습니다.");

			//2.
			String url = "jdbc:oracle:thin:@192.168.20.23:1521:xe";
			String username = "sys as sysdba"; //오라클 로그인 아이디 (잊으면 큰일남)
			String password = "jey1234"; //오라클 로그인 비밀번호
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("DB 연결 성공!");

			//3.
			Statement st = con.createStatement();
			
			//4. st를 이용해서 쿼리 작성
			String sql = "SELECT * FROM memberJoin";
			
			//5. 실제 프로그램 실행
			ResultSet resultSQL = st.executeQuery(sql);
			System.out.println("오라클에서 꺼내왔습니다.");
			
			//6. select해온 결과를 뽑아냄
			String getID = null;
			String getPW = null;
			String loginResult = null;
			while(resultSQL.next()){ 				
				getID = resultSQL.getString("id");
				getPW = resultSQL.getString("pw");					
			}	
			
			//로그인 확인 조건문
			if(!(logCheck[0].equals(getID))){	
				System.out.println("존재하지 않는 ID");
				loginResult = "failID:";
			}else if(!(logCheck[1].equals(getPW))){
				System.out.println("pw 틀림");
				loginResult = "failPW:";
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void sendLoginResult(InetAddress cliAddr, String loginResult) {	// 
		try {
			byte[] bt = loginResult.getBytes("UTF-8");
			OutputStream sender = cliSock.getOutputStream();
			sender.write(bt);
			for (int a = 0; a < clientInfo.size(); a++) {
				ServerClientInfo cliSocket = clientInfo.get(a);				
				byte []br = loginResult.getBytes("UTF-8");
				OutputStream os = cliSocket.ss.getOutputStream();
				os.write(br);
				System.out.println("다시 보내쏘");
			}	
			
		} catch (IOException e) {
			System.out.println("error : " + e);
		}

	}
}