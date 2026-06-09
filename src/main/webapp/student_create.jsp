<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<link rel="stylesheet" href="../../student.css">
<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
<main class="content">

<h2>学生情報登録</h2>

<c:if test="${not empty error_message}">
	<p class="error-msg">${error_message}</p>
</c:if>

<form action="StudentCreateExecute.action" method="post" class="regist-form">

	<div class="form-item">
		<label>入学年度</label>
		<select name="ent_year">
	    <option value="">--------</option>
	    <c:forEach var="year" begin="2020" end="2035">
	    	<option value="${year}" ${ent_year == year ? 'selected' : ''}>${year}</option>
	    </c:forEach>
		</select>
	</div>

	<div class="form-item">
		<label>学生番号</label>
		<input type="text" name="no" value="${no}" placeholder="学生番号を入力してください" required>
	</div>

	<div class="form-item">
		<label>氏名</label>
		<input type="text" name="name" value="${name}" placeholder="氏名を入力してください" required>
	</div>

	<div class="form-item">
		<label>クラス</label>
		<select name="class_num" required>
	    <option value="">--------</option>
		    <c:forEach var="num" items="${class_list}">
		    	<option value="${num}" ${class_num == num ? 'selected' : ''}>${num}</option>
		    </c:forEach>
		</select>
	</div>

	<button type="submit" name="end" class="btn-submit">登録して終了</button>

</form>

<div class="back-link">
	<a href="StudentList.action">戻る</a>
</div>

</main>
</div>
</body>
<%@include file="../../footer.jsp" %>
</html>