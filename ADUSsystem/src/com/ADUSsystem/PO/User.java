package com.ADUSsystem.PO;

public class User {
	private String userName;
	private String passWord;
	private String userType;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassWord() {
		return passWord;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	

	

	@Override
	public String toString() {
		return "User ["+userName+" "+passWord+" "+userType+"]";
	}
}
