package t3_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HoewonDAO {
	private Connection conn = null; // 컨넥션 필드 생성
	
	public HoewonDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 드라이버 찾기
			String url = "jdbc:mysql://localhost:3306/javaclass";
			String user ="atom", password = "1234";
			conn = DriverManager.getConnection(url, user, password); // Connection: 연결하는 객체
			// 필드에 url, user, password 넣어 컨넥션 객체 생성
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
		}
	}
	
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}
	
	// 전체 회원 조회
	public HoewonVO getList() {
		HoewonVO vo = new HoewonVO();
		try {
			Statement stmt = conn.createStatement(); // Statement: sql 명령을 실행할 수 있게 해주는 객체
			String sql = "SELECT * FROM hoewon";
			ResultSet rs = stmt.executeQuery(sql); // ResultSet: 가져온 값을 담아주는 객체
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
			}
			
			
		} catch (SQLException e) {
			System.out.println("SQL 오류: "+e.getMessage());
		}
		return vo;
	}
	
}
