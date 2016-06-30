<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/web/include/head.jsp"%>
<%@ include file="/WEB-INF/web/include/page.jsp"%>
<link href="${sysPath}/css/list.css" rel="stylesheet"/>
<link href="${sysPath}/css/bootstrap-multiselect.css" rel="stylesheet"/>
<script defer src="${sysPath}/js/bootstrap-multiselect.js"></script>
<script defer src="${sysPath}/js/list.js"></script>
</head>
<body class="content">

<div class="search-bar">
	<form class="form-inline" method="post" action="list">
        <div class="input-group">
          <div class="input-group-addon">真实姓名</div><input type="text" class="form-control" name="name" value="${param.name}">
        </div>
		<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 搜索</button>
		<button type="button" class="btn btn-success create" style="float:right;"><span class="glyphicon glyphicon-plus-sign"></span> 新建</button>
	</form>
</div>

<table class="table table-striped">
	<tr>
		<th>用户名</th>
		<th>真实姓名</th>
		<th>昵称</th>
		<th>创建时间</th>
		<th>用户组</th>
		<th>操作</th>	
	</tr>
	<c:forEach items="${list}" var="obj">
	<tr db-id="${obj.id}" type="positionList" window>
		<td>${obj.username}</td>
		<td>${obj.name}</td>
		<td>${obj.nickname}</td>
		<td>${obj.time(obj.createTime)}</td>
		<td>${obj.roleName}</td>
		<td>
			<button type="button" class="btn btn-default modify"><span class="glyphicon glyphicon-wrench"></span> 修改</button>
			<button type="button" db-id="${obj.id}" class="btn btn-danger" builder="delete"><span class="glyphicon glyphicon-remove"></span> 删除</button>
		</td>
	</tr>
	</c:forEach>
</table>
<div class="page" currentPage="${param.page}" maxPage="${maxPage}"></div>
<div class="modal fade mform">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
			<form class="form" method="post" action="list">
				<input type="hidden" name="eid">
		        <div class="input-group">
		          <div class="input-group-addon">用户名</div><input type="text" class="form-control" name="username">
		        </div>
		        <div class="input-group">
		          <div class="input-group-addon">真实姓名</div><input type="text" class="form-control" name="name">
		        </div>
		        <div class="input-group">
		          <div class="input-group-addon">昵称</div><input type="text" class="form-control" name="nickname">
		        </div>
		        <div class="input-group password">
		          <div class="input-group-addon">密码</div><input type="text" class="form-control" name="password">
		        </div>
		        <div class="input-group">
		          <div class="input-group-addon">用户组</div>
		          <select class="form-control" name="role" multiple="multiple">
		          	<c:forEach items="${role}" var="obj">
		          		<option value="${obj.id}">${obj.name}</option>
		          	</c:forEach>
		          </select>
		        </div>
		    <div style="margin-top:35px;">
				<button type="button" class="btn btn-primary msubmit"><span class="glyphicon glyphicon-ok"></span> 确定</button>
		        <button type="button" class="btn btn-danger resetPassword" append="将要重置密码为：123456" builder="reset" style="float:right;"><span class="glyphicon glyphicon-alert"></span> 重置密码</button>
	        </div>
	        
			</form>
			</div>
		</div>
	</div>
</div>

<script>
$(function(){
	$(".modify").click(function(){
		var id = $(this).parents("tr").attr("db-id"); 
		$.ajax({url:"get?id="+id,dataType:"json",success:function(e){
			$(".modal").modal(true);
			var user = eval("(" + e.user + ")");
			$(".modal input[name='username']").val(user.username);
			$(".modal input[name='name']").val(user.name);
			$(".modal input[name='nickname']").val(user.nickname);
			$(".modal input[name='eid']").val(user.id);
			
			$("select[multiple]").multiselect("destroy");
			
			var arr = user.roleName == undefined ? "" : user.roleName.split(",");
			$("select[name='role']").find("option").attr("selected",null);
			for(var i in arr)
				$("select[name='role']").find("option[value='"+arr[i]+"']").attr("selected","selected");
				
			$("select[multiple]").multiselect();
			
			$(".password").hide();
			$(".resetPassword").show().attr("db-id",id);
		}});
	});
	
	$(".msubmit").click(function(){
		$(this).attr("disabled","disabled");
		var that = $(this);
		$(".modal,.modal-backdrop").hide();
		$.ajax({url:"add",type:"post",data:{
			username:$(".modal input[name='username']").val(),
			name:$(".modal input[name='name']").val(),
			nickname:$(".modal input[name='nickname']").val(),
			id:$(".modal input[name='eid']").val(),
			role:function(){
				var arr = [];
				$(".modal select[name='role']").each(function(){arr.push($(this).val())});
				return arr.join(",");
			}(),
			password:$(".modal input[name='password']").val(),
		},success:function(e){
			if(e == "1") {
				$(".modal,.modal-backdrop").show();
				$(that).attr("disabled",null);
				alert("用户名已存在");
				return;
			}
			alert("操作成功");
			location.href = location.href; 
		}});
	});
	
	$("button.create").click(function(){
		$.ajax({url:"get?id=-1",dataType:"json",success:function(e){
			$(".modal").modal(true);
			$(".modal input").val("");
			$(".password").show();
			$(".resetPassword").hide();
			$("select[multiple]").multiselect("destroy");
			$("select[name='role']").find("option").attr("selected",null);
			$("select[multiple]").multiselect();
		}});
	});
	
	$("button[builder]").click(function(){
		var id = $(this).attr("db-id");
		var url = $(this).attr("builder");
		var append = $(this).attr("append") == undefined ? "" : $(this).attr("append");
		if(confirm("确定要操作么？" + append))
			$.ajax({url:url + "?id="+id,success:function(e){
				location.href = location.href; 
			}});
	});
	
	$("select[multiple]").multiselect();
});

</script>
<style>
.mform div.input-group{margin-bottom:10px;}
.mform div.input-group div.input-group-addon{width:98px;}
.mform .form-control{width:300px;}
.modal-dialog, .modal-content{width:430px!important;}
.modal-body .btn-group button{border-top-left-radius:0px;border-bottom-left-radius:0px;}
</style>

</body>
</html>