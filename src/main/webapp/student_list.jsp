<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
<main class="content">

<h2>学生管理</h2>
<!-- 学生新規登録画面へのリンク -->
<a href="StudentCreate.action">新規登録</a>

<!--  学生検索フォーム-->
<form action="StudentList.action" method="get">
<!-- 入学年度選択 -->
	<label>入学年度</label>
	<select name="f1">
		<option value="0">--------</option>
		<c:forEach var="year" begin="2020" end="2030">
			<option value="${year}" ${f1 == year ? 'selected' : ''}>${year}</option>
		</c:forEach>
	</select>
	<!-- クラス選択 -->
	<label>クラス</label>
	<select name="f2">
		<option value="">--------</option>
		<c:forEach var="num" items="${class_list}">
			<option value="${num}" ${f2 == num ? 'selected' : ''}>${num}</option>
		</c:forEach>
	</select>
	
	<!-- 在学中の生徒のみ表示 -->
	<input type="checkbox" name="f3" value="t" ${f3 ? 'checked' : ''}>
	<label>在学中</label>
	<!-- 絞り込み実行ボタン -->
	<button type="submit">絞込み</button>
</form>
<!-- 検索結果表示 -->
<c:if test="${not empty students}">
	<div class="search-result-count" >
		検索結果 ： ${students.size()}件
	</div>
</c:if>	

<!-- 学生一覧表示 -->
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
			<!--  在学状況表示-->
			<td class="text-center">
				<c:choose>
					<c:when test="${student.attend}">o</c:when>
					<c:otherwise>x</c:otherwise>
				</c:choose>
			</td>
			<!-- 学生情報変更画面へのリンク -->
			<td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
		</tr>
	</c:forEach>
</table>
<!-- 学生情報がない時 -->
<c:if test="${empty students}">
	<p>学生情報が存在しません</p>
</c:if>
</main>
</div>

</body>
<%@include file="../../footer.jsp" %>
</html>