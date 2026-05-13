<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<%@include file="../../header.jsp" %>
<body>
<%@include file="../../sidebar.jsp" %>
<h2>科目管理</h2>
<a href="SubjectCreate.action">新規登録</a>
<table>
	<tr>
		<th>科目コード</th>
		<th>科目名</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach var="subject" items="${subjects}">
	<tr>
		<td>${subject.cd}</td>
		<td>${subject.name}</td>
		<td><a href="SubjectUpdate.action">変更</a></td>
		<td><a href="SubjectDelete.action">削除</a></td>
	</tr>
	</c:forEach>
</table>

</body>
<%@include file="../../footer.jsp" %>
</html>