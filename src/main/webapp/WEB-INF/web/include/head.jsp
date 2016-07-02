<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="sysPath" value="${pageContext.request.contextPath}"/>
<c:if test="${sysPath=='/'}">
	<c:set var="sysPath" value="" />
</c:if>
<!doctype html>
<html>
<head>
<!--[if lt IE 9]>
<script>
location.href = "${sysPath}/browser";
</script>
<![endif]-->
<script src="${sysPath}/js/jquery-1.12.4.min.js"></script>
<script src="${sysPath}/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<link href="${sysPath}/css/global.css" rel="stylesheet"/>
<link href="${sysPath}/lib/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<title>聘道管理平台</title>
