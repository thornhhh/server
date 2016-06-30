<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/web/include/head.jsp"%>
<link href="${sysPath}/css/index.css" rel="stylesheet"/>
</head>
<body>
<script>
if(window.parent != window)
	window.parent.location.href = window.parent.location.href;
</script>
<div class="index-header normal">
	<img src="${sysPath}/img/logo.png"/>
	聘道管理平台
</div>

<form class="form login" action="login">
	<div class="input-group">
		<div class="input-group-addon">用户名</div>
		<input type="text" name="username" class="form-control"/>
	</div>
	<div class="input-group">
		<div class="input-group-addon">密码</div>
		<input type="password" name="password" class="form-control"/>
	</div>
	<c:if test="${msg==1}">
		<div class="form-group">
			<span class="has-error">用户与密码不符！</span>
		</div>
	</c:if>
	<div class="form-group">
		<div class="checkbox">
			<label> <input type="checkbox" name="rememberme"> 记住我 </label>
		</div>
	</div>
	<div class="form-group">
		<button type="submit" class="btn btn-default">登陆</button>
	</div>
</form>

<script defer>
	$(".index-leftbar li").click(function() {
		$(this).siblings().removeClass("current").end().addClass("current");
		$("iframe")[0].src = $(this).attr("src") || "about:blank";
	}).filter(".current").click();
	
	$("input[type='checkbox']").change(function(){
		$(this).attr("value",$(this).prop("checked"));
	});
</script>

</body>
</html>