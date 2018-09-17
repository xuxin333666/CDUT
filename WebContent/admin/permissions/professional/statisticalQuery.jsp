<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <div></div>
<div class="row">
	<div class="col-xs-2">
		<div class="panel-group" id="statisticalQuerySelectTree" style="height: 400px;overflow: auto;">
		    <div class="panel panel-info">
		        <div class="panel-heading">
		            <h4 class="panel-title ">
	                	<a data-toggle="collapse" data-parent="#statisticalQuerySelectTree" href="#statisticalQuerySelectTreeList" onclick="statisticalQueryTreeClick('')">
		            	专业
	                	</a>
	            	</h4>
		        </div>
		        <div id="statisticalQuerySelectTreeList" class="panel-collapse collapse in statisticalTypeSelectCt">
		            <div class="panel-body text-primary statisticalTypeSelect" data-type="bar" onclick="statisticalQueryTreeClick('bar')">柱状图</div>
    		        <div class="panel-body statisticalTypeSelect" data-type="pie" onclick="statisticalQueryTreeClick('pie')">饼图</div>
    		        <div class="panel-body statisticalTypeSelect" data-type="stepLine" onclick="statisticalQueryTreeClick('stepLine')">折线图</div>
		        </div>
		    </div>
		</div>
	</div>
	<div class="col-xs-10">
		<div class="row">
			<div class="col-xs-1"></div>
			<div class="col-xs-8" id="statisticalQueryCt" style="margin-top:30px;height:350px;"></div>
			<div class="col-xs-3"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
	
$(function() {
	setTimeout(function() {
		// 基于准备好的dom，初始化echarts实例
	    var proChart = echarts.init(document.getElementById('statisticalQueryCt'));
	    
	    //获取数据的方法
	    function getDataAndRander(type){
		      $.get('permissions/professional/statisticalQuery?type=' + type).done(function (data) {
		   	 		proChart.clear();
		   	 		proChart.setOption(JSON.parse(data));
		   		});
	    } 
	    
	    // 默认第一个统计类型展开
	    getDataAndRander($(".statisticalTypeSelectCt>.statisticalTypeSelect:first").attr("data-type"));

		//不同样式的统计树点击监听
		function statisticalQueryTreeClick(type) {
			getDataAndRander(type);
		}
		$(".statisticalTypeSelectCt>.statisticalTypeSelect").on("click",function() {
			$(this).addClass("text-primary").siblings().removeClass("text-primary");
			getDataAndRander($(this).attr("data-type"));
		})

	      
	},500);
})
	
  </script>
