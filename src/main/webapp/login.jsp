<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ログイン</title>
	<link rel="stylesheet" href="login.css" />
</head>
<body>
	<h2>ログイン</h2>

	<form action='login' method='post'>

		<div>
			<label for='id'>ログインID</label>
			<input type='text' id='id' name='id' value='${id}' placeholder='半角でご入力ください'>
		</div>

		<br>
		
		<div>
			<label for='password'>パスワード</label><br>
            <input type='password' id='password' name='password' maxlength='30' placeholder='30文字以内の半角英数字でご入力ください'>
		</div>
		
		<br>
		
		<div>
			<input type='checkbox' id='chk_d_ps' name='chk_d_ps' onclick='togglePassword()'>
            <label for='chk_d_ps'>パスワードを表示</label>
		</div>
		
		<br>

		<div>
			<input type='button' name='login' value='ログイン'>
		</div>

	</form>
</body>
</html>