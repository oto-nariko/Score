<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<body>

<%@include file="../../sidebar.jsp" %>

<h2>学生管理</h2>

	<label>入学年度</label>
	<select name="f1">
		<option value="">--------</option>
		<option value=""></option>
	</select>
	
	<label>クラス</label>
	<select name="f2">
	<option value="">--------</option>
	<option value=""></option>
	</select>
	
	<input type="checkbox" name="f3" value="t">
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
		<th>在学中</th>
		<th></th>
	</tr>
</table>

</body>
<%@include file="../../footer.jsp" %>
</html>