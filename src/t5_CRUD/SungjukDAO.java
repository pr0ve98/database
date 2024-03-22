package t5_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SungjukDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	SungjukVO vo = null;
	String sql = "";
	
	public SungjukDAO() {
		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "atom", password = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패"+e.getMessage());
		} catch (SQLException e) {
			System.out.println("DB 연결 실패"+e.getMessage());
		}
	}
	
	// connClose()
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}
	
	// pstmt Close()
	public void pstmtClose() {
		try {
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {}
	}
	
	// rsClose()
	public void rsClose() {
		try {
			if(rs != null) rs.close();
			pstmtClose();
		} catch (SQLException e) {}
	}

	// 성적 자료 입력처리
	public int setSungjukInput(SungjukVO vo) {
		int res = 0;
		try {
			// PreparedStatement 는 무조건 sql 먼저
			sql = "INSERT INTO sungjuk VALUES (DEFAULT,?,?,?,?)"; // 문자열이나 숫자 둘 다 그냥 ?만 쓰면 됨
			pstmt = conn.prepareStatement(sql); // PreparedStatement 는 생성할 때 sql 을 괄호 안에 넣으면서 생성
			pstmt.setString(1, vo.getName()); // 첫번째 물음표의 타입 설정
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 성명 조회(개별검색/동명이인 검색)
	public SungjukVO getSungjukSearch(String name) {
		SungjukVO vo = new SungjukVO();
		try {
			sql = "SELECT * FROM sungjuk WHERE name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMat(rs.getInt("mat"));
			}
			else vo = null;
			
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}
	
	// 전체 자료 검색
	public ArrayList<SungjukVO> getSungjukList() {
		ArrayList<SungjukVO> vos = new ArrayList<SungjukVO>();
		
		try {
			sql = "SELECT * FROM sungjuk ORDER BY name";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new SungjukVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMat(rs.getInt("mat"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 자료 수정
	public int setSungjukUpdate(SungjukVO vo) {
		int res = 0;
		try {
			sql = "UPDATE sungjuk SET name=?, kor=?, eng=?, mat=? WHERE idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			pstmt.setInt(5, vo.getIdx());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	public int setSungjukDelete(SungjukVO vo) {
		int res = 0;
		try {
			sql = "DELETE FROM sungjuk WHERE name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
}
