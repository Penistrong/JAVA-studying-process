package com.ADUSsystem.DatabaseLayer;

import java.sql.*;


public class DataBaseUtils {
	private static final String USERNAME = "C##Penistrong";
	private static final String PASSWORD = "chenliwei";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	Connection connection = null;
	PreparedStatement command = null;
	ResultSet rs = null;
	
	/**
	 * 判断是否登录成功的方法
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public boolean isLoginIn(String userName,String passWord) {
		connection = getConnection();
		String sql = "select * from accountsINFO where accountID = ? and password = ?";
		boolean returnValue = false;
		try {
			command = connection.prepareStatement(sql);
			command.setString(1, userName);
			command.setString(2, passWord);
			rs = command.executeQuery();
			if(rs.next()) {
				returnValue = true;
			}else {
				returnValue = false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			ReleaseResource();
		}
		return returnValue;
	}
	
	/**同意对数据库数据进行增删改的方法(非查询)A/add;U/update;D/delete
		@param sql
		@param arr
		@return 
	*/
	public boolean startAUD(String sql,Object[] arr) {
		try {
			connection = getConnection();
			command = connection.prepareStatement(sql);
			if(arr!=null && arr.length!=0) {
				//若有参数，则将参数装配进语句
				for(int i = 0;i<arr.length;i++) {
					command.setObject(i+1, arr[i]);
				}
			}
			int count = command.executeUpdate();//执行sql语句,返回更新的记录的条目数
			if(count>0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;//当未执行时默认返回false
	}
	
	public ResultSet startQuery(String sql,Object[] arr) {
		try {
			connection = getConnection();
			command = connection.prepareStatement(sql);
			if(arr!=null && arr.length!=0) {
				for(int i = 0;i<arr.length;i++) {
					command.setObject(i+1, arr[i]);
				}
			}
			ResultSet rs = command.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//加载驱动程序后连接数据库
	public Connection getConnection() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch(Exception e) {
			throw new RuntimeException("填入提示信息？");
		}
		return connection;
	}
	
	//释放资源，只在需要的时候访问数据库
	public void ReleaseResource() {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if(command!=null) {
			try {
				command.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
