<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<div class="row">
<div class="col-xs-1"></div>
	<div class="col-xs-4">
		<img class="TXpreView" style="height:210px" src="${student.photoUrl }"></img>
	</div>
	<div class="col-xs-2"></div>
	<div class="col-xs-4">
	<div class="row" style="height:100px"></div>
		<form class="uploadForm form-horizontal form-group" action="permissions/student/fileUpload" method="post" enctype="multipart/form-data" target="rfFrame">
			<label class="control-label" for="stuentId">选择一张证件照上传吧</label>
			<div class=" form-group">
			    <input  type="file" name="myfile1" accept=".jpg,.gif,.png" disabled/>
			</div>
			<div class=" form-group text-center ">
			    <input class="btn btn-primary  col-xs-4"  onclick="uploadSubmit()" type="submit" value="上传">
			    <input type="hidden" name="id" value="${student.id }">
			</div>
		</form>
	</div>
<div class="col-xs-1"></div>
	
	<iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe> 
</div>

<shiro:hasPermission name="student:proFileUpload">
 	<script>
 		$(".studentModalBody_modify .form-horizontal").find("input,select,textarea").prop("disabled",false);
 	</script>
 </shiro:hasPermission>

<script>
	function uploadSubmit() {
		setTimeout(function() {
			var url = $($("iframe")[0].contentDocument).find("body").text();
			if(url === "") {
				uploadSubmit();
			} else {
				$(".TXpreView").attr("src",url);
			}
		},200)
	}
</script>