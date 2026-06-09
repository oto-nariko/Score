<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
<main class="content">
<h2>成績参照</h2>
<!-- 科目情報 -->
<section class="search-block">
	<p><strong>科目情報</strong></p>
	<form action="TestListSubjectExecute.action" method="post">
		<!-- 識別コード -->
		<input type="hidden" name="f" value="sj">
		
		<label>入学年度</label>
		<select name="f1">
		<option value="">-----------</option>
		<c:forEach var="year" items="${ent_year_set}">
			<option value="${year}"<c:if test="${year == f1}">selected</c:if>>${year}</option>
		</c:forEach>
		</select>
	
		<label>クラス</label>
        <select name="f2">
            <option value="">--------</option>
            <c:forEach var="v" items="${class_num_set}">
                <option value="${v}" <c:if test="${v == f2}">selected</c:if>>${v}</option>
            </c:forEach>
        </select>

    	<label>科目</label>
        <select name="f3">
            <option value="">--------</option>
            <c:forEach var="subject" items="${subjects}">
                <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
            </c:forEach>
        </select>
        <!-- 検索ボタン -->
        <input type="submit" value="検索" class="btn-search">    
	</form>
</section>

	<!-- エラー表示エリア -->
	<c:if test="${not empty error}">
    <div class="error-msg">
        <p style="color: #ffcc00;">${error}</p>
    </div>
	</c:if>
<br>
<!-- 学生情報 -->
<section>
	<p><strong>学生情報</strong></p>
	<form action="TestListStudentExecute.action" method="post">
		<!-- 識別コード -->
		<input type="hidden" name="f" value="st">
		
		<label>学生番号</label>
		<input type="text" name="f4" value="${f4}" placeholder="学生番号を入力してください" maxlength="10" required>
		
		<c:if test="${not empty studentError}">
		
		<div style="color: #ff9900; font-size: 0.9em; margin-top: 5px; margin-bottom: 5px;">
                ${studentError }
           </div>
		</c:if>
		<!-- 検索ボタン -->
		<input type="submit" value="検索">
	</form>
	</section>
	<!-- 学生情報表示 -->
	<c:if test="${not empty student}">
	<p>氏名：${student.name}（${student.no}）</p>
	</c:if>
	<!-- 成績なし -->
	<c:if test="${not empty studentResultError}">
	<p>${studentResultError}</p>
	</c:if>
	<hr>
	<p class="info-msg" style="color: #00a4e4;">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
	</main>
	</div>
</body>
<%@include file="../../footer.jsp" %>
</html>