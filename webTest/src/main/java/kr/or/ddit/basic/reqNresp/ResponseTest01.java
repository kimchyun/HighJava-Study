package kr.or.ddit.basic.reqNresp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseTest01.do")
public class ResponseTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*	
	     - forward방식
	       * 특정 서블릿에 대한 요청을 다른 서블릿이나 JSP로 넘기는 것을 말한다.
	         (이 때 HttpServletRequest객체와 HttpServletResponse객체를 공유한다.)
	       * 클라이언트에서 요청한 URL주소는 처음 요청할 때의 주소가 바뀌지 않으며 redirect보다 성능이 좋다.
	       * 서버 내부에서만 접근이 가능하다.
	        
	        
	       
	     - 이동되는 페이지로 값을 넘기려면 HttpServletRequest객체의 setAttribute()메서드로 데이터를 셋팅하여 보내고
	       받는 쪽에서는 getAttribute()메서드로 데이터를 읽어온다.
	       
	       보낼 때 형식) request객체.setAttribute("key값", 데이터);
	                     ==> 'key값'은 문자열로 지정하고 '데이터'는 Java의 모든 자료형을 사용할 수 있다.
	                     
	       받을 때 형식) request객체.getAttribute("key값");
	*/
		//request.getParameter("username");
		
		request.setAttribute("tel", "010-1234-5678");   // 보낼 데이터 셋팅
		
		// forward방식으로 다른 문서로 이동하기
		// Request객체의 getRequestDispatcher()메서드에 이동할 서블릿이나 JSP를 지정해 주는데
		// 전체 URI경로 중에서 ContextPath이후의 경로를 지정해 준다.
		
		// 이동할 문서의 전체 URL경로 예) http://localhost/webTest/forwardTest.do
		// 위의 경로 중 ContextPath(지금은 /webTest)이후의 경로로 지정한다.
		RequestDispatcher rd = request.getRequestDispatcher("/forwardTest.do");
		
		// forwarding 직업 수행 (이때 request객체와 response객체를 인수값으로 넣어준다.)
		rd.forward(request, response);    
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
