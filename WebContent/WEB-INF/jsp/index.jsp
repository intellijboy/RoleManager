<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--JQuery插件  -->
<script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统设计——用户角色管理</title>
</head>
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
			<span>ID：</span> <input type="text" id="userId" />
		</div>
		<div class="inputItem">
			<span>用户：</span> <input type="text" id="userName" />
		</div>
		<div class="inputItem">
			<span>密码：</span> <input type="text" id="userPassword" />
		</div>
		<div class="inputItem">
			<button id="createUser">创建用户</button>
			<button id="updateUser">修改用户</button>
			<button id="flushResult">刷新</button>
			<button id="roleManager">用户角色管理</button>
		</div>
		<hr style="clear: both;">
		<div style="text-align: center; padding-left: 300px;">
			<table cellspacing="15" id="userTable">
				<thead>
					<th>ID</th>
					<th>用户名</th>
					<th>密码</th>
					<th>操作</th>
				</thead>
			</table>
		</div>


	</div>

</body>
</html>
<script>
	$(function() {
		flushData();//刷新数据
		bindCreateUserEvent();//创建用户事件
		bindUpdateUserEvent();//更新用户事件
		bindFlushEvent();//刷新用户事件
		bindRoleMangeEvent();//角色绑定事件
	})

	function bindRoleMangeEvent() {
		$("#roleManager").bind("click", function() {
			window.open("<%=request.getContextPath() %>/roleManager");
		})
	}

	function bindFlushEvent() {
		$("#flushResult")
				.bind(
						"click",
						function() {
						flushData();
						})
	}
	
	function flushData(){
		$.ajax({
			type : "get",
			url : "queryUsers",
			success : function(data) {
				$("#userTable").html(
						"<thead>" + "<th>ID</th>"
								+ "<th>用户名</th>"
								+ "<th>密码</th>"
								+ "<th>操作</th>"
								+ "</thead>");
				console.log(data);
				for ( var i = 0; i < data.length; i++) {
					$("#userTable")
							.append(
									"<tr>"
											+ "<td>"
											+ data[i].id
											+ "</td>"
											+ "<td>"
											+ data[i].userName
											+ "</td>"
											+ "<td>"
											+ data[i].password
											+ "</td>"
											+ "<td><button id='deleteUser_"+data[i].id+"'>删除</button></td>"
											+ "<td><button id='bindRole_"+data[i].id+"'>绑定角色</button></td>"
											+ "</tr>");
					bindDeleteUserEvent(data[i].id);
					bindBindRoleEvent(data[i].id);
				}
			}
		});
	}

	//删除用户事件
	function bindDeleteUserEvent(id) {
		$("#bindRole_" + id).bind("click", function() {
		window.open("bindRole/"+id);
		})
	}

	//绑定角色事件
	function bindBindRoleEvent(id) {
		console.log("id==>"+id);
		$("#deleteUser_" + id).bind("click", function() {
			$.ajax({
				type : "get",
				url : "deleteUser/" + id,
				success : function(data) {
					if (data.status == 1) {
						alert("删除成功!!");
					}
				}
			});
		})
	}

	function bindCreateUserEvent() {
		$("#createUser").bind("click", function() {
			var id = $("#userId").val();
			var name = $("#userName").val();
			var password = $("#userPassword").val();
			console.log("id" + id + "name" + name);
			$.ajax({
				type : "get",
				url : "addUser",
				dataType : "json",
				data : {
					"id" : id,
					"userName" : name,
					"password" : password
				},
				success : function(data) {
					if (data.status == 1)
						alert("添加成功");
				}
			});
		})
	}

	function bindUpdateUserEvent() {
		$("#updateUser").bind("click", function() {
			var id = $("#userId").val();
			var name = $("#userName").val();
			var password = $("#userPassword").val();
			console.log("id" + id + "name" + name);
			$.ajax({
				type : "get",
				url : "updateUser",
				dataType : "json",
				data : {
					"id" : id,
					"userName" : name,
					"password" : password
				},
				success : function(data) {
					if (data.status == 1)
						alert("更新成功");
				}
			});
		})
	}
</script>