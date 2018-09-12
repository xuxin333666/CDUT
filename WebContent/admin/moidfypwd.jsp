<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div>
	<form method="post" action="modifyUserPwd" onsubmit="return modifyUserPwd()">
		<label for="modifyPwdInput">请修改密码后再使用:</label>
			<input type="password" name="password" id="modifyPwdInput"/>
			<input type="hidden" name="username" value="${username}">
			<input type="submit" />
		</form>
	</div>
</body>
<script src="${pageContext.request.contextPath}/lib/jquery-3.3.1.min.js"></script>
<script>
	function modifyUserPwd() {
		var value = $("#modifyPwdInput").val();
		if(value == "" || value == "123") {
			alert("请按要求修改密码");
		event.preventDefault();
		}
	}
</script>
</html>