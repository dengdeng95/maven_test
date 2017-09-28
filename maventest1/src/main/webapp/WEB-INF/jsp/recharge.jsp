
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'allUser.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form action="<%=basePath%>obsystemaccount/updateMoney/${obsystemaccount.accountid}" method="post">
		<input type="hidden" value="${obsystemaccount.accountid}">
		用户名：&nbsp;&nbsp;&nbsp;<input type="text" name="name" value="${obsystemaccount.investpersonname}" readonly="true"/></br>
		账户余额：<input type="text" name="name" value="${obsystemaccount.totalmoney}" readonly="true"/></br>
		<input type="text" name="money" />
		<input type="submit" value="提现" /> 
		<input type="reset" value="重置" />
	</form>
</body>
</html>
