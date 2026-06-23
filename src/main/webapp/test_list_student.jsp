<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/test_list.css">
<!DOCTYPE html>


<!-- 学生情報表示 -->
	<c:if test="${not empty student}">
		<div class="student-info-label">
			<p>氏名：${student.name}（${student.no}）</p>
		</div>
	</c:if>
	
<!-- 成績なし -->
<c:if test="${not empty studentResultError}">
	<p class="student-error-msg">${studentResultError}</p>
</c:if>
	
<!-- 成績一覧 -->
<c:if test="${not empty list}">
<table class="student-score-table">
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
				<td>${subject.point == -1 ? '-' : subject.point}</td>
			</tr>
		</c:if>
	</c:forEach>
</table>
</c:if>
