<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js" type="text/javascript"></script>
	
	介绍：<textarea name="description" id="description"/></textarea>
			
	
	<script type="text/javascript">	
	    CKEDITOR.replace('description');
	
	</script>
</body>
</html>