<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!--JQuery插件  -->
<script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试页面</title>
</head>
<body>
	<button id="stirngBtn">字符串</button>
	<button id="entityBtn">单个对象</button>
	<button id="entitysBtn">多个对象</button>
	<button id="jsonBtn">Json</button>
	<button id="putDelBtn">Ajax测试PUT请求</button>
	
	<form action="demo/requestParam" method="post">
    <input type="hidden" name="_method" value="PUT">
    <input type="text" name="name" value="liuburu">
    <input type="submit" value="Form发送PUT请求">
</form>
	
</body>
</html>
<script type="text/javascript">
		$(function(){
			$("#stirngBtn").bind("click",function(){//字符串测试
				bindGetStringEvent();
			});
			
			$("#entityBtn").bind("click",function(){//单个对象测试
				bindGetUserEvent();
			});
			
			$("#entitysBtn").bind("click",function(){//多个对象测试
				bindGetUsersEvent();
			});
			
			$("#jsonBtn").bind("click",function(){//返回JSON数据测试
				bindGetJsonEvent();
			});
			
			$("#putDelBtn").bind("click",function(){
				bindPutDelEvent();
			})
		
			
		})		
		function bindPutDelEvent(){
			console.log("putDelBtn click");
			  $.ajax({ 
		            type:"put", 
		            url:"demo/requestParam?name=刘卜铷", 
		            data:{}, 
		            success:function(data){ 
		            	console.log(data);
		            } 
		         }); 
		}
		
		
		function bindGetUsersEvent(){
			 	var saveDataAry=[];  
		        var data1={"userName":"test","id":"101","password":"123456"};  
		        var data2={"userName":"quu","id":"103","password":"4dfsd65"};  
		        saveDataAry.push(data1);  
		        saveDataAry.push(data2);         
		        $.ajax({ 
		            type:"POST", 
		            url:"demo/addUsers", 
		            dataType:"json",      
		            contentType:"application/json",               
		            data:JSON.stringify(saveDataAry), 
		            success:function(data){ 
		            	console.log(data)
		            } 
		         }); 
		}
		
		function bindGetStringEvent(){
			console.log("字符串");
			var user = new Object();
			user.userName = "刘卜铷";
			user.password = "123456"
			var jsonStrData = JSON.stringify(user);
			console.log(jsonStrData);
			$.ajax({
				type : "get",
				url : "demo/getString",
				data:{
					"userName":"刘卜铷",
					"password":"123456"
					},
				 dataType:"json",      
		         contentType:"application/json",  
				success : function(data) {
					console.log(data);
				}
			});
		}
		
		function bindGetUserEvent(){
			console.log("发送的Json对象:");
		//	var data={"id":"133846","userName":"刘卜铷","password":"123456"}; 
			//var jsonData = JSON.stringify(data);
		//	console.log(jsonData);
		var user = new Object();
		user.id = '133846';
		user.userName = "刘卜铷";
		user.password = "123456"
			$.ajax({
				type : "put",
				url : "demo/addUser",
				 dataType:"json",      
		         contentType:"application/json",  
				//	data:{"id":"123","userName":"刘卜铷","password":"123456"},//错误，因为@RequestBody不接受Json字符串对象
				 data:JSON.stringify(user),
				success : function(data) {
					console.log(data);
				}
			});
		}
		
		function bindGetJsonEvent(){
			console.log("对象");
			$.ajax({
				type : "get",
				url : "demo/getJson",
				success : function(data) {
					console.log(data);
				}
			});
		}
</script>

