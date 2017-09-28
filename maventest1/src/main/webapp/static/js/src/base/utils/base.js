/**
 * 默认渲染到page_wrapper
 */
var loadUrl = function(url){
	
	//清空主体
	$("#content").empty();
	//加载对应的页面
	$.ajax({
		type : "get",
		url : url,
		cache : false,
		dataType : "html",
		success : function(data) {
			//进行渲染
			$("#content").html(data);
		},
		error : function(e) {
			$("#content").html("<div class='row'><h1>此路径不存在："+url.substring(url.indexOf("u=")+2)+"</h1></div>");
		}
	});
}


/**
 * 自定义渲染到指定DIV上
 */
var loadUrl2Div = function(url,divId){
	
	//清空主体
	$("#"+divId).empty();
	//加载对应的页面
	$.ajax({
		type : "get",
		url : url,
		cache : false,
		dataType : "html",
		success : function(data) {
			//进行渲染
			$("#"+divId).html(data);
		},
		error : function(e) {
			$("#"+divId).html("<div class='row'><h1>此路径不存在："+url.substring(url.indexOf("u=")+2)+"</h1></div>");
		}
	});
}

/**
 * ajax全局成功拦截
 */
$(document).ajaxSuccess(function(event,xhr,options){
	if(xhr.getResponseHeader("nologin")=="true"){
		var data = JSON.parse(xhr.responseText.replace(/\\/g, "\\\\"));
		layer.msg("登录超时",{time:2000,ico:2},function(){
			window.location.href= data.loginUrl;
		})
	}
});

/**
 * 鉴权方法
 */
var permissions = function(){
	//HRY_permissions从base.ftl中取出
	var HRY_permissions_array = HRY_permissions.split(",")
	var elements = $("[permissions]");
	if(elements.length>0){
		for(var i = 0 ; i < elements.length ; i++){
			var obj = $(elements[i]);
			var key = obj.attr("permissions");
			if(HRY_permissions_array.indexOf(key)==-1){
				obj.remove();
			}
		}
	}
}


define(function(require,exports,module){
	module.exports = {
		loadUrl : loadUrl
	}
	
})


var guid = function() {
    function S4() {
       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}

//定义定时器全局变量
var pageTimer = {} ; 

var clearPageTimer = function(){
	try{
		//全部清除方法
		for(var each in pageTimer){
			window.clearInterval(pageTimer[each]);
		}
	}catch(e){
		
	}
}

	
