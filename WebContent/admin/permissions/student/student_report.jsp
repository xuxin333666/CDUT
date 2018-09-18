<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
					<form method="post" action="javascript:void(0)" class="form-inline student_reportForm">
						<div class="form-group">
							<label for="studentName">姓名：</label><input type="text" class="form-control" name="sname" id="studentName" value="${maps.sname[0] }"/>
							<label class="myMarginLeft20" for="studentId">学号：</label><input type="text" class="form-control" name="sid" id="studentId" value="${maps.sid[0] }"/>
							<label class="myMarginLeft20" for="report_status">报道状态：</label>  
							<select class="myMarginLeft20 form-control" name="report_status" id="report_status">
								<option value="">全部</option>
							    <c:forEach items="${selectMap.reportStatus }" var="reportStatus">
							    	<option value="${reportStatus.key }">${reportStatus.value }</option>
							    </c:forEach>
						    </select>
							<input class="myMarginLeft20 btn btn-primary " type="submit" value="查询"><input type="hidden" name="page" value="1"><input type="hidden" name="gid" value="${maps.gid[0] }"><input type="hidden" name="pid" value="${maps.pid[0] }">
						</div>
					</form>
				</div>
			</div>
			<div class="row">
				    <div class="col-xs-12">
				        <div class="btn-toolbar" role="toolbar" aria-label="...">
				            <div class="btn-group" role="group" aria-label="...">
				                <button type="button" class="btn btn-primary student_report">报道</button>
				                <button type="button" class="btn btn-info student_unreport">未报到</button>
				            </div>
				            
				            <div class="btn-group float-right user-set" role="group" aria-label="...">
				                <button type="button" class="btn btn-default">设置</button>
				                <button type="button" class="btn btn-default">帮助</button>
				            </div>                       
				        </div>
				    </div>
				</div>
			<div class="row student_reportCt">
			<jsp:include page="../../temp/table.jsp"></jsp:include>
		    </div>
		</div>
	</div>
	
	<script>
	//搜索去下拉框回显
	$("#report_status").val('${maps.report_status[0]}');

	function submitStudentForm() {
		var $inputs = $(".student_reportForm").find("input,select");
		var str = "?";
		$inputs.each(function(k,v) {
			if($(this).attr("type") != "submit") {
				str += $(this).attr("name") + "=" + $(this).val() + "&";
			}
		})
		refreshFrame("学生报道",str);
	}
	
	$(".student_reportForm").on("submit",submitStudentForm);
	
	function refreshTable(page) {
		$(".student_reportForm").find("input[name=page]").val(page);
		submitStudentForm();
	}

	
	//报道
	$(".student_report").on("click",function() {
		var idArr = select2Arr(".student_reportCt .selectItem");
		if(idArr.length == 0) {
			alert("请选择至少一个行内容再报道");
			return;
		}
		if(confirm("请确认该学生已报到?")) {
			var data = JSON.stringify(idArr);
			$.ajax({
				type: "post",
				url: "permissions/student/report",
				data: {data:data},
				dataType: "json",
				success: function(msg){
					if(msg != 0) {
						refreshTable($(".currentPage").attr("data-currentPage"));
					}else {
						alert("学生报道失败");
					}
				}
			});
		}
	})
	
	//未报道
	$(".student_unreport").on("click",function() {
		var idArr = select2Arr(".student_reportCt .selectItem");
		if(idArr.length == 0) {
			alert("请选择至少一个行内容再报道");
			return;
		}
		if(confirm("请确认该学生还未报道!")) {
			var data = JSON.stringify(idArr);
			$.ajax({
				type: "post",
				url: "permissions/student/unreport",
				data: {data:data},
				success: function(msg){
					alert(msg);
					refreshTable($(".currentPage").attr("data-currentPage"));
				}
			});
		}
	})
	

	
		
	//班级树调用
	function getTree() {
		return ${nodes}; 
	}
	
	//专业树点击监听
	function groupTreeClick(gid) {
		$(".student_reportForm").find("input[name=page]").val(1);
		$(".student_reportForm").find("input[name=gid]").val(gid);
		$(".student_reportForm").find("input[name=pid]").val("");
		submitStudentForm();
	}
	
	
	function proTreeClick(pid) {
		$(".student_reportForm").find("input[name=page]").val(1);
		$(".student_reportForm").find("input[name=pid]").val(pid);
		$(".student_reportForm").find("input[name=gid]").val("");
		submitStudentForm();
	}
	 
	$('#tree').treeview({data: getTree(),levels: 1});     
	

	</script>
</body>
</html>