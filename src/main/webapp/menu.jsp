<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>

<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
	<main class="content">
	<h2>メニュー</h2>
	<div class="menu-container">
	<p class="menu-box student">
	<a href='/Score/scoremanager/main/StudentList.action'>学生管理</a>
	</p>
	<div class="menu-box score">
	
	<p class="score-title">成績管理</p>
	
	<p class="score-item">
		<a href='/Score/scoremanager/main/TestRegist.action'>成績登録</a>
	</p>
	
	<p class="score-item">
		<a href='/Score/scoremanager/main/TestList.action'>成績参照</a>
	</p>
	
	</div>
		<p class="menu-box subject">
			<a href='/Score/scoremanager/main/SubjectList.action'>科目管理</a>
		</p>
	</div>
	</main>
</div>	
</body>
<%@include file="../../footer.jsp" %>
</html>