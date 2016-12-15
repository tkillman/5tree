package omokServer;

import java.net.Socket;

//array list
class ServerClientInfo{
	
	Socket ss;
	int peopleCount;
	
	ServerClientInfo(Socket ss, int peopleCount){
		this.ss = ss;
		this.peopleCount = peopleCount;
	}
		
}