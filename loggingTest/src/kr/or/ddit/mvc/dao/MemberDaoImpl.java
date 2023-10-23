package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.basic.util.DBUtil;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	private static final Logger logger = Logger.getLogger(MemberDaoImpl.class);
	
	// 1번
	private static MemberDaoImpl dao;
	
	// 2번
	private MemberDaoImpl() { }
	
	// 3번
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		return dao;
	}
	

	@Override
	public int insertMember(MemberVO memVo) {
		logger.trace("insert 작업 시작...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;       // 반환값이 저장될 변수 선언
		
		try {
			conn = DBUtil.getConnection();
			logger.info("Connection객체 생성 완료~~~");
		    
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
					+ "values(?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			logger.info("PreparedStatement 객체 생성 성공~~~");
			
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_pass());
			pstmt.setString(3, memVo.getMem_name());
			pstmt.setString(4, memVo.getMem_tel());
			pstmt.setString(5, memVo.getMem_addr());
			logger.debug("실행 SQL : " + sql);
            logger.debug("사용 데이터 : [" + memVo.getMem_id() + ", " + memVo.getMem_pass()
                         + ", " + memVo.getMem_name() + ", " + memVo.getMem_tel()
                         + ", " + memVo.getMem_addr() + "]"); 
			
			cnt = pstmt.executeUpdate();
			logger.info("작업 성공 ~~~");
			
			
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.error("작업 실패!!!", e);
		} finally {
			if(pstmt != null) try { 
				pstmt.close();
				logger.info("PreparedStatement객체 반납 성공~~~");
				} catch(SQLException e) {}
			if(conn != null) try { 
				conn.close();
				logger.info("Connection객체 반납 성공~~~");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		logger.trace("delete 작업 시작...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;    // 반환값이 저장될 변수
		
		try {
			conn = DBUtil.getConnection();
			logger.info("Connection객체 생성 완료~~~");
			
			String sql = "delete from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			logger.info("PreparedStatement 객체 생성 성공~~~");
			
			pstmt.setString(1, memId);
			logger.debug("실행 SQL : " + sql);
            logger.debug("사용 데이터 : [" + memId + "]"); 
			
			cnt = pstmt.executeUpdate();
			logger.info("작업 성공 ~~~");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close();}catch(SQLException e) {}
			if(conn != null) try { conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		logger.trace("update 작업 시작...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			logger.info("Connection객체 생성 완료~~~");
			
			String sql = "update mymember set mem_pass=? , mem_name=? , "
					+ "mem_tel=? , mem_addr=? "
					+ "where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			logger.info("PreparedStatement 객체 생성 성공~~~");
			
			pstmt.setString(1, memVo.getMem_pass());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			pstmt.setString(5, memVo.getMem_id());
			logger.debug("실행 SQL : " + sql);
            logger.debug("사용 데이터 : [" + memVo.getMem_pass() + ", " + memVo.getMem_name()
                         + ", " + memVo.getMem_tel() + ", " + memVo.getMem_addr()
                         + ", " + memVo.getMem_id() + "]"); 
			
			
			cnt = pstmt.executeUpdate();
			logger.info("작업 성공 ~~~");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close();}catch(SQLException e) {}
			if(conn != null) try { conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}
	
	@Override
	public int updateMember2(Map<String, String> paraMap) {
		logger.trace("update2 작업 시작...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;    // 반환값이 저장될 변수
		
		try {
			conn = DBUtil.getConnection();
			logger.info("Connection객체 생성 완료~~~");
			
			// key값 정보 ==> 회원ID(memid), 수정할 컬럼명(field), 수정할 데이터(data)
			
			String sql = "update mymember set " + paraMap.get("field") + " = ? where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			logger.info("PreparedStatement 객체 생성 성공~~~");
			
			pstmt.setString(1, paraMap.get("data"));
			pstmt.setString(2, paraMap.get("memid"));
			logger.debug("실행 SQL : " + sql);
            logger.debug("사용 데이터 : [" + paraMap.get("data") + ", " + paraMap.get("memid") + "]"); 
			
			cnt = pstmt.executeUpdate();
			logger.info("작업 성공 ~~~");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		logger.trace("select 작업 시작...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<MemberVO> memList = null;        // 반환값이 저장될 변수
		
		
		try {
			conn = DBUtil.getConnection();
			logger.info("Connection객체 생성 완료~~~");
			
			String sql = "select * from mymember";
			
			pstmt = conn.prepareStatement(sql);
			logger.info("PreparedStatement 객체 생성 성공~~~");
			
			rs = pstmt.executeQuery();
			
			memList = new ArrayList<>();     // List객체 생성
			logger.debug("실행 SQL : " + sql);
			while(rs.next()) {
				// 한 레코드의 내용을 VO객체에 저장한다.
				MemberVO memVo = new MemberVO();
				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_pass(rs.getString("mem_pass"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_addr(rs.getString("mem_addr"));
	            logger.debug("출력 데이터 : [" + rs.getString("mem_id") + ", " + rs.getString("mem_pass")
	                                           + ", " + rs.getString("mem_name") + ", "
	                                           + rs.getString("mem_tel") + ", " + rs.getString("mem_addr") + "]"); 
				
				
				
				// 데이터가 저장된 VO객체를 List에 추가한다.
				memList.add(memVo);
			}
			
			logger.info("작업 성공 ~~~");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) try { conn.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close();}catch(SQLException e) {}
			if(rs != null) try { rs.close();}catch(SQLException e) {}
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		logger.trace("select2 작업 시작...");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;        // 반환값이 저장될 변수
		
		try {
			conn = DBUtil.getConnection();
			logger.info("Connection객체 생성 완료~~~");
			
			String sql = "select count(*) cnt from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			logger.info("PreparedStatement 객체 생성 성공~~~");
			
			pstmt.setString(1, memId);
			logger.debug("실행 SQL : " + sql);
            logger.debug("사용 데이터 : [" + memId + "]");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) try { conn.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close();}catch(SQLException e) {}
			if(rs != null) try { rs.close();}catch(SQLException e) {}
		}
	
		return count;
	}

}
