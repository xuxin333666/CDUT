<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div class="col-xs-1"></div>
				<div class="col-xs-10">
					<div class="row">
						    <div class="col-xs-12">
						        <div class="btn-toolbar" role="toolbar" aria-label="...">
						            <div class="btn-group" role="group" aria-label="...">
							             <button type="button" class="btn btn-primary perms_modify">修改</button>
						            </div>
						            <div class="btn-group float-right user-set" role="group" aria-label="...">
						                <button type="button" class="btn btn-default">设置</button>
						                <button type="button" class="btn btn-default">帮助</button>
						            </div>                       
						        </div>
						    </div>
						</div>
					<div class="row">
						<form class="form-horizontal">
							 <div class="form-group">
								    <div class="col-sm-6">
								 	 <label for="perms_id" class="control-label">编号：</label>
								      <input type="text" class="form-control" name="id" id="perms_id" value="${perms.id }" readonly disabled>
								    </div>
								    <div class="col-sm-6">
								    <label for="perms_name" class="control-label">资源名称：</label>
								   	<input type="text" class="form-control" name="name" id="perms_name" value="${perms.name }" disabled>
								    </div>
							  </div>
							  
							  <div class="form-group">
								    <div class="col-sm-6">
								 	 <label for="perms_iconName" class="control-label">资源图标 ：</label>
								      <input type="text" class="form-control" name="iconName" id="perms_iconName" value="${perms.iconName}" disabled>
								    </div>
								    <div class="col-sm-6">
								    <label for="perms_type" class="control-label">资源类型：</label>
								    <select class="form-control" name="type" id="perms_type" disabled>
								    	<option value="01">目录</option>
								    	<option value="02">菜单</option>
								    	<option value="03">按钮</option>
								    </select>
								    </div>
							  </div>	
							  
							  <div class="form-group">
								    <div class="col-sm-6">
								 	 <label for="perms_date" class="control-label">创建日期：</label>
								      <input type="text" class="form-control perms_date span2" autocomplete="off" name="date" size="16" id="perms_date" value="${perms.date }" readonly disabled>
								    </div>
								    <div class="col-sm-6">
								    <label for="perms_url" class="control-label">资源url：</label>
								    <input type="text" class="form-control" name="dataUrl" id="perms_url" value="${perms.dataUrl}" disabled>
								    </div>
							  </div>	
							  
							  <div class="form-group">
								    <div class="col-sm-6">
								 	 <label for="perms_tagName" class="control-label">资源权限名称：</label>
								      <input type="text" class="form-control" name="tagName" id="perms_tagName" value="${perms.tagName }" disabled>
								    </div>
							  </div>	
								<input type="hidden" name="command" value="${command }" disabled/>
						</form>
					</div>
					<button type="button" class="btn btn-info saveChangeBtn">保存修改</button>
				</div>
				<div class="col-xs-1"></div>

		
	
	
	<script>
	$("#perms_type").val("${perms.type }");
	
	
	$('.perms_date').datetimepicker({
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
	
	$(".proModifyCt .perms_modify").on("click",function() {
		$(".proModifyCt .form-horizontal").find("input,select,textarea").prop("disabled",false);
	})
	
	$(".proModifyCt .saveChangeBtn").on("click",function() {
		var obj = form2obj($(".proModifyCt .form-horizontal"));
		obj = JSON.stringify(obj);
		var data = {command:$(".proModifyCt .form-horizontal").find("input[name=command]").val(),data:obj};
		console.log(data);
		$.ajax({
			type: "post",
			url: "permissions/perms/save",
			data: data,
			success: function(msg){
				if(msg == 1) {
					$(".proModifyCt .form-horizontal").find("input,select,textarea").prop("disabled",false);
				}else {
					alert("修改保存失败," + msg);
				}
			}
		});
	})
	
	</script>
