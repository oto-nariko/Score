<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
<main class="content">
	<h2>成績削除</h2>
	
	<table border="1">
		<thead>
			<tr>
				<th>入学年度</th>
				<th>クラス</th>
				<th>学生番号</th>
				<th>氏名</th>
				<th>点数</th>		
			</tr>
		</thead>	
		<tbody>
				<tr>
					<td>${test.student.entYear}</td>
					<td>${test.student.classNum}</td>
					<td>${test.student.no}</td>
					<td>${test.student.name}</td>
					<td>${test.point }</td>
				</tr>
		</tbody>
	</table>
	
	<p>上記の成績を削除してもよろしいですか</p>
	
	<form action="Testdelete.action" method="post">
		<input type="hidden" name="regect" value="${student_no}">
		<input type="hidden" name="subject" value="${f3}">
		<input type="hidden" name="count" value="${f4}">
		<input type="hidden" name="classNum" value="${f2}">
		<input type="submit" value="削除" >
    </form>
    <a href="TestRegist.action">戻る</a>
    
</main>
</div>
</body>
<%@include file="../../footer.jsp" %>
</html>