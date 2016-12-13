package omokServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//클라이언트 들어오면 연결해주는 스레드
public class ServerThreadConnect extends Thread{
	//소켓 저장하는 list
	ArrayList<ServerClientInfo> clientInfo = new ArrayList<ServerClientInfo>();
	ServerSocket servSock = null;

	ServerThreadConnect(ServerSocket servSock){
		this.servSock = servSock;
	}

	//start로 자동 실행
	public void run(){
		//사람 2명 들어오면 막으려고..
		int peopleCount = 0;
		
		try {
			while(true){	
				
				if(peopleCount < 2){
					Socket cliSock = servSock.accept();//서버소켓으로 찾아온 고객을 고객 전용 소켓으로 연결시켜줌
					peopleCount++;
					System.out.println("클라이언트"+peopleCount+"님이 접속하였습니다.");

					//들어온 클라이언트 관리하는 리스트에 저장한다.			
					clientInfo.add(new ServerClientInfo(cliSock, peopleCount));				

					//스레드 콜
					ServerThreadUser user = new ServerThreadUser(cliSock, clientInfo);
					user.start();
				}else{
					System.out.println("접속하지 못합니다.");
					break;
				}
				
			}				
		} catch (Exception e) {
			System.out.println("error : " + e);
		}		
	}	
}
