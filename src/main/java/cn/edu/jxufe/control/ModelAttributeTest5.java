/**
 * Copyright (C) 2002-2016 江西财经大学软通学院
 * 文件名：cn.edu.jxufe.controlModelAttributeTest2.java
 * 作  者：刘卜铷
 * 日  期：2016年11月23日 下午2:23:09
 * 描  述：@ModelAttribute注解的使用详解
 * 版  本：1.0
 */
package cn.edu.jxufe.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  ModelAttribute修饰Map对象
 * @author 刘卜铷
 * @date 2016年11月23日 下午2:23:26
 */
@Controller
@RequestMapping("model5")
public class ModelAttributeTest5 {
	
	@ModelAttribute(value="myMap")
	public Map<String,String> modelAttrMethod(){
		System.out.println("@ModelAttribute is working....");
		Map<String,String> map  = new HashMap<String,String>();
		map.put("name", "刘卜铷map");
		map.put("age", "23");
		return map;
	}
	
	@RequestMapping(value="mapMethod",method={RequestMethod.POST})
	@ResponseBody
	public Object updateUser(
			@ModelAttribute("myMap") Map<String,String> map
			){
		return map;
	}
	
	@RequestMapping("modelPage")
	public String toModelPage(){
		return "forward:/modelPage.jsp";
	}
}
