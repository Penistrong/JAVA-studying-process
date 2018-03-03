package com.ADUSsystem.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.ADUSsystem.DatabaseLayer.DataBaseUtils;
import com.ADUSsystem.PO.User;

public class UserLRDAO implements SystemUserDAO{

	@Override
	public User login(User user) {
		Connection connection = null;
		PreparedStatement command = null;
		ResultSet rs = null;
		DataBaseUtils dbu = new DataBaseUtils();
		try {
			connection = dbu.getConnection();
			String sql = "select * from ADUSuserINFO where username = ? and password = ?";
			command = connection.prepareStatement(sql);
			command.setString(1, user.getUserName());
			command.setString(2, user.getPassWord());
			rs = command.executeQuery();//执行查询
			User users = null;
			if(rs.next()) {
				users = new User();
				users.setUserName(rs.getString("UserName"));
				users.setPassWord(rs.getString("PassWord"));
				users.setUserType(rs.getString("UserType"));
				
				return users;
			}else {
				return null;
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			dbu.ReleaseResource();
		}
		
		return null;
	}

	@Override
	/**注册用户
	 * @param user
	 */
	public boolean register(User user) {
		String sql = "insert into ADUSuserINFO values(?,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(user.getUserName());
		list.add(user.getPassWord());
		list.add("普通用户");//UserType默认为普通用户
		
		
		DataBaseUtils dbu = new DataBaseUtils();
		boolean isRegisterSuccess = dbu.startAUD(sql, list.toArray());
		if(isRegisterSuccess) {
			return true;
		}else {
			return false;
		}
	}
}
