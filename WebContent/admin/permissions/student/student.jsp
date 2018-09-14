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
	<div class="row">
		<div class="col-xs-2">
			<div id="tree" style="width: 120%;margin-left: -25.3px;height: 400px;overflow: auto;"></div>
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
							<input class="myMarginLeft20 btn btn-primary " type="submit" value="查询"><input type="hidden" name="page" value="1"><input type="hidden" name="gid" value="${maps.gid[0] }">
						</div>
					</form>
				</div>
			</div>
			<div class="row">
				    <div class="col-xs-12">
				        <div class="btn-toolbar" role="toolbar" aria-label="...">
				            <div class="btn-group" role="group" aria-label="...">
				                <button type="button" class="btn btn-primary student_add" data-toggle="modal">国网注册</button>
				                <button type="button" class="btn btn-success student_modify" data-toggle="modal">修改</button>
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
		<div class="modal fade bs-example-modal-sm studentModal_modify" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			     <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">班级修改</h4>
			      </div>
			      <div class="modal-body studentModalBody_modify">
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
	/*

	$(".group_add").on("click",function() {
		var pid = $(".groupForm").find("input[name=pid]").val();
		if(pid === "") {
			alert("请选择一个专业再添加班级");
			return;
		}
		$('.groupModal_modify').modal();
		$(".groupModalBody_modify").load("permissions/group/pro_add?pid=" + pid);
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

	
	//专业树点击监听
	function proTreeClick(pid) {
		$(".groupForm").find("input[name=page]").val(1);
		$(".groupForm").find("input[name=pid]").val(pid);
		submitGroupForm();
	}
	
	//专业树高亮显示
	var proId = "${maps.pid[0]}" || "1";
	$("#proList>.panel-body[data-val="+ proId +"]").addClass("text-primary");
	*/
	
	//班级树调用
	function getTree() {
		var tree = [
			  {
		        text: "土木工程",
		        href: "#node-1",
			    selectable: true,
			    state: {
			      checked: true,
			      expanded: true,
			      selected: true
			    },
			    tags: ['available'],
		        nodes: [
		          {
		            text: "土木1班",
		            icon: "glyphicon glyphicon-stop",
		            selectedIcon: "glyphicon glyphicon-stop"
		          },
		          {
		            text: "土木2班",
		            icon: "glyphicon glyphicon-stop",
		            selectedIcon: "glyphicon glyphicon-stop",
		          }
		        ]
		      },
		      {
		        text: "java",
		        nodes: [
			          {
			            text: "java1802",
			            icon: "glyphicon glyphicon-stop",
			            selectedIcon: "glyphicon glyphicon-stop",
			          },
			          {
			            text: "java1803",
			            icon: "glyphicon glyphicon-stop",
			            selectedIcon: "glyphicon glyphicon-stop",
			          }
			        ]
		      },
		      {
			        text: "计算机网络通讯技术",
			        nodes: [
				          {
				            text: "java1802",
				            icon: "glyphicon glyphicon-stop",
				            selectedIcon: "glyphicon glyphicon-stop",
				          },
				          {
				            text: "java1803",
				            icon: "glyphicon glyphicon-stop",
				            selectedIcon: "glyphicon glyphicon-stop"
				          }
				        ]
			      }
			];                  
	    return tree;
	}
	 
	$('#tree').treeview({data: getTree(),levels: 1,onNodeSelected : function(event, data) {
        console.log(event.target);
    }});             
	</script>
</body>
</html>