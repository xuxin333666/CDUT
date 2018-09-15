<%@page import="java.lang.reflect.Field"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="com.alibaba.fastjson.JSON"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

	
	<div class="table-responsive studentTable_jsp">
		<table class="table table-hover table-striped table-condensed">
			<tr><th class="selectAllCt"><input type="checkbox" class="selectAll" name="selectAll"></th>
			<c:forEach items="${columnNames}" var="columnName">
				<th>${columnName}</th>	
			</c:forEach>
			</tr>
					<% int i = 1; %>
				<c:forEach items="${pageBean.dataList}" var="item">
					<tr><td class="selectCt"><input type="checkbox" class="selectItem" name="selectItem" value="${item.id}"></td>
					<td class="myPointer"><%=i++ %></td>
					<c:forEach items="${fields}" var="field">
						<c:if test="${field.startsWith('#') }">
							<c:set value="${field.split('#')}" var="arrs"></c:set>
							<c:set value="${item}" var="c"></c:set>
							<c:forEach items="${arrs }" var="b">
								<c:if test="${b != ''}">
									<c:set value="${c[b]}" var="c"></c:set>
								</c:if>
							</c:forEach>
							<td class="myPointer">${c}</td>
						</c:if>
						<c:if test="${!field.startsWith('#') }">
							<td class="myPointer">${item[field]}</td>
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
	
	
	function registTableModify($parentCt,callback) {
		$parentCt.find(".table-hover").on("dblclick",function() {
			var $this = $(event.target);
			if($this.parent().find("input.selectItem").length != 0) {
				if(callback != undefined) {
					callback($this.parent().find("input.selectItem").val());
				}
			}
		})
	}
	
	function registTableModifySelect($parentCt,callback) {
		var flag = true;
		$parentCt.find(".selectItem").each(function(){
			if(this.checked) {
				var id = $(this).val();
				callback(id);
				flag = false;
				return;
			};
		});
		if(flag) {
			alert("请选择一行内容进行修改");
		}
		
	}

	
</script>
