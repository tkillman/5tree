package com.omok.game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

//클라이언트 접속하고 메시지 보내기
public class ClientClass {

	public static void main(String[] args){
		try {
			//서버에 접속할 때 사용할 소켓 생성
			Socket connSock = new Socket(); 
			System.out.println("클라이언트 소켓을 생성하였습니다.");

			InetSocketAddress connAddr = new InetSocketAddress("127.0.0.1", 1818);
			connSock.connect(connAddr); // 위에서 결정한 주소로 연결 
			System.out.println("서버에 접속하였습니다.");            

			//메시지 보내기
			OutputStream sender = connSock.getOutputStream(); //소켓으로 데이터를 보내려면 꼭 필요함.
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(sender));

			//입력받아 버퍼드스트림으로 읽음
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

			//보낼 메시지
			String sendMsg = null;
			
			//while문 돌려서 계속 쓸 수 있게 해준다.
			while(true){
				ClientThreadRead read = new ClientThreadRead(connSock);
				read.start();

				//run이 돌면서 쓰레드가 실행된다.
				sendMsg = keyboard.readLine(); 
				pw.println(sendMsg);
				pw.flush();

				if(sendMsg.equals("esc")){
					break;
				}          
			}

		} catch (Exception e) {
			System.out.println("error : " + e);
		}

	}
}