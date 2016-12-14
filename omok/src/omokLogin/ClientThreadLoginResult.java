package omokLogin;

import java.io.InputStream;
import java.net.Socket;

//로그인 결과
public class ClientThreadLoginResult extends Thread{
	Socket connSock = null;
	
	public ClientThreadLoginResult(Socket connSock) {
		this.connSock = connSock;
	}
	
	public void run(){
	      try {         
	         while(true){             
	            InputStream receiver = connSock.getInputStream();// 소켓에서 데이터를 가져오려면 꼭 필요함.
	            byte []bt = new byte[256];
	            int size = receiver.read(bt);
	            String readMsg = new String (bt, 0, size, "UTF-8");
	            
	            if(readMsg.startsWith("failID:")){
	               //팝업 띄우기
	            	System.out.println("클라이언트 아이디 실패");
	            }else if(readMsg.startsWith("failPW:")){
	            	System.out.println("클라이언트 비번 실패");
	            }else if(readMsg.startsWith("welcome:")){
	            	System.out.println("클라이언트 성공");
	            }
	         }          
	         
	      } catch (Exception e) {         
	         System.out.println("error : " + e);
	      }      
	   }
	
}
