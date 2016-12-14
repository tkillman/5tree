package omokLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBMemberJoin {

	public static void DBJoin(String joinId, String joinPw, String joinNick) {
		// TODO Auto-generated method stub
		
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
			
			//st -> INSERT INTO memberJoin(id, pw, nick, win, draw, lose)
			//pst -> INSERT INTO memberJoin(id, pw, nick, ?, ?, ?)
			
			//4. st를 이용해서 쿼리 작성
			String sql = "INSERT INTO memberJoin(id, pw, nick) "
					+ "VALUES ('"+joinId+"', '"+joinPw+"', '"+joinNick+"')";
			//삽입. sql에서 쓰는 거 그대로 씀. 여기서 숫자를 넣으면 계속해서 oracle에 저장됨.
			//이런식으로 쓰게 되면 오라클에 1000이 저장되게 된다. UPDATE, DELETE도 가능.

			//5. 실제 프로그램 실행
			st.executeUpdate(sql);	

			if(joinId.equals(null)){
				System.out.println("아이디를 입력해주세요.");
			}
			
			if(joinPw.equals(null)){
				System.out.println("비밀번호를 입력해주세요.");
			}
			
			if(joinNick.equals(null)){
				System.out.println("닉네임을 입력해주세요.");
			}
			
			System.out.println("오라클에 저장되었습니다.");
			System.out.println("가입하였습니다.");
			
		} catch (Exception e) {
			System.out.println(e);
		}	


	}
}
