<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<body>
<%@include file="../../sidebar.jsp" %>
<div>科目</div>
<table>
	<tr>
		<th>入学年度</th>
		<th>クラス</th>
		<th>学籍番号</th>
		<th>氏名</th>
		<th>1回</th>
		<th>2回</th>
	</tr>
	
	<c:forEach var="student" items="${list}">
	<tr>
		<td>${student.entYear}</td>
		<td>${student.classNum}</td>
		<td>${student.no}</td>
		<td>${student.name}</td>
		
		<td>
			<c:choose>
				<c:when test="${empty score.point1}">-</c:when>
				<c:otherwise>${score.point1 }</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:choose>
				<c:when test="${empty score.point2}">-</c:when>
				<c:otherwise>${score.point2 }</c:otherwise>
			</c:choose>
		</td>
		
	</tr>
	
	</c:forEach>	
</table>
</body>
<%@include file="../../footer.jsp" %>
</html>