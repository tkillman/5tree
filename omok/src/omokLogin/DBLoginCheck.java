package omokLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBLoginCheck {

	public static void DBlogin(String id, String pw) {
		// TODO Auto-generated method stub

		try {			
			//1. 드라이버 로딩
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver); //class명을 집어 넣어주면 자동으로 객체를 만들어주는 역할을 함.
			System.out.println("드라이버 로딩하였습니다.");

			//2.
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String username = "sys as sysdba"; //오라클 로그인 아이디 (잊으면 큰일남)
			String password = "jey1234"; //오라클 로그인 비밀번호
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("DB 연결 성공!");

			//3.
			Statement st = con.createStatement();
			
			//4. st를 이용해서 쿼리 작성
			String sqlID = "SELECT * FROM memberJoin WHERE id = '"+id+"'";
			String sqlPW = "SELECT * FROM memberJoin WHERE pw = '"+pw+"'";
			
			if(id.equals(sqlID)){
				System.out.println("아이디 맞습니다.");
			}
			
			if(pw.equals(sqlPW)){
				System.out.println("비밀번호 맞습니다.");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}			
		
		
	}

}
