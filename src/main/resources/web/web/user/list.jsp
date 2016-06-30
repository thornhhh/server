<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/web/include/head.jsp"%>
<%@ include file="/WEB-INF/web/include/page.jsp"%>
<link href="${sysPath}/css/list.css" rel="stylesheet"/>
<script defer src="${sysPath}/js/list.js"></script>
</head>
<body class="content">
<c:if test="${param.id eq null}">
<div class="search-bar">
	<form class="form-inline" method="post" action="list">
		<div class="input-group">
          <div class="input-group-addon">姓名</div><input type="text" class="form-control" name="name" value="${param.name}">
        </div>
        <div class="input-group">
          <div class="input-group-addon">昵称</div><input type="text" class="form-control" name="nickname" value="${param.nickname}">
        </div>
        <div class="input-group">
          <div class="input-group-addon">手机号</div><input type="text" class="form-control" name="phone" value="${param.phone}">
        </div>
        <div class="input-group">
          <div class="input-group-addon">简历名称</div><input type="text" class="form-control" name="rname" value="${param.rname}">
        </div>
        
		<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 搜索</button>
        &nbsp;&nbsp;
		
		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				更多搜索条件 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu dropdown-menu-right">
				<div class="more">
					<div class="input-group rate">
						<div class="input-group-addon">露面率</div>
						<input type="text" placeholder=">1.0" class="form-control mini" name="appbegin" value="${param.appbegin}"/>
						<input type="text" placeholder="<5.0" class="form-control mini" name="append" value="${param.append}"/>
					</div>
					
					<div class="input-group rate">
						<div class="input-group-addon">相关率</div>
						<input type="text" placeholder=">1.0" class="form-control mini" name="aboutbegin" value="${param.aboutbegin}"/>
						<input type="text" placeholder="<5.0" class="form-control mini" name="aboutend" value="${param.aboutend}"/>
					</div>
					
					<label><input type="checkbox" <c:if test="${param.stop eq 'true'}">checked</c:if> name="stop"/>停用中</label>
					<label><input type="checkbox" <c:if test="${param.using eq 'true'}">checked</c:if> name="using"/>使用中</label>
					<label><input type="checkbox" <c:if test="${param.report eq 'true'}">checked</c:if> name="report"/>被举报</label>
				</div>
			</ul>
		</div>
	</form>
</div>
</c:if>
<c:if test="${param.id ne null}">
<div class="search-bar mb10">
	<button class="btn btn-default" onClick="history.go(-1)"><span class="glyphicon glyphicon-chevron-left"></span> 返回</button>
</div>
</c:if>

<table class="table table-striped">
	<tr>
		<th>姓名</th>
		<th>昵称</th>
		<th>手机号</th>
		<th>简历名称</th>
		<th>露面率</th>
		<th>相关率</th>
		<th>被举报</th>
		<th>操作</th>	
	</tr>
	<c:forEach items="${list}" var="obj">
	<tr db-id="${obj.rid}" type="resume">
		<td>${obj.name}</td>
		<td>${obj.nickname}</td>
		<td>${obj.phone}</td>
		<td more right>${obj.rname} <span class="glyphicon glyphicon-chevron-right"></span></td>
		<td>${obj.appearanceRate}</td>
		<td>${obj.aboutRate}</td>
		<td right report uid="${obj.uid}">${obj.reportCount} <span class="glyphicon glyphicon-chevron-right"></span></td>
		<td>
			<c:if test="${obj.isDel ne '2'}">
				<button type="button" class="btn btn-danger" switch oid="${obj.uid}"><span class="glyphicon glyphicon-pause"></span> 停用</button>
			</c:if>
			<c:if test="${obj.isDel eq '2'}">
				<button type="button" class="btn btn-success" switch oid="${obj.uid}"><span class="glyphicon glyphicon-play"></span> 启用</button>
			</c:if>
			<button type="button" class="btn btn-primary" message="user"><span class="glyphicon glyphicon-envelope"></span> 发消息</button>
		</td>
	</tr>
	</c:forEach>
</table>

<c:if test="${param.id eq null}">
<div class="page" currentPage="${param.page}" maxPage="${maxPage}"></div>
</c:if>

<div class="modal fade"><div class="modal-dialog"><div class="modal-content"><div class="modal-body"></div></div></div></div>

</body>
</html>