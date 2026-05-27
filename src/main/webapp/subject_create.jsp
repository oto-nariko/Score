<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="../../header.jsp" %>
<div class="wrapper">
<%@include file="../../sidebar.jsp" %>
<main class="content">
<h2>科目情報登録</h2>
	<form action="SubjectCreateExecute.action" method="post">		
		<label>科目コード</label><br>
		<input type="text" name="cd" value="${cd}"
			maxlength="3" required
         	placeholder="科目コードを入力してください"><br>
         	
        <c:if test="${not empty errors.cd}">
            <div style="color: #ff9900; font-size: 0.9em; margin-top: 5px; margin-bottom: 5px;">
                ${errors.cd}
            </div>
        </c:if>
         	
		<label>科目名</label><br>
		<input type="text" name="name" value="${name}"
			maxlength="20" required
            placeholder="科目名を入力してください"><br>
		<input type="submit" value="登録" style="margin-top: 10px;">
	</form>	
	<a href="/Score/scoremanager/main/SubjectList.action">戻る</a>
</main>
</div>
</body>
<%@include file="../../footer.jsp" %>
</html>