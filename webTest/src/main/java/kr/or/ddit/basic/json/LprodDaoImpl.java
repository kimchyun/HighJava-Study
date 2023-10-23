package kr.or.ddit.basic.json;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MyBatisSqlSessionFactory;
import kr.or.ddit.vo.LprodVO;

public class LprodDaoImpl {
	private static LprodDaoImpl dao;
	
	private LprodDaoImpl() { }
	
	public static LprodDaoImpl getInstance() {
		if(dao==null) dao = new LprodDaoImpl();
		return dao;
	}
	
	
	public List<LprodVO> getAlllprod() {
		List<LprodVO> lprodList = null;   // 반환값이 저장될 변수
		SqlSession session = null;
		try {
			session = MyBatisSqlSessionFactory.getSqlSession();
			
			lprodList = session.selectList("lprod.getAlllprod");
			
		} finally {
			if(session!=null) session.close();
		}
		
		return lprodList;
		
	}

}
