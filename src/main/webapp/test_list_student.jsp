<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<body>
<%@include file="../../sidebar.jsp" %>
<div>氏名：</div>
<table>
	<tr>
		<th>科目名</th>
		<th>科目コード</th>
		<th>回数</th>
		<th>点数</th>
	</tr>
	<c:forEach var="subject" items="${list}">
	<tr>
		<td>{subject.name}</td>
		<td>{subject.cd}</td>
		<td>{subject.no}</td>
		<td>{subject.score}</td>
	</tr>
	</c:forEach>
</table>
</body>
<%@include file="../../footer.jsp" %>
</html>