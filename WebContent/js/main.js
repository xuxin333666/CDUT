    
//在线人数显示
$.ajax({
	type: "get",
	url: "getUserOnLineCount",
	dataType: "JSON",
	success: function(msg) {
		if(msg != 0) {
			$(".userCount").html(",当前在线人数为:" + msg + "人");
		}
	}
})



//退出登录
$(".signOut").on("click",function() {
	if(confirm("是否确认退出？")) {
		location = "signOut";
	}
})
		


//默认显示第一标签页
if($('.mainContentTabs a:first').length != 0) {
	$('.mainContentTabs a:first').tab('show')
	$(".tab-content>div:first").load($(".tab-content>div:first").attr("data-url"));
}

//关闭tab标签按钮
$(".mainContentTabs").on("click",function() {
	if("span" === event.target.localName) {
		var name = $(event.target).parents(".tabItem").attr("data-name");
		$("[data-name="+ name +"]").remove();
		$('.mainContentTabs a:last').tab('show');
	};
}).on("mouseover",function() {
	if("span" === event.target.localName) {
		$(event.target).removeClass("glyphicon-remove-circle").addClass("glyphicon-remove-sign");
	}
}).on("mouseout",function() {
	if("span" === event.target.localName) {
		$(event.target).addClass("glyphicon-remove-circle").removeClass("glyphicon-remove-sign");
	}
})


//导航栏bing查询
$(".searchForm").on("submit",function() {
	event.preventDefault();
	var serchCondition = $(".serchConditionCt").val();
	var searchContent = $(".searchInput").val()
	var url = serchCondition + searchContent;
    window.open(url);
})

function refreshTable(name) {
	if(name === "refreshSt_jspTable") {
		refreshSt_jspTable();
	}
}


//折叠控件点击监听
	$(".panel-group").on("click",function() {
		var a = $(event.target);
		if(a.hasClass("panel-body")) {
			 var url = a.attr("data-url");
		     var tabName = a.attr("data-tabName");
		     var searchName = a.attr("data-searchName");
		     if($(`.mainContentTabs>.tabItem[data-name="${tabName}"]`).length == 0) {
			     $(".mainContentTabs").append($(`<li class="tabItem" data-name="${tabName}"><a href="#${tabName}" data-toggle="tab">${tabName}<span class="glyphicon glyphicon-remove-circle closeTab" aria-hidden="true"></span></a></li>`));
			     $(".tab-content").append($(`<div class="tab-pane fade" data-name="${tabName}" data-url="${url}" id="${tabName}">找不到页面</div>`));	    	 
		     }
		     $(`.mainContentTabs>.tabItem[data-name="${tabName}"] a`).tab('show');
		     $(`.tab-content>div[data-name="${tabName}"]`).load($(`.tab-content>div[data-name="${tabName}"]`).attr("data-url"));
		     
		} else {
			a.parents(".panel").siblings().removeClass("panel-primary").addClass("panel-info");
			a.parents(".panel").removeClass("panel-info").addClass("panel-primary");
		}
	})
	
	
	//刷新主要内容框架的方法
	function refreshFrame(name,args) {
		var str = args || "";
		$('.tab-content>div[data-name='+ name +']').load($('.tab-content>div[data-name='+ name +']').attr("data-url") + str);
	}
	
	//tab点击监听
	$(".mainContentTabs").on("click",function() {
		var tabName = $(event.target).parent().attr("data-name");
		refreshFrame(tabName);
	})
	
	//将用户选择的表格行数的val装入数组
	function select2Arr(str) {
		var idArr = [];
		$(str).each(function(){
			if(this.checked) {
				idArr.push($(this).val());
			};
		});
		return idArr
	}


