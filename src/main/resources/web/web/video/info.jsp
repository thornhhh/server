<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sysPath" value="${pageContext.request.contextPath}"/>
<c:if test="${sysPath=='/'}">
	<c:set var="sysPath" value="" />
</c:if>
<div class="mauto">
	<div class="video-outer">
		<video width="100%" height="100%" controls src="${sysPath}/${vo.path}"><source type=video/mp4 /></video>
	</div>
	发布人：<span class="blue">${vo.name }</span>
	<br>
	<span class="light-blue">${vo.time(vo.createTime) }</span>
	<br>	
	<div>
		<button class="btn btn-warning" ajax="reject"><span class="glyphicon glyphicon-remove"></span> 停止发布并删除</button>
		<button class="btn btn-success" ajax="accept"><span class="glyphicon glyphicon-ok"></span> 通过审核</button>
	</div>
</div>

<script>
$("button[ajax]").click(function(){
	$.ajax({url:$(this).attr("ajax")+"?id=${param.id}",success:function(){
		alert("操作成功。");
		location.href = location.href; 
	}});
});
</script>