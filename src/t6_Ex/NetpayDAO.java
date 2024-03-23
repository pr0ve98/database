package t6_Ex;

import java.sql.*;
import java.util.ArrayList;

public class NetpayDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	NetpayVO vo = null;
	String sql = "";
	int res = 0;
	
	public NetpayDAO() {
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
	
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}
	
	public void pstmtClose() {
		try {
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {}
	}
	
	public void rsClose() {
		try {
			if(rs != null) {
				pstmtClose();
				rs.close();
			}
		} catch (SQLException e) {}
	}
	
	// 전체 직급별 본봉 목록
	public ArrayList<NetpayVO> getSalaryBonbongList() {
		ArrayList<NetpayVO> vos = new ArrayList<NetpayVO>();
		try {
			sql = "SELECT * FROM salary ORDER BY bonbong DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new NetpayVO();
				vo.setJikkub(rs.getString("jikkub"));
				vo.setBonbong(rs.getInt("bonbong"));
				vos.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 직급별 본봉 수정
	public int getSalaryUpdate(NetpayVO vo, String jikkub) {
		try {
			sql = "UPDATE salary SET bonbong=? where jikkub=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getBonbong());
			pstmt.setString(2, jikkub);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} catch (NullPointerException e) {
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 전체 직원 목록
	public ArrayList<NetpayVO> getInsaList() {
		ArrayList<NetpayVO> vos = new ArrayList<>();
		
		try {
			sql = "SELECT i.idx, i.sabun, i.buseo, i.name, i.jikkub, i.age, i.ipsail, i.gender, i.address, s.bonbong FROM salary s,insa i WHERE s.jikkub = i.jikkub ORDER BY name";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new NetpayVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setSabun(rs.getString("sabun"));
				vo.setBuseo(rs.getString("buseo"));
				vo.setName(rs.getString("name"));
				vo.setJikkub(rs.getString("jikkub"));
				vo.setAge(rs.getInt("age"));
				vo.setIpsail(rs.getString("ipsail"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
				vo.setBonbong(rs.getInt("bonbong"));
				vos.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}
	
	// 직원 추가
	public int setInsanInsert(NetpayVO vo) {
		try {
			sql = "INSERT INTO insa VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSabun());
			pstmt.setString(2, vo.getBuseo());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getJikkub());
			pstmt.setInt(5, vo.getAge());
			pstmt.setString(6, vo.getIpsail());
			pstmt.setString(7, vo.getGender());
			pstmt.setString(8, vo.getAddress());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 직원 개별 이름으로 검색
	public ArrayList<NetpayVO> getInsaSearch(String name) {
		ArrayList<NetpayVO> vos = new ArrayList<NetpayVO>();
		try {
			sql = "SELECT i.idx, i.sabun, i.buseo, i.name, i.jikkub, i.age, i.ipsail, i.gender, i.address, s.bonbong FROM salary s,insa i WHERE s.jikkub = i.jikkub AND name=? ORDER BY name";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new NetpayVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setSabun(rs.getString("sabun"));
				vo.setBuseo(rs.getString("buseo"));
				vo.setName(rs.getString("name"));
				vo.setJikkub(rs.getString("jikkub"));
				vo.setAge(rs.getInt("age"));
				vo.setIpsail(rs.getString("ipsail"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
				vo.setBonbong(rs.getInt("bonbong"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 동명이인 사번으로 재검색
	public NetpayVO getInsaSearchSabun(String sabun) {
		NetpayVO vo = new NetpayVO();
		try {
			sql = "SELECT i.idx, i.sabun, i.buseo, i.name, i.jikkub, i.age, i.ipsail, i.gender, i.address, s.bonbong FROM salary s,insa i WHERE s.jikkub = i.jikkub AND sabun=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sabun);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setSabun(rs.getString("sabun"));
				vo.setBuseo(rs.getString("buseo"));
				vo.setName(rs.getString("name"));
				vo.setJikkub(rs.getString("jikkub"));
				vo.setAge(rs.getInt("age"));
				vo.setIpsail(rs.getString("ipsail"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
				vo.setBonbong(rs.getInt("bonbong"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	// 직원 수정
	public int setInsaUpdate(NetpayVO vo) {
		try {
			sql = "UPDATE insa SET buseo=?, name=?, jikkub=?, age=?, ipsail=?, gender=?, address=?  WHERE sabun=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBuseo());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getJikkub());
			pstmt.setInt(4, vo.getAge());
			pstmt.setString(5, vo.getIpsail());
			pstmt.setString(6, vo.getGender());
			pstmt.setString(7, vo.getAddress());
			pstmt.setString(8, vo.getSabun());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	// 직원 삭제
	public int insaDelete(NetpayVO vo) {
		try {
			sql = "DELETE FROM insa WHERE sabun=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSabun());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류"+e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}




	
}
