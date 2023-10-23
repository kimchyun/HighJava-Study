<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-3.7.0.min.js"></script>

<script type="text/javascript">

$(function(){
	
	// ajax 이용
	$("#lprodBtn").on("click", function(){
		$.ajax({
		    url: "<%=request.getContextPath()%>/lprodListServlet.do",
		    type : "post",
		    //data :         // 서버로 가져갈 data가 없으면 생략 가능
		    
		    success : function(lprodList){
		    	console.log("lprodList", lprodList);
		    	let htmlCode = "<table border='1'>"
		    	htmlCode += "<tr><td>LPROD_ID</td><td>LPROD_GU</td><td>LPROD_NM</td></tr>"
		    	
		    	$.each(lprodList, function(i,v){
		    		htmlCode += "<tr><td>" + v.lprod_id + "</td>";
		    		htmlCode += "<td>" + v.lprod_gu + "</td>";
		    		htmlCode += "<td>" + v.lprod_nm + "</td></tr>";
		    	})
		    		
		    	htmlCode +="</table>"
		    	
		    	$("#result").html(htmlCode);
		    	
		    },
		    error : function(xhr){
		    	alert("오류");
		    },
		    dataType : "json"
		});
		
	});
	
	// ajax를 사용하지 않기
	$("#lprodBtn2").on("click", function(){
		
		// 서블릿으로 요청을 하면 서블릿에서 DB의 자료를 가져오고
		// 가져온 자료를 view용 JSP문서로 forward방식으로 보낸다.
		// view용 JSP문서에서는 서블릿이 보낸 데이터를 받아서 화면에 출력한다.
		
		location.href = "<%=request.getContextPath()%>/lprodListServlet2.do"
	})
	
});

</script>

</head>
<body>
<form>
    <input type="button" id="lprodBtn" value="Lprod자료 가져오기(Ajax이용)">
    <input type="button" id="lprodBtn2" value="Lprod자료 가져오기(Ajax사용 안함)">
</form>
<h3>Lprod 자료 목록</h3>
<div id="result"></div>
</body>
</html>