<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--JQuery插件  -->
<script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.inputItem {
	float: left;
}

.container {
	width: 1200px;
	margin: 0 auto;
	margin-top: 50px;
}
</style>
<title>角色管理页面</title>
</head>
<body>
	<div class="container">
		<div class="inputItem">
			<span>ID：</span> <input type="text" id="roleId" />
		</div>
		<div class="inputItem">
			<span>角色名：</span> <input type="text" id="roleName" />
		</div>
		<div class="inputItem">
			<button id="createRole">创建角色</button>
			<button id="updateRole">修改角色</button>
			<button id="flushResult">刷新</button>
		</div>
		<hr style="clear: both;">
		<div style="text-align: center; padding-left: 300px;">
			<table cellspacing="15" id="rolesTable">
				<thead>
					<th>ID</th>
					<th>角色</th>
					<th>操作</th>
				</thead>
				
			</table>
		</div>
	</div>
</body>
</html>

<script>
		$(function(){
			flushData();//初始化用户数据
			bindCreateRoleEvent();//绑定创建角色事件
			bindUpdateRoleEvent();//绑定更新角色事件
			bindFlushEvent();//绑定刷新事件
		})
		
		function bindCreateRoleEvent(){
		
			$("#createRole").bind("click",function(){
				var id = $("#roleId").val();
				var roleName = $("#roleName").val();
				$.ajax({
					type : "get",
					url : "addRole",
					data:{
						"id":id,
						"roleName":roleName
					},
					success : function(data) {
						if(data.status==1){
							alert("添加成功");
						}
					}
				});
			})
			
		}
		
		function bindUpdateRoleEvent(){
		
			$("#updateRole").bind("click",function(){
				var id = $("#roleId").val();
				var roleName = $("#roleName").val();
				$.ajax({
					type : "get",
					url : "updateRole",
					data:{
						"id":id,
						"roleName":roleName
					},
					success : function(data) {
						if(data.status==1){
							alert("更新成功");
						}
					}
				});
			})
		}
		
		function bindFlushEvent(){
			$("#flushResult").bind("click",function(){
				flushData();
			})
		}
		
		function flushData(){
			$.ajax({
				type : "get",
				url :  "<%=request.getContextPath() %>/roles",
				success : function(data) {
					$("#rolesTable").html("");
					$("#rolesTable").append(
							"<thead>"+
							"<th>ID</th>"+
							"<th>角色</th>"+
							"<th>操作</th>"+
						"</thead>"
							);
					for(var i=0;i<data.length;i++){
						$("#rolesTable").append(
								"<tr>"+
								"<td>"+data[i].id+"</td>"+
								"<td>"+data[i].roleName+"</td>"+
								"<td><button id='deleteRole_"+data[i].id+"'>删除</button></td>"+
						"</tr>"
						);
						bindDeleteRoleEvent(data[i].id);
					}
				}
			});
		}
		
		function bindDeleteRoleEvent(id){
			$("#deleteRole_"+id).bind("click",function(){
				$.ajax({
					type : "get",
					url : "deleteRole/"+id,
					success : function(data) {
						if(data.status==1){
							alert("删除成功");
						}
					}
				});
			});
		}
		
</script>
