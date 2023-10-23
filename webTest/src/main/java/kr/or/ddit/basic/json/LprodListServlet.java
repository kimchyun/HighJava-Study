package kr.or.ddit.basic.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.vo.LprodVO;


@WebServlet("/lprodListServlet.do")
public class LprodListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
				
		LprodDaoImpl dao = LprodDaoImpl.getInstance();
		Gson gson = new Gson();
		String jsonData = null;
		
		List<LprodVO> lprodList = dao.getAlllprod();
		
		jsonData = gson.toJson(lprodList);
		System.out.println("jsonData => " + jsonData);
		
		PrintWriter out = response.getWriter();
		out.write(jsonData);
		
		response.flushBuffer();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
