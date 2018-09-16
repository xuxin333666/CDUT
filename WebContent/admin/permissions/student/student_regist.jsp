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
			<div id="student_regist_tree" style="height: 400px;overflow: auto;"></div>
		</div>
		<div class="col-xs-10">
			<div class="row">
				<div class="col-xs-12">
					<form method="post" action="javascript:void(0)" class="form-inline student_registForm">
						<div class="form-group">
							<label for="studentName">姓名：</label><input type="text" class="form-control" name="sname" id="studentName" value="${maps.sname[0] }"/>
							<label class="myMarginLeft20" for="studentId">学号：</label><input type="text" class="form-control" name="sid" id="studentId" value="${maps.sid[0] }"/>
							<label class="myMarginLeft20" for="regist_status">注册状态：</label>  
							<select class="myMarginLeft20 form-control" name="regist_status" id="regist_status">
								<option value="">全部</option>
							    <c:forEach items="${selectMap.registStatus }" var="registStatus">
							    	<option value="${registStatus.key }">${registStatus.value }</option>
							    </c:forEach>
						    </select>
							<input class="myMarginLeft20 btn btn-primary " type="submit" value="查询"><input type="hidden" name="page" value="1"><input type="hidden" name="gid" value="${maps.gid[0] }">
						</div>
					</form>
				</div>
			</div>
			<div class="row">
				    <div class="col-xs-12">
				        <div class="btn-toolbar" role="toolbar" aria-label="...">
				            <div class="btn-group" role="group" aria-label="...">
				                <button type="button" class="btn btn-primary student_regist">注册</button>
				                <button type="button" class="btn btn-info student_unregist">未注册</button>
				            </div>
				            
				            <div class="btn-group float-right user-set" role="group" aria-label="...">
				                <button type="button" class="btn btn-default">设置</button>
				                <button type="button" class="btn btn-default">帮助</button>
				            </div>                       
				        </div>
				    </div>
				</div>
			<div class="row student_registCt">
			<jsp:include page="../../temp/table.jsp"></jsp:include>
		    </div>
		</div>
	</div>
	
	<script>
	//搜索去下拉框回显
	$("#regist_status").val('${maps.regist_status[0]}');

	function submitStudentForm() {
		var $inputs = $(".student_registForm").find("input,select");
		var str = "?";
		$inputs.each(function(k,v) {
			if($(this).attr("type") != "submit") {
				str += $(this).attr("name") + "=" + $(this).val() + "&";
			}
		})
		refreshFrame("学生注册",str);
	}
	
	$(".student_registForm").on("submit",submitStudentForm);
	
	function refreshTable(page) {
		$(".student_registForm").find("input[name=page]").val(page);
		submitStudentForm();
	}

	
	//报道
	$(".student_regist").on("click",function() {
		var idArr = select2Arr(".student_registCt .selectItem");
		if(idArr.length == 0) {
			alert("请选择至少一个行内容再注册");
			return;
		}
		if(confirm("请确认该学生已注册?")) {
			var data = JSON.stringify(idArr);
			$.ajax({
				type: "post",
				url: "permissions/student/regist",
				data: {data:data},
				dataType: "json",
				success: function(msg){
					if(msg != 0) {
						refreshTable($(".currentPage").attr("data-currentPage"));
					}else {
						alert("学生注册失败");
					}
				}
			});
		}
	})
	
	//未报道
	$(".student_unregist").on("click",function() {
		var idArr = select2Arr(".student_registCt .selectItem");
		if(idArr.length == 0) {
			alert("请选择至少一个行内容再注册");
			return;
		}
		if(confirm("请确认该学生还未注册!")) {
			var data = JSON.stringify(idArr);
			$.ajax({
				type: "post",
				url: "permissions/student/unregist",
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
		$(".student_registForm").find("input[name=page]").val(1);
		$(".student_registForm").find("input[name=gid]").val(gid);
		submitStudentForm();
	}
	 
	$('#student_regist_tree').treeview({data: getTree(),levels: 1});     
	

	</script>
</body>
</html>