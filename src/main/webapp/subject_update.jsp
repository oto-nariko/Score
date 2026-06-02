<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
<main class="content">
<h2>科目情報変更</h2>
	<form action="SubjectUpdateExacute.action" method="post">
		<label>科目コード</label><br>
		<strong>${cd}</strong><br>
		<input type="hidden" name="cd" value="${cd}"><br>
		<label>科目名</label><br>
		<input type="text" name="name" value="${name}" maxlength="20"><br>
		
		<c:if test="${not empty errors.name}">
            <span style="color:#ff9900;">${errors.name}</span><br>
        </c:if>
        
		<input type="submit" value="変更" style="margin-top: 20px;">
	</form>	
	<a href="SubjectList.action">戻る</a>
</main>
</div>
</body>
<%@include file="../../footer.jsp" %>
</html>