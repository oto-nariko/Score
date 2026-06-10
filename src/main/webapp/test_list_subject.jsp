<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<!-- 科目名 -->
<div>科目：${subject.name }</div>
<!-- 成績一覧 -->
<table>
	<tr>
		<th>入学年度</th>
		<th>クラス</th>
		<th>学籍番号</th>
		<th>氏名</th>
		<th>1回</th>
		<th>2回</th>
	</tr>
	<!-- 学生情報表示 -->
	<c:forEach var="student" items="${list}">
	<tr>
		<td>${student.entYear}</td>
		<td>${student.classNum}</td>
		<td>${student.studentNo}</td>
		<td>${student.studentName}</td>
		<!-- 1回目 -->
		<td>
			<c:choose>
				<c:when test="${empty student.getPoint(1)}">-</c:when>
				<c:otherwise>${student.getPoint(1)}</c:otherwise>
			</c:choose>
		</td>
		<!-- 2回目 -->
		<td>
			<c:choose>
				<c:when test="${empty student.getPoint(2)}">-</c:when>
				<c:otherwise>${student.getPoint(2) }</c:otherwise>
			</c:choose>
		</td>
		
	</tr>
	
	</c:forEach>	
</table>
</body>
</html>