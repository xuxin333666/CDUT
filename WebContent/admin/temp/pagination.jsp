<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
  <head>
	 <style>
		.jumpGroupPage {
			height: 14px;
			width: 20px;
			margin-left: 5px;
			text-align: center;
		}
	</style>   
  </head>  
 
<nav aria-label="Page navigation">
  <ul class="pagination ">
  	<li><a href="javascript: void(0)" onclick="refreshTable(1)">首页</a></li>
    <li>
      <a href="javascript: void(0)" aria-label="Previous" onclick="refreshTable(${pageBean.currentPage - 1})">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
	    <li><a href="javascript: void(0)" class="studentCurrentPage" data-currentPage="${pageBean.currentPage}">-</a></li>
    <li>
      <a href="javascript: void(0)" aria-label="Next" onclick="refreshTable(${pageBean.currentPage + 1})">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    <li><a href="javascript: void(0)" onclick="refreshTable(${pageBean.totalNumberOfPages})">末页</a></li>
    <li><a href="javascript: void(0)" class="jumpGroupPageBtn">转到<input type="text" class="jumpGroupPage" value="${pageBean.currentPage}"></a></li>
    <li><a href="javascript: void(0)" >第${pageBean.currentPage}页 / 共${pageBean.totalNumberOfPages}页</a></li>
  </ul>
</nav>
<script>
	$(".jumpGroupPageBtn").on("click",function() {
		if(event.target.localName != "input") {
			refreshTable($(".jumpGroupPage").val());
		}
	})
</script>