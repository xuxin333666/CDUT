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
		<div class="col-xs-2">
			<div id="tree" style="height: 400px;overflow: auto;"></div>
		</div>
		<div class="col-xs-10">
			<div class="row">
				<div class="col-xs-12">
					<form method="post" action="javascript:void(0)" class="form-inline studentForm">
						<div class="form-group">
							<label for="studentName">姓名：</label><input type="text" class="form-control" style="width:120px" name="sname" id="studentName" value="${maps.sname[0] }"/>
							<label class="myMarginLeft20" for="studentId">学号：</label><input type="text" class="form-control" style="width:120px" name="sid" id="studentId" value="${maps.sid[0] }"/>
							<label class="myMarginLeft20" for="stady_status">学籍状态：</label>  
							<select class="myMarginLeft20 form-control" name="stady_status" id="stady_status">
								<option value="">全部</option>
							    <c:forEach items="${selectMap.stadyStatus }" var="status">
							    	<option value="${status.key }">${status.value }</option>
							    </c:forEach>
						    </select>
						    <label class="myMarginLeft20" for="idcard">身份证号：</label><input type="text" class="form-control" style="width:120px" name="idcard" id="idcard" value="${maps.idcard[0] }"/>
						</div><br/>
						<div class="form-group myMargintop20">
							<label for="studentMinDate">入学日期：</label><input  class="form-control dateInput" autocomplete="off" style="width:120px" size="16" type="text" name="studentMinDate" id="studentMinDate" value="${maps.studentMinDate[0] }" readonly/> —— <input class="form-control dateInput" autocomplete="off" style="width:120px" size="16" type="text" name="studentMaxDate" id="studentMaxDate" value="${maps.studentMaxDate[0] }" readonly/>
							<label class="myMarginLeft20" for="groupName">班级名称：</label><input type="text" class="form-control" style="width:120px" name="gname" id="groupName" value="${maps.gname[0] }"/>
							<input class="myMarginLeft20 btn btn-primary " type="submit" value="查询"><input type="hidden" name="page" value="1"><input type="hidden" name="gid" value="${maps.gid[0] }"><input type="hidden" name="pid" value="${maps.pid[0] }">
						</div>
					</form>
				</div>
			</div>
			<div class="row">
				    <div class="col-xs-12">
				        <div class="btn-toolbar" role="toolbar" aria-label="...">
				            <div class="btn-group" role="group" aria-label="...">
				            	<shiro:hasPermission name="student:add">
					                <button type="button" class="btn btn-primary student_add">国网注册</button>
				            	</shiro:hasPermission>
				            	<shiro:hasPermission name="student:proFileUpload">
					                <button type="button" class="btn btn-info student_fileUpload" data-toggle="modal">信息采集</button>
				            	</shiro:hasPermission>
				            	<shiro:hasPermission name="student:modify">
					                <button type="button" class="btn btn-success student_modify">修改</button>
				            	</shiro:hasPermission>
				            </div>
				            
				            <div class="btn-group float-right user-set" role="group" aria-label="...">
				                <button type="button" class="btn btn-default">设置</button>
				                <button type="button" class="btn btn-default">帮助</button>
				            </div>                       
				        </div>
				    </div>
				</div>
			<div class="row studentCt">
			<jsp:include page="../../temp/table.jsp"></jsp:include>
		    </div>
		</div>
	</div>
	
	<div class="row studentRow hidden">
		<div class="row modifyTitle">
	       <h4 class="text-center">学生修改</h4>
		</div>
		
	     <div class="row modifyBody"></div>
	     
	     <div class="text-center row modifyFooter">
	     <shiro:hasPermission name="student:save">
		 	<button type="button" class="btn btn-primary saveChangeBtn">保存</button>
	     </shiro:hasPermission>
	       <button type="button" class="btn btn-default quitBtn">退出</button>
		</div>
	</div>
	
	
	<div class="modal fade bs-example-modal-sm studentModal_modify" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	     <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">信息采集</h4>
	      </div>
	      <div class="modal-body studentModalBody_modify">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
	        <shiro:hasPermission name="student:fileUpload">
		        <button type="button" class="btn btn-primary uploadSave">保存修改</button>
	        </shiro:hasPermission>
	      </div>
	    </div>
	  </div>
	</div>
	
	<script>
	//搜索去下拉框回显
	$("#stady_status").val('${maps.stady_status[0]}');

	function submitStudentForm() {
		var $inputs = $(".studentForm").find("input,select");
		var str = "?";
		$inputs.each(function(k,v) {
			if($(this).attr("type") != "submit") {
				str += $(this).attr("name") + "=" + $(this).val() + "&";
			}
		})
		refreshFrame("学生档案",str);
	}
	
		$(".studentForm").on("submit",submitStudentForm);
		
		function refreshTable(page) {
			$(".studentForm").find("input[name=page]").val(page);
			submitStudentForm();
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
	
	
	//新建修改按钮触发页面显示隐藏转换
	function modifyChange() {
		$(".studentRow").toggleClass("hidden");
	}
	

	
	
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
	
	//双击表格调用修改表格方法
	registTableModify($(".studentCt"),modifyStudent);
	
	
	
	$(".quitBtn").on("click",modifyChange);
	
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
	

	//班级树调用
	function getTree() {
		return ${nodes}; 
	}
	
	
	
	//专业树点击监听
	function groupTreeClick(gid) {
		$(".studentForm").find("input[name=page]").val(1);
		$(".studentForm").find("input[name=gid]").val(gid);
		$(".studentForm").find("input[name=pid]").val("");
		submitStudentForm();
	}
	
	function proTreeClick(pid) {
		$(".studentForm").find("input[name=page]").val(1);
		$(".studentForm").find("input[name=pid]").val(pid);
		$(".studentForm").find("input[name=gid]").val("");
		submitStudentForm();
	}
	 
	$('#tree').treeview({data: getTree(),levels: 1});     
	
	//上传头像打开监听
	$(".student_fileUpload").on("click",function() {
		registTableModifySelect($(".studentCt"),function(id) {
			$('.studentModal_modify').modal('toggle');
			$(".studentModalBody_modify").load("permissions/student/pro_fileUpload?id=" + id);
		});
	})
	
	
	//上传头像保存监听
	$(".uploadSave").on("click",function() {
		var photoUrl = $(".TXpreView").attr("src");
		var id = $(".uploadForm input[name=id]").val();
		$.ajax({
			type: "post",
			url: "permissions/student/saveUpload",
			data: {id:id,photoUrl:photoUrl},
			success: function(msg){
				if(msg == 1) {
					$('.studentModal_modify').modal('toggle');
					setTimeout(function() {
						refreshTable($(".currentPage").attr("data-currentPage"));
					},300);
				}else {
					alert("修改保存失败," + msg);
				}
			}
		});
	})
	</script>
</body>
</html>