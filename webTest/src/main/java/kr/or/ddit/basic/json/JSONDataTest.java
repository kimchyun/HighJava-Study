package kr.or.ddit.basic.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.vo.SampleVO;


@WebServlet("/JSONDataTest.do")
public class JSONDataTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// 응답을 JSON구조로 하겠다.
		response.setContentType("application/json; charset=utf-8");
		
		String choice = request.getParameter("choice");
		
		Gson gson = new Gson();
		
		String jsonData = null;       // JSON구조의 문자열로 변환된 데이터가 저장될 변수
		
		switch(choice) {
		    case "string" : 
		    	String str = "안녕하세요";     // 응답으로 보낼 데이터
		    	jsonData = gson.toJson(str);   // 응답 데이터를 JSON구조의 문자열로 변환
		    	break;
		    case "array" :
		    	int[] arr = {10,20,30,40,50,60};
		    	jsonData = gson.toJson(arr);
		    	break;
		    case "obj" :
		    	SampleVO smVo = new SampleVO(1, "홍길동");
		        jsonData = gson.toJson(smVo);
		    	break;
		    case "list" :
		    	ArrayList<SampleVO> samList = new ArrayList<>();
		    	samList.add(new SampleVO(100, "이순신"));
		    	samList.add(new SampleVO(200, "강감찬"));
		    	samList.add(new SampleVO(300, "이몽룡"));
		    	
		    	jsonData = gson.toJson(samList);
		    	break;
		    case "map" :
		    	HashMap<String, String> map = new HashMap<>();
		    	map.put("name", "성춘향");
		    	map.put("tel", "010-1234-5678");
		    	map.put("addr", "대전시 중구 오류동");
		    	
		    	jsonData = gson.toJson(map);
		    	break;
		    	
		}
		
		System.out.println("JSON으로 변환된 문자열 ==> " + jsonData);
		
		// JSON으로 변환된 데이터를 응답으로 보낸다.
		PrintWriter out = response.getWriter();
		out.write(jsonData);
		response.flushBuffer();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
