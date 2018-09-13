<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<form method="post" action="javascript:void(0)" class="groupForm">
				<label for="groupName">班级名称：</label><input type="text" name="gname" id="groupName" value="${maps.gname[0] }"/>
				<label class="myMarginLeft20" for="groupMinDate">创班日期：</label><input  class="span2 dateInput" size="16" type="text" name="groupMinDate" id="groupMinDate" value="${maps.groupMinDate[0] }"/> —— <input class="span2 dateInput" size="16" type="text" name="groupMaxDate" id="groupMaxDate" value="${maps.groupMaxDate[0] }"/>
				<label class="myMarginLeft20" for="groupManager">班主任：</label><input type="text" name="group_manager" id="groupManager" value="${maps.group_manager[0] }"/>
				<input class="myMarginLeft20 btn btn-primary " type="submit" value="查询"><input type="hidden" name="page" value="1">
			</form>
		</div>
	</div>
	<div class="row">
		    <div class="col-xs-12">
		        <div class="btn-toolbar" role="toolbar" aria-label="...">
		            <div class="btn-group" role="group" aria-label="...">
		                <button type="button" class="btn btn-primary group_add" data-toggle="modal">新增</button>
		                <button type="button" class="btn btn-success group_modify" data-toggle="modal">修改</button>
		                <button type="button" class="btn btn-info group_del" data-url="">删除</button>
		            </div>
		            <div class="btn-group" role="group" aria-label="...">
						<button type="button" class="btn btn-primary group_enable" data-toggle="modal">启用</button>
		                <button type="button" class="btn btn-success group_disable" data-toggle="modal">停用</button>
		            </div>
		            <div class="btn-group float-right user-set" role="group" aria-label="...">
		                <button type="button" class="btn btn-default">设置</button>
		                <button type="button" class="btn btn-default">帮助</button>
		            </div>                       
		        </div>
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
		<div class="row  groupCt">
		<jsp:include page="../../temp/table.jsp"></jsp:include>
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
			$(".groupForm").find("input[type=hidden]").val(page);
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

	    showMeridian: 1

	    });


	$(".group_add").on("click",function() {
		$('.groupModal_modify').modal();
		$(".groupModalBody_modify").load("permissions/group/pro_add");
	})

	$(".group_modify").on("click",function() {
		var flag = true;
		$(".groupCt").find(".selectItem").each(function(){
			if(this.checked) {
				var id = $(this).val();
				$('.groupModal_modify').modal();
				$(".groupModalBody_modify").load("permissions/group/pro_modify?id=" + id);
				flag = false;
				return;
			};
		});
		if(flag) {
			alert("请选择一行内容进行修改");
		}
		
		
	})
	
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
				dataType: "JSON",
				success: function(msg) {
					if(msg != 0 ) {
						refreshTable($(".currentPage").attr("data-currentPage"));
					} else {
						alert("未知原因，删除失败");
					}
				}
			})
		}
	})
	
	$(".saveChangeBtn").on("click",function(){
		var data = {};
		var command = "";
		$(".groupModalBody_modify").find("input,select").each(function(i,v){
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

	
	</script>
</body>
</html>