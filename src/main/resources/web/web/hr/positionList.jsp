<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/web/include/head.jsp"%>
<link href="${sysPath}/css/list.css" rel="stylesheet"/>
<script defer src="${sysPath}/js/list.js"></script>
</head>
<body class="content">

<div class="search-bar">
	<button class="btn btn-default" onClick="history.go(-1)"><span class="glyphicon glyphicon-chevron-left"></span> 返回招聘方列表</button>
</div>
<div class="list-info">
	昵称：${hr.nickname}
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	手机号：${hr.phone}
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	职位数：${list.size()}
</div>

<c:forEach items="${list}" var="obj">
<div class="position-block" positionId="${obj.p.id}">
	<div class="time light-blue">${obj.day(obj.p.createTime)}</div>
	<h4>${obj.p.positionName}</h4>
	<h4 class="orange">
		<c:if test="${obj.p.salaryBegin eq -1}">面议</c:if>
		<c:if test="${obj.p.salaryBegin ne -1}">￥${obj.p.salaryBegin}-${obj.p.salaryEnd}/月</c:if>
	</h4>
	<div>${obj.p.companyName} | ${obj.city}</div>
</div>
</c:forEach>


<div class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-body"></div></div></div></div>

<script>
$(".position-block").click(function(){
	var positionId = $(this).attr("positionId");
	$(".modal").modal(true);
	$.ajax({url:"position?id="+positionId,success:function(e){
		$(".modal-body").html(e);
	}});
});
</script>
</body>
</html>