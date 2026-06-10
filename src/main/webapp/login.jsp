<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/login.css">
</head>
<body>
<!-- ログインフォーム-->
	<form action='LoginExecute.action' method='post'>
		<h2>ログイン</h2>
		<!-- エラーメッセージ表示 -->
		<ul>
		    <li>${errors}</li>
		</ul>
		<!-- ログインid入力 -->
		<div>
			<label for='id'>ログインID</label>
			<input type='text' id='id' name='id' value='${id}' placeholder='半角でご入力ください' maxlength="10">
		</div>

		<br>
		<!-- パスワード入力 -->
		<div>
			<label for='password'>パスワード</label><br>
            <input type='password' id='password' name='password' maxlength='30' placeholder='30文字以内の半角英数字でご入力ください'>
		</div>
		
		<br>
		<!-- パスワード表示チェックボックス -->
		<div>
			<input type='checkbox' id='chk_d_ps' name='chk_d_ps' onclick='togglePassword()'>
            <label for='chk_d_ps'>パスワードを表示</label>
		</div>
		
		<br>
		<!-- ログインボタン -->
		<div>
			<input type='submit' value='ログイン'>
		</div>

	</form>
	<!-- パスワード表示、非表示を切り替える関数 -->
	<script>
		function togglePassword(){
			const password=
				document.getElementById("password");
			if (password.type==="password"){
				password.type="text"
					}else{
						password.type="password";
						}
		}
	</script>
</body>
</html>