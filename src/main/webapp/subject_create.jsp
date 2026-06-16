<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<link rel="stylesheet" href="../../subject_create.css">
<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
<main class="content">
<h2>科目情報登録</h2>
	<form action="SubjectCreateExecute.action" method="post" class="create-form">		
		<div class="create-form-item">
			<label>科目コード</label>
			<input type="text" name="cd" value="${cd}"
				maxlength="3" required
         		placeholder="科目コードを入力してください">
         	
	        <!-- java側から重複エラーが返ってきたときだけ表示するエリア --> 	
	        <c:if test="${not empty errors.cd}">
	            <div class="create-error-text">
	                ${errors.cd}
	            </div>
	        </c:if>
        </div>
         
        <div class="create-form-item">
			<label>科目名</label>
			<input type="text" name="name" value="${name}"
				maxlength="20" required
            	placeholder="科目名を入力してください">
        </div>
        
		<button type="submit" class="btn-create-submit">登録</button>
	</form>
	<div class="create-back-link">
		<a href="/Score/scoremanager/main/SubjectList.action">戻る</a>
	</div>
</main>
</div>
</body>
<%@include file="../../footer.jsp" %>
</html>