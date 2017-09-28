<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
	<spring:message code="welcome"></spring:message>
	<a href="<%=basePath%>shiro/language.do?language=zh_CN"><spring:message code="Chinese"></spring:message></a>
    <a href="<%=basePath%>shiro/language.do?language=en"><spring:message code="English"></spring:message></a>
	<form action="<%=basePath%>shiro/login" method="post">
		姓名：<input type="text" name="username" /> </br>
		密码：<input type="password" name="password" /> </br>
		<input type="submit" value="确定" /> 
		<input type="reset" value="重置" />
		<a href="<%=basePath%>shiro/jump">注册</a>
	</form>
	<object width="210" height="225" data="http://cdn.abowman.com/widgets/hamster/hamster.swf?" type="application/x-shockwave-flash" style="">
	<param name="movie" value="http://cdn.abowman.com/widgets/hamster/hamster.swf?">
	<param name="AllowScriptAccess" value="always"><param name="wmode" value="opaque"></object>
	<div></div><a href="http://localhost:3000" target="_blank">进入聊天室</a></div>
	<div></div><a href="<%=basePath%>shiro/market" target="_blank">交易大厅</a></div>
</body>
</html>
<script type="text/javascript"  src="./static/js/base.js"></script>
<script type="text/javascript"  src="./static/js/src/lib/seajs/sea.js"></script>
<script type="text/javascript">
 seajs.config({
	base : basepath,
	alias : {
		"jquery" : "lib/jquery/1.9.1.min.js",
		"jq" : "lib/jquery/jquery.js",
		"layer" : "lib/layer/layer.js"
	},
	preload : ['jquery','jq','layer'],
	map:[['.js','.js?v='+new Date().getTime()]]
}); 
</script>
<script type="text/javascript">
seajs.use('app/login',function(l){
	l.login();
});
</script>