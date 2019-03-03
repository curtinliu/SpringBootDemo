package com.xiaour.spring.boot.mapper;

import java.util.List;

import com.xiaour.spring.boot.entity.UserInfo;

public interface UserInfoMapper {

	/**
	 * 插入用户信息表数据
	 * @param userInfo
	 * @return
	 */
    Integer addUserInfo(UserInfo userInfo);
    
    /**
     * 查询所有用户信息
     * @param id
     * @return
     */
    List<UserInfo> selectAllUserInfo();
    
    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    UserInfo selectByPrimaryKey(Integer id);

}