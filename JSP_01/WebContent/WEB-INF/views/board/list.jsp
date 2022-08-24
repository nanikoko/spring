<%@page import="java.util.Map"%>
<%@page import="com.jsp.command.PageMaker"%>
<%@page import="com.jsp.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Map<String, Object> dataMap = (Map<String, Object>)request.getAttribute("dataMap");
	List<Board> boardList = (List<Board>)dataMap.get("boardList");
	PageMaker pageMaker = (PageMaker)dataMap.get("pageMaker");
%>    
    
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Simple Tables</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&amp;display=fallback">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css?v=3.2.0">
<script defer referrerpolicy="origin" src="/cdn-cgi/zaraz/s.js?z=JTdCJTIyZXhlY3V0ZWQlMjIlM0ElNUIlNUQlMkMlMjJ0JTIyJTNBJTIyQWRtaW5MVEUlMjAzJTIwJTdDJTIwU2ltcGxlJTIwVGFibGVzJTIyJTJDJTIydyUyMiUzQTE5MjAlMkMlMjJoJTIyJTNBMTA4MCUyQyUyMmolMjIlM0E3MTklMkMlMjJlJTIyJTNBMTIzNyUyQyUyMmwlMjIlM0ElMjJodHRwcyUzQSUyRiUyRmFkbWlubHRlLmlvJTJGdGhlbWVzJTJGdjMlMkZwYWdlcyUyRnRhYmxlcyUyRnNpbXBsZS5odG1sJTIyJTJDJTIyciUyMiUzQSUyMmh0dHBzJTNBJTJGJTJGYWRtaW5sdGUuaW8lMkZ0aGVtZXMlMkZ2MyUyRiUyMiUyQyUyMmslMjIlM0EyNCUyQyUyMm4lMjIlM0ElMjJVVEYtOCUyMiUyQyUyMm8lMjIlM0EtNTQwJTJDJTIycSUyMiUzQSU1QiU1RCU3RA==">
</script>
<script nonce="bd3a8df3-ca73-4d4d-8cf9-9ebbf0391dd2">(function(w,d){!function(a,e,t,r){a.zarazData=a.zarazData||{},a.zarazData.executed=[],a.zaraz={deferred:[]},a.zaraz.q=[],a.zaraz._f=function(e){return function(){var t=Array.prototype.slice.call(arguments);a.zaraz.q.push({m:e,a:t})}};for(const e of["track","set","ecommerce","debug"])a.zaraz[e]=a.zaraz._f(e);a.addEventListener("DOMContentLoaded",(()=>{var t=e.getElementsByTagName(r)[0],z=e.createElement(r),n=e.getElementsByTagName("title")[0];for(n&&(a.zarazData.t=e.getElementsByTagName("title")[0].text),a.zarazData.w=a.screen.width,a.zarazData.h=a.screen.height,a.zarazData.j=a.innerHeight,a.zarazData.e=a.innerWidth,a.zarazData.l=a.location.href,a.zarazData.r=e.referrer,a.zarazData.k=a.screen.colorDepth,a.zarazData.n=e.characterSet,a.zarazData.o=(new Date).getTimezoneOffset(),a.zarazData.q=[];a.zaraz.q.length;){const e=a.zaraz.q.shift();a.zarazData.q.push(e)}z.defer=!0,z.referrerPolicy="origin",z.src="/cdn-cgi/zaraz/s.js?z="+btoa(encodeURIComponent(JSON.stringify(a.zarazData))),t.parentNode.insertBefore(z,t)}))}(w,d,0,"script");})(window,document);
</script>

</head>
<body>

<div class="row">
<div class="col-12">
<div class="card">
<div class="card-header">
<h1 style="text-align:center;">게시판목록</h1>
<div style="width:70%;margin:0 auto;position:relative;overflow:hidden;">
	<button type="button" onclick="location.href='regist'" class="btn btn-block btn-outline-secondary">글등록</button>
	<div id="keyword" class="card-tools" style="width:550px;">
   			<div class="input-group row">
   				<!-- search bar -->
   				<!-- sort num -->
				<select class="form-control col-md-3" name="perPageNum" 
					  id="perPageNum" onchange="">					  		  		
			  		<option value="10" >정렬개수</option>
			  		<option value="2" >2개씩</option>
			  		<option value="3">3개씩</option>
			  		<option value="5" >5개씩</option>
			  	</select>
					  	
			  	<!-- search bar -->
			 	<select class="form-control col-md-3" name="searchType" id="searchType">
			 		<option value=""  >검색구분</option>
					<option value="i" >아이디</option>
					<option value="n" >이 름</option>
					<option value="p" >전화번호</option>
					<option value="e" >이메일</option>				 									
				</select>
				<!-- keyword -->
 					 	<input  class="form-control" type="text" name="keyword" 
								placeholder="검색어를 입력하세요." value=""/>
				<span class="input-group-append">
					<button class="btn btn-primary" type="button" 
							id="searchBtn" data-card-widget="search" onclick="">
						<i class="fa fa-fw fa-search"></i>
					</button>
				</span>
			<!-- end : search bar -->		
 			</div>
 	</div> 
</div>
</div>


<div class="card-body table-responsive p-0">
 <table class="table table-hover text-nowrap">
<thead>
<tr style="text-align:center;">
<th>번호</th>
<th>제목</th>
<th>작성자</th>
<th>날짜</th>
<th>조회수</th>
</tr>
</thead>
<%
	//List<Board> boardList = (List<Board>)request.getAttribute("boardList");
	
	if (boardList!=null) {
		for(Board board : boardList){
			pageContext.setAttribute("board",board);
%>
<tbody>
<tr style="text-align:center;cursor:pointer;" onclick="location.href='detail?bno=${board.bno }'">
<td>${board.bno }</td>
<td>${board.title }</td>
<td>${board.writer }</td>
<td>${board.regDate }</td>
<td>${board.viewCnt }</td>
</tr>
<%		
		}	
	}else{
%>
<tr>
	<td colspan="5">해당내용이 없습니다.</td>
</tr>
<%		
	}
%>
</tbody>
</table>
</div>

    		<div class="card-footer">
    			<!-- pagination -->
    			<nav aria-label="Navigation">
					<ul class="pagination justify-content-center m-0">
						<li class="page-item">
							<a class="page-link" href="">
								<i class="fas fa-angle-double-left"></i>
							</a>
						<li class="page-item">
							<a class="page-link" href="">
								<i class="fas fa-angle-left"></i>
							</a>						
						</li>
						<%
							int startPage = pageMaker.getStartPage();
							int endPage = pageMaker.getEndPage();
							int pageNum = pageMaker.getCri().getPage();
							
							for(int i=startPage; i<endPage+1; i++){
							%>
						<li class="page-item <%=(i==pageNum)? "active":"" %>">
							<a class="page-link" href="javascript:list_go(<%=i %>)"><%=i %></a>
						</li>							
							<%	
							}
						%>
						<li class="page-item">
							<a class="page-link" href="">
								<i class="fas fa-angle-right"></i>
							</a>						
						</li>
						<li class="page-item">
							<a class="page-link" href="">
								<i class="fas fa-angle-double-right"></i>
							</a>						
						</li>
					</ul>
				</nav>
    		</div>

</div>

</div>
</div>

<form id="jobForm">
  	<input type="hidden" name="page" value="">
  	<input type="hidden" name="perPageNum" value="">
  	<input type="hidden" name="searchType" value="">
  	<input type="hidden" name="keyword" value="">
</form>

<script>
  	function list_go(page,url){
  		//alert(page);
		if(!url) url="list";
		
		var jobForm=$('#jobForm');
		jobForm.find("[name='page']").val(page);
		jobForm.find("[name='perPageNum']").val($('select[name="perPageNum"]').val());
		
		jobForm.attr({
			action:url,
			method:'get'
		}).submit();
  	}
</script>

<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js?v=3.2.0"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/demo.js"></script>

</body>
</html>