package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cookieCountDelServlet.do")
public class CookieCountDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		// 예) count쿠키 삭제하기
		Cookie[] cookieArr = request.getCookies();
		
		if(cookieArr != null) {
			// 쿠키 배열에서 삭제된 쿠키를 찾는다.
			for(Cookie cookie : cookieArr) {
				String name = cookie.getName();  // 쿠키 이름 구하기
				if("count".equals(name)) {     // 삭제할 쿠키이름이 맞는지 검사
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		
		out.println("<html><head><meta charset='utf-8'><title>쿠키 연습2</title></head>");
		out.println("<body>");
		
		out.println("<h2>count가 초기화 되었습니다.</h2>");
		
		
		out.println("<br><br>");
		out.println("<a href='" + request.getContextPath() + 
	             "/basic/cookie/cookieTest02.jsp'> 시작 문서로 이동하기</a>");
		
		out.println("</body></html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
