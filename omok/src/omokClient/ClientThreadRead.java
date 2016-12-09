package omokClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

//소켓 연결하고 메시지 읽는 스레드
public class ClientThreadRead extends Thread{
	GameEntryPoint gep = null;
	Socket connSock = null;

	ClientThreadRead(Socket connSock, GameEntryPoint gep){
		this.connSock = connSock;
		this.gep = gep;
	}

	public void run(){
		try {
			//받기
			InputStream receiver = connSock.getInputStream();// 소켓에서 데이터를 가져오려면 꼭 필요함.
			BufferedReader br = new BufferedReader(new InputStreamReader(receiver));

			//메시지 읽기
			String readMsg = null;         
			while(true){ 
				readMsg = br.readLine();
				gep.infoArea.getText();	
				System.out.println("받기");
				//System.out.println(readMsg); // 버퍼값 출력
			}          
		} catch (Exception e) {
			
			System.out.println("error : " + e);
		}      
	}   
}
