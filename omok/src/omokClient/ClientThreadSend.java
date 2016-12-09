package omokClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThreadSend {
	Socket connSock;
	GameEntryPoint gep;
	
	ClientThreadSend(Socket connSock, GameEntryPoint gep) {
		this.connSock = connSock;
		this.gep = gep;
	}
	
	void clientSend(){	
		
		try {
			//메시지 보내기
			OutputStream sender = connSock.getOutputStream(); //소켓으로 데이터를 보내려면 꼭 필요함.
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(sender));

			//입력받아 버퍼드스트림으로 읽음
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

			//보낼 메시지
			String sendMsg = null;
			
			//while문 돌려서 계속 쓸 수 있게 해준다.
			while(true){
				ClientThreadRead read = new ClientThreadRead(connSock, gep);
				read.start();

				//run이 돌면서 쓰레드가 실행된다.
				sendMsg = keyboard.readLine(); 
				gep.chatInput.setText(sendMsg);	
				System.out.println("보내기");
				
				pw.println(sendMsg);
				pw.flush();

				if(sendMsg.equals("esc")){
					break;
				}          
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}	
}
