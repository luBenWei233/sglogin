package org.sg.lf.sglogin.dao;

import org.sg.lf.sglogin.entity.User;

public interface SGLoginDao {
	//用户或管理员注册
	public int register(User user);
	//用户登陆
	public User login(User user);
	//用户详情
	public User userInfo(String username);
	//查询是否有该用户
	public int isExistUser(User user);
	//修改用户数据
	public int updateUser(User user);
	//删除用户帐号
	public int removeUser(User user);
	//用户封号，更新状态
	public int updateUserForBan(User user);
	//用户封号，更新状态
	public int unUserForBan(String username);
	//初始化玩家在线状态
	public int initUserState();
}
