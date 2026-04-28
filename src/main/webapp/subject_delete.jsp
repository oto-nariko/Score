<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<body>
<%@include file="../../sidebar.jsp" %>
<main>
	<h2>科目情報削除</h2>
	<p>「」を削除してもよろしいですか</p>
	<form action="SubjectDeleteExecuteAction" method="post">
		<input type="submit" value="削除" >
    </form>
    <a href="SubjectListAction">戻る</a>
    
</main>
</body>
<%@include file="../../footer.jsp" %>
</html>