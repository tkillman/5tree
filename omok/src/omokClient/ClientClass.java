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
		//게임메인
		GameEntryPoint gep = new GameEntryPoint();
		gep.gameMain(); //게임 메인을 돌려야 GUI가 돌아간다.	
		
	}
}