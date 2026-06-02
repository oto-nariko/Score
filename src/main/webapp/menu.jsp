<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>

<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
	<main class="content">
	<h2>メニュー</h2>
	<div>
	<p>
	<a href='/Score/scoremanager/main/StudentListAction'>学生管理</a>
	</p>
	<div>
	
	<p>成績管理</p>
	
	<p>
		<a href='/Score/scoremanager/main/TestRegistAction'>成績登録</a>
	</p>
	
	<p>
		<a href='/Score/scoremanager/main/TestListAction'>成績参照</a>
	</p>
	
	</div>
		<p>
			<a href='/Score/scoremanager/main/SubjectListAction'>科目管理</a>
		</p>
	</div>
	</main>
</div>	
</body>
<%@include file="../../footer.jsp" %>
</html>