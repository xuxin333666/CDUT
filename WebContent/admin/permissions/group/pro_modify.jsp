<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<form class="form-horizontal">
			 <div class="form-group">
				    <div class="col-sm-6">
				 	 <label for="group_id" class="control-label">班级编号：</label>
				      <input type="text" class="form-control" name="id" id="group_id" value="${group.id }" readonly disabled>
				    </div>
				    <div class="col-sm-6">
				    <label for="group_name" class="control-label">班级名称：</label>
				    <input type="text" class="form-control" name="name" id="group_name" value="${group.name}" disabled>
				    </div>
			  </div>
			  
			  <div class="form-group">
				    <div class="col-sm-6">
				 	<label for="group_date" class="control-label">建班日期：</label>
				      <input type="text" class="form-control group_date span2" autocomplete="off" name="date" size="16" id="group_date" value="${group.date }" readonly disabled>
				    </div>
				    <div class="col-sm-6">
				    <label for="group_groupManager" class="control-label">班主任：</label>
				      <input type="text" class="form-control" name="groupManager" id="group_groupManager" value="${group.groupManager }" disabled>
				    </div>
			  </div>	
			  
			  <div class="form-group">
				    <div class="col-sm-6">
					 	 <label for="group_maleCount" class="control-label">男生人数：</label>
					      <input type="text" class="form-control" name="maleCount" id="group_maleCount" value="${group.maleCount }" disabled>
				    </div>
				    <div class="col-sm-6">
					   <label for="group_femaleCount" class="control-label">女生人数：</label>
					   <input type="text" class="form-control" name="femaleCount" id="group_femaleCount" value="${group.femaleCount }" disabled>
				    </div>
			   </div>
				    <input type="hidden" name="command" value="${command }" disabled/>
				    <input type="hidden" name="pid" value="${group.professional.id }" disabled/>
		</form>
	</body>
	
	 <shiro:hasPermission name="group:modify">
	 	<script>
	 		$(".groupModal_modify .form-horizontal").find("input,select,textarea").prop("disabled",false);
	 	</script>
	 </shiro:hasPermission>
	 
	
	<script>
	$('.group_date').datetimepicker({
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
	
	
	</script>
	
</html>