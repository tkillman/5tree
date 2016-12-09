package omokClient;

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
			
			//게임메인
			GameEntryPoint gep = new GameEntryPoint();
			gep.gameMain(connSock); //게임 메인을 돌려야 GUI가 돌아간다.			

		} catch (Exception e) {
			System.out.println("error : " + e);
		}

	}
}