<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie 연습</title>
</head>

<%
// 쿠키 정보를 읽어서 처리한다.

String cookieUserId = "";   // 쿠키값이 저장될 변수
String chk = "";      // 체크박스 체크용 변수

Cookie[] cookieArr = request.getCookies();   // 전체 쿠키 정보 가져오기

for(Cookie cookie : cookieArr) {
	if("USERID".equals(cookie.getName())) {   // 원하는 쿠키이름 찾기
		cookieUserId = cookie.getValue();     // 쿠키값을 구한다.
		chk = "checked";
	}
}


%>


<body>

<form action="<%=request.getContextPath()%>/cookieLoginServlet.do" method="get">
<table border="1" style="margin : auto;">
<tr>
    <td>ID : </td>
    <td><input type="text" name="userid" value="<%=cookieUserId %>" placeholder="ID를 입력하세요."></td>
</tr>

<tr>
    <td>Pass : </td>
    <td><input type="password" name="pass" placeholder="Password를 입력하세요."></td>
</tr>

<tr>
    <td colspan="2"><input type="checkbox" <%=chk %> name="chkid" value="check" >ID 기억하기</td>
</tr>

<tr>
    <td colspan="2" style="text-align : center;"><input type="submit" value="LOGIN"></td>
</tr>
</table>

</form>
</body>
</html>