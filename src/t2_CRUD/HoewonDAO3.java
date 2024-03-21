package t2_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HoewonDAO3 {
	private Connection conn = null; // 컨넥션 필드 생성
	
	public HoewonDAO3() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/javaclass";
			String user ="atom", password = "1234";
			conn = DriverManager.getConnection(url, user, password);
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
	
	// 전체 회원 조회 (서비스 객체에서 써야 함)
	public void getList() { // 비즈니스 로직
		try {
			Statement stmt = conn.createStatement();
			// statement 는 컨넥션 객체의 도움을 받아야 함 
			String sql = "SELECT * FROM hoewon";
			ResultSet rs = stmt.executeQuery(sql);
			// 넘겨 받은 것을 제어해야 할 때 생성하는 객체 ResultSet
			// SELECT 를 사용하면 rs 가 필요
			// 수정하고 끝나거나 삭제하고 끝나면 rs 가 필요없음
			
			System.out.println("==========================================");
			System.out.println("번호\t성명\t나이\t성별\t주소");
			System.out.println("==========================================");
			while(rs.next()) { // 자료가 있으면 while 문을 돌면서 출력해라
				System.out.print(rs.getInt("idx")+"\t"); // rs.get타입명("sql 테이블 필드명")
				System.out.print(rs.getString("name")+"\t");
				System.out.print(rs.getInt("age")+"\t");
				System.out.print(rs.getString("gender")+"\t");
				System.out.print(rs.getString("address")+"\n");
			}
			System.out.println("==========================================");
			
		} catch (SQLException e) {
			System.out.println("SQL 오류: "+e.getMessage());
		}
	}
}
