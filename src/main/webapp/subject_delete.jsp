<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
<main class="content">
	<h2>科目情報削除</h2>
	<p>「${subject_name}(${subject_cd})」を削除してもよろしいですか</p>
	<form action="SubjectDeleteExecute.action" method="post">
		<input type="hidden" name="subject_cd" value="${subject_cd}">
		<input type="hidden" name="subject_name" value="${subject_name}">
		<input type="submit" value="削除" >
    </form>
    <a href="SubjectList.action">戻る</a>
    
</main>
</div>
</body>
<%@include file="../../footer.jsp" %>
</html>