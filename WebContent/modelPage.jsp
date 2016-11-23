<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<!--JQuery插件  -->
<script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ModelAttribute测试使用案例</title>
</head>
<body>
<hr>
<div>
	<h3>Model测试1-2</h3>
	<p>ID:${user.id}</p>
	<p>姓名:${user.userName }</p>
	<p>密码:${user.password }</p>
</div>
<hr>
<div>
	<h3>Model测试3</h3>
	<p>ID:${myUser.id}</p>
	<p>姓名:${myUser.userName }</p>
	<p>密码:${myUser.password }</p>
</div>
<hr>
<div>
	<h3>Model测试4</h3>
	<button id="addUserBtn">更新用户</button>
	<p>ID:${myUser.id}</p>
	<p>姓名:${myUser.userName }</p>
	<p>密码:${myUser.password }</p>
</div>
<hr>
<div>
	<h3>Model测试5</h3>
	<form action="<%=request.getContextPath() %>/model5/mapMethod" id="mapForm" method="post">
			<input name="userName" value="刘卜铷"/>
			<input name="password" value="123456"/>
	</form>
	<button id="modelMapBtn">测试@ModelAttribute绑定Map</button>
</div>
</body>
</html>
<script>
		$(function(){
				bindAddUserBtnEvent();
				bindMapBtnEvent();
		})
		
		function bindMapBtnEvent(){
			$("#modelMapBtn").bind("click",function(){
						$("#mapForm").submit();
			})
		}
		
		function bindAddUserBtnEvent(){
			$("#addUserBtn").bind("click",function(){
				var user = new Object();
				var id = 10086;
				user.userName = "刘卜铷";
				var updatUrl = "<%=request.getContextPath() %>/model4/updateUser/"+id;
				   $.ajax({ 
			            type:"POST", 
			            url:updatUrl, 
			            dataType:"json",      
			            contentType:"application/json",               
			            data:JSON.stringify(user), 
			            success:function(data){ 
			            	console.log("更新后的User对象:");
			            	console.log(data)
			            } 
			         }); 
			})			
		}
</script>