<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<!-- 成績一覧 -->
<c:if test="${not empty list}">
<table>
	<tr>
		<th>科目名</th>
		<th>科目コード</th>
		<th>回数</th>
		<th>点数</th>
	</tr>
</c:if>
	<!-- 成績表示 -->
	<c:forEach var="subject" items="${list}">
	<tr>
		<td>${subject.subjectName}</td>
		<td>${subject.subjectCd}</td>
		<td>${subject.num}</td>
		<td>${subject.point}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>