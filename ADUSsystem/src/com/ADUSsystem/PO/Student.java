package com.ADUSsystem.PO;
/**
 * 
 * @author ����ΰ
 * @�޸����ڣ�2018��2��12������11:24:19
 * @������ʵ���࣬����ѧ��
 */
public class Student {
	private String stu_ID;
	private String stu_Name;
	private String gender;
	private String stu_Tel;
	
	public String getID() {
		return stu_ID;
	}
	
	public void setID(String stu_ID) {
		this.stu_ID = stu_ID;
	}
	
	public String getName() {
		return stu_Name;
	}
	
	public void setName(String stu_Name) {
		this.stu_Name = stu_Name;
	}
	
	public String getTel() {
		return stu_Tel;
	}
	
	public void setTel(String stu_Tel) {
		this.stu_Tel = stu_Tel;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "Student ["+stu_ID+" "+stu_Name+" "+gender+" "+stu_Tel+"]";
	}
	
	public void InitStudentAllNull() {
		this.stu_ID = null;
		this.stu_Name = null;
		this.stu_Tel = null;
		this.gender = null;
	}
}
