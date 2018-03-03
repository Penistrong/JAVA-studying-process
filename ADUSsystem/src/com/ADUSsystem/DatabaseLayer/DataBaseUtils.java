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
	 * �ж��Ƿ��¼�ɹ��ķ���
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
	
	/**ͬ������ݿ����ݽ�����ɾ�ĵķ���(�ǲ�ѯ)A/add;U/update;D/delete
		@param sql
		@param arr
		@return 
	*/
	public boolean startAUD(String sql,Object[] arr) {
		try {
			connection = getConnection();
			command = connection.prepareStatement(sql);
			if(arr!=null && arr.length!=0) {
				//���в������򽫲���װ������
				for(int i = 0;i<arr.length;i++) {
					command.setObject(i+1, arr[i]);
				}
			}
			int count = command.executeUpdate();//ִ��sql���,���ظ��µļ�¼����Ŀ��
			if(count>0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;//��δִ��ʱĬ�Ϸ���false
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
	
	//��������������������ݿ�
	public Connection getConnection() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch(Exception e) {
			throw new RuntimeException("������ʾ��Ϣ��");
		}
		return connection;
	}
	
	//�ͷ���Դ��ֻ����Ҫ��ʱ��������ݿ�
	public void ReleaseResource() {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		if(command!=null) {
			try {
				command.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}
