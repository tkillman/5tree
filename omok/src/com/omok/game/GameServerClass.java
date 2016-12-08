package com.omok.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//--------------------------------------------------------------------------
class ThreadUser extends Thread{	
	Socket cliSock;	
	ArrayList<Socket> clientInfo;
	
	ThreadUser(Socket cliSock, ArrayList<Socket> clientInfo){
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

//--------------------------------------------------------------------------
class ThreadConnect extends Thread{	
	//소켓 저장하는 list
	ArrayList<Socket> clientInfo = new ArrayList<Socket>();
	ServerSocket servSock = null;
	
	ThreadConnect(ServerSocket servSock){
		this.servSock = servSock;
	}
	
	//start로 자동 실행
	public void run(){
		try {
			while(true){
				Socket cliSock = servSock.accept();//서버소켓으로 찾아온 고객을 고객 전용 소켓으로 연결시켜줌
				System.out.println("클라이언트가 접속하였습니다.");
				
				//들어온 클라이언트 관리하는 리스트에 저장한다.			
				clientInfo.add(cliSock);				
				
				//스레드 콜
				ThreadUser user = new ThreadUser(cliSock, clientInfo);
				user.start();
			}				
		} catch (Exception e) {
			System.out.println("error : " + e);
		}		
	}		
}


//---------------------------------------------------------------------------
public class GameServerClass{	
	
	public static void main(String[] args){
		try {			
			ServerSocket servSock = new ServerSocket();//서버용 소켓 생성
			System.out.println("서버 소켓을 생성하였습니다.");
			
			InetSocketAddress servAddr = new InetSocketAddress("127.0.0.1", 1818);//서비스할 IP주소와 포트번호를 결정
			servSock.bind(servAddr); //소켓에 위에서 결정한 주소(IP,Port)를 등록한다.	
			System.out.println("클라이언트 바인딩을 성공하였습니다. 연결을 기다립니다...");
			
			//연결하는 스레드
			ThreadConnect connect = new ThreadConnect(servSock);
			connect.start();		
			
		} catch (Exception e) {
			System.out.println(e+"예외 발생");
		}
	}

}
