<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
    // JSP문서에서 세션객체는 'session'이라는 이름으로 이미 저장되어 있다.
    
    // 로그인에 성공했을 때 저장한 세션 데이터 가져오기
    MemberVO memVo = (MemberVO)session.getAttribute("loginMember");   
%>

<%
if(memVo == null){    // 로그인이 안 되었을 때
	
%>

<form action="<%=request.getContextPath()%>/sessionLoginDB.do" method="post">
<table border="1" style="margin : auto;">
<tr>
    <td>ID : </td>
    <td><input type="text" name="userid" placeholder="ID를 입력하세요."></td>
</tr>

<tr>
    <td>Pass : </td>
    <td><input type="password" name="pass" placeholder="Password를 입력하세요."></td>
</tr>

<tr>
    <td colspan="2" style="text-align : center;"><input type="submit" value="LOGIN"></td>
</tr>
</table>

</form>

<%
} else {  // 로그인 성공했을 때
%>
     <h3><%=memVo.getMem_name() %>님 반갑습니다.</h3>
     <a href="<%=request.getContextPath() %>/sessionLogoutDB.do">로그아웃</a>

<% 	
}
%>


</body>
</html>