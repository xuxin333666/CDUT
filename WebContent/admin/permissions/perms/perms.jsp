<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

</head>
<body>
	<div class="row permsRow">
		<div class="col-xs-3">
			<div id="permsTree" style="height: 400px;overflow: auto;"></div>
		</div>
		<div class="col-xs-9">

			<div class="row proModifyCt">
				
			</div>

		</div>
	</div>
	
	<script>

		function submitStudentForm() {
			
			refreshFrame("资源管理",str);
		}
	
		$(".studentForm").on("submit",submitStudentForm);
		
	
	//新建
	$(".student_add").on("click",function() {
		
		var gid = $(".studentForm").find("input[name=gid]").val();
		if(gid === "") {
			alert("请选择一个班级再添加学生");
			return;
		}
		modifyChange();
		$(".studentRow .modifyBody").load("permissions/student/pro_add?gid=" + gid);
	});
	
	//修改方法
	function modifyStudent(id) {
		modifyChange();
		$(".studentRow .modifyBody").load("permissions/student/pro_modify?id=" + id);
	}
	
	//普通修改方法调用
	$(".student_modify").on("click",function() {
		registTableModifySelect($(".studentCt"),modifyStudent);
	});
	
	
	$(".saveChangeBtn").on("click",function(){
		var id = $(".modifyBody").find("input[name=id]").val();
		if(id === "") {
			alert("学号不能为空！");
			return;
		}
		var data = {};
		var command = "";
		var pid = "";
		$(".studentRow .modifyBody").find("input,select").each(function(i,v){
			if($(this).attr("type") != "hidden") {
			data[$(this).attr("name")] = $(this).val();
			} else if($(this).attr("name") === "command") {
				command =  $(this).val();
			} else if($(this).attr("name") === "gid") {
				gid =  $(this).val();
			}
		})
		var specialtyValue = CKEDITOR.instances.specialty.getData();
		data.specialty = specialtyValue;
		data = JSON.stringify(data);
		var obj = {command:command,gid:gid,data:data};
		$.ajax({
			type: "post",
			url: "permissions/student/save",
			data: obj,
			success: function(msg){
				if(msg == 1) {
					modifyChange();
					refreshTable($(".currentPage").attr("data-currentPage"));
				}else {
					alert("修改保存失败," + msg);
				}
			}
		});
	})
	
	//更新修改框
	function updateProModify(url) {
		$(".permsRow .proModifyCt").load(url);
	}
	
	
	
	//树的全局变量
	var nodes = ${nodes};
	
	//渲染树
	randerTree($('#permsTree'),expandedTree,selectedTree);
	//展开树执行该方法
	function expandedTree(event, node) {
		var $span = $(event.target).find("li[data-nodeId="+node.nodeId+"]").find("span:last");
		linkArr = [$span.attr("data-num")];
		emptyTreeSelect(nodes);
		getArrIndex($span);
		$.ajax({
			type: "get",
			url: "permissions/perms/getSubTree",
			data: {pid:$span.attr("data-id")},
			dataType: "json",
			success: function(msg) {
				if(msg != null) {
					var mynode = nodes[linkArr[linkArr.length-1]];
					for(var i=linkArr.length-2;i>=0;i--) {
						mynode = mynode.nodes[linkArr[i]];
					}
					mynode.nodes = msg;
					mynode.state = node.state;
					mynode.state.selected = true;
					
					randerTree($('#permsTree'),expandedTree,selectedTree);
					
				}
			}
		}) 
	}
	//选择树执行该方法
	function selectedTree(event, node) {
		var $span = $(event.target).find("li[data-nodeId="+node.nodeId+"]").find("span:last");
		var id = $span.attr("data-id");
		var url;
		if(id != undefined && id != "") {
			url = "permissions/perms/pro_modify?id=" + id;
		} else {
			url = "permissions/perms/pro_add";
		}
		updateProModify(url);
	}
	</script>
</body>
</html>