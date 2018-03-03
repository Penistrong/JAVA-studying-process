package com.ADUSsystem.DAO;

import com.ADUSsystem.PO.User;

/**
 * 
 * @author 陈立伟
 * @修改日期：2018年2月13日上午12:05:11
 * @描述：使用学生信息管理系统的用户，声明其登陆注册的方法
 */
public interface SystemUserDAO {
	public User login(User user);
	public boolean register(User user);
}
