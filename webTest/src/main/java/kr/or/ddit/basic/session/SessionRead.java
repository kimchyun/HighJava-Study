package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.SampleVO;

// Session정보를 읽어오는 서블릿
@WebServlet("/sessionRead.do")
public class SessionRead extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 저장된 Session정보 읽어오기
		
		// 1. Session객체를 생성하거나 현재 세션 가져오기
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'><title>세션 연습</title></head>");
		out.println("<body>");
		
		out.println("<h2>Session 데이터 확인하기</h2><br><br>");
		
		// 2. getAttribute()메서드를 이용해서 Session 데이터를 읽어온다.
		// 형식) session객체.getAttribute("key값");
		//       ==> Session에 지정한 'key값'에 해당하는 데이터가 없으면 null을 반환한다.
		
		String sessionValue = (String)session.getAttribute("testSession");
		out.println("testSession의 세션값 : " + sessionValue + "<br><br>");
		
		String userName = (String)session.getAttribute("userName");
		out.println("userName의 세션값 : " + userName + "<br><br>");
		
		int age = 0;
		if(session.getAttribute("age")!=null) {
			age = (int)session.getAttribute("age");
		}
		out.println("age의 세션값 : " + age + "<br><br>");
		
		SampleVO svo = (SampleVO)session.getAttribute("sample");
		out.println("sample의 세션값 : " + svo + "<br><br>");
		
		out.println("<hr>");
		out.println("<h3>Session관련 정보들...</h3>");
		
		// 세션ID ==> 세션을 구분하기 위한 고유한 값
		out.println("세션 ID : " + session.getId() + "<br>");
		
		// 생성시간 ==> 1970년1월1일부터 경과한 시간(밀리세컨드 단위)
		out.println("세션 생성 시간 : " + session.getCreationTime() + "<br>");
		
		// 가장 최근 세션에 접근한 시간 ==> 1970년1월1일부터 경과한 시간(밀리세컨드 단위)
		out.println("세션 최근 접근 시간 :" + session.getLastAccessedTime() + "<br>");
		
		// 세션 유효시간 ==> (초 단위)
		out.println("세션 유효 시간 : " + session.getMaxInactiveInterval() + "<br><br>");

		
		out.println("<a href='" + request.getContextPath() + 
			    "/basic/session/sessionTest.jsp'> 시작 문서로 이동하기</a>");
	
	    out.println("</body></html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
