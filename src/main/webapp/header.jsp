<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8"
	<link rel="stylesheet" href="${pageContext.request.contextPath}/main.css">
</head>

<body>
<header>
	<div class="header-left">
		<h1>得点管理システム</h1>
	</div>	
	
	<div class="header-right">
		<!-- ログイン後 -->
		<p><span>${user.name}様</span></p>
		<a href="/Score/scoremanager/main/Logout.action">ログアウト</a>
	</div>
</header>		
		