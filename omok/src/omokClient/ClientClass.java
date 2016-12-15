package omokClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

//클라이언트 접속하고 메시지 보내기
public class ClientClass {
	Socket connSock = null;
	
	ClientClass(Socket connSock){
		this.connSock = connSock;
	}
	
	public void clientMain(){		
		//게임메인
		GameEntryPoint gep = new GameEntryPoint();
		gep.gameMain(connSock); //게임 메인을 돌려야 GUI가 돌아간다.	
		System.out.println(1);//ㅈㅣ워
	}
}