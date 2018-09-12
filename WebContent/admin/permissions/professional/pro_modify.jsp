<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
	<body> 
		<form class="form-horizontal">
		<c:if test="${id != null}">
				 <div class="form-group">
					    <div class="col-sm-6">
					 	 <label for="pro_id" class="control-label">编号：</label>
					      <input type="text" class="form-control" id="pro_id" value="${id }" readonly>
					    </div>
					    <div class="col-sm-6">
					    <label for="pro_code" class="control-label">专业代码：</label>
					      <input type="text" class="form-control" id="pro_code">
					    </div>
				  </div>
				  
				  <div class="form-group">
					    <div class="col-sm-6">
					 	 <label for="pro_name" class="control-label">专业名称：</label>
					      <input type="text" class="form-control" id="pro_name">
					    </div>
					    <div class="col-sm-6">
					    <label for="pro_nameEn" class="control-label">专业英文名称：</label>
					      <input type="text" class="form-control" id="pro_nameEn">
					    </div>
				  </div>	
				  
				  <div class="form-group">
					    <div class="col-sm-6">
					 	 <label for="pro_date" class="control-label">创建日期：</label>
					      <input type="text" class="form-control pro_date span2"  size="16" id="pro_date">
					    </div>
					    <div class="col-sm-6">
					    <label for="pro_eductionalSystme" class="control-label">学制：</label>
					      <input type="text" class="form-control" id="pro_eductionalSystme">
					    </div>
				  </div>	
				  
				  <div class="form-group">
					    <div class="col-sm-6">
					 	 <label for="pro_totalScore" class="control-label">总学分：</label>
					      <input type="text" class="form-control" id="pro_totalScore">
					    </div>
					    <div class="col-sm-6">
					    <label for="pro_teatherCount" class="control-label">教师人数：</label>
					      <input type="text" class="form-control" id="pro_teatherCount">
					    </div>
				  </div>	
		 
		</c:if>
		</form>
	</body>
	<script>
	$('.pro_date').datetimepicker({
		format:"yyyy-mm-dd",

	    weekStart: 1,

	    todayBtn:  1,

		autoclose: 1,

		todayHighlight: 1,

		startView: 2,

		forceParse: 0,

	    showMeridian: 1

	    });
	
	</script>
	
</html>