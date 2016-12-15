package omokServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//클라이언트 들어오면 연결해주는 스레드
public class ServerThreadDB extends Thread{
	String getID = null;
	String getPW = null;
	String getNICK = null;
	String getTOTAL = null;
	
	//start로 자동 실행
	public void run(){
		
		try {			
			//1. 드라이버 로딩
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver); //class명을 집어 넣어주면 자동으로 객체를 만들어주는 역할을 함.
			System.out.println("드라이버 로딩하였습니다.");
			
			//2.
			String url = "jdbc:oracle:thin:@192.168.20.28:1521:xe";
			String username = "sys as sysdba"; //오라클 로그인 아이디 (잊으면 큰일남)
			String password = "jey1234"; //오라클 로그인 비밀번호
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("DB 연결 성공!");

			//3.
			Statement st = con.createStatement();
			
			//4. st를 이용해서 쿼리 작성
			String sql = "SELECT * FROM memberJoin";
			
			//5. 실제 프로그램 실행
			ResultSet resultSQL = st.executeQuery(sql);
			System.out.println("오라클에서 꺼내왔습니다.");
			
			//6. select해온 결과를 뽑아냄			
			while(resultSQL.next()){ 				
				getID = resultSQL.getString("id");
				getPW = resultSQL.getString("pw");	
				getNICK = resultSQL.getString("nick");	
				getTOTAL = resultSQL.getString("total");
			}	
			
			
		} catch (Exception e) {
			System.out.println(e);
		}		
	}	
}
