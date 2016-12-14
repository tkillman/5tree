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
	ServerThreadDB db;	
	
	ServerThreadUser(Socket cliSock, ArrayList<ServerClientInfo> clientInfo, ServerThreadDB db){
		this.cliSock = cliSock;
		this.clientInfo = clientInfo;
		this.db = db;
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
					loginCheck(readMsg);
					System.out.println("로그인 체크");
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
	
	//로그인 체크
	public void loginCheck(String readMsg){		
		readMsg = readMsg.substring(6, readMsg.length());
		String [] logCheck = readMsg.split(",");
		String loginResult = null;
		try {			
			//로그인 확인 조건문
			if(!(logCheck[0].equals(db.getID))){	
				System.out.println("존재하지 않는 ID");
				loginResult = "failID:";
				sendLoginResult(loginResult);
			}else if(!(logCheck[1].equals(db.getPW))){
				System.out.println("pw 틀림");
				loginResult = "failPW:";
				sendLoginResult(loginResult);
			}else{
				System.out.println("로그인 성공");
				loginResult = "welcome:";
				sendLoginResult(loginResult);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//확인 결과 send
	public void sendLoginResult(String loginResult) {	
		try {
			byte[] bt = loginResult.getBytes("UTF-8");
			OutputStream sender = cliSock.getOutputStream();
			sender.write(bt);
			System.out.println("로그인 다시 보내쏘");

		} catch (IOException e) {
			System.out.println("error : " + e);
		}

	}
}