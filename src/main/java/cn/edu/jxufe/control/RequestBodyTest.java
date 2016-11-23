package cn.edu.jxufe.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.jxufe.entity.User;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("demo")
public class RequestBodyTest {

	@RequestMapping("/requestBodyDemo")
	public String toTestPage() {
		return "redirect:/requestBodyDemo.jsp";
	}

	/**
	 * JSON字符串映射到String类型
	 * @author 刘卜铷
	 * @date 2016年11月23日 上午9:18:50
	 * @param userName
	 * @param pwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getString", method = RequestMethod.GET)
	public String acceptString(
			@RequestParam("userName") String userName,
			@RequestParam("password") String pwd) {
		System.out.println(userName + " ：" + pwd);
		return "success getString";
	}

	/**
	 * 注意：此处的请求方式为非GET的请求方式，既可以为POST，PUT，DELETE方式
	 * JSON字符串映射到对象
	 * @author 刘卜铷
	 * @date 2016年11月23日 上午9:19:00
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addUser", method = { RequestMethod.PUT })
	@ResponseBody
	public Object acceptEntity(@RequestBody User user) {
		System.out.println("User==>" + user);
		return user;
	}
	
	/**
	 * JSON数据映射到对象数组
	 * @author 刘卜铷
	 * @date 2016年11月23日 上午9:19:38
	 * @param users
	 * @return
	 */
	@RequestMapping(value = "addUsers", method = { RequestMethod.POST })
	@ResponseBody
	public Object saveUser(@RequestBody List<User> users) {
		System.out.println("Users==>" + JSON.toJSONString(users));
		return users;
	}


	/**
	 * 返回JSON格式数据
	 * @author 刘卜铷
	 * @date 2016年11月23日 上午9:19:51
	 * @return
	 */
	@RequestMapping("/getJson")
	@ResponseBody
	public User getJson() {
		User user = new User();
		user.setId(10086l);
		user.setUserName("刘卜铷");
		user.setPassword("213456");
		return user;
	}
	
	/**
	 * 如果使用PUT的请求参数乱码，则使用过滤器CharacterEncodingFilter；
	 * 如果使用PUT的返回数据乱码，则使用poroduces指定返回格式并且指定UTF-8编码
	 * 注意：web.xml编码过滤器的先后顺序CharacterEncodingFilter>HiddenHttpMethodFilter
	 * @author 刘卜铷
	 * @date 2016年11月23日 下午1:35:02
	 * @param userName
	 * @return
	 */
	@RequestMapping(value="/requestParam",method={RequestMethod.PUT}, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object getRequedstParam(@RequestParam("name") String userName){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("name", userName);
		return resultMap;
	}
	

	
}
