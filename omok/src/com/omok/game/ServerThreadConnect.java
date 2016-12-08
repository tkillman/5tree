package com.omok.game;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//클라이언트 들어오면 연결해주는 스레드
public class ServerThreadConnect extends Thread{
	//소켓 저장하는 list
	ArrayList<Socket> clientInfo = new ArrayList<Socket>();
	ServerSocket servSock = null;

	ServerThreadConnect(ServerSocket servSock){
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
				ServerThreadUser user = new ServerThreadUser(cliSock, clientInfo);
				user.start();
			}				
		} catch (Exception e) {
			System.out.println("error : " + e);
		}		
	}	
}
