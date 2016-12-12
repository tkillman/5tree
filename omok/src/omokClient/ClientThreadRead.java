package omokClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javafx.scene.control.TextArea;

//소켓 연결하고 메시지 읽는 스레드
public class ClientThreadRead extends Thread{
	TextArea infoArea;
	Socket connSock;

	ClientThreadRead(Socket connSock, TextArea infoArea){
		this.connSock = connSock;
		this.infoArea = infoArea;
	}

	public void run(){
		try {			
			while(true){ 				
				InputStream receiver = connSock.getInputStream();// 소켓에서 데이터를 가져오려면 꼭 필요함.
				byte []bt = new byte[256];
				int size = receiver.read(bt);
				String readMsg = new String (bt, 0, size, "UTF-8");				
				infoArea.setText(infoArea.getText()+"상대방▶ "+readMsg+"\n");					
			}          
			
		} catch (Exception e) {			
			System.out.println("error : " + e);
		}      
	}   
}
