<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.User,model.Mutter,java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%--
User loginUser = (User) session.getAttribute("loginUser");
List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
	<h1>どこつぶめいん</h1>
	<p>
		<c:out value="${loginUser.name}" />
		さん、ログイン中
	</p>
	
	<a href="Logout">ログアウト</a>
	<p>
		<a href="Main">更新</a>
	</p>
	<form action="Main" method="post">
		<input type="text" name="text"><br> <input type="submit"
			value="つぶやく"><br>
	</form>
	<c:if test="${not empty errorMsg}">
		<p>
			<c:out value="${errorMsg}"/>
		</p>
	</c:if>
	<c:forEach var="mutter" items="${mutterList}" >
	<p>
		<c:out value="${mutter.userName}" />:
		<c:out value="${mutter.text}" />
	</p>
	</c:forEach>
</body>
</html>