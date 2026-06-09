<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
<main class="content">
<h2>学生情報登録</h2>
<!-- エラーメッセージ表示 -->
<c:if test="${not empty error_message}">
	<p>${error_message}</p>
</c:if>
<!-- 学生情報登録フォーム -->
<form action="StudentCreateExecute.action" method="post">

	<!-- 入学年度選択 -->
	<label>入学年度</label>
	<select name="ent_year">
    <option value="">--------</option>
    <c:forEach var="year" begin="2020" end="2035">
    	<option value="${year}" ${ent_year == year ? 'selected' : ''}>${year}</option>
    </c:forEach>
	</select><br>
	<!-- 学生番号入力 -->
	<label>学生番号</label>
	<input type="text" name="no" value="${no}" placeholder="このフィールドを入力してください" required><br>
	<!--  氏名入力-->
	<label>氏名</label>
	<input type="text" name="name" value="${name}" placeholder="このフィールドを入力してください" required><br>
	<!-- クラス選択 -->
	<label>クラス</label>
	<select name="class_num" required>
    <option value="">--------</option>
	    <c:forEach var="num" items="${class_list}">
	    	<option value="${num}" ${class_num == num ? 'selected' : ''}>${num}</option>
	    </c:forEach>
	</select><br>
	
	<!-- 登録ボタン -->
	<button type="submit" name="end">登録して終了</button>

</form>
<!-- 学生一覧画面へもどる -->
<a href="StudentList.action">戻る</a>
</main>
</div>
</body>
<%@include file="../../footer.jsp" %>
</html>