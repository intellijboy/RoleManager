<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SpringMVC文件上传测试</title>
</head>
<body>
	<hr>
	<h2>实例1：使用IO流</h2>
	<form action="upload/method1" method="post"  enctype="multipart/form-data">
		选择文件:<input type="file" name="file"> 
		<input type="submit" 	value="提交">
	</form>
		<hr>
	<h2>实例2：使用commonsMultipartFile.transferTo()单个文件上传</h2>
	<form action="upload/method2" method="post"  enctype="multipart/form-data">
		选择文件:<input type="file" name="file"> 
		<input type="submit" 	value="提交">
	</form>
	<h2>实例22：使用commonsMultipartFile.transferTo()多个文件上传</h2>
	<form action="upload/method22" method="post"  enctype="multipart/form-data">
		选择文件:<input type="file" name="file"> 
		选择文件:<input type="file" name="file"> 
		<input type="submit" 	value="提交">
	</form>
	<hr>
	<h2>实例3：springMVC单个自带的上传方式</h2>
	<form action="upload/method3" method="post"  enctype="multipart/form-data">
		选择文件:<input type="file" name="file1"> 
		<input type="submit" 	value="提交">
	</form>
	<hr>
	<h2>实例33：springMVC多个自带的上传方式</h2>
	<form action="upload/method3" method="post"  enctype="multipart/form-data">
		选择文件:<input type="file" name="file1"> 
		选择文件:<input type="file" name="file2"> 
		<input type="submit" 	value="提交">
	</form>
</body>
</html>