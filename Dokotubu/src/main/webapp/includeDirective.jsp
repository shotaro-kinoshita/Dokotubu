<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<%
Date date = new Date();
SimpleDateFormat sdf= new SimpleDateFormat("MM月dd日");
String today = sdf.format(date);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>動的インクルードによるフッター表示</title>
</head>
<body>
<h1>どこつぶへようこそ</h1>
<p>「どこつぶ」は<%= name %>さんが<%= today %>に作りました</p>
<jsp:include page="footer.jsp" />
</body>
</html>