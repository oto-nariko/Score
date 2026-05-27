<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
<main class="content">
<h2>学生情報登録</h2>

<c:if test="${not empty error_message}">
	<p>${error_message}</p>
</c:if>

<form action="StudentCreateExecute.action" method="post">

	<label>入学年度</label>
	<select name="ent_year">
    <option value="">--------</option>
    <c:forEach var="year" begin="2020" end="2035">
    	<option value="${year}" ${ent_year == year ? 'selected' : ''}>${year}</option>
    </c:forEach>
	</select><br>

	<label>学生番号</label>
	<input type="text" name="no" value="${no}" placeholder="このフィールドを入力してください" required><br>

	<label>氏名</label>
	<input type="text" name="name" value="${name}" placeholder="このフィールドを入力してください" required><br>

	<label>クラス</label>
	<select name="class_num" required>
    <option value="">--------</option>
	    <c:forEach var="num" items="${class_list}">
	    	<option value="${num}" ${class_num == num ? 'selected' : ''}>${num}</option>
	    </c:forEach>
	</select><br>

	<button type="submit" name="end">登録して終了</button>

</form>

<a href="StudentList.action">戻る</a>
</main>
</div>
</body>
<%@include file="../../footer.jsp" %>
</html>