package cn.edu.jxufe.dao;

import java.util.List;

import cn.edu.jxufe.entity.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);
    
    List<UserRole> selectAllUserRole();

    int updateByNameSelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    
    int deleteByUserName(String userName);
    
}