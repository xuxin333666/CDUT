<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" errorPage="/temp/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>成都理工大学网络信息化平台</title>

        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/buttons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
        <link href="${pageContext.request.contextPath }/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />

        <style type="text/css">
            .icon {
               fill: currentColor;
               overflow: hidden;
               vertical-align: middle;
            }
            .header {
                background: url("${pageContext.request.contextPath}/img/bg_header_1.jpg") no-repeat;
                background-size: cover;
                padding: 0px 10px;
                color: white;
            }
            .userInfo {
                padding: 15px 20px;
                text-align: right;
            }
            .row.body {
            	height: 500px;
            }
        </style>

        <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
        <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
        <!--[if lt IE 9]>
        <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="header clearfix">
            <div class="userInfo pull-right">
                <shiro:principal property="realName"></shiro:principal>,欢迎您 <span class="userCount">,当前在线人数为2人</span><br>
                <span class="myPointer" onclick="location='http://www.cdut.edu.cn/default.html'">学校首页</span> | 支持论坛 | 帮助中心 | <span class="signOut myPointer">安全退出</span>
            </div>
            <h3>成都理工大学网络信息化平台</h3>

        </div>		
        <jsp:include page="permissions/nav.jsp"></jsp:include>
        <div class="container-fluid"> 
            <div class="row body">
                <div class="col-md-2 col-sm-4 hidden-xs">
	                <jsp:include page="permissions/menu.jsp"></jsp:include>
                </div>
                <div class="col-md-10 col-sm-8 col-xs-12 mainContent">
					<ul class="nav nav-tabs mainContentTabs">
					<c:if test="${secMenus.size() == 0}">
					    <li class="tabItem" data-name="欢迎页"><a href="#欢迎页" data-toggle="tab">首页<span class="glyphicon glyphicon-remove-circle closeTab" aria-hidden="true"></span></a></li>
					</c:if>
					</ul>
					<div class="tab-content">
					<c:if test="${secMenus.size() == 0}">
					    <div class="tab-pane fade" data-name="欢迎页" data-url="permissions/tabIndex.jsp" id="欢迎页">找不到页面</div>
					</c:if>
					</div>
                </div>
            </div>
            <div class="footer row">
                <div class="col-xs-12 text-center">
                    Copyright 2006 Tblog.com.cn All Rights Reserved xhtml | css <br/> 湘ICP备06991797号
                </div>
            </div>
        </div>
    </body>
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="${pageContext.request.contextPath}/lib/jquery-3.3.1.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="${pageContext.request.contextPath}/lib/bootstrap.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    
	<script src="${pageContext.request.contextPath }/lib/bootstrap-datetimepicker.min.js"></script>
	
	<script src="${pageContext.request.contextPath }/lib/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	
	<script src="${pageContext.request.contextPath }/lib/bootstrap-treeview.js"></script>
	
	<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js" type="text/javascript"></script>
    
    <script src="http://at.alicdn.com/t/font_783692_gfwpqhoo67.js"></script>
    
</html>