<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<body>
<%@include file="../../sidebar.jsp" %>

<h2>学生管理</h2>



<form action="StudentList.action" method="get">
	<label>入学年度</label>
	<select name="f1">
		<option value="0">--------</option>
		<c:forEach var="year" begin="2020" end="2030">
			<option value="${year}" ${f1 == year ? 'selected' : ''}>${year}</option>
		</c:forEach>
	</select>
	
	<label>クラス</label>
	<select name="f2">
		<option value="">--------</option>
		<c:forEach var="num" items="${class_list}">
			<option value="${num}" ${f2 == num ? 'selected' : ''}>${num}</option>
		</c:forEach>
	</select>
	
	<input type="checkbox" name="f3" value="t" ${f3 ? 'checked' : ''}>
	<label>在学中</label>
	
	<button type="submit">絞込み</button>
</form>

<a href="student_create">新規登録</a>

<table border="1">
	<tr>
		<th>入学年度</th>
		<th>学生番号</th>
		<th>氏名</th>
		<th>クラス</th>
		<th class="text-center">在学中</th>
		<th></th>
	</tr>
	<c:forEach var="student" items="${students}">
		<tr>
			<td>${student.entYear}</td>
			<td>${student.no}</td>
			<td>${student.name}</td>
			<td>${student.classNum}</td>
			<td class="text-center">
				<c:choose>
					<c:when test="${student.attend}">o</c:when>
					<c:otherwise>x</c:otherwise>
				</c:choose>
			</td>
			<td><a herf="#">変更</a></td>
		</tr>
	</c:forEach>
</table>

<c:if test="${empty students}">
	<p>学生情報が存在しません</p>
</c:if>

</body>
<%@include file="../../footer.jsp" %>
</html>