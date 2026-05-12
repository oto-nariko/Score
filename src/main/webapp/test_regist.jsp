<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<body>
<%@include file="../../sidebar.jsp" %>
<main>
<h2>成績管理</h2>
<form action="TestRegistAction" method="post">
	<label>入学年度</label>
		<select name="f1">
			<option value="">--------</option>
			<c:forEach var="year" items="${ent_year_set}">
				<option value="${year}">${year}</option>
			</c:forEach>  
		</select>
	<label>クラス</label>
		<select name="f2">
			<option value="">--------</option>
			<c:forEach var="v" items="${class_num_set}">
        		<option value="${v}">${v}</option>
    		</c:forEach>
		</select>
	<label>科目</label>
		<select name="f3">
			<option value="">--------</option>
			<c:forEach var="subject" items="${subjects}">
        		<%-- Subject Beanのフィールド名（cd, name）に合わせて表示 --%>
        		<option value="${subject.cd}">${subject.name}</option>
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


<div><label>科目：${subject_name}（${f4}回目）</label></div>
<form action="TestRegistExecuteAction" method="post">
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
			<c:forEach var="student" items="${students}">
				<tr>
					<td>${student.entYear}</td>
					<td>${student.classNum}</td>
					<td>${student.no}</td>
					<td>${student.name}</td>
					<td><input type="number" name="point_${student.no}" value="" min="0" max="100"></td>
					<input type="hidden" name="regist" value="${student.no}">
				</tr>
		
	</table>
	<input type="hidden" name="count" value="${f4}">
            <input type="hidden" name="subject" value="${f3}">

<input type="submit" value="登録して終了"class="btn-sbmit" >
</form>

</main>
</body>
<%@include file="../../footer.jsp" %>
</html>