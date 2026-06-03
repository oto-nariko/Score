<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<div class="wrapper">

<%@include file="../../sidebar.jsp" %>

<main class="content">
<h2>学生情報変更</h2>
<form action="StudentUpdateExecute.action" method="post">

	<c:if test="${not empty errors}">
    	<p>${errors}</p>
	</c:if>

	<label>入学年度</label>
	<span>${ent_year }</span>
	<input type="hidden" name="ent_year" value="${ent_year}">
	<br>

	<label>学生番号</label>
	<span>${no }</span>
	<input type="hidden" name="no" value="${no}">
	<br>

	<label>氏名</label>
	<input type="text" name="name" value="${name }" placeholder="氏名を入力してください" required><br>

	<label>クラス</label>
	<select name="class_num">
		<option value="">--------</option>
		<c:forEach var="num" items="${class_list}">
			<option value="${num}" ${class_num == num ? 'selected' : ''}>${num}</option>
		</c:forEach>
	</select>
	<br>

	<label>在学中</label>
	<input type="checkbox" name="is_attend" ${is_attend ? 'checked' : '' }><br>

	<button type="submit" name="login">変更</button>
	
</form>

<a href="StudentList.action">戻る</a>
</main>
</div>
</body>
<%@include file="../../footer.jsp" %>
</html>