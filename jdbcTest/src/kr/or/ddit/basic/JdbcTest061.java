package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.basic.util.DBUtil;

/*
     회원을 관리하는 프로그램을 작성하시오 ( MYMEMBER 테이블 이용 ) 
     
      아래 메뉴의 기능을 모두 구현하시오.  ( CRUD 기능 구현하기 )
      
      메뉴예시)
      ------------------------------
      1. 자료 추가                  ----> insert (C)
      2. 자료 삭제                  ----> delete (D)
      3. 자료 수정                  ----> update (U)
      4. 전체 자료 출력             ----> select (R)
      0. 작업 끝
      ------------------------------
      
      조건)
      1) '자료 추가' 메뉴에서 '회원 ID'는 중복되지 않는다. (중복되면 다시 입력 받는다.)
      2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
      3) 자료 수정에서 '회원ID'는 변경되지 않는다.
  
*/

public class JdbcTest061 {
	
	Scanner scan = new Scanner(System.in);
	
	// 시작 메서드
	public void start() {
		System.out.println("------------------------------");
		
		while(true) {
			int choice = displayMenu();
			
			switch(choice) {
			case 1 : // 자료 추가
				insert(); break;
			case 2 : // 자료 삭제
				delete(); break;
			case 3 : // 자료 수정
				update(); break;
			case 4 : // 전체 자료 출력
				select(); break;
			case 0 : // 작업 끝
				System.out.println();
				System.out.println("프로그램을 종료합니다. . .");
				return;
			   default :
				System.out.println();
				System.out.println("작업 번호를 선택했습니다. 다시 선택하세요. . .");
			}
		}
	}
	

	private int displayMenu() {
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("0. 작업 끝");
		System.out.println("------------------------------");
		System.out.print("메뉴를 선택하세요 >> ");
		return scan.nextInt();
	}
	
	
    // 1. 자료 추가
	private void insert() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			
			// id 중복확인
			String id;
			int count = 0;
			
			do {
				System.out.print("아이디를 입력하세요 >> ");
				id = scan.next();
				
				String sql = "select count(*) cnt from mymember where mem_id = ?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count>0) {
					System.out.println("입력한 아이디 " + id + "는 이미 등록된 아이디 입니다.");
					System.out.println("===== 다시 입력하세요. =====");
				}
				
			} while(count>0);
			
			System.out.print("비밀번호를 입력하세요 >> ");
			String pw = scan.next();
			
			System.out.print("이름을 입력하세요 >> ");
			String name = scan.next();
			
			System.out.print("전화번호를 입력하세요 >> ");
			String tel = scan.next();
			scan.nextLine();
			
			System.out.print("주소를 입력하세요 >> ");
			String addr = scan.nextLine();
			
			String sql2 = "insert into mymember(mem_id, mem_pass, mem_name, mem_tel, mem_addr) values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("===== 등록 완료 =====");
			} else {
				System.out.println("===== 등록 실패 =====");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close();}catch(SQLException e) {}
			if(conn != null) try { conn.close();}catch(SQLException e) {}
		}
	}
	
	
	// 2. 자료 삭제
	private void delete() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			
			System.out.print("삭제할 회원 아이디를 입력하세요 >> ");
			String id = scan.next();
			
			String sql3 = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql3);
			
			pstmt.setString(1, id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("===== 삭제 완료 =====");
			} else {
				System.out.println("===== 삭제 실패 =====");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close();}catch(SQLException e) {}
			if(conn != null) try { conn.close();}catch(SQLException e) {}
		}
		
	}
	
	
	// 3. 자료 수정
	private void update() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			
			System.out.print("아이디를 입력하세요 >> ");
			String id = scan.next();
			
			System.out.print("수정할 비밀번호를 입력하세요 >> ");
			String pw = scan.next();
			
			System.out.print("수정할 이름을 입력하세요 >> ");
			String name = scan.next();
			
			System.out.print("수정할 전화번호를 입력하세요 >> ");
			String tel = scan.next();
			scan.nextLine();
			
			System.out.print("수정할 주소를 입력하세요 >> ");
			String addr = scan.nextLine();
			
			String sql4 = "update mymember set mem_pass = ?, mem_name = ?, mem_tel = ?, mem_addr = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql4);
			
			pstmt.setString(5, id);
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("===== 수정 완료 =====");
			} else {
				System.out.println("===== 수정 실패 =====");
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();}catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close();}catch(SQLException e) {}
			if(conn != null) try { conn.close();}catch(SQLException e) {}
		}
		
	}
	
	
	// 4. 자료 전체 출력
	private void select() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql5 = "select * from mymember";
			
			stmt = conn.createStatement();
		    rs = stmt.executeQuery(sql5);
			
			while(rs.next()) {
				System.out.println("MEM_ID : " + rs.getString("mem_id"));
				System.out.println("MEM_PASS : " + rs.getString("mem_pass"));
				System.out.println("MEM_NAME : " + rs.getString("mem_name"));
				System.out.println("MEM_TEL : " + rs.getString("mem_tel"));
				System.out.println("MEM_ADDR : " + rs.getString("mem_addr"));
				System.out.println("------------------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close();}catch(SQLException e) {}
			if(stmt != null) try { stmt.close();}catch(SQLException e) {}
			if(conn != null) try { conn.close();}catch(SQLException e) {}
		}
		
	}

	public static void main(String[] args) {
		new JdbcTest061().start();

	}

}
