<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" errorPage="/TestWeb/temp/error.jsp"%>

<nav class="navbar navbar-default">
  <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">
              <img src="${pageContext.request.contextPath}/img/bing.png" alt="Brand" id="brandTest">
          </a>
      </div>            
      <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <form class="navbar-form navbar-left searchForm">
                  <div class="form-group">
                  <select class="form-control serchConditionCt" name="serchCondition">
                 	 	<option value="https://cn.bing.com/search?q=">必应搜索</option>
				  </select>
                  <input type="text" class="form-control searchInput" placeholder="Search">
                  </div>
                  <button type="submit" class="btn btn-default">搜索</button>
              </form>
              <ul class="nav navbar-nav navbar-right">
              <c:forEach items="${mainMenus}" var="menu">
		          <li><a href="main?command=${menu.tagName}">${menu.name}</a></li>
              </c:forEach>
			      
                  <li class="dropdown">
                      <a href="#" class="dropdown-toggle userInfo" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">用户信息 <span class="caret"></span></a>
                      <ul class="dropdown-menu dropdown-menu-right">
                          <li><a href="#"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> 详细信息</a></li>
                          <li><a href="#"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> 权限查询</a></li>
                          <li><a href="#"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改密码</a></li>
                          <li role="separator" class="divider"></li>
                          <li><a href="#" class="signOut"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 退出登录</a></li>
                      </ul>
                  </li>
              </ul>
          </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav> 