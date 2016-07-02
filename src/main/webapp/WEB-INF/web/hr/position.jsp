<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sysPath" value="${pageContext.request.contextPath}"/>
<c:if test="${sysPath=='/'}">
	<c:set var="sysPath" value="" />
</c:if>

<div class="info-title container-fluid">
	<div class="time">
		<h5>发布时间：${vo.day(vo.p.createTime)}</h5>
		<h5>刷新时间：${vo.day(vo.p.refreshTime)}</h5>
	</div>
	<h3>${vo.job}</h3>
	<h5>${vo.trade}</h5>
	<h4 orange><c:if test="${vo.p.salaryBegin eq -1 }">面议</c:if><c:if test="${vo.p.salaryBegin ne -1 }">￥${vo.p.salaryBegin}-${vo.p.salaryEnd}/月</c:if></h4>
	<h5>
		招聘人数：${vo.p.employCount }
		&nbsp;&nbsp;&nbsp;
		已申请：${vo.p.positionApplyNum }
	</h5>
</div>

<hr>

<div class="container-fluid cpanel-body ctabled">
	<div class="col-xs-6">学历要求：${vo.education }</div>
	<div class="col-xs-6">语言要求：${vo.lang(vo.p.language) }</div>
	<div class="col-xs-6">工作经验：<c:if test="${vo.p.workExpBegin eq -1}">面议</c:if><c:if test="${vo.p.workExpBegin ne -1}">${vo.p.workExpBegin } - ${vo.p.workExpEnd }</c:if></div>
	<div class="col-xs-6">工作性质：${vo.property }</div>
	
	<div style="padding-top:70px;">
		<c:forEach items="${vo.tradeWord}" var="obj">
		<span class="clabel">${obj }</span>
		</c:forEach>
	</div>
</div>

<div class="cpanel-head">工作地点</div>
<div class="container-fluid cpanel-body">
	<div style="margin-top:10px;">
		${vo.p.detailedAddress }
	</div>
</div>

<c:if test="${vo.c ne null}">
<div class="cpanel-head">公司介绍</div>
<div class="container-fluid cpanel-body">
	<div strong mb10>${vo.c.companyName}<span style="margin-left:50px;">${vo.companyScale(vo.c.scale)}</div>
	<div>地址：${vo.companyCity}</div>
	<div>性质：${vo.companyProperty(vo.c.companyType)}</div>
</div>
</c:if>

<div class="cpanel-head">关键词</div>
<div class="container-fluid cpanel-body cpt10">
	<c:forEach items="${vo.tags}" var="obj">
	<span class="clabel">${obj }</span>
	</c:forEach>
</div>

<div class="cpanel-head">职位描述</div>
<div class="container-fluid cpanel-body cpt10">
	<div class="">${vo.p.description }</div>
</div>

<div class="cpanel-head">招聘流程</div>
<div class="container-fluid cpanel-body cgview flow">
	<c:forEach items="${vo.flow}" var="obj">
		<div><span title="${obj }">${obj }</span></div>
		<p></p>
	</c:forEach>
	<script>$(".flow p").last().remove()</script>
</div>