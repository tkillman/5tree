package omokServer;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClass{	

	public static void main(String[] args){
		try {			
			ServerSocket servSock = new ServerSocket();//서버용 소켓 생성
			System.out.println("서버 소켓을 생성하였습니다.");

			InetSocketAddress servAddr = new InetSocketAddress("127.0.0.1", 1818);//서비스할 IP주소와 포트번호를 결정
			servSock.bind(servAddr); //소켓에 위에서 결정한 주소(IP,Port)를 등록한다.	
			System.out.println("클라이언트 바인딩을 성공하였습니다. 연결을 기다립니다...");

			// 디비 스레드 콜		
			ServerThreadDB db = new ServerThreadDB();
			db.start();

			//소켓 저장하는 list
			ArrayList<ServerClientInfo> clientInfo = new ArrayList<ServerClientInfo>();

			//클라이언트 두명인지 세서 막기
//			int cnt = 0;
//			boolean peopleTrue = true;

			while(true){
//				if(peopleTrue){
					//서버소켓으로 찾아온 고객을 고객 전용 소켓으로 연결시켜줌	
					Socket cliSock = servSock.accept();		

//					cnt++;
					//들어온 클라이언트 관리하는 리스트에 저장한다.			
					clientInfo.add(new ServerClientInfo(cliSock));		
					System.out.println("클라이언트"+clientInfo.size()+"님이 접속하였습니다.");				

					//클라이언트 버퍼를 읽고 보내는 쓰레드!!!!!!!!
					ServerThreadUser user = new ServerThreadUser(cliSock, clientInfo, db);
					user.start();
					
//					cnt++;
//				}else if(cnt == 2){
//					peopleTrue = false;
//					cnt = 0;
//					break;
//				}

			}

		} catch (Exception e) {
			System.out.println(e+"예외 발생");
		}
	}

}
