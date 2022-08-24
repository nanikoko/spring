<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
	int a = 95;

	if(a>90){
		out.println("A");
	}else if(a>80){
		out.println("B");
	}else{
		out.println("C");
	}
%>

<hr>
<c:set var="a" value="95" />
<c:choose>
	<c:when test="${a gt 90 }">
		A
	</c:when>
	<c:when test="${a gt 80 }">
		B
	</c:when>
	<c:otherwise>
		C
	</c:otherwise>
</c:choose>