<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<!-- 学生情報表示 -->
	<c:if test="${not empty student}">
	<p>氏名：${student.name}（${student.no}）</p>
	</c:if>
	
<!-- 成績なし -->
<c:if test="${not empty studentResultError}">
	<p>${studentResultError}</p>
</c:if>
	
<!-- 成績一覧 -->
<c:if test="${not empty list}">
<table>
	<tr>
		<th>科目名</th>
		<th>科目コード</th>
		<th>回数</th>
		<th>点数</th>
	</tr>

	<!-- 成績表示 -->
	<c:forEach var="subject" items="${list}">
		<c:if test="${not empty subject.point}">
			<tr>
				<td>${subject.subjectName}</td>
				<td>${subject.subjectCd}</td>
				<td>${subject.num}</td>
				<td>${subject.point}</td>
			</tr>
		</c:if>
	</c:forEach>
</table>
</c:if>
</body>
</html>