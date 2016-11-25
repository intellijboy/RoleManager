/**
 * Copyright (C) 2002-2016 江西财经大学软通学院
 * 文件名：cn.edu.jxufe.controlRestfulStyleControl.java
 * 作  者：刘卜铷
 * 日  期：2016年11月25日 上午9:55:52
 * 描  述：
 * 版  本：1.0
 */
package cn.edu.jxufe.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("rest")
public class RestfulStyleControl {

	@RequestMapping(value="getMethod",method={RequestMethod.GET})
	@ResponseBody
	public Object getMethod(){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("method", "Get 方法");
		return resultMap;
	}
	
	@RequestMapping(value="postMethod",method={RequestMethod.POST})
	@ResponseBody
	public Object postMethod(){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("method", "Post 方法");
		return resultMap;
	}
	
	@RequestMapping(value="putMethod",method={RequestMethod.PUT})
	@ResponseBody
	public Object putMethod(){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("method", "Put 方法");
		return resultMap;
	}
	
	
	@RequestMapping(value="deleteMethod",method={RequestMethod.DELETE})
	@ResponseBody
	public Object deleteMethod(){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("method", "Delete 方法");
		return resultMap;
	}
	
	
	@RequestMapping(value="patchMethod",method={RequestMethod.PATCH})
	@ResponseBody
	public Object patchMethod(){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("method", "Patch 方法");
		return resultMap;
	}
	
	@RequestMapping(value="optionsMethod",method={RequestMethod.OPTIONS})
	@ResponseBody
	public Object optionsMethod(){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("method", "Options 方法");
		return resultMap;
	}
	
	@RequestMapping(value="headMethod",method={RequestMethod.HEAD})
	@ResponseBody
	public Object headMethod(){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("method", "Head 方法");
		return resultMap;
	}
	
	@RequestMapping(value="traceMethod",method={RequestMethod.TRACE})
	@ResponseBody
	public Object traceMethod(){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("method", "Trace 方法");
		return resultMap;
	}

	
	
}
