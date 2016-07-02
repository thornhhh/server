<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/web/include/head.jsp"%>
<link href="${sysPath}/css/index.css" rel="stylesheet"/>
<style>
.dashboard{width:100%;}
.dashboard>div{display:inline-block;text-align:center;width:33%;padding:20px;}
.dashboard>div>div[title]{font-size:1.2em;}
.dashboard>div>div[value]{font-size:1.8em;color:orange;}
</style>
</head>
<body>
<div class="dashboard">
<div>
	<div title>求职者用户总数(昨日增加)：</div>
	<div value>${yesterdayUserCount}</div>
</div>

<div>
	<div title>招聘者用户总数(昨日增加)：</div>
	<div value>${yesterdayHrCount}</div>
</div>

<div>
	<div title>职位总数：</div>
	<div value>${positionCount}</div>
</div>

<div>
	<div title>简历总数：</div>
	<div value>${resumeCount}</div>
</div>
</div>
</body>
</html>