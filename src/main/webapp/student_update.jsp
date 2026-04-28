<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<body>

<%@include file="../../sidebar.jsp" %>

<h2>学生情報変更</h2>

<form action="" method="post">

<label>入学年度</label>
<input type="text" name="ent_year" value="${ent_year }" readonly >

<br>

<label>学生番号</label>
<input type="text" name="no" value="${no }" readonly>

<label>氏名</label>
<input type="text" name="name" value="${name }">

<br>

<label>クラス</label>
<select name="class_num">
<option value="">--------</option>
</section>

<br>

<label>在学中</label>
<input type="chexbox" name="is_attend">
<br>

<button type="submit" name="login">変更</button>

</form>

<<a href="student_list.jsp">戻る</a>

</body>
<%@include file="../../footer.jsp" %>
</html>