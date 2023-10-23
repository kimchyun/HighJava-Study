package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.basic.util.DBUtil;

/*
    LPROD테이블에 새로운 데이터 추가하기
    
    lprod_gu와 lprod_nm값은 직접 입력 받아 처리하고,
    lprod_id는 현재의 lprod_id값 중에서 제일 큰 값보다 1 크게 한다.
    
    입력 받은 lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
  
*/

public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc04", "java");
			
			conn = DBUtil.getConnection();
			
			// Lprod_id는 현재의 Lprod_id 중에서 제일 큰 값보다 1 크게 한다.
//			String sql = "select nvl(max(lprod_id),0) from lprod";
			String sql = "select nvl(max(lprod_id),0) maxid from lprod";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			int maxNum = 0;
			if(rs.next()) {    // select문을 처리한 결과가 1개의 레코드일 경우에는 while문 대신 if문을 사용해도 된다.
//				maxNum = rs.getInt(1);     // 컬럼번호
//				maxNum= rs.getInt("nvl(max(lprod_id),0)");     // 컬럼의 alias가 없을 때는 '식 내용'이 컬럼명 역할을 한다.
				maxNum = rs.getInt("maxid");        // '컬럼의 alias' 이용
			}
			
			maxNum++;         // 값 증가
			// -----------------------------------------------------------------
			
			// 입력 받은 Lprod_id가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
			String gu;           // Lprod_gu(상품분류코드)가 저장될 변수 선언
			int count = 0;       // 입력한 상품분류코드가 DB에 저장된 개수를 저장할 변수 선언
			
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >> ");
				gu = scan.next();
				
				String sql2 = "select count(*) cnt from lprod where lprod_gu = ? ";
				
				pstmt = conn.prepareStatement(sql2);
				
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count>0) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는 이미 등록된 코드입니다.");
					System.out.println("===== 다시 입력하세요. =====");
				}
				
			} while(count>0);
			
			// 상품 분류명(LPROD_NM) 입력 받아 DB에 insert하기
			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			String nm = scan.next();
			
			String sql3 = "insert into lprod(lprod_id, lprod_gu, lprod_nm) values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, maxNum);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("===== 등록 성공 =====");
			} else {
				System.out.println("===== 등록 실패 =====");
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); }catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn != null) try { conn.close(); }catch(SQLException e) {}
		}

	}

}
