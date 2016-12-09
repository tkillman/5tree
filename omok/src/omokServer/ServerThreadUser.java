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
import java.util.ArrayList;

//메시지 읽고 쓰고 하는 스레드
class ServerThreadUser extends Thread{	
	Socket cliSock;	
	ArrayList<Socket> clientInfo;
	
	ServerThreadUser(Socket cliSock, ArrayList<Socket> clientInfo){
		this.cliSock = cliSock;
		this.clientInfo = clientInfo;
	}
	
	@Override
	public void run(){
		try {
			InetAddress cliAddr = cliSock.getInetAddress();
			System.out.println(cliAddr.getHostAddress() + "접속");

			//어떤 클라이언트 들어왔는지 확인해야함.
			for(Socket ci:clientInfo){
				System.out.println(ci);
			}

			//받기
			InputStream receiver = cliSock.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(receiver));

			String readMsg = null;         
			while(true){ 
				readMsg = br.readLine();
				send(cliAddr, readMsg); //메소드 콜
			} 
		} catch (Exception e) {
			System.out.println("error : " + e);
		}
	}
	
	//메시지 뿌리기
	public void send(InetAddress cliAddr, String readMsg) {
		try {
			for(Socket socket:clientInfo){
				
				OutputStream sender = socket.getOutputStream();// client 소켓으로부터
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(sender));

				pw.println(cliAddr.getHostAddress() + " : " + readMsg); // 클라이언트에게
				pw.flush(); // 플러쉬~
			}			
		} catch (IOException e) {
			System.out.println("error : " + e);
		}

	}		
}