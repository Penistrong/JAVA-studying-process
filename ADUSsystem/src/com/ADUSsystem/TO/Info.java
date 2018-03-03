package com.ADUSsystem.TO;
/**
 * 
 * @author ����ΰ
 * @�޸����ڣ�2018��2��18������9:45:43
 * @���������ڱ����봫����Ϣ��ʵ����
 */
public class Info {
	private String MainInfo;
	private Integer InfoType;  
	
	public static final Integer LoginSuccess = 1;
	public static final Integer LoginFail = 2;
	public static final Integer RegisterSuccess = 3;
	public static final Integer RegisterFail = 4;
	
	public String getMainInfo() {
		return MainInfo;
	}
	
	public void setMainInfo(String MainInfo) {
		this.MainInfo = MainInfo;
	}
	
	public Integer getInfoType() {
		return InfoType;
	}
	
	public void setInfoType(Integer InfoType) {
		this.InfoType = InfoType;
	}
	
	public Info(String MainInfo,Integer InfoType) {
		this.MainInfo = MainInfo;
		this.InfoType = InfoType;
	}
	
	@Override
	public String toString() {
		return "[INFO:"+MainInfo+"|"+InfoType+"]";
	}
}
