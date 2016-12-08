package com.omok.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClass{	
	
	public static void main(String[] args){
		try {			
			ServerSocket servSock = new ServerSocket();//서버용 소켓 생성
			System.out.println("서버 소켓을 생성하였습니다.");
			
			InetSocketAddress servAddr = new InetSocketAddress("127.0.0.1", 1818);//서비스할 IP주소와 포트번호를 결정
			servSock.bind(servAddr); //소켓에 위에서 결정한 주소(IP,Port)를 등록한다.	
			System.out.println("클라이언트 바인딩을 성공하였습니다. 연결을 기다립니다...");
			
			//연결하는 스레드
			ServerThreadConnect connect = new ServerThreadConnect(servSock);
			connect.start();		
			
		} catch (Exception e) {
			System.out.println(e+"예외 발생");
		}
	}

}
