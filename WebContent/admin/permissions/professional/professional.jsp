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
			<form method="post" action="javascript: void(0)" class="proForm">
				<label for="proName">专业名称：</label><input type="text" name="name" id="proName" value="${maps.name[0] }"/>
				<label class="myMarginLeft20" for="proMinDate">创建日期：</label><input type="text" name="proMinDate" id="proMinDate" value="${maps.proMinDate[0] }"/> —— <input type="text" name="proMaxDate" id="proMaxDate" value="${maps.proMaxDate[0] }"/>
				<input class="myMarginLeft20 btn btn-primary " type="submit" value="查询"><input type="hidden" name="page" value="1">
			</form>
		</div>
	</div>
	<div class="row professionalCt">
		    <div class="col-xs-12">
		        <div class="btn-toolbar" role="toolbar" aria-label="...">
		            <div class="btn-group" role="group" aria-label="...">
		                <button type="button" class="btn btn-primary professional_add" data-toggle="modal">新增</button>
		                <button type="button" class="btn btn-success professional_modify" data-toggle="modal">修改</button>
		                <button type="button" class="btn btn-info professional_del" data-url="">删除</button>
		            </div>
		            <div class="btn-group" role="group" aria-label="...">
						<button type="button" class="btn btn-primary professional_enable" data-toggle="modal">启用</button>
		                <button type="button" class="btn btn-success professional_disable" data-toggle="modal">停用</button>
		            </div>
		            <div class="btn-group float-right user-set" role="group" aria-label="...">
		                <button type="button" class="btn btn-default">设置</button>
		                <button type="button" class="btn btn-default">帮助</button>
		            </div>                       
		        </div>
		    </div>
		</div>
		
		<div class="modal fade bs-example-modal-sm professionalModal_modify" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
		  <div class="modal-dialog modal-sm" role="document">
		    <div class="modal-content">
		     <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
		      </div>
		      <div class="modal-body professionalModalBody_modify" data-url="">
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
		        <button type="button" class="btn btn-primary saveChangeBtn">保存修改</button>
		      </div>
		    </div>
		  </div>
		</div>
		<div class="row">
		<jsp:include page="../../temp/table.jsp"></jsp:include>
	    </div>
	
	
	<script>
	
	$(".proForm").on("submit",function() {
		var $inputs = $(this).find("input");
		event.preventDefault();
		var str = "?";
		$inputs.each(function(k,v) {
			if($(this).attr("type") != "submit") {
				str += $(this).attr("name") + "=" + $(this).val() + "&";
			}
		})
		$('.tab-content>div[data-name="专业管理"]').load($('.tab-content>div[data-name="专业管理"]').attr("data-url") + str);
	})
	
	function refreshTable(page) {
		$(".proForm").find("input[type=hidden]").val(page);
		$(".proForm")[0].submit();
	}

	$(".professional_add").on("click",function() {
		$('.professionalModal_modify').modal();
		$(".professionalModalBody_modify").load($(".professionalModalBody_modify").attr("data-url"));
	})

	$(".professional_modify").on("click",function() {
		var flag = true;
		$(".professionalCt .selectItem").each(function(){
			if(this.checked) {
				var id = $(this).val();
				$('.professionalModal_modify').modal();
				$(".professionalModalBody_modify").load($(".modifyStudentModalBody").attr("data-url") + "?id=" + id);
				flag = false;
				return;
			};
		});
		if(flag) {
			alert("请选择一行内容进行修改");
		}
		
		
	})
	
	$(".professional_del").on("click",function() {
		var idArr = [];
		$(".professionalCt .selectItem").each(function(){
			if(this.checked) {
				idArr.push($(this).val());
			};
		});
		if(idArr.length == 0) {
			alert("请选择至少一个行内容再删除");
			return;
		}
		if(confirm("是否确定删除？")) {
			var data = JSON.stringify(idArr);
			$.ajax({
				type: "post",
				url: "",
				data: data,
				dataType: "JSON",
				success: function(msg) {
					if(msg.status != 0 ) {
						refreshTable($(".studentCurrentPage").attr("data-currentPage"));
					}
				}
			})
		}
	})
	
	/*
	$(".saveChangeBtn").on("click",function(){
		var filed = ["id","name","sex","age","score"];
		var data = {id:"",name:"",sex:"",age:"",score:"",group:group};
		$(".modifyStudentModalBody").find("input").each(function(i,v){
			data[filed[i]] = $(this).val();
		})
		var obj = {command:"addSt",data:JSON.stringify(data)};
		
		$.ajax({
			type: "get",
			url: "/TestWeb/st",
			data: obj,
			dataType: "json",
			success: function(msg){
				if(msg.status != 0) {
					$('.modifyStudentModal').modal('toggle');
					refreshSt_jspTable($(".studentCurrentPage").attr("data-currentPage"));
				}else {
					alert("修改保存失败");
				}
			}
		});
	})
	*/
	
	</script>
</body>
</html>