package t3_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HoewonDAO2 {
	private Connection conn = null; // 컨넥션 필드 생성
	private Statement stmt = null;
	private ResultSet rs = null;
	private String sql = null;
	
	public HoewonDAO2() {
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
	public ArrayList<HoewonVO> getList() {
		ArrayList<HoewonVO> vos = new ArrayList<>();
		try {
			stmt = conn.createStatement(); // Statement: sql 명령을 실행할 수 있게 해주는 객체
			sql = "SELECT * FROM hoewon";
			rs = stmt.executeQuery(sql); // ResultSet: 가져온 값을 담아주는 객체
			
			HoewonVO vo = null;
			while(rs.next()) {
				vo = new HoewonVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
				vos.add(vo);
			}
			
			
		} catch (SQLException e) {
			System.out.println("SQL 오류: "+e.getMessage());
		} 
		return vos;
	}
	
	// 개별 검색 처리
	public HoewonVO getSearch(String name) {
		HoewonVO vo = new HoewonVO();
		
		try {
			stmt = conn.createStatement(); // Statement: sql 명령을 실행할 수 있게 해주는 객체
			sql = "SELECT * FROM hoewon WHERE name='"+name+"'";
			rs = stmt.executeQuery(sql);
			// ResultSet: 가져온 값을 담아주는 객체, executeQuery: 결과를 넘기려고 할 때 씀 | 둘 다 SELECT일때만 씀
			
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
	
	// 회원 자료 수정
	public void setUpdate(int idx, int choice, String content) {
		try {
			stmt= conn.createStatement();
			sql = "";
			if(choice == 1) {
				sql = "Update hoewon set name='"+content+"'where idx="+idx;
			}
			else if(choice == 2) {
				sql = "Update hoewon set age="+Integer.parseInt(content)+" where idx="+idx;
			}
			else if(choice == 3) {
				sql = "Update hoewon set gender='"+content+"' where idx="+idx;
			}
			else if(choice == 4) {
				sql = "Update hoewon set address='"+content+"' where idx="+idx;
			}
			stmt.executeUpdate(sql); // SELECT 가 아닐 때는 모두 executeUpdate
		} catch (SQLException e) {
			System.out.println("SQL 오류: "+e.getMessage());
		}
	}

	// 회원 삭제 처리
	public void setDelete(String name) {
		try {
			stmt= conn.createStatement();
			sql = "Delete from hoewon where name='"+name+"'";
			stmt.executeUpdate(sql); // SELECT 가 아닐 때는 모두 executeUpdate
		} catch (SQLException e) {
			System.out.println("SQL 오류: "+e.getMessage());
		}
	}
	
	// 회원 등록
	public void setInput(HoewonVO vo) {
		try {
			stmt= conn.createStatement();
			sql = "INSERT INTO hoewon VALUES (DEFAULT,'"+vo.getName()+"',"+vo.getAge()+",'"+vo.getGender()+"','"+vo.getAddress()+"')";
			stmt.executeUpdate(sql); // SELECT 가 아닐 때는 모두 executeUpdate
		} catch (SQLException e) {
			System.out.println("SQL 오류: "+e.getMessage());
		}
	}
}
