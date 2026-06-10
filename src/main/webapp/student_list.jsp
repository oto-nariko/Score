<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<link rel="stylesheet" href="../../student.css">
<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
<main class="content">

<h2>学生管理</h2>
<<<<<<< HEAD
<div class="action-box">
	<a href="StudentCreate.action">新規登録</a>
</div>

<form action="StudentList.action" method="get" class="filter-box">
	<div class="form-group">
		<label>入学年度</label>
		<select name="f1">
			<option value="0">--------</option>
			<c:forEach var="year" begin="2020" end="2030">
				<option value="${year}" ${f1 == year ? 'selected' : ''}>${year}</option>
			</c:forEach>
		</select>
	</div>
		
	<div class="form-group">
		<label>クラス</label>
		<select name="f2">
			<option value="">--------</option>
			<c:forEach var="num" items="${class_list}">
				<option value="${num}" ${f2 == num ? 'selected' : ''}>${num}</option>
			</c:forEach>
		</select>
	</div>
	
	<div class="checkbox-group">
		<input type="checkbox" name="f3" value="t" ${f3 ? 'checked' : ''}>
		<label>在学中</label>
	</div>
	
<<<<<<< HEAD
	<button type="submit" class="btn-filter">絞込み</button>
=======
</form>
<!-- 検索結果表示 -->
<c:if test="${not empty students}">
	<div class="search-result-count" >
		検索結果 ： ${students.size()}件
	</div>
</c:if>	

<<<<<<< HEAD

<table class="student-table">
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