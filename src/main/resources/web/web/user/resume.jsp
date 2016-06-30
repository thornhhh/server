<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sysPath" value="${pageContext.request.contextPath}"/>
<c:if test="${sysPath=='/'}">
	<c:set var="sysPath" value="" />
</c:if>

<div class="info-title container-fluid">
	<div class="col-xs-3"><img src="${sysPath}/${r.headImg}"></div>
	<div class="col-xs-9">
		<h3>${r.r.name}</h3>
		<h5><c:if test="${r.r.sex eq 0}">女</c:if><c:if test="${r.r.sex eq 1}">男</c:if> ${r.day(r.r.birthday)}</h5>
		<h5>现住：${r.address}</h5>
		<h5>${r.r.email}</h5>
		<button type="button" class="btn btn-default" onclick="window.open('video?id=${r.r.uid}','_blank')"><span class="glyphicon glyphicon-film"></span> 视频简历</button>
	</div>
</div>

<div class="cpanel-head">我的意向</div>
<div class="container-fluid cpanel-body ctabled">
	<div class="col-xs-6">工作性质：${r.property}</div>
	<div class="col-xs-6">期望地区：${r.city}</div>
	<div class="col-xs-6">期望职位：${r.jobType}</div>
	<div class="col-xs-6">期望月薪：${r.r.salaryBegin}-${r.r.salaryEnd}</div>
	<div class="col-xs-6">期望行业：${r.trade}</div>
	<div class="col-xs-6">上岗时间：<c:if test="${r.r.arrivalTime eq 0}">立即</c:if><c:if test="${r.r.arrivalTime > 0}">${r.r.arrivalTime}个月</c:if></div>
</div>

<div class="cpanel-head">自我介绍</div>
<div class="container-fluid cpanel-body">
	<c:forEach items="${r.introductionLabels}" var="obj">
		<span class="clabel">${obj}</span>	
	</c:forEach>
	<div style="margin-top:10px;">
		${r.r.selfIntroduction}
	</div>
</div>

<div class="cpanel-head">工作经验</div>
<div class="container-fluid cpanel-body remove-hr">
	<c:forEach items="${r.experience}" var="obj">
	<div strong mb10>${r.day(obj.exp.startDate)} 至  ${r.day(obj.exp.endDate)}</div>
	<div strong mb10>${obj.exp.companyName}<span style="margin-left:50px;">
	<c:if test="${obj.exp.companySize eq 0}">10人以下</c:if>
	<c:if test="${obj.exp.companySize eq 1}">10-50人</c:if>
	<c:if test="${obj.exp.companySize eq 2}">50-200人</c:if>
	<c:if test="${obj.exp.companySize eq 3}">200-500人</c:if>
	<c:if test="${obj.exp.companySize eq 4}">500-1000人</c:if>
	<c:if test="${obj.exp.companySize eq 5}">5000以上</c:if></div>
	<div>${obj.trade}</div>
	
	<div style="margin-top:10px;"></div>
	<c:forEach items="${obj.labels}" var="label">
		<span class="clabel">${label}</span>	
	</c:forEach>
	
	<div style="margin-top:10px;">
		${obj.exp.jobContent}
	</div>
	<hr>
	</c:forEach>
</div>

<div class="cpanel-head">学历与培训</div>
<div class="container-fluid cpanel-body remove-hr">
	<c:forEach items="${r.education}" var="obj">
	<div strong mb10>${r.day(obj.edu.startDate)} 至  ${r.day(obj.edu.endDate)}</div>
	<div strong mb10>${obj.edu.schoolName}<span style="margin-left:50px;">${obj.education}</div>
	<div>${obj.edu.specialty}</div>
	
	<div style="margin-top:10px;">
		${obj.edu.content}
	</div>
	<hr>
	</c:forEach>
</div>

<div class="cpanel-head">语言能力</div>
<div class="container-fluid cpanel-body">
	<div>精通：${r.lang(r.r.languageProfessional)}</div>
	<div>熟练：${r.lang(r.r.languageGood)}</div>
	<div>一般：${r.lang(r.r.languageNormal)}</div>
</div>

<script>$(".remove-hr hr:last-child").remove()</script>