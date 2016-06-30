<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/web/include/head.jsp"%>
<link href="${sysPath}/css/index.css" rel="stylesheet"/>
</head>
<body>
<div class="index-header">
	<img src="${sysPath}/img/logo.png"/>
	聘道管理平台
	<span><a href="logout">注销</a></span>
	<span>你好: ${user.username}</span>
</div>
<ul class="nav index-leftbar">
	<shiro:hasPermission name="account:index">
		<li src="dashboard" class="current">首页</li>
	</shiro:hasPermission>
	<shiro:hasPermission name="jobhunter:index">
		<li src="user/list">求职者管理</li>
	</shiro:hasPermission>
	<shiro:hasPermission name="recruiter:index">
		<li src="hr/list">招聘方管理</li>
	</shiro:hasPermission>
	<shiro:hasPermission name="report:index">
		<li src="report/list">举报管理</li>
	</shiro:hasPermission>
	<shiro:hasPermission name="check:index">
		<li src="video/list">视频简历审核</li>
	</shiro:hasPermission>
	<shiro:hasPermission name="log:index">
		<li src="log/list">操作日志</li>
	</shiro:hasPermission>
	<shiro:hasPermission name="user:index">
		<li src="admin/list">审核者管理</li>
	</shiro:hasPermission>
	<!-- 
	<li child="submenu">子菜单</li>
	<div id="submenu">
		<li src="video/list">视频简历审核</li>
		<li src="video/list">视频简历审核</li>		
	</div>
	 -->
</ul>

<div class="main-container"><iframe></iframe></div>
<script defer>
$(".index-leftbar li").click(function(){
	var child = $(this).attr("child");
	$(this).siblings().removeClass("current").end().addClass("current");
	$(this).parent().find("div").hide();
	if(!!!child){
		$("iframe")[0].src = $(this).attr("src") || "about:blank";
	}else{
		$(this).parent().find("div#" + child).show().children(".current").removeClass("current");
	}
}).filter(".current").click();
</script>
</body>
</html>