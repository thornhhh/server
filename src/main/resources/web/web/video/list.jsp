<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/web/include/head.jsp"%>
<%@ include file="/WEB-INF/web/include/page.jsp"%>
<link href="${sysPath}/css/list.css" rel="stylesheet"/>
<script src="${sysPath}/lib/laydate-master/laydate.js"></script>
<script defer src="${sysPath}/js/list.js"></script>
</head>
<body class="content">

<div class="search-bar">
	<form class="form-inline" method="post" action="list">
		<div class="input-group">
          <div class="input-group-addon">发布人</div><input type="text" class="form-control" name="name" value="${param.name }">
        </div>
        <div class="input-group">
          <div class="input-group-addon">发布时间</div>
          <input type="text" class="form-control middle" placeholder="起始时间" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" name="beginTime" value="${param.beginTime}">
          <input type="text" class="form-control middle" placeholder="结束时间" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" name="endTime" value="${param.endTime}">
        </div>
        <label><input type="checkbox" <c:if test="${param.uncheck eq 'true'}">checked</c:if> name="uncheck"/>未审核</label>
		<label><input type="checkbox" <c:if test="${param.check eq 'true'}">checked</c:if> name="check"/>已审核</label>
		<label><input type="checkbox" <c:if test="${param.del eq 'true'}">checked</c:if> name="del"/>未通过</label>
		<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 搜索</button>
	</form>
</div>

<hr>

<div>
<c:forEach items="${list}" var="obj">
	<div class="video-list" vid="${obj.id}">
		<div>
			<img src="about:blank" class="fix"/>
			发布人：<span class="blue">${obj.name}</span>
			<br>
			<span class="light-blue">${obj.time(obj.createTime)}</span>		
			<br>
			<c:if test="${obj.type eq 2}"><span style="color:red">未通过</span></c:if>
			<c:if test="${obj.type eq 1}"><span style="color:green">已通过</span></c:if>
			<c:if test="${obj.type eq 0}"><span style="color:blue">未审核</span></c:if>
		</div>
	</div>
</c:forEach>
</div>

<div class="page" currentPage="${param.page}" maxPage="${maxPage}"></div>

<div class="modal fade mini"><div class="modal-dialog"><div class="modal-content"><div class="modal-body"></div></div></div></div>

</body>
</html>