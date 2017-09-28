<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
	Welcome:<shiro:principal></shiro:principal>
	<table border="1">
		<tr>
			<td>序号</td>
			<td>ID</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>身份证</td>
			<td>操作 </td>
		</tr>
		<c:if test="${fn:length(listUser)<6}">
			<c:forEach items="${listUser}" var="list" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${list.id}</td>
					<td>${list.name}</td>
					<td>${list.age}</td>
					<td>${list.card}</td>
					<input type="hidden" value="${list.name}" />
					<td>
						<a href="user/delete/${list.id}" onclick="javascript:return confirm('是否确认删除?');">删除</a>
						<a href="user/updateJump/${list.id}"">修改</a> 
						<a href="user/detail?id=${list.id}">查看</a>
						<a href="obsystemaccount/rechargeJump/${list.id}">充值</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<a href="user/pager?pageSize=0">首页</a>
	<c:choose>
		<c:when test="${pager.startSize-1 < 0}">
			<a href="javascript:void(0);">上一页</a>
		</c:when>
		<c:otherwise>
			<a href="user/pager?pageSize=${pager.startSize-1}">上一页</a>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${pager.startSize+1 == pager.allNum}">
			<a href="ujavascript:void(0);">下一页</a>
		</c:when>
		<c:otherwise>
			<a href="user/pager?pageSize=${pager.startSize+1}">下一页</a>
		</c:otherwise>
	</c:choose>
	<a href="user/pager?pageSize=${pager.allNum}">末页</a>
	<div>当前页数：${pager.startSize+1} 总页数：${pager.allNum}
		总数：${pager.allSize}</div>
	<div>
		<form action="user/pager" method="post">
			<input type="text" name="pageSize" width="10px" /> <input
				type="submit" value="跳转" />
		</form>
	</div>
	<div>
		<a href="<%=basePath%>shiro/logout">退出</a>
	</div>
	<object width="210" height="225" data="http://cdn.abowman.com/widgets/hamster/hamster.swf?" type="application/x-shockwave-flash" style="">
	<param name="movie" value="http://cdn.abowman.com/widgets/hamster/hamster.swf?">
	<param name="AllowScriptAccess" value="always"><param name="wmode" value="opaque"></object>
</body>
</html>
