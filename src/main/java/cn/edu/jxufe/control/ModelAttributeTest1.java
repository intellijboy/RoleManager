/**
 * Copyright (C) 2002-2016 江西财经大学软通学院
 * 文件名：cn.edu.jxufe.controlModelAttributeTest.java
 * 作  者：刘卜铷
 * 日  期：2016年11月23日 下午1:50:55
 * 描  述：@ModelAttribute注解的使用详解
 * 版  本：1.0
 */
package cn.edu.jxufe.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.jxufe.entity.User;

/**
 * 1.@ModelAttribute注释void返回值的方法
 * @author 刘卜铷
 * @date 2016年11月23日 下午1:55:39
 */
@Controller
@RequestMapping("model1")
public class ModelAttributeTest1 {
	
	@ModelAttribute
	public void voidModelAttrMethod(@PathVariable(value="userId") String id,Model model){
		User user = new User();
		user.setId(Long.parseLong(id));
		user.setPassword("voidPwd");
		user.setUserName("刘卜铷");
		model.addAttribute("user", user);
		System.out.println("@ModelAttribute==>"+user);
	}
	
	@RequestMapping("modelPage/{userId}")
	public String toModelPage(){
		return "forward:/modelPage.jsp";
	}
}
