package cn.edu.jxufe.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.jxufe.dao.RoleMapper;
import cn.edu.jxufe.dao.UserMapper;
import cn.edu.jxufe.dao.UserRoleMapper;
import cn.edu.jxufe.entity.Role;
import cn.edu.jxufe.entity.User;
import cn.edu.jxufe.entity.UserRole;

@Controller
public class RoleControl {
	@Autowired
	private UserMapper userDao; //用户接口
	
	@Autowired
	private UserRoleMapper userRoleDao;//用户角色接口
	
	@Autowired
	private RoleMapper roleDao;//角色接口
	
	
	@RequestMapping("/index")
	public String toIndexPage(){
		return "index";
	}
	
	
	@RequestMapping("/roleManager")
	public String toRoleManagerPage(){
		return "role_manager";
	}
	
	
	
	
	
	@RequestMapping("/bindRole/{id}")
	public ModelAndView toBindRolePage(@PathVariable("id")String id){
		Long newId = Long.parseLong(id);
		User user = userDao.selectByPrimaryKey(newId);
		 Map<String,Object> model = new HashMap<String,Object>();  
		 model.put("user", user);
		return new ModelAndView("bindRole", model);
	}
	
	@ResponseBody
	@RequestMapping("/addUser")
	public Object addUser(
			@RequestParam("id")Long id,
			@RequestParam("userName")String userName,
			@RequestParam("password")String password){
		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setPassword(password);
		int insertRow = userDao.insertSelective(user);
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("status", String.valueOf(insertRow));
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/updateUser")
	public Object updateUser(User user){
		int updateRow = userDao.updateByPrimaryKey(user);
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("status", String.valueOf(updateRow));
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/deleteUser/{userId}")
	public Object deleteUser(@PathVariable("userId")Long id){
		int deleteRow = userDao.deleteByPrimaryKey(id);
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("status", String.valueOf(deleteRow));
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/queryUsers")
	public Object queryAllUser(){
		List<User> allUsers = userDao.selectAllUser();
		return allUsers;
	}
	
	
	@ResponseBody
	@RequestMapping("/userRoles")
	public Object queryAllUserRoles(){
		List<UserRole> userRoles = userRoleDao.selectAllUserRole();
		return userRoles;
	}
	
	@ResponseBody
	@RequestMapping("/roles")
	public Object queryAllRoles(){
		List<Role> roles = roleDao.selectAllRoles();
		return roles;
	}
	
	@ResponseBody
	@RequestMapping("/addUserRole/{id}")
	public Object addUserRole(
			@PathVariable("id") String id,
			@RequestParam("roleName") String roleName
			){
		Long newId = Long.parseLong(id);
		User user = userDao.selectByPrimaryKey(newId);
		UserRole record = new UserRole();
		record.setRoleName(roleName);
		record.setUserName(user.getUserName());
		record.setId(newId);
		int insertRow=0;
		try {
			insertRow = userRoleDao.insertSelective(record);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("status", String.valueOf(insertRow));
		return resultMap;
	}
	
	
	@ResponseBody
	@RequestMapping("/updateUserRole/{id}")
	public Object updateUserRole(
			@PathVariable("id") String id,
			@RequestParam("roleName") String roleName
			){
		Long newId = Long.parseLong(id);
		User user = userDao.selectByPrimaryKey(newId);
		
		UserRole record = new UserRole();
		record.setRoleName(roleName);
		record.setUserName(user.getUserName());
		int updateRow = userRoleDao.updateByNameSelective(record);
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("status", String.valueOf(updateRow));
		return resultMap;
	}
	
	
	@ResponseBody
	@RequestMapping("/deleteUserRole/{id}")
	public Object deleteUserRoleById(
			@PathVariable("id") String id
			){
		Long newId = Long.parseLong(id);
		User user = userDao.selectByPrimaryKey(newId);
		int deleteRow = userRoleDao.deleteByUserName(user.getUserName());
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("status", String.valueOf(deleteRow));
		return resultMap;
	}
	
	
	@ResponseBody
	@RequestMapping("/addRole")
	public Object addRole(
			@RequestParam("id") String id,
			@RequestParam("roleName") String roleName
			){
		Role record = new Role();
		record.setId(Long.parseLong(id));
		record.setRoleName(roleName);
		int insertRow = roleDao.insertSelective(record );
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("status", String.valueOf(insertRow));
		return resultMap;
	}
	
	
	@ResponseBody
	@RequestMapping("/deleteRole/{id}")
	public Object addRole(
			@PathVariable("id") String id
			){
		int deleteRow = roleDao.deleteByPrimaryKey(Long.parseLong(id));
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("status", String.valueOf(deleteRow));
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/updateRole")
	public Object updateRole(
			@RequestParam("id") String id,
			@RequestParam("roleName") String roleName
			){
		Role record = new Role();
		record.setId(Long.parseLong(id));
		record.setRoleName(roleName);
		int updateRow = roleDao.updateByPrimaryKey(record);
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("status", String.valueOf(updateRow));
		return resultMap;
	}
	
	
	@ResponseBody
	@RequestMapping("/user/add")
	public Object addUser(@RequestBody User user){
		int insertRow = userDao.insertSelective(user);
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("status", String.valueOf(insertRow));
		return resultMap;
	}
	
	
}
