<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원상세</h1>
		<button type="button" onclick="location.href='<%=request.getContextPath()%>/member/modify?id=${member.id }';" >수정</button>&nbsp;&nbsp;&nbsp;
		<button type="button" onclick="location.href='<%=request.getContextPath()%>/member/remove?id=${member.id }';">삭제</button>&nbsp;&nbsp;&nbsp;
		<button type="button" onclick="location.href='list';">목록</button>
	<hr/>
	<table>
		<tr>
			<td>아이디</td>
			<td>패스워드</td>
		</tr>
		<tr>
			<td>${member.id }</td>
			<td>${member.pwd }</td>
		</tr>
	</table>
</body>
</html>