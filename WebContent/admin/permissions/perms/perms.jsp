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
	<div class="row studentRow">
		<div class="col-xs-3">
			<div id="permsTree" style="height: 400px;overflow: auto;"></div>
		</div>
		<div class="col-xs-9">

			<div class="row">
				    <div class="col-xs-12">
				        <div class="btn-toolbar" role="toolbar" aria-label="...">
				            <div class="btn-group" role="group" aria-label="...">
					                <button type="button" class="btn btn-primary perms_add">新增资源</button>
				            </div>
				            
				            <div class="btn-group float-right user-set" role="group" aria-label="...">
				                <button type="button" class="btn btn-default">设置</button>
				                <button type="button" class="btn btn-default">帮助</button>
				            </div>                       
				        </div>
				    </div>
				</div>
		</div>
	</div>
	
	<script>

		function submitStudentForm() {
			refreshFrame("资源管理",str);
		}
	
		$(".studentForm").on("submit",submitStudentForm);
		

	$('.dateInput').datetimepicker({
		format:"yyyy-mm-dd",

	    weekStart: 1,

	    todayBtn:  1,

		autoclose: 1,

		todayHighlight: 1,

		startView: 2,

		forceParse: 0,

	    showMeridian: 1,
	    
	    minView: 2,
	    
	    language: "zh-CN"


	    });
	

	
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
	var nodes = ${nodes};

	//班级树调用
	function randerTree() {
		$('#permsTree').treeview({data: nodes,levels: 1,showCheckbox: true});    
		$('#permsTree').on("nodeExpanded",expandedTree);
	}
	 
	randerTree();
	
	var linkArr = [];
	function getArrIndex($span) {
		var pid = $span.attr("data-pid");
		var $pspan = $("span[data-id="+ pid +"]");
		
		if($pspan.length != 0) {
			 var pnum = $pspan.attr("data-num");
			 linkArr.push(pnum);
			 getArrIndex($pspan);
		}
	}
	
	function emptyTreeSelect(nodes) {
		for(var i=0;i<nodes.length;i++) {
			nodes[i].state.selected = false;
			if(nodes[i].nodes != undefined && nodes[i].nodes.length != 0) {
				emptyTreeSelect(nodes[i].nodes);
			}
		}
	}
	
	
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
					
					randerTree();     
				}
			}
		}) 
	}
	
	
	
	</script>
</body>
</html>