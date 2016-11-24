/**
 * Copyright (C) 2002-2016 江西财经大学软通学院
 * 文件名：cn.edu.jxufe.controlFileUploadControl.java
 * 作  者：刘卜铷
 * 日  期：2016年11月23日 下午6:14:49
 * 描  述：
 * 版  本：1.0
 */
package cn.edu.jxufe.control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping("upload")
public class FileUploadControl {
	private String savePath = "D:/fileUpload";

	/**
	 * 方式1：只支持单个文件上传
	 * @author 刘卜铷
	 * @date 2016年11月24日 上午9:19:19
	 * @param file
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("method1")
	@ResponseBody
	public Object uploadFileMethod1(
			@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request) throws IOException {
		long startTime = System.currentTimeMillis();
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {// 如果文件不存在，那么就创建文件夹
			fileSaveDir.mkdir();
		}
		File fileCompletePath = new File(savePath + "/"
				+ file.getOriginalFilename());
		InputStream inputStream = file.getInputStream();
		BufferedInputStream bfIStream = new BufferedInputStream(
				file.getInputStream());
		BufferedOutputStream bfOStream = new BufferedOutputStream(
				new FileOutputStream(fileCompletePath));
		// 使用高效封装的Stream流进行写到磁盘
		int len;
		byte bys[] = new byte[1024];
		while ((len = bfIStream.read(bys)) != -1) {
			bfOStream.write(bys, 0, len);
		}
		// 释放资源
		bfIStream.close();
		bfOStream.close();
		long endTime = System.currentTimeMillis();

		Map<String, String> resultMap = new HashMap<String, String>();
		if (fileCompletePath.length() > 0) {
			resultMap.put(fileCompletePath.getName(), "success");
		} else {
			resultMap.put("status", "fail");
		}
		resultMap.put("wastTime", (endTime - startTime) + "ms");
		return resultMap;
	}

	/**
	 * 方式2：单个文件上传
	 * @author 刘卜铷
	 * @date 2016年11月24日 上午9:19:59
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("method2")
	@ResponseBody
	public Object uploadFileMethod2(@RequestParam("file") CommonsMultipartFile file) throws IllegalStateException, IOException {
		long startTime = System.currentTimeMillis();
		String filePath  = savePath +"/"+file.getOriginalFilename();
		File newFile = new File(filePath);
		file.transferTo(newFile);
		long endTime = System.currentTimeMillis();
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("wastTime", (endTime - startTime) + "ms");
		if(newFile.length()>0){
			resultMap.put("status", "success");
		}
		return resultMap;
	}
	/**
	 * 方式2：多个文件上传
	 * @author 刘卜铷
	 * @date 2016年11月24日 上午9:19:59
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("method22")
	@ResponseBody
	public Object uploadFileMethod22(@RequestParam("file") CommonsMultipartFile[] files) throws IllegalStateException, IOException {
		long startTime = System.currentTimeMillis();
		if(files!=null&&files.length>0){
			for (CommonsMultipartFile multipartFile : files) {
				String fileName  =savePath+"/"+multipartFile.getOriginalFilename();
				multipartFile.transferTo(new File(fileName));
			}
		}
		long endTime = System.currentTimeMillis();
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("wastTime", (endTime - startTime) + "ms");
		resultMap.put("status", "success");
		return resultMap;
	}
	
	

	/**
	 * 方式3：支持多文件上传
	 * @author 刘卜铷
	 * @date 2016年11月24日 上午9:19:01
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("method3")
	@ResponseBody
	public Object uploadFileMethod3(HttpServletRequest request)
			throws IllegalStateException, IOException {
		long startTime = System.currentTimeMillis();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		Map<String, String> resultMap = new HashMap<String, String>();//返回JSON结果
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile multipartFile = multiRequest.getFile(iter.next()
						.toString());
				if (multipartFile != null) {
					String path = savePath + "/"+ multipartFile.getOriginalFilename();
					// 上传
					multipartFile.transferTo(new File(path));
					if(new File(path).length()>0){//如果文件大小大于0，说明文件上传成功
						resultMap.put(savePath, "success");
					}
				}
			}
		}
		long endTime = System.currentTimeMillis();
		
		resultMap.put("wastTime", (endTime - startTime) + "ms");
		return resultMap;
	}

}
