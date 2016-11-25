<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!--JQuery插件  -->
<script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpirngRestful风格测试</title>
</head>
<body>
<button id="getBtn">GET请求</button>
<button id="postBtn">POST请求</button>
<button id="putBtn">PUT请求</button>
<button id="deleteBtn">DELETE请求</button>
<button id="patchBtn">PATCH请求</button>
<button id="headBtn">HEAD请求</button>
<button id="traceBtn">TRACE请求</button>
<button id="optionsBtn">OPTIONS请求</button>
</body>
</html>

<script>
		$(function(){
			bindGetBtnEvent();//get事件
			bindPostBtnEvent();//post事件
			bindPutBtnEvent();//put事件
			bindDeleteBtnEvent();//delete事件
			bindPatchBtnEvent();//patch事件绑定
			bindHeadBtnEvent();//head事件绑定
			bindTraceBtnEvent();//trace事件绑定s
			bindOptinsBtnEvent();//options事件绑定
		})
		
			function bindOptinsBtnEvent(){
			$("#optionsBtn").bind("click",function(){
				$.ajax({
					type:"options",
					url:"rest/optionsMethod",
					data:{},
					success:function(data){
						alert(data.method);
					}
				})
			})
		}
		
		function bindTraceBtnEvent(){
			$("#traceBtn").bind("click",function(){
			console.log("trace click");
				$.ajax({
					type:"post",
					url:"rest/traceMethod",
					data:{
						"_method":"trace"
					},
					success:function(data){
					console.log(data);
					}
				})
			})
		}
		
		function bindHeadBtnEvent(){
			$("#headBtn").bind("click",function(){
				$.ajax({
					type:"post",
					url:"rest/headMethod",
					data:{
						"_method":"head"
					},
					success:function(data){
						console.log(data);
					}
				})
			})
		}
		
		
		function bindPatchBtnEvent(){
			$("#patchBtn").bind("click",function(){
				/* $.ajax({
					type:"patch",
					url:"rest/patchMethod",
					data:{},
					success:function(data){
						alert(data.method);
					}
				}) */
				$.ajax({
					type:"post",
					url:"rest/patchMethod",
					data:{
						"_method":"patch"
					},
					success:function(data){
						alert(data.method);
					}
				})
			})
		}
			function bindDeleteBtnEvent(){
			$("#deleteBtn").bind("click",function(){
				//第一种方式发送delete请求
				/* $.ajax({
					type:"delete",
					url:"rest/deleteMethod",
					data:{},
					success:function(data){
						alert(data.method);
					}
				}) */
				//第二种方式发送delete请求
				$.ajax({
					type:"post",
					url:"rest/deleteMethod",
					data:{
						"_method":"delete"
					},
					success:function(data){
						alert(data.method);
					}
				})
			})
		}
		
		function bindPutBtnEvent(){
			$("#putBtn").bind("click",function(){
			/* 	$.ajax({
					type:"put",
					url:"rest/putMethod",
					data:{},
					success:function(data){
						alert(data.method);
					}
				}) */
				$.ajax({
					type:"post",
					url:"rest/putMethod",
					data:{
						"_method":"put"
					},
					success:function(data){
						alert(data.method);
					}
				})
			})
		}
		function bindPostBtnEvent(){
			$("#postBtn").bind("click",function(){
				$.ajax({
					type:"post",
					url:"rest/postMethod",
					data:{},
					success:function(data){
						alert(data.method);
					}
				})
			})
		}
		
		function bindGetBtnEvent(){
			$("#getBtn").bind("click",function(){
				$.ajax({
					type:"get",
					url:"rest/getMethod",
					data:{},
					success:function(data){
						alert(data.method);
					}
				})
			})
		}
</script>