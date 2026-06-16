<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<link rel="stylesheet" href="../../student.css">
<div class="wrapper">

<%@include file="../../sidebar.jsp" %>

<main class="content">
<h2>学生情報変更</h2>
<form action="StudentUpdateExecute.action" method="post" class="regist-form">

	<c:if test="${not empty errors}">
    	<p class="error-msg">${errors}</p>
	</c:if>

	<div class="form-item">
		<label>入学年度</label>
		<span>${ent_year }</span>
		<input type="hidden" name="ent_year" value="${ent_year}">
	</div>

	<div class="form-item">
		<label>学生番号</label>
		<span>${no }</span>
		<input type="hidden" name="no" value="${no}">
	</div>

	<div class="form-item">
		<label>氏名</label>
		<input type="text" name="name" value="${name }" placeholder="氏名を入力してください" maxlength="30" required>
	</div>

	<div class="form-item">
		<label>クラス</label>
		<select name="class_num">
			<option value="">--------</option>
			<c:forEach var="num" items="${class_list}">
				<option value="${num}" ${class_num == num ? 'selected' : ''}>${num}</option>
			</c:forEach>
		</select>
	</div>

	<div class="checkbox-grop update-checkbox">
		<label>在学中</label>
		<input type="checkbox" name="is_attend" ${is_attend ? 'checked' : '' }>
	</div>

	<button type="submit" class="btn-update-submit" name="login">変更</button>
	
</form>

<div class="back-link"></div>
	<a href="StudentList.action">戻る</a>
</div>
</main>
</div>
</body>
<%@include file="../../footer.jsp" %>
</html>