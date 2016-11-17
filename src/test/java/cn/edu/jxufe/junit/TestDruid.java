package cn.edu.jxufe.junit;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.edu.jxufe.dao.RoleMapper;
import cn.edu.jxufe.dao.UserMapper;
import cn.edu.jxufe.dao.UserRoleMapper;
import cn.edu.jxufe.entity.Role;
import cn.edu.jxufe.entity.User;
import cn.edu.jxufe.entity.UserRole;

/**
 * @author 刘卜铷
 *2016年11月7日 下午8:06:18
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class TestDruid {
	
		@Autowired
		private  DataSource dataSource;
		
		@Autowired
		private UserMapper userMapper;
		
		@Autowired
		private RoleMapper roleMapper;
		
		@Autowired
		private UserRoleMapper userRoleDao;
	
		@Test
		public void testGetDataSource() throws SQLException{
			System.out.println(dataSource.getConnection());
		}
		@Test
		public void mapper() throws SQLException{
			System.out.println(roleMapper.selectByPrimaryKey(1006l));
		}
		@Test
		public void addUser() throws SQLException{
			User user = new User();
			user.setId(123456L);
			user.setUserName("myname");
			user.setPassword("123456");
			int insertRow = userMapper.insertSelective(user);
			System.out.println(insertRow);
		}
		@Test
		public void selectUser() throws SQLException{
			System.out.println(JSON.toJSON(userMapper.selectAllUser()));
		}
		@Test
		public void updateByNameSelective() throws SQLException{
			UserRole record = new UserRole();
			record.setUserName("yes");
			record.setRoleName("会员");
			userRoleDao.updateByNameSelective(record );
		}
		@Test
		public void selectAllRoles() throws SQLException{
			List<Role> selectAllRoles = roleMapper.selectAllRoles();
			System.out.println(JSON.toJSON(selectAllRoles));
		}
		
		@Test
		public void deleteRow() throws SQLException{
			int deleteRow= userRoleDao.deleteByUserName("yes");
			System.out.println(JSON.toJSON(deleteRow));
		}
		
		
}
