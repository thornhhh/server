<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/web/include/head.jsp"%>
<link href="${sysPath}/css/index.css" rel="stylesheet"/>
</head>
<body>
<div style="text-align:center;margin-top:50px;">
	<!-- <h1 class="glyphicon glyphicon-remove" style="font-size:100px;"></h1> -->
	<img src="" id="rimg"/>
	<h1>非法访问</h1>
	<h3>403 : 你没有访问该地址的权限</h3>
	<h3><a href="${sysPath }" title="返回主页">返回主页</a></h3>
	<hr>
	<h5 style="margin-top:50px;color:#aaa;">系统已经记录下本次操作的IP以及访问动作以供审查</h5>
</div>
<script>
var imgs = ["403.jpg","403_1.png","403_2.png","403_3.png"];
$("#rimg")[0].src = "${sysPath}/img/" + imgs[Math.floor(Math.random() * imgs.length)]; 
</script>
</body>
</html>