<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<body>
<%@include file="../../sidebar.jsp" %>
<main>
<h2>成績管理</h2>
<form action="TestRegistAction" method="post">
	<label>入学年度</label>
		<select name="f1">
			<option value="">--------</option>
		</select>
	<label>クラス</label>
		<select name="f2">
			<option value="">--------</option>
		</select>
	<label>科目</label>
		<select name="f3">
			<option value="">--------</option>
		</select>
	<label>回数</label>
		<select name="f4">
			<option value="">--------</option>
		</select>
		
		
	<input type="submit" value="検索" class="btn-search">	
</form>

<div><label>科目</label></div>
<table>
	<tr>
		<th>入学年度</th>
		<th>クラス</th>
		<th>学生番号</th>
		<th>氏名</th>
		<th>点数</th>		
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td><input></td>
	</tr>
	
</table>

<input>

</main>
</body>
<%@include file="../../footer.jsp" %>
</html>