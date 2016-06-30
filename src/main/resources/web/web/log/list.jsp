<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/web/include/head.jsp"%>
<%@ include file="/WEB-INF/web/include/page.jsp"%>
<link href="${sysPath}/css/list.css" rel="stylesheet"/>
<script defer src="${sysPath}/js/list.js"></script>
</head>
<body class="content">

<div class="search-bar">
	<form class="form-inline" method="post" action="list">
		<div class="input-group">
			<div class="input-group">
				<div class="input-group-addon">操作人（ID）</div><input type="text" class="form-control" value="${param.eid}" name="eid"/>
			</div>
			<div class="input-group">
				<div class="input-group-addon">发布时间</div>
				<input type="text" class="form-control middle" placeholder="起始时间" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" name="beginTime" value="${param.beginTime}">
				<input type="text" class="form-control middle" placeholder="结束时间" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" name="endTime" value="${param.endTime}">
			</div>
			<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 搜索</button>
		</div>
	</form>
</div>

<ul class="report-list">
	<c:forEach items="${list}" var="obj">
		<li>
			<p>
				时间：<span class="blue">${obj.time(obj.time)}</span>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				操作人：<span class="blue">${obj.name}</span>
			</p>
			<p>操作内容：${obj.content }</p>
		</li>
	</c:forEach>
</ul>


<div class="page" currentPage="${param.page}" maxPage="${maxPage}"></div>

</body>
</html>