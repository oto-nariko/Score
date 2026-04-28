<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<body>
<%@include file="../../sidebar.jsp" %>
<main>
<h2>科目情報登録</h2>
	<form action="SubjectCreateExecuteAction" method="post">		
		<label>科目コード</label><br>
		<input type="text" name="cd" value="${cd}"><br>
		<label>科目名</label><br>
		<input type="text" name="name" id="${name}"><br>
		<input type="submit" value="登録">
	</form>	
	<a href="SubjectListAction">戻る</a>
</main>
</body>
<%@include file="../../footer.jsp" %>
</html>