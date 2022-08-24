<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="modify" method="post" >
		제목 : <input type="text" name="title" value="${board.title }"/><br/>
		내용 : <input type="text" name="content" value="${board.content }"/><br/>
		작성자 : <input type="text" name="writer" value="${board.writer }"/><br/>
		<input type="submit" value="수정" />
	</form>
</body>
</html>