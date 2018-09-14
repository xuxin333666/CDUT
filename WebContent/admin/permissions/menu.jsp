<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" errorPage="/CDUT/error.jsp"%>



 <div class="panel-group" id="accordion">
	 <c:if test="${secMenus.size() == 0}">
	    <div class="panel panel-info">
	        <div class="panel-heading">
	            <h4 class="panel-title ">
		            <svg class="icon" aria-hidden="true">
	                       <use xlink:href="#icon-users"></use>
	                   </svg>
	                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
		            	欢迎页
	                </a>
	            </h4>
	        </div>
	        <div id="collapseOne" class="panel-collapse collapse">
	            <div class="panel-body" data-tabName="欢迎页" data-url="permissions/tabIndex.jsp">
	        		欢迎页
	            </div>
	        </div>
	    </div>
	 </c:if>
	 <c:if test="${secMenus.size() != 0}">
	 <c:forEach items="${secMenus}" var="menu" >
	 	<c:if test="${menu.id % 10 == 0 }">
	 	<div class="panel panel-info">
	 	 	<div class="panel-heading">
	            <h4 class="panel-title ">
		            <svg class="icon" aria-hidden="true">
	                       <use xlink:href="${menu.iconName }"></use>
	                   </svg>
	                <a data-toggle="collapse" data-parent="#accordion" href=#${menu.tagName}>
		            	${menu.name}
	                </a>
	            </h4>
	        </div>
			<div id="${menu.tagName}" class="panel-collapse collapse">
	        <c:forEach items="${secMenus}" var="menu2" >
		        <c:if test="${menu2.parentMenu.id == menu.id }">
			            <div class="panel-body" data-tabName="${menu2.name}" data-url="${menu2.dataUrl }">
			        		${menu2.name}
			            </div>
		        </c:if>
	        </c:forEach>
			</div>
	    </div>
	 	</c:if>
	 </c:forEach>
	 
	 
	  <div class="panel panel-info">
	        <div class="panel-heading">
	            <h4 class="panel-title ">
		            <svg class="icon" aria-hidden="true">
	                       <use xlink:href="#icon-users"></use>
	                   </svg>
	                <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
		            	其他功能
	                </a>
	            </h4>
	        </div>
	        <div id="collapse1" class="panel-collapse collapse">
	            <div class="panel-body">
	        		其他功能
	            </div>
	        </div>
	    </div>
	    	  <div class="panel panel-info">
	        <div class="panel-heading">
	            <h4 class="panel-title ">
		            <svg class="icon" aria-hidden="true">
	                       <use xlink:href="#icon-users"></use>
	                   </svg>
	                <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
		            	其他功能
	                </a>
	            </h4>
	        </div>
	        <div id="collapse2" class="panel-collapse collapse">
	            <div class="panel-body">
	        		其他功能
	            </div>
	        </div>
	    </div>
	    	  <div class="panel panel-info">
	        <div class="panel-heading">
	            <h4 class="panel-title ">
		            <svg class="icon" aria-hidden="true">
	                       <use xlink:href="#icon-users"></use>
	                   </svg>
	                <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">
		            	其他功能
	                </a>
	            </h4>
	        </div>
	        <div id="collapse3" class="panel-collapse collapse">
	            <div class="panel-body">
	        		其他功能
	            </div>
	        </div>
	    </div>
	    	  <div class="panel panel-info">
	        <div class="panel-heading">
	            <h4 class="panel-title ">
		            <svg class="icon" aria-hidden="true">
	                       <use xlink:href="#icon-users"></use>
	                   </svg>
	                <a data-toggle="collapse" data-parent="#accordion" href="#collapse4">
		            	其他功能
	                </a>
	            </h4>
	        </div>
	        <div id="collapse4" class="panel-collapse collapse">
	            <div class="panel-body">
	        		其他功能
	            </div>
	        </div>
	    </div>
	    
	    
	    
	 </c:if>
	 
</div>