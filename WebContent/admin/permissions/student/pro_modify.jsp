<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 
 
<div class="col-sm-1"></div>

<form class="col-sm-10 form-horizontal" style="height:300px;overflow-x: hidden;overflow-y: auto;">
 	<div class="row">
 		<div id="legendMain" class="col-sm-12">
        	<legend style="font-size: 16px;">主要信息</legend>
 		</div>
 	</div>
 	
 	<div class="row">
		<div class="col-sm-2">
			<div class="form-group">
			    <!-- Text input-->
				<label class="control-label" for="stuentId">学号</label>
				<c:if test="${command == 'add' }">
					<input id="stuentId" type="text" class="form-control" name="id" value="${student.id }">
				</c:if>
				<c:if test="${command == 'modify' }">
					<input id="stuentId" type="text" class="form-control" name="id" value="${student.id }" readonly>
				</c:if>
				<p class="help-block">请按一定规则编写学号</p>
			</div>
			
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="stuentGroupName">班级名称</label>
				<input id="stuentGroupName" type="text" class="form-control" value="${student.group.name }" disabled>
				<p class="help-block" style="color:blue">已确定，不可修改</p>
			</div>
			
			<div class="form-group">
			    <!-- Text input-->
			    <label class="control-label" for="registeredResidence">户口所在地</label>
			    <input id="registeredResidence" type="text" class="form-control" name="registeredResidence" value="${student.registeredResidence }">
			    <p class="help-block">规范填写</p>
			</div>   

			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="birthday">出生日期</label>
				<input id="birthday" type="text" class="form-control student_date" name="birthday" value="${student.birthday }">
				<p class="help-block">使用控件填写时间</p>
			</div>  
			
			
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="st_bloodType">血型</label>
				<div class="controls">
					<select class="form-control" name="bloodType" id="st_bloodType">
					<c:forEach items="${selectMap.bloodType }" var="bloodType">
						<option value="${bloodType.key }">${bloodType.value }</option>
					</c:forEach>
					</select>
					<p class="help-block">请按一定规则编写</p>
				</div>
			</div>

			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="st_stadyStatus">在读状态</label>
				<div class="controls">
					<select class="form-control" name="stadyStatus" id="st_stadyStatus">
					<c:forEach items="${selectMap.stadyStatus }" var="stadyStatus">
						<option value="${stadyStatus.key }">${stadyStatus.value }</option>
					</c:forEach>
					</select>
					<p class="help-block">请按一定规则编写</p>
				</div>
			</div>
						
		</div>



		<div class="col-sm-1"></div>
		<div class="col-sm-2">
		    <div class="form-group">
		        <!-- Text input-->
		        <label class="control-label" for="stuentName">学生姓名</label>
		        <input id="stuentName" type="text" class="form-control" name="name" value="${student.name }">
		        <p class="help-block">请输入合法的姓名</p>
			</div>

			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="stuentGroupManager">班主任</label>
				<input id="stuentEductionalSystme" type="text" class="form-control" value="${student.group.groupManager}" disabled>
				<p class="help-block" style="color:blue">已确定，不可修改</p>
			</div>
			
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="st_national">民族</label>
				<div class="controls">
					<select class="form-control" name="national" id="st_national">
					<c:forEach items="${selectMap.national }" var="national">
						<option value="${national.key }">${national.value }</option>
					</c:forEach>
					</select>
					<p class="help-block">请按一定规则编写</p>
				</div>
			</div>
			

			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="birthplace">出生地点</label>
				<input id="birthplace" type="text" class="form-control" name="birthplace" value="${student.birthplace }">
				<p class="help-block">请按一定规则编写</p>
			</div>

			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="sourceSchool">来源学校</label>
				<input id="sourceSchool" type="text" class="form-control" name="sourceSchool" value="${student.sourceSchool }">
				<p class="help-block">请按一定规则编写</p>
			</div>
			
			
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="st_residenceStatus">是否住校</label>
				<div class="controls">
					<select class="form-control" name="residenceStatus" id="st_residenceStatus">
					<c:forEach items="${selectMap.residenceStatus }" var="residenceStatus">
						<option value="${residenceStatus.key }">${residenceStatus.value }</option>
					</c:forEach>
					</select>
					<p class="help-block">请按一定规则编写</p>
				</div>
			</div>
			
			
		</div>
		<div class="col-sm-1"></div>
		<div class="col-sm-2">
			<div class="form-group">
					<!-- Text input-->
				<label class="control-label" for="st_gender">性别</label>
				<div class="controls">
					<select class="form-control" name="gender" id="st_gender">
					<c:forEach items="${selectMap.gender }" var="gender">
						<option value="${gender.key }">${gender.value }</option>
					</c:forEach>
					</select>
					<p class="help-block">请按一定规则编写</p>
				</div>
			</div>

			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="stuentProName">专业名称</label>
				<input id="stuentProName" type="text" class="form-control" value="${student.group.professional.name }" disabled>
				<p class="help-block" style="color:blue">已确定，不可修改</p>
			</div>
				
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="st_idcardType">身份证件类型</label>
				<div class="controls">
					<select class="form-control" name="idcardType" id="st_idcardType">
					<c:forEach items="${selectMap.idcardType }" var="idcardType">
						<option value="${idcardType.key }">${idcardType.value }</option>
					</c:forEach>
					</select>
					<p class="help-block">请按一定规则编写</p>
				</div>
			</div>
			
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="nativePlace">籍贯</label>
				<input id="nativePlace" type="text" class="form-control" name="nativePlace" value="${student.nativePlace }">
				<p class="help-block">请按一定规则编写</p>
			</div>

			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="admissionDate">报道日期</label>
				<input id="admissionDate" type="text" class="form-control student_date" name="admissionDate" value="${student.admissionDate }">
				<p class="help-block">使用控件填写时间</p>
			</div>  

		</div>
		<div class="col-sm-1"></div>
		<div class="col-sm-2">
			<div class="form-group">
					<!-- Text input-->
				<label class="control-label" for="registrationNo">报道号</label>
				<input id="registrationNo" type="text" class="form-control" name="registrationNo" value="${student.registrationNo }">
				<p class="help-block">请按一定规则编写</p>
			</div>

			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="stuentTotalScore">总学分</label>
				<input id="stuentTotalScore" type="text" class="form-control" value="${student.group.professional.totalScore }" disabled>
				<p class="help-block" style="color:blue">已确定，不可修改</p>
			</div>
			
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="idcard">身份证件号码</label>
				<input id="idcard" type="text" class="form-control" name="idcard" value="${student.idcard }">
				<p class="help-block">请输入合法证件号</p>
			</div>

			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="st_registeredType">户口类型</label>
				<div class="controls">
					<select class="form-control" name="registeredType" id="st_registeredType">
					<c:forEach items="${selectMap.registeredType }" var="registeredType">
						<option value="${registeredType.key }">${registeredType.value }</option>
					</c:forEach>
					</select>
					<p class="help-block">请按一定规则编写</p>
				</div>
			</div>	
	
	
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="st_educationBackground">目前学历</label>
				<div class="controls">
					<select class="form-control" name="educationBackground" id="st_educationBackground">
					<c:forEach items="${selectMap.educationBackground }" var="educationBackground">
						<option value="${educationBackground.key }">${educationBackground.value }</option>
					</c:forEach>
					</select>
					<p class="help-block">请按一定规则编写</p>
				</div>
			</div>

		</div>


 	</div>
 	
 	<div class="row">
 		<div id="legendMain" class="col-sm-12">
        	<legend style="font-size: 16px;">其他信息</legend>
 		</div>
	 </div>
	 
	 <div class="row">
		<div class="col-sm-2">
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="nameEn">英文名</label>
				<input id="nameEn" type="text" class="form-control" name="nameEn" value="${student.nameEn }">
				<p class="help-block">请按一定规则编写</p>
			</div>
			
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="nationality">国籍</label>
				<input id="nationality" type="text" class="form-control" name="nationality" value="${student.nationality }">
				<p class="help-block">请按一定规则编写</p>
			</div>  
		</div> 

		<div class="col-sm-1"></div>
		
		<div class="col-sm-2">
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="usedName">曾用名</label>
				<input id="usedName" type="text" class="form-control" name="usedName" value="${student.usedName }">
				<p class="help-block">请按一定规则编写</p>
			</div>  

			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="phoneNum">联系电话</label>
				<input id="phoneNum" type="text" class="form-control" name="phoneNum" value="${student.phoneNum }">
				<p class="help-block">请按一定规则编写</p>
			</div>
		</div>	
		
		<div class="col-sm-1"></div>


		<div class="col-sm-2">
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="st_maritalStatus">婚姻状态</label>
				<div class="controls">
					<select class="form-control" name="maritalStatus" id="st_maritalStatus">
					<c:forEach items="${selectMap.maritalStatus }" var="maritalStatus">
						<option value="${maritalStatus.key }">${maritalStatus.value }</option>
					</c:forEach>
					</select>
					<p class="help-block">请按一定规则编写</p>
				</div>
			</div>  

			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="st_politicalStatus">政治情况</label>
				<div class="controls">
					<select class="form-control" name="politicalStatus" id="st_politicalStatus">
					<c:forEach items="${selectMap.politicalStatus }" var="politicalStatus">
						<option value="${politicalStatus.key }">${politicalStatus.value }</option>
					</c:forEach>
					</select>
					<p class="help-block">请按一定规则编写</p>
				</div>
			</div>
		</div>	

		<div class="col-sm-1"></div>

		<div class="col-sm-2">
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="st_healthStatus">健康状态</label>
				<div class="controls">
					<select class="form-control" name="healthStatus" id="st_healthStatus">
					<c:forEach items="${selectMap.healthStatus }" var="healthStatus">
						<option value="${healthStatus.key }">${healthStatus.value }</option>
					</c:forEach>
					</select>
					<p class="help-block">请按一定规则编写</p>
				</div>
			</div> 
			
			<div class="form-group">
				<!-- Text input-->
				<label class="control-label" for="email">电子邮箱</label>
				<input id="email" type="text" class="form-control" name="email" value="${student.email }">
				<p class="help-block">请按一定规则编写</p>
			</div>
		</div>	

	</div>  
				
 	
 	<div class="row">
	 	<div class="col-sm-12 form-group">
			<label class="control-label" for="specialty">特长写在这里：</label>
			<textarea class="form-group" name="specialty" id="specialty"></textarea>
		</div>
 	</div>
 	
 	<input type="hidden" name="command" value="${command }"/>
	<input type="hidden" name="gid" value="${student.group.id }"/>
</form>
  
<div class="col-sm-1"></div>



	<c:forEach items="${selectMap }" var="key">
		<c:if test="${student[key.key] != null && student[key.key] != ''}">
			<script>$("#st_" + '${key.key}').val("${student[key.key] }")</script>
		</c:if>
	</c:forEach>
  
<script>
	
	$('.student_date').datetimepicker({
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

	var ckSpecialty = CKEDITOR.replace('specialty');
	
	CKEDITOR.instances.specialty.setData(`${student.specialty}`);
	


	


</script>
	