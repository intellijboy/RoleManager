<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色绑定页面</title>
</head>
<!--JQuery插件  -->
<script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
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
<body>
	<div class="container">
		<div class="inputItem">
			<span>ID：</span> <input type="text" id="userId"  value="${user.id}"/>
		</div>
		<div class="inputItem">
			<span>用户：</span> <input type="text" id="userName" value="${user.userName}"/>
		</div>
		<div class="inputItem">
			<span>角色：</span> 
			<select id="rolesSelect">
			</select>
		</div>
		<div class="inputItem">
			<button id="addBindUserBtn">添加角色绑定</button>
			<button id="updateUserBindBtn">修改角色绑定</button>
			<button id="flushResult">刷新</button>
		</div>
		<hr style="clear:both;">
			<div style="text-align: center; padding-left: 300px;">
			<table cellspacing="15" id="userRoleTable">
				<thead>
					<th>ID</th>
					<th>用户名</th>
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
			initSelectRoles();//初始化回显的所有角色
			flushTable();//刷新权限表中的数据
			bindAddRoleBtnEvent();//添加角色事件
			bindUpdateRoleBtnEvent();//更新角色事件
			bindFlushResultBtnEvent();//刷新按钮事件
		})
		
		function bindFlushResultBtnEvent(){
			$("#flushResult").bind("click",function(){
				flushTable();
			})
		}
		
		function flushTable(){
			$.ajax({
				type : "get",
				url : "<%=request.getContextPath() %>/userRoles",
				success : function(data) {
					$("#userRoleTable").html("");
					for(var i=0;i<data.length;i++){
						$("#userRoleTable").append(
								"<tr>"+
								"<td>"+data[i].id+"</td>"+
								"<td>"+data[i].userName+"</td>"+
								"<td>"+data[i].roleName+"</td>"+
								"<td><button id='deleteUserRole_"+data[i].id+"'>删除</button></td>"+
							"</tr>"
						);
						bindUserRoleDeleteEvent(data[i].id);
					}
				}
			});
		}
		
		
		function bindUserRoleDeleteEvent(id){
			$("#deleteUserRole_"+id).bind("click",function(){
				$.ajax({
					type : "get",
					url : "<%=request.getContextPath() %>/deleteUserRole/"+id,
					success : function(data) {
						if (data.status == 1) {
							alert("删除权限成功!!");
						}else{
							alert("删除权限失败");
						}
					}
				});
			})
		}
		
		function bindUpdateRoleBtnEvent(){
			$("#updateUserBindBtn").bind("click",function(){
			var id = $("#userId").val();
			var roleName = $("#rolesSelect").val();
			console.log("id="+id+"  test=="+roleName);
			$.ajax({
				type : "get",
				url : "<%=request.getContextPath() %>/updateUserRole/"+id,
				data:{
					"roleName":roleName
				},
				success : function(data) {
					if (data.status == 1) {
						alert("更新"+roleName+"管理员成功!!");
					}
				}
			});
			})
		}
		
		function initSelectRoles(){
			$.ajax({
				type : "get",
				url : "<%=request.getContextPath() %>/roles",
				success : function(data) {
					console.log(data);
					$("#rolesSelect").html("");
					for(var i=0;i<data.length;i++){
						$("#rolesSelect").append("<option>"+data[i].roleName+"</option>");
					}
				}
			});
		}
		
		//绑定动作
		function bindAddRoleBtnEvent(){
			$("#addBindUserBtn").bind("click",function(){
				var id = $("#userId").val();
				var roleName = $("#rolesSelect").val();
			
				$.ajax({
					type : "get",
					url : "<%=request.getContextPath() %>/addUserRole/" + id,
					data:{
						"roleName":roleName
					},
					success : function(data) {
						if (data.status == 1) {
							alert("添加"+roleName+"管理员成功!!");
						}else{
							alert("权限添加失败，repeat role..");
						}
					}
				});
			})
		}
</script>