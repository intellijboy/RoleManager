/**
 * Copyright (C) 2002-2016 江西财经大学软通学院
 * 文件名：cn.edu.jxufe.controlModelAttributeTest2.java
 * 作  者：刘卜铷
 * 日  期：2016年11月23日 下午2:23:09
 * 描  述：@ModelAttribute注解的使用详解
 * 版  本：1.0
 */
package cn.edu.jxufe.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import cn.edu.jxufe.entity.User;

/**
 *  ModelAttribute注释返回具体类的方法
 * @author 刘卜铷
 * @date 2016年11月23日 下午2:23:26
 */
@Controller
@RequestMapping("model2")
public class ModelAttributeTest2 {
	@ModelAttribute
	public User modelAttrMethod(@PathVariable(value="userId") String id,Model model){
		User user = new User();
		user.setId(Long.parseLong(id));
		user.setPassword("voidPwd");
		user.setUserName("刘卜铷");
		System.out.println("@ModelAttribute==>"+JSON.toJSONString(user));
		return user;//model属性的名称没有指定，它由返回类型隐含表示
	}
	
	@RequestMapping("modelPage/{userId}")
	public String toModelPage(){
		return "forward:/modelPage.jsp";
	}
}
