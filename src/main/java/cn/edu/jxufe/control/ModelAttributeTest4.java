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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.edu.jxufe.entity.User;

/**
 *  ModelAttribute对象合并
 * @author 刘卜铷
 * @date 2016年11月23日 下午2:23:26
 */
@Controller
@RequestMapping("model4")
public class ModelAttributeTest4 {
	
	@ModelAttribute(value = "myUser")//指定属性名，会替代默认属性名user
	public User modelAttrMethod(@PathVariable(value="userId") String id){
		User user = null;
		long temp = Long.parseLong(id);
		if(temp ==10086){
			user = new User();
			user.setId(temp);
			user.setPassword("voidPwd-"+temp);//密码从后台
			user.setUserName("朱灵燕"+temp);  
		}
		System.out.println("@ModelAttribute==>"+JSON.toJSONString(user));
		return user;//model属性的名称没有指定，它由返回类型隐含表示
	}
	
	@RequestMapping(value="updateUser/{userId}",method={RequestMethod.POST})
	@ResponseBody
	public User updateUser(
			@ModelAttribute(value="myUser") User user,//@ModelAttribute域中的对象
			@RequestBody User updateUser//前段发送过来的对象
			){
		System.out.println("修改的用户对象为:"+updateUser);
		user.setUserName(updateUser.getUserName());
		return user;
	}
	
	
	@RequestMapping("modelPage/{userId}")
	public String toModelPage(){
		return "forward:/modelPage.jsp";
	}
}
