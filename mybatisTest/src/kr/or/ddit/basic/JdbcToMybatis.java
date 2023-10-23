package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.util.MyBatisSqlSessionFactory;
import kr.or.ddit.vo.LprodVO;

public class JdbcToMybatis {

	public static void main(String[] args) {
		// Jdbc예제 중 JdbcTest05.java를 Mybatis용으로 변환하시오.
		Scanner scan = new Scanner(System.in);
		
		//-------------------------------------
		InputStream in = null;
		SqlSessionFactory sqlSessionfactory = null;
		
		/*
		 * try { in =
		 * Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml")
		 * ; sqlSessionfactory = new SqlSessionFactoryBuilder().build(in);
		 * 
		 * } catch (Exception e) { System.out.println("myBatis 초기화 실패!!!");
		 * e.printStackTrace(); } finally { if(in!=null) try { in.close(); }
		 * catch(IOException e) {}
		 * 
		 * }
		 */
		//-------------------------------------
		
		SqlSession session = null;
		try {
//			session = sqlSessionfactory.openSession();
			session = MyBatisSqlSessionFactory.getSqlSession();
			
			// Lprod_id는 현재의 Lprod_id 중에서 제일 큰 값보다 1 크게 한다.
			int maxNum = session.selectOne("jdbc.getMaxid");
			maxNum++;
			
			
			// 입력 받은 Lprod_id가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
			String gu;
			int count = 0;
			do {
				System.out.print("상품 분류 코드 (LPROD_GU) 입력 >> ");
				gu = scan.next();
				
				count = session.selectOne("jdbc.getLprodCount", gu);
				
				if(count>0) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는 이미 등록된 코드입니다.");
					System.out.println("===== 다시 입력하세요. =====");
				}
				
			}while(count>0);
			
			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			String nm = scan.next();
			
			// 입력한 데이터들을 VO객체에 저장한다.
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(maxNum);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			int cnt = session.insert("jdbc.insertLprod", lvo);
			
			if(cnt>0) {
				System.out.println("등록 성공 !!!");
				session.commit();
			} else {
				System.out.println("등록 실패 ~~~");
			}
			
		} finally {
			if(session!=null) session.close();
		}
		
		
		
	}

}
