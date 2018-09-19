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
		<div class="col-xs-12">
			<form method="post" action="javascript:void(0)" class="form-inline proForm">
				<label for="proName">专业名称：</label><input type="text" name="name" id="proName" class="form-control" value="${maps.name[0] }"/>
				<label class="myMarginLeft20" for="proMinDate">创建日期：</label><input  class="form-control span2 dateInput" autocomplete="off" size="16" type="text" name="proMinDate" id="proMinDate" value="${maps.proMinDate[0] }" readonly/> —— <input class="form-control span2 dateInput" autocomplete="off" size="16" type="text" name="proMaxDate" id="proMaxDate" value="${maps.proMaxDate[0] }" readonly/>
				<input class="myMarginLeft20 btn btn-primary " type="submit" value="查询"><input type="hidden" name="page" value="1">
			</form>
		</div>
	</div>
	<div class="row">
		    <div class="col-xs-12">
		        <div class="btn-toolbar" role="toolbar" aria-label="...">
		            <div class="btn-group" role="group" aria-label="...">
		            <shiro:hasPermission name="pro:add">
		                <button type="button" class="btn btn-primary professional_add" data-toggle="modal">新增</button>
		            </shiro:hasPermission>
		             <shiro:hasPermission name="pro:modify">
		                <button type="button" class="btn btn-success professional_modify" data-toggle="modal">修改</button>
		            </shiro:hasPermission>
		             <shiro:hasPermission name="pro:del">
		                <button type="button" class="btn btn-info professional_del" data-url="">删除</button>
		            </shiro:hasPermission>
		            </div>
		            <div class="btn-group" role="group" aria-label="...">
		            <shiro:hasPermission name="pro:enable">
						<button type="button" class="btn btn-primary professional_enable" data-toggle="modal">启用</button>
		            </shiro:hasPermission>
		           	<shiro:hasPermission name="pro:disable">
		                <button type="button" class="btn btn-success professional_disable" data-toggle="modal">停用</button>
		            </shiro:hasPermission>
		            </div>
		            <div class="btn-group float-right user-set" role="group" aria-label="...">
		                <button type="button" class="btn btn-default">设置</button>
		                <button type="button" class="btn btn-default">帮助</button>
		            </div>                       
		        </div>
		    </div>
		</div>
		
		<div class="modal fade bs-example-modal-sm professionalModal_modify" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		     <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">专业修改</h4>
		      </div>
		      <div class="modal-body professionalModalBody_modify">
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
		        <shiro:hasPermission name="pro:save">
			        <button type="button" class="btn btn-primary saveChangeBtn">保存修改</button>
		        </shiro:hasPermission>
		      </div>
		    </div>
		  </div>
		</div>
		<div class="row  professionalCt">
		<jsp:include page="../../temp/table.jsp"></jsp:include>
	    </div>

	<script>
	
	function submitProForm() {
		var $inputs = $(".proForm").find("input");
		var str = "?";
		$inputs.each(function(k,v) {
			if($(this).attr("type") != "submit") {
				str += $(this).attr("name") + "=" + $(this).val() + "&";
			}
		})
		refreshFrame("专业管理",str);
	}
	
		$(".proForm").on("submit",submitProForm);
		
		function refreshTable(page) {
			$(".proForm").find("input[type=hidden]").val(page);
			submitProForm();
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


	$(".professional_add").on("click",function() {
		$('.professionalModal_modify').modal();
		$(".professionalModalBody_modify").load("permissions/professional/pro_add");
	})
	
	//修改数据方法
	

	$(".professional_modify").on("click",function() {
		registTableModifySelect($(".professionalCt"),function(id) {
			$('.professionalModal_modify').modal();
			$(".professionalModalBody_modify").load("permissions/professional/pro_modify?id=" + id);
		});
	})
	
	$(".professional_del").on("click",function() {
		var idArr = select2Arr(".professionalCt .selectItem");
		if(idArr.length == 0) {
			alert("请选择至少一个行内容再删除");
			return;
		}
		if(confirm("是否确定删除？")) {
			var data = JSON.stringify(idArr);
			$.ajax({
				type: "post",
				url: "permissions/professional/dels",
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
		$(".professionalModalBody_modify").find("input,select").each(function(i,v){
			if($(this).attr("type") != "hidden") {
			data[$(this).attr("name")] = $(this).val();
			} else {
				command =  $(this).val();
			}
		})
		data = JSON.stringify(data);
		var obj = {command:command,data:data};
		$.ajax({
			type: "post",
			url: "permissions/professional/save",
			data: obj,
			dataType: "json",
			success: function(msg){
				if(msg != 0) {
					$('.professionalModal_modify').modal('toggle');
					setTimeout(function() {
						refreshTable($(".currentPage").attr("data-currentPage"));
					},300);
				}else {
					alert("修改保存失败");
				}
			}
		});
	})
	
	
	$(".professional_enable").on("click",function() {
		var idArr = select2Arr(".professionalCt .selectItem");
		if(idArr.length == 0) {
			alert("请选择至少一个行内容再启用");
			return;
		}
		if(confirm("请确认要启用该专业?")) {
			var data = JSON.stringify(idArr);
			$.ajax({
				type: "post",
				url: "permissions/professional/enable",
				data: {data:data},
				dataType: "json",
				success: function(msg){
					if(msg != 0) {
						refreshTable($(".currentPage").attr("data-currentPage"));
					}else {
						alert("专业启用失败");
					}
				}
			});
		}
	})
	
	$(".professional_disable").on("click",function() {
		var idArr = select2Arr(".professionalCt .selectItem");
		if(idArr.length == 0) {
			alert("请选择至少一个行内容再停用");
			return;
		}
		if(confirm("请确认要停用该专业?若还存在班级使用该专业，将不能停用!")) {
			var data = JSON.stringify(idArr);
			$.ajax({
				type: "post",
				url: "permissions/professional/disable",
				data: {data:data},
				success: function(msg){
					alert(msg);
					refreshTable($(".currentPage").attr("data-currentPage"));
				}
			});
		}
	})
	
	//双击表格调用修改表格方法
	registTableModify($(".professionalCt"),function(id) {
		$('.professionalModal_modify').modal();
		$(".professionalModalBody_modify").load("permissions/professional/pro_modify?id=" + id);
	});
	
	
	</script>
</body>
</html>