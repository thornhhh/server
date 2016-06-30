$("td[more]").click(function(){
	var tr = $(this).parent();
	var dialogType = tr.attr("type");
	var id = tr.attr("db-id");
	var newWindow = tr.attr("window") != undefined;
	
	if(!newWindow){
		$(".modal-body").empty();
		$(".modal").modal(true);
		$.ajax({url:dialogType+"?id="+id,async:true,success:function(e){
			$(".modal-body").html(e);
		}});
	}else{
		location.href = dialogType + "?id=" + id;
	}
});

$(".dropdown-menu *").click(function(event){
	event.stopPropagation();
});
$("input[type='checkbox']").change(function(){
	$(this).val($(this).prop("checked"));
}).change();

$(".video-list").click(function(){
	$(".modal-body").empty();
	var vid = $(this).attr("vid");
	$(".modal").modal(true);
	$.ajax({url:"info?id="+vid,async:true,success:function(e){
		$(".modal-body").html(e);
	}});
});



$("td[report]").each(function(){
	if($(this).text() == "0 ") return $(this).attr("right",null).find("span").remove();
	$(this).click(function(){
		var hrid = $(this).attr("hrid");
		var uid = $(this).attr("uid");
		
		$(".modal-body").empty();
		$(".modal").modal(true);
		$.ajax({url:"../report/list?list=true&"+(hrid == undefined ? "" : "hrid="+hrid)+(uid == undefined ? "" : "uid="+uid),async:true,success:function(e){
			$(".modal-body").html(e);
		}});
	});
});

$("button[switch]").click(function(){
	if(confirm("确定要进行操作么？"))
		$.ajax({url:"switch?id="+$(this).attr("oid"),success:function(){
			location.href = location.href;
		}});
});

$("button[message]").click(function(){
	var type =$(this).attr("message");
	var id = $(this).parents("tr").attr("db-id");
	parent.open("../lib/webim/index.jsp?id="+type+id,"_blank");
});
