<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<body>
<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
<main class="content">
<h2>成績管理</h2>

<c:if test="${not empty errors}">
	<p style="color: #ffcc00;">${errors}</p>
</c:if>

<form action="TestRegist.action" method="post">
	<label>入学年度</label>
		<select name="f1">
			<option value="">--------</option>
			<c:forEach var="year" begin="2020" end="2035">
				<option value="${year}"${f1 == year ? 'selected' : ''}>${year}</option>
			</c:forEach>  
		</select>
		
	<label>クラス</label>
		<select name="f2">
			<option value="">--------</option>
			<c:forEach var="num1" items="${class_list}">
        		<option value="${num1}"${f2 == num1 ? 'selected' : ''}>${num1}</option>
    		</c:forEach>
		</select>
		
	<label>科目</label>
		<select name="f3">
			<option value="">--------</option>
			<c:forEach var="subject" items="${subject_list}">
        		<%-- Subject Beanのフィールド名（cd, name）に合わせて表示 --%>
        		<option value="${subject.cd}"${f3 == subject.cd ? 'selected' : ''}>${subject.name}</option>
    		</c:forEach>
		</select>
		
	<label>回数</label>
		<select name="f4">
			<option value="">--------</option>
			<c:forEach var="num" begin="1" end="10">
                <option value="${num}" <c:if test="${num == f4}">selected</c:if>>${num}</option>
            </c:forEach>
		</select>
		
	
	<input type="submit" value="検索" class="btn-search">	
</form>

<div><label>科目：${subject_name}（${f4}回目）</label></div>
<form action="TestRegistExecute.action" method="post">
	<table border="1">
		<thead>
			<tr>
				<th>入学年度</th>
				<th>クラス</th>
				<th>学生番号</th>
				<th>氏名</th>
				<th>点数</th>	
				<th width="50">操作</th>	
			</tr>
		</thead>	
		<tbody>
			<c:forEach var="test" items="${students}">
				<tr>
					<td>${test.student.entYear}</td>
					<td>${test.student.classNum}</td>
					<td>${test.student.no}</td>
					<td>${test.student.name}</td>
					<td>
						<input type="number" name="point_${test.student.no}" value="${test.point}" min="0" max="100">
						<input type="hidden" name="regist" value="${test.student.no}">
					</td>
					<td>
						<a href="TestDelete.action?student_no=${test.student.no}&f2=${f2}&f3=${f3}&f4=${f4}" class="btn-delete">削除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="hidden" name="count" value="${f4}">
    <input type="hidden" name="subject" value="${f3}">
    <input type="hidden" name="classNum" value="${f2}">
	<input type="submit" value="登録して終了"class="btn-sbmit" >
</form>

</main>
</div>
</body>
<%@include file="../../footer.jsp" %>
</html>