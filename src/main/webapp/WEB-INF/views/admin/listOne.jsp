<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<H2>1대1문의</H2>

	<table border="1" width="80%">
		<tr>
			<td>번호</td>
			<td>문의제목</td>
			<td>내용</td>
			<td>작성일</td>
			<td>작성자</td>						
		</tr>
		<c:forEach var="o" items="${list }">
			<tr>
				<td>
			  <a href="/mypage/detailOne?no=${o.no}">${o.no }</a>
				</td>
				<td>
					${o.one_title} 					
				</td>				 
				<td>${o.one_content }</td>
				<td>${o.one_date }</td>
				<td>${o.member_no }</td>								
			</tr>
		</c:forEach>
	</table> 	
	
	<c:forEach var="i" begin="1" end="${totalPage }">
				<a href="listOne?pageNUM=${i }">${i }</a>&nbsp;&nbsp;
		</c:forEach>

</body>
</html>