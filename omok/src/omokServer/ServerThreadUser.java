package omokServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//¸Ş½ÃÁö ÀĞ°í ¾²°í ÇÏ´Â ½º·¹µå
class ServerThreadUser extends Thread{	
	Socket cliSock;	
	ArrayList<ServerClientInfo> clientInfo;
	ServerThreadDB db;	
	
	ServerThreadUser(Socket cliSock, ArrayList<ServerClientInfo> clientInfo, ServerThreadDB db){
		this.cliSock = cliSock;
		this.clientInfo = clientInfo;
		this.db = db;
	}
	
	@Override
	public void run(){
		try {
			InetAddress cliAddr = cliSock.getInetAddress();
			System.out.println(cliAddr.getHostAddress() + "Á¢¼Ó");

			//¾î¶² Å¬¶óÀÌ¾ğÆ® µé¾î¿Ô´ÂÁö È®ÀÎÇØ¾ßÇÔ.
			for(ServerClientInfo ci:clientInfo){
				System.out.println(ci);
			}				
			
			//Å¬¶óÀÌ¾ğÆ®¿¡¼­ º¸³½ ±Û ¹Ş±â
			while(true){
				InputStream receiver = cliSock.getInputStream();
				byte []bt = new byte[256];
				int size = receiver.read(bt);
				String readMsg = new String (bt, 0, size, "UTF-8");		
				System.out.println("¹Ş¾Æ½î");
				if(readMsg.startsWith("chat:")||readMsg.startsWith("point:")){ //chatÀ¸·Î ½ÃÀÛÇÏ°Å³ª point:½ÃÀÛÇÏ¸é  
					send(cliAddr, readMsg); //Á¢¼ÓµÈ Å¬¶óÀÌ¾ğÆ®¿¡°Ô ¸Ş½ÃÁö ±×´ë·Î Àü´Ş...//ÀÌ ¸Ş¼¼Áö¸¦ º¸³½ Å¬¶óÀÌ¾ğÆ®°¡ ¾Æ´Ï¶ó....´ëÀû»ó´ë Å¬¶óÀÌ¾ğÆ®¿¡°Ô Àü¼ÛÇÏµµ·Ï ¸¸µé¾î¶ó...Áö±İÀº º¸³½³ğÇÑÅ× ´Ù½Ã º¸³»ÁÖ´Âµí...
				}else if(readMsg.startsWith("login:")){//loginÀ¸·Î ½ÃÀÛÇÏ¸é
					loginCheck(readMsg); //·Î±×ÀÎÀ» È®ÀÎÇÏ´Â ¸Ş¼ÒµåloginCheck¸¦ È£­„, ÀÎÀÚ´Â ¹æ±İ ¹ŞÀº ¸Ş½ÃÁö¸¦ ³Ö¾îÁØ´Ù.
					System.out.println("·Î±×ÀÎ Ã¼Å©");
				}
			}		
			
		} catch (Exception e) {
			System.out.println("error : " + e);
		}
	}
	
	//¸Ş½ÃÁö »Ñ¸®±â
	public void send(InetAddress cliAddr, String readMsg) {
		try {
			
			for (int a = 0; a < clientInfo.size(); a++) {
				ServerClientInfo cliSocket = clientInfo.get(a);				
				byte []br = readMsg.getBytes("UTF-8");
				OutputStream os = cliSocket.ss.getOutputStream();
				os.write(br);
				System.out.println("´Ù½Ã º¸³»½î");
			}	
			
		} catch (IOException e) {
			System.out.println("error : " + e);
		}

	}	
	
	//·Î±×ÀÎ Ã¼Å©
	public void loginCheck(String readMsg){		
		//adMsg = readMsg.substring(6, readMsg.length());//ÀÌµûÀ§·Î ¸¸µéÁö¸¶!! ¼ö½ÅµÈ ¸Ş½ÃÁö¿¡ login: <--¿ä·¸°Ô 6±ÛÀÚ°¡ ³ª¿À°í µÚ¿¡ ¾ÆÀÌµğ
		String [] logCheck = readMsg.split(":");
		
		String loginResult = null;
		try {			
			//·Î±×ÀÎ È®ÀÎ Á¶°Ç¹®
			if(!(logCheck[1].equals(db.getID))){	
				System.out.println("Á¸ÀçÇÏÁö ¾Ê´Â ID");
				loginResult = "failID:";
				sendLoginResult(loginResult);
			}else if(!(logCheck[2].equals(db.getPW))){
				System.out.println("pw Æ²¸²");
				loginResult = "failPW:";
				sendLoginResult(loginResult);
			}else{
				System.out.println("·Î±×ÀÎ ¼º°ø");
				System.out.println("total="+db.getTOTAL);
				loginResult = "welcome" + ":" + db.getNICK + ":" + db.getTOTAL; //´Ğ³×ÀÓ,ÀüÀû °¡Á®¿Í¼­ welcome Å°¿öµå¿Í °áÇÕ
				sendLoginResult(loginResult);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//È®ÀÎ °á°ú send
	public void sendLoginResult(String loginResult) {	
		try {
			byte[] bt = loginResult.getBytes("UTF-8");
			OutputStream sender = cliSock.getOutputStream();
			sender.write(bt);
			System.out.println("·Î±×ÀÎ ´Ù½Ã º¸³»½î");

		} catch (IOException e) {
			System.out.println("error : " + e);
		}

	}
}