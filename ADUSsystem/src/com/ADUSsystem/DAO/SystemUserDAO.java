package com.ADUSsystem.DAO;

import com.ADUSsystem.PO.User;

/**
 * 
 * @author ����ΰ
 * @�޸����ڣ�2018��2��13������12:05:11
 * @������ʹ��ѧ����Ϣ����ϵͳ���û����������½ע��ķ���
 */
public interface SystemUserDAO {
	public User login(User user);
	public boolean register(User user);
}
