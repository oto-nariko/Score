<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生情報登録</title>
</head>
<body>

<%@include file="../../header.jsp" %>

<%@include file="../../sidebar.jsp" %>

<h2>学生情報登録</h2>

<form action="" method="post">

<label>入学年度</label>
<select name="ent_year">
    <option value="">--------</option>
</select>

<br>

<label>学生番号</label>
<input type="text" name="no" value="${no}" placeholder="学生番号を入力してください">

<br>

<label>氏名</label>
<input type="text" name="name" value="${name}" placeholder="氏名を入力してください">

<br>

<label>クラス</label>
<select name="class_num">
    <option value="">--------</option>
</select>

<br>

<button type="submit" name="end">登録して終了</button>

</form>

<a href="">戻る</a>

<%@include file="../../footer.jsp" %>

</body>
</html>