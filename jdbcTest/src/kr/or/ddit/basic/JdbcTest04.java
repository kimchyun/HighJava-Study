package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc04", "java");
			
			System.out.println("===== 계좌번호 정보 추가하기 =====");
			
			System.out.print("계좌번호 >> ");
			String bankNo = scan.next();
			
			System.out.print("은행이름 >> ");
			String bankName = scan.next();
			
			System.out.print("예금주명 >> ");
			String userName = scan.next();
			
//			insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date)
//	        values ('111-2222-33-4444', '하나은행', '홍길동', sysdate);
			
//			Statement객체를 이용하여 데이터 추가하기
//			String sql = "insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) "
//					+ "values('" + bankNo + "', '" + bankName + "', '" + userName + "', sysdate)";
//			
//			System.out.println("sql ==> " + sql);
			
//			stmt = conn.createStatement();
			
			// 'select문'을 실행할 때는 executeQuery()메서드를 사용하고, 
			// 'insert문, update문, delete문'등과 같이 'select문'이 아닌 SQL문을 실행할 때는
			// executeUpdate()메서드를 사용한다.
			// executeUpdate()메서드의 반환값은 작업에 성공한 레코드 수이다.
			
//			int cnt = stmt.executeUpdate(sql);
			
			
			// -----------------------------------------------------------------------
			
			// PreparedStatement객체를 이용하여 처리하기
			
			// SQL문을 작성할 때 SQL문에 데이터가 들어갈 자리를 물음표(?)로 표시하여 작성한다.
			String sql = "insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) "
					+ "values(?, ?, ?, sysdate)";
			
			// PreparedStatement객체 생성 ==> 이 때 사용할 SQL문을 인수값으로 넘겨준다.
			pstmt = conn.prepareStatement(sql);
			
			// SQL문의 물음표(?)자리에 들어갈 데이터를 셋팅한다.
			// 형식) pstmt.set자료형이름(물음표순번, 셋팅할데이터);
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, userName);
			
			
			// 데이터 셋팅이 완료되면 SQL문을 실행한다.
			int cnt = pstmt.executeUpdate();
			
			
			System.out.println("반환값 : " + cnt);
			
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) try { stmt.close(); }catch(SQLException e) {}
			if(pstmt != null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn != null) try { conn.close(); }catch(SQLException e) {}
		}

	}

}
