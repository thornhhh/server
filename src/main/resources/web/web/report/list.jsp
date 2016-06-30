<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/web/include/head.jsp"%>
<%@ include file="/WEB-INF/web/include/page.jsp"%>
<link href="${sysPath}/css/list.css" rel="stylesheet"/>
<script defer src="${sysPath}/js/list.js"></script>
</head>
<body class="content">

<c:if test="${param.list ne 'true'}">
	<div class="search-bar">
		<form class="form-inline" method="post" action="list">
			<div class="input-group">
				被举报方：
				&nbsp;&nbsp;&nbsp;&nbsp;
	         	<label><input type="checkbox" <c:if test="${param.user eq 'true'}">checked</c:if> name="user"/>求职者</label>
				<label><input type="checkbox" <c:if test="${param.hr eq 'true'}">checked</c:if> name="hr"/>招聘方</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 搜索</button>
			</div>
		</form>
	</div>
</c:if>

<ul class="report-list">
	<c:forEach items="${list}" var="obj">
		<li>
			<p>
				举报人：<span dbid="${obj.iid }" class="blue"<c:if test="${obj.type eq '0' }"> type='user'>求职者——</c:if><c:if test="${obj.type ne '0' }"> type='hr'>招聘者——</c:if>>${obj.informantName }</span>
				&nbsp;&nbsp;&nbsp;&nbsp;
				被举报人：<span dbid="${obj.bid }" class="orange"<c:if test="${obj.type ne '0' }"> type='user'>求职者——</c:if><c:if test="${obj.type eq '0' }"> type='hr'>招聘者——</c:if>>${obj.beinformantName }</span>
				&nbsp;&nbsp;&nbsp;&nbsp;
				举报时间：<span class="light-blue">${obj.time(obj.time)}</span>
			</p>
			<p>举报内容：${obj.content }</p>
			<p>
				类型：<c:if test="${obj.type eq '0' }">举报职位</c:if>
					<c:if test="${obj.type eq '1' }">举报简历</c:if>
					<c:if test="${obj.type eq '2' }">举报没露面</c:if>
			</p>
		</li>
	</c:forEach>
</ul>

<c:if test="${param.list eq 'true'}">
<script>
	$(".report-list li").first().addClass("no-border-top");
</script>
</c:if>

<script>
$(".report-list span[type]").click(function(){
	location.href = "../" + $(this).attr("type") + "/list?id=" + $(this).attr("dbid");
});
</script>

<c:if test="${param.list ne 'true'}">
	<div class="page" currentPage="${param.page}" maxPage="${maxPage}"></div>
</c:if>

</body>
</html>