<%@page import="kr.or.ddit.vo.FileInfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
   // 서블릿에서 보낸 자료 받기
   List<FileInfoVO> fileList = (List<FileInfoVO>)request.getAttribute("fileList");
%>

<body>
<h3>전체 파일 목록</h3> <br><hr><br>

<a href="<%=request.getContextPath()%>/fileUpload.do">파일 업로드 페이지로 가기</a>
<table border="1">
<thead>
   <tr>
      <th>번호</th><th>작성자</th><th>저장파일명</th><th>원래의파일명</th>
      <th>파일크기</th><th>저장날짜</th><th>비 고</th>
   </tr>
</thead>
<tbody>
<%
   if(fileList==null || fileList.size()==0){
%>
   <tr>
       <td colspan="7">파일 목록이 하나도 없습니다.</td>
   </tr>
<% 	      
   } else {
	   for(FileInfoVO fileVo : fileList){
%>
   <tr>
      <td><%=fileVo.getFile_no() %></td>
      <td><%=fileVo.getFile_writer() %></td>
      <td><%=fileVo.getSave_file_name() %></td>
      <td><%=fileVo.getOrigin_file_name() %></td>
      <td><%=fileVo.getFile_size() %></td>
      <td><%=fileVo.getFile_date() %></td>
      <td><a href="<%=request.getContextPath()%>/fileDownLoad.do?fileno=<%=fileVo.getFile_no()%>">DownLoad</a></td>
   </tr>

<% 	   
	   }
   }
%>

</tbody>

</table>

</body>
</html>