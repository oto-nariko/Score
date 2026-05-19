<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<body>
<%@include file="../../sidebar.jsp" %>
	<h2>メニュー</h2>
	<div>
	<p>
	<a href='main/StudentList.action'>学生管理</a>
	</p>
	<div>
	
	<p>成績管理</p>
	
	<p>
		<a href='main/TestRegist.action'>成績登録</a>
	</p>
	
	<p>
		<a href='main/TestList.action'>成績参照</a>
	</p>
	
	</div>
		<p>
			<a href='main/SubjectList.action'>科目管理</a>
		</p>
	</div>
</body>
<%@include file="../../footer.jsp" %>
</html>