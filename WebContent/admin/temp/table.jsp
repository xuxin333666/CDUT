<%@page import="java.lang.reflect.Field"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

	
	<div class="table-responsive studentTable_jsp">
		<table class="table table-hover table-striped">
			<tr><th class="selectAllCt"><input type="checkbox" class="selectAll" name="selectAll"></th>
			<c:forEach items="${columnNames}" var="columnName">
				<th>${columnName}</th>	
			</c:forEach>
			</tr>
					<% int i = 1; %>
				<c:forEach items="${pageBean.dataList}" var="item">
					<tr><td class="selectCt"><input type="checkbox" class="selectItem" name="selectItem" value="${item.id}"></td>
					<td><%=i++ %></td>
					<c:forEach items="${fields}" var="field">
						<c:if test="${field.startsWith('#') }">
							<c:set value="${field.split('#')}" var="arrs"></c:set>
							<td>${item[arrs[1]][arrs[2]]}</td>
						</c:if>
						<c:if test="${!field.startsWith('#') }">
							<td>${item[field]}</td>
						</c:if>
					</c:forEach>
					</tr>
				</c:forEach>
		</table>
    </div>
    <jsp:include page="pagination.jsp"></jsp:include>
<script>
	
	$(".selectAllCt").on("click",function() {
		if($(this).find("input")[0].checked) {
			$(".selectItem").each(function(){
				this.checked = true;
			});
		} else {
			$(".selectItem").each(function(){
				this.checked = false;
			});
		}
	})

	
</script>
