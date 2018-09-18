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
	<div class="row">
		<div class="col-xs-2">
			<div class="panel-group" id="proTree" style="height: 400px;overflow: auto;">
			    <div class="panel panel-info">
			        <div class="panel-heading">
			            <h4 class="panel-title ">
		                	<a data-toggle="collapse" data-parent="#proTree" href="#proList" onclick="proTreeClick('')">
			            	专业
		                	</a>
		            	</h4>
			        </div>
			        <div id="proList" class="panel-collapse collapse in">
				        <c:forEach items="${pros }" var="pro">
				            <div class="panel-body" data-val="${pro.id }" onclick="proTreeClick('${pro.id }')">${pro.name }</div>
				        </c:forEach>
			        </div>
			    </div>
			</div>
		</div>
		<div class="col-xs-10">
			<div class="row">
				<div class="col-xs-12">
					<form method="post" action="javascript:void(0)" class="form-inline groupForm">
						<label for="groupName">班级名称：</label><input type="text" class="form-control" style="width:120px" name="gname" id="groupName" value="${maps.gname[0] }"/>
						<label class="myMarginLeft20" for="groupMinDate">创班日期：</label><input  class="form-control span2 dateInput" autocomplete="off" style="width:120px" size="16" type="text" name="groupMinDate" id="groupMinDate" value="${maps.groupMinDate[0] }" readonly/> —— <input class="form-control span2 dateInput" autocomplete="off" style="width:120px" size="16" type="text" name="groupMaxDate" id="groupMaxDate" value="${maps.groupMaxDate[0] }" readonly/>
						<label class="myMarginLeft20" for="groupManager">班主任：</label><input type="text" class="form-control" style="width:120px" name="group_manager" id="groupManager" value="${maps.group_manager[0] }"/>
						<input class="myMarginLeft20 btn btn-primary " type="submit" value="查询"><input type="hidden" name="page" value="1"><input type="hidden" name="pid" value="${maps.pid[0] }">
					</form>
				</div>
			</div>
			<div class="row">
				    <div class="col-xs-12">
				        <div class="btn-toolbar" role="toolbar" aria-label="...">
				            <div class="btn-group" role="group" aria-label="...">
				                <button type="button" class="btn btn-primary group_add" data-toggle="modal">新增</button>
				                <button type="button" class="btn btn-success group_modify" data-toggle="modal">修改</button>
				                <button type="button" class="btn btn-info group_del">删除</button>
				            </div>
				            <div class="btn-group" role="group" aria-label="...">
				            <shiro:hasPermission name="group:enable">
								<button type="button" class="btn btn-primary group_enable" data-toggle="modal">启用</button>
				            </shiro:hasPermission>
				             <shiro:hasPermission name="group:disable">
				                <button type="button" class="btn btn-success group_disable" data-toggle="modal">停用</button>
				             </shiro:hasPermission>
				            </div>
				            <div class="btn-group float-right user-set" role="group" aria-label="...">
				                <button type="button" class="btn btn-default">设置</button>
				                <button type="button" class="btn btn-default">帮助</button>
				            </div>                       
				        </div>
				    </div>
				</div>
			<div class="row  groupCt">
			<jsp:include page="../../temp/table.jsp"></jsp:include>
		    </div>
		</div>
		<div class="modal fade bs-example-modal-sm groupModal_modify" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			     <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">班级修改</h4>
			      </div>
			      <div class="modal-body groupModalBody_modify">
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
			        <button type="button" class="btn btn-primary saveChangeBtn">保存修改</button>
			      </div>
			    </div>
			  </div>
			</div>
	</div>
	<script>

	function submitGroupForm() {
		var $inputs = $(".groupForm").find("input");
		var str = "?";
		$inputs.each(function(k,v) {
			if($(this).attr("type") != "submit") {
				str += $(this).attr("name") + "=" + $(this).val() + "&";
			}
		})
		refreshFrame("班级管理",str);
	}
	
		$(".groupForm").on("submit",submitGroupForm);
		
		function refreshTable(page) {
			$(".groupForm").find("input[name=page]").val(page);
			submitGroupForm();
		}
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


	$(".group_add").on("click",function() {
		var pid = $(".groupForm").find("input[name=pid]").val();
		if(pid === "") {
			alert("请选择一个专业再添加班级");
			return;
		}
		$('.groupModal_modify').modal();
		$(".groupModalBody_modify").load("permissions/group/pro_add?pid=" + pid);
	})
	
	//修改方法
	function modifyGroup(id) {
		$('.groupModal_modify').modal();
		$(".groupModalBody_modify").load("permissions/group/pro_modify?id=" + id);
	}
	//普通修改方法调用
	$(".group_modify").on("click",function() {
		registTableModifySelect($(".groupCt"),modifyGroup);
	})
	
	//双击表格调用修改表格方法
	registTableModify($(".groupCt"),modifyGroup);
	
	
	$(".group_del").on("click",function() {
		var idArr = select2Arr(".groupCt .selectItem");
		if(idArr.length == 0) {
			alert("请选择至少一个行内容再删除");
			return;
		}
		if(confirm("是否确定删除？")) {
			var data = JSON.stringify(idArr);
			$.ajax({
				type: "post",
				url: "permissions/group/dels",
				data: {data:data},
				success: function(msg) {
					alert(msg);
					refreshTable($(".currentPage").attr("data-currentPage"));
				}
			})
		}
	})
	
	$(".saveChangeBtn").on("click",function(){
		var data = {};
		var command = "";
		var pid = "";
		$(".groupModalBody_modify").find("input,select").each(function(i,v){
			if($(this).attr("type") != "hidden") {
			data[$(this).attr("name")] = $(this).val();
			} else if($(this).attr("name") === "command") {
				command =  $(this).val();
			} else if($(this).attr("name") === "pid") {
				pid =  $(this).val();
			}
		})
		data = JSON.stringify(data);
		var obj = {command:command,pid:pid,data:data};
		$.ajax({
			type: "post",
			url: "permissions/group/save",
			data: obj,
			dataType: "json",
			success: function(msg){
				if(msg != 0) {
					$('.groupModal_modify').modal('toggle');
					setTimeout(function() {
						refreshTable($(".currentPage").attr("data-currentPage"));
					},300);
				}else {
					alert("修改保存失败");
				}
			}
		});
	})
	
	$(".group_enable").on("click",function() {
		var idArr = select2Arr(".groupCt .selectItem");
		if(idArr.length == 0) {
			alert("请选择至少一个行内容再启用");
			return;
		}
		if(confirm("请确认要启用该班级?")) {
			var data = JSON.stringify(idArr);
			$.ajax({
				type: "post",
				url: "permissions/group/enable",
				data: {data:data},
				dataType: "json",
				success: function(msg){
					if(msg != 0) {
						refreshTable($(".currentPage").attr("data-currentPage"));
					}else {
						alert("班级启用失败");
					}
				}
			});
		}
	})
	
	$(".group_disable").on("click",function() {
		var idArr = select2Arr(".groupCt .selectItem");
		if(idArr.length == 0) {
			alert("请选择至少一个行内容再停用");
			return;
		}
		if(confirm("请确认要停用该班级?若还存在学生使用该班级，将不能停用!")) {
			var data = JSON.stringify(idArr);
			$.ajax({
				type: "post",
				url: "permissions/group/disable",
				data: {data:data},
				success: function(msg){
					alert(msg);
					refreshTable($(".currentPage").attr("data-currentPage"));
				}
			});
		}
	})
	

	
	//专业树点击监听
	function proTreeClick(pid) {
		$(".groupForm").find("input[name=page]").val(1);
		$(".groupForm").find("input[name=pid]").val(pid);
		submitGroupForm();
	}
	
	//专业树高亮显示
	var proId = "${maps.pid[0]}" || "1";
	$("#proList>.panel-body[data-val="+ proId +"]").addClass("text-primary");

	
	</script>
</body>
</html>