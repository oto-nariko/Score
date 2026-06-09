<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<link rel="stylesheet" href="../../subject.css">
<div class="wrapper">
	<%@include file="../../sidebar.jsp" %>
<main class="content">
	<h2>科目管理</h2>
	<a href="SubjectCreate.action">新規登録</a>
	<table class="subject-table">
		<tr>
			<th>科目コード</th>
			<th>科目名</th>
			<th></th><!-- 変更ボタンのとこ -->
			<th></th><!-- 削除ボタンのとこ -->
		</tr>
		<c:forEach var="subject" items="${subjects}">
		<tr>
			<!-- リストから取り出した科目のコードと名前をセルに表示 -->
			<td>${subject.cd}</td>
			<td>${subject.name}</td>
			<!-- ?cd=${subject.cd}でクリックした科目コードを次の画面に引き継ぐ -->
			<td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
			<!-- こっちも科目コードを引き継いで次の画面へ -->
			<td><a href="SubjectDelete.action?cd=${subject.cd}">削除</a></td>
		</tr>
		</c:forEach>
	</table>
</main>
</div>

</body>
<%@include file="../../footer.jsp" %>
</html>