<%@ page language="java" pageEncoding="UTF-8"%>
<style>
.page{
	float:right;
	margin-top:27px;
	border-left:1px solid #dfdfdf;	
	margin-bottom:20px;
}
.page span{
	padding:6px 10px;
	color:#484c55;
	border:1px solid #dfdfdf;
	float:left;
	border-right:1px solid #dfdfdf;
	border-left:0px;
	cursor:pointer;
	background-color:white!important;
	font-size:13px!important;
}
.page span:hover{
	background-color: #EAEAEA!important;
}
.page .prev{
	background-repeat: no-repeat;
	background-position: 10% 9px;
}
.page .next{
	background-repeat: no-repeat;
	background-position: 90% 9px;
}
.page span.current{
	background-color:#8D8D8E!important;
	color:white;
}
.page span.disabled{
	background-color: #EAEAEA!important;
	cursor: default!important;
	color:#848484!important;
}
</style>
<script>
$(function(){
	$(".page").each(function(){
		genpage(this);
	});
});

function genpage(dom){
	$(dom).empty();
	var current = parseInt($(dom).attr("currentpage") == "" ? 1 : $(dom).attr("currentpage"));
	var max = parseInt($(dom).attr("maxPage"));
	if(max == 0) return $(dom).remove();
	
	$("<span class='prev' pageTo='"+(current - 1)+"'><b class='glyphicon glyphicon-chevron-left'></b> 上一页</span>").appendTo($(dom)).addClass(current == 1 ? "disabled" : "enabled");
	
	if(current - 2 > 1) {
		$("<span pageTo='1'>1</span>").appendTo($(dom));
		$("<span >...</span>").appendTo($(dom));
	}
	var begin = current - 2 > 1 ? current - 2 : 1; 
	var end = current + 2 < max ? current + 2 : max;
	
	if(end - begin < 5 && max > 5){
		if(end - 2 < current)
			begin -= current - max + 2;
		else if(begin + 2 > current)
			end += begin - current + 2; 
	}
	
	for(var i = begin; i <= end; i++){
		$("<span pageTo='"+i+"'>"+i+"</span>").appendTo($(dom)).addClass(current == i ? "current" : "enabled");
	}
	
	if(current + 2 < max) {
		$("<span>...</span>").appendTo($(dom));
		$("<span pageTo='"+max+"'>"+max+"</span>").appendTo($(dom));
	}
	
	$("<span class='next' pageTo='"+(current + 1)+"'>下一页 <b class='glyphicon glyphicon-chevron-right'></b></span>").appendTo($(dom)).addClass(current == max ? "disabled" : "enabled");
	
	$(dom).find("span[pageTo]:not(.disabled)").click(function(){
		location.href = updateURLParameter("page",$(this).attr("pageTo"))
	});
}

/**
 * 添加url参数
 */
function updateURLParameter(param, paramVal){
	var url = location.href;
    var newAdditionalURL = "";
    var tempArray = url.split("?");
    var baseURL = tempArray[0];
    var additionalURL = tempArray[1];
    var temp = "";
    if (additionalURL) {
        tempArray = additionalURL.split("&");
        for (i=0; i<tempArray.length; i++){
            if(tempArray[i].split('=')[0] != param){
                newAdditionalURL += temp + tempArray[i];
                temp = "&";
            }
        }
    }

    var rows_txt = temp + "" + param + "=" + paramVal;
    return baseURL + "?" + newAdditionalURL + rows_txt;
}

</script>
