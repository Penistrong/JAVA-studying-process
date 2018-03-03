package com.ADUSsystem.TO;
/**
 * 
 * @author 陈立伟
 * @修改日期：2018年2月18日下午9:45:43
 * @描述：用于保存与传递信息的实体类
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
