package t7_ExS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NetpayDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	InsaVO vo = null;
	String sql = "";
	
	public NetpayDAO() {
		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "atom";
		String password = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패~~" + e.getMessage());
		}
	}
	
	// conn객체 Close
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}
	
	// pstmt객체 close
	public void pstmtClose() {
		try {
			if(pstmt != null) pstmt.close();
		} catch (Exception e) {}
	}
	
	// rs객체 close
	public void rsClose() {
		try {
			if(rs != null) rs.close();
			pstmtClose();
		} catch (Exception e) {}
	}

	// 본본테이블 입력처리
	public int setSalaryInput(InsaVO vo) {
		int res = 0;
		try {
			sql = "insert into salary values (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getJikkub());
			pstmt.setInt(2, vo.getBonbong());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 같은 직급 제외 처리
	public InsaVO getJikkubSearch(String jikkub) {
		InsaVO vo = new InsaVO();
		try {
			sql = "select * from salary where jikkub = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jikkub);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setJikkub(jikkub);
				vo.setBonbong(rs.getInt("bonbong"));
			}
			else vo = null;
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 전체 자료 검색 처리
	public ArrayList<InsaVO> getSalaryList() {
		ArrayList<InsaVO> vos = new ArrayList<>();
		
		try {
			sql = "select * from salary order by bonbong desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new InsaVO();
				vo.setJikkub(rs.getString("jikkub"));
				vo.setBonbong(rs.getInt("bonbong"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 본봉테이블자료 수정하기
	public int setSalaryUpdate(InsaVO vo) {
		int res = 0;
		try {
			sql = "update salary set bonbong=? where jikkub=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBonbong());
			pstmt.setString(2, vo.getJikkub());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 본봉테이블자료 삭제처리
	public int setSalaryDelete(String jikkub) {
		int res = 0;
		try {
			sql = "delete from salary where jikkub = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, jikkub);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
}
