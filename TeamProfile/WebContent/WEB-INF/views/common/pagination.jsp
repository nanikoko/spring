<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav aria-label="Navigation">
	<ul class="pagination justify-content-center m-0">
		<c:if test="${pageMaker.prev}">
		<li class="page-item">
			<a class="page-link" href="javascript:list_go(${pageMaker.startPage-1 })">
				<i class="fas fa-angle-double-left"></i>
			</a>
		</li>	
		<li class="page-item">
			<a class="page-link" href="javascript:list_go(${cri.page-1 })">
				<i class="fas fa-angle-left"></i>
			</a>						
		</li>
		</c:if> 
		<c:forEach var="i" begin="${pageMaker.startPage }" end="${pageMaker.endPage }" step="1" varStatus="status">
			<li class="page-item ${cri.page eq i? 'active':'' }">
				<a class="page-link" href="javascript:list_go(${i })">${i }</a>
			</li>
		</c:forEach>	
		<c:if test="${pageMaker.next }"> 
		<li class="page-item">
			<a class="page-link" href="javascript:list_go(${cri.page+1 })">
				<i class="fas fa-angle-right"></i>
			</a>						
		</li>
		<li class="page-item">
			<a class="page-link" href="javascript:list_go(${pageMaker.endPage+1 })">
				<i class="fas fa-angle-double-right"></i>
			</a>						
		</li>
		</c:if>
	</ul>
</nav>
			
<form id="jobForm">
	<input type="hidden" name="page" value="">
	<input type="hidden" name="perPageNum" value="">
	<input type="hidden" name="searchType" value="">
	<input type="hidden" name="keyword" value="">
</form>
