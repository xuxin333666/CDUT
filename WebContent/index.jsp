<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>成都理工大学教务系统</title>

        <!-- Bootstrap -->
        <link href="./css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="./css/buttons.css">
        <link rel="stylesheet" href="./css/index.css">

        <style type="text/css">
            .icon {
               fill: currentColor;
               overflow: hidden;
               vertical-align: middle;
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
	    <!-- <div class="indexheader">
            <img src="img/cdut.jpg" alt=""  style="width: 250px;height:70px" class="headerBrand">
            <div class="headerContentCt">
                <div class="item">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-phone1"></use>
                    </svg>
                    <span>售前：400-830-9860</span>
                </div>
                <div class="item">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-users"></use>
                    </svg>
                    <span>企业查询入口</span>
                </div>
                <div class="item">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-users"></use>
                    </svg>
                    <span>老版管家</span>
                </div>
            </div>
		</div>-->
        <div class="jumbotron">
            <h1 class="text-center">Bootstrap</h1>
            <p class="text-center">Bootstrap 是最受欢迎的 HTML、CSS 和 JS 框架，用于开发响应式布局、移动设备优先的 WEB 项目。</p>
            <p class="text-center"><a  class="button button-3d button-action button-large button-pill" href="#" role="button"  data-toggle="modal" data-target="#myModal">立刻登陆</a></p>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form class="loginForm" method="post" action="login">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title text-center text-primary " id="myModalLabel">登 录</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                            <label for="exampleInputText1">用户名：</label>
                            <input type="text" class="form-control formContent" name="username" id="exampleInputText1" placeholder="请在此输入用户名">
                            </div>
                            <div class="form-group">
                            <label for="exampleInputPassword1">密码：</label>
                            <input type="password" class="form-control formContent" name="password" id="exampleInputPassword1" placeholder="请在此输入密码">
                            </div>
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-xs-4 text-left"><a class="text-muted pointer">忘记密码？</a></div>
                                    <div class="col-xs-8 text-right"><a class="text-primary pointer registChange">没有帐号，立即注册 ></a></div>
                                </div>                              
                            </div> 
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="submit" class="btn btn-primary">登陆</button>
                        </div>                      
                        <div class="container-fluid otherLoginChoose">
                            <div class="row">
                                <div class="col-xs-4 text-center rightBorder">
                                    <a class="pointer">
                                        <svg class="icon" aria-hidden="true">
                                            <use xlink:href="#icon-wechat"></use>
                                        </svg>
                                    微信登陆</a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a class="pointer">
                                        <svg class="icon" aria-hidden="true">
                                            <use xlink:href="#icon-qq"></use>
                                        </svg>
                                    QQ登陆</a>
                                </div>
                                <div class="col-xs-4 text-center leftBorder">
                                    <a class="pointer">
                                        <svg class="icon" aria-hidden="true">
                                            <use xlink:href="#icon-phone"></use>
                                        </svg>    
                                    手机短信登陆</a>
                                </div>
                            </div>
                        </div>
                    </form>
                    <form class="registForm hidden" action="success.html" method="get" target="_blank">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title text-center text-primary " id="myModalLabel">注 册</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                            <label for="exampleInputText2">用户名：</label>
                            <input type="text" class="form-control formContent" name="username" id="exampleInputText2" placeholder="用户名为6~20位字母、数字">
                            </div>
                            <div class="form-group">
                            <label for="exampleInputPassword2">密码：</label>
                            <input type="password" class="form-control formContent" name="password" id="exampleInputPassword2" placeholder="密码为6~20位字母、数字">
                            </div>
                            <div class="form-group">
                                <label>您所在城市是：</label><br>
                                <div class="row">
                                    <div class="col-xs-4">
                                        <select class="form-control ProvincesSelect">
                                            <option value="00">北京</option>
                                            <option value="01">四川</option>
                                            <option value="02">湖北</option>
                                            <option value="03">重庆</option>
                                        </select>
                                    </div>
                                    <div class="col-xs-4">
                                        <select class="form-control citySelect">
                                        </select>
                                    </div>
                                    <div class="col-xs-4">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                    <label>您的爱好是：</label><br>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" id="inlineCheckbox1" class="gameSelect" name="game" value="game00"> 魔兽世界
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" id="inlineCheckbox2" class="gameSelect" name="game" value="game01"> 英雄联盟
                                    </label>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" id="inlineCheckbox3" class="gameSelect" name="game" value="game02"> 炉石传说
                                    </label>
                                    <button type="button" class="btn btn-default allSelect leftMargin">全选</button>
                                    <button type="button" class="btn btn-default reverseSelect">反选</button>
                            </div>
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-xs-12 text-right"><a class="text-primary pointer loginChange">已经有帐号，立即登录 ></a></div>
                                </div>                              
                            </div> 
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="submit" class="btn btn-primary">注册</button>
                        </div>                      
                    </form>
                </div>
            </div>
        </div>
        <div class="container-fluid"> 
            <div class="footer row">
                <div class="col-xs-12 text-center">
                    Copyright 2006 Tblog.com.cn All Rights Reserved xhtml | css <br/> 湘ICP备06991797号
                </div>
            </div>
        </div>
    </body>
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="./lib/jquery-3.3.1.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="./lib/bootstrap.min.js"></script>


    <script>
    
		//登录
		$(".loginForm").on("submit",function() {
			event.preventDefault();
			var username = $(this).find("input[name=username]").val();
			var password = $(this).find("input[name=password]").val();
			$.ajax({
				type: "post",
				url: "login",
				data:{username:username,password:password},
				success: function(msg){
					if(msg === "true") {
						location = "admin/main?command=index";
					} else {
						alert(msg);
					}
				}
			})
		})

	
	    // 级联选择
	    var selectData = {"00":["北京"],"01":["成都","宜宾","绵阳","泸州"],"02":["武汉","宜昌","荆州"],"03":["重庆"]};
	    var parentSelect = document.getElementsByClassName("ProvincesSelect")[0];
	    var childSelect = document.getElementsByClassName("citySelect")[0];
	    randerChildSelect("00");
	
	    function randerChildSelect(index) {
	        var arr = selectData[index];
	        var str = "";
	        for (const key in arr) {
	            if (arr.hasOwnProperty(key)) {
	                str += `<option>${arr[key]}</option>`;          
	            }
	        }
	        childSelect.innerHTML = str;
	    }
	
	    parentSelect.addEventListener("change",function(){
	        randerChildSelect(parentSelect.value);
	    })
	
	
	
	    //注册
	    var registChange = document.getElementsByClassName("registChange")[0];
	    var loginChange = document.getElementsByClassName("loginChange")[0];
	    var loginForm = document.getElementsByClassName("loginForm")[0];
	    var registForm = document.getElementsByClassName("registForm")[0];
	
	    registChange.addEventListener("click",function(){
	        loginForm.classList.toggle("hidden");
	        registForm.classList.toggle("hidden");
	    });
	
	    loginChange.addEventListener("click",function(){
	        loginForm.classList.toggle("hidden");
	        registForm.classList.toggle("hidden");
	    });
	
	
	    //全选反选
	    var allSelect = document.getElementsByClassName("allSelect")[0];
	    var reverseSelect = document.getElementsByClassName("reverseSelect")[0];
	    var gameSelect = document.getElementsByClassName("gameSelect"); 
	
	    allSelect.addEventListener("click",function(){
	        for (const key in gameSelect) {
	            if (gameSelect.hasOwnProperty(key)) {
	                gameSelect[key].checked = true;            
	            }
	        }
	    });
	
	    reverseSelect.addEventListener("click",function(){
	        for (const key in gameSelect) {
	            if (gameSelect.hasOwnProperty(key)) {
	                if(gameSelect[key].checked === true) {
	                    gameSelect[key].checked = false; 
	                } else {
	                    gameSelect[key].checked = true; 
	                }           
	            }
	        }
	    });
	    
	

    </script>

    <script src="http://at.alicdn.com/t/font_783692_gfwpqhoo67.js"></script>
    
  <c:if test="${msg != null}">
  	<script>alert('${msg}')</script>
  </c:if>
    
</html>