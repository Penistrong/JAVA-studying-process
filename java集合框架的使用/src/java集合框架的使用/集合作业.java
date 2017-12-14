package java集合框架的使用;

import java.util.*;

public class 集合作业 {
	static Scanner sc = new Scanner(System.in);//构造扫描器对象
	static Collection users = new ArrayList();//储存用户信息的集合
	
	public static void main(String[] args) {
		initAdmin();
		while(true) {
			System.out.println("请选择功能： A.登陆   B.注册");
			String option = sc.next();
			if(option.equalsIgnoreCase("a")) {
				System.out.println("您选择了登陆功能...");
				Login();
			}else if(option.equalsIgnoreCase("b")) {
				System.out.println("您选择了注册功能...");
				Register();
			}else {
				System.out.println("无法识别您的指令，请重新输入！");
			}
		}
	}
	
	private static void initAdmin() {
		User admin = new User(001,"admin");
		admin.setPassWord("12345678");
		users.add(admin);
	}
	
	public static void Login() {
			System.out.println("Please input your id:");
			int id = sc.nextInt();
			System.out.println("Please input your password:");
			String password = sc.next();
			Iterator it = users.iterator();
			boolean isLogin = false;
			while(it.hasNext()) {
				User user = (User) it.next();//一个个取出User对象
				String userPassWord = user.getPassWord();
				System.out.println(userPassWord);
				if(user.ID == id&&userPassWord.equals(password)){
					isLogin = true;
					System.out.println("登陆成功！当前登陆用户信息为："+user);
				}
			}
			if(isLogin == true) {
				System.out.println("欢迎登陆...加载中");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				System.out.println("欢迎登入用户信息管理系统！");
			}else {
				System.out.println("登陆失败，重新登陆！");
			}
	}
	
	public static void Register() {
		while(true) {
			int flag = 1;
			System.out.println("Please input your new id:");
			int id = sc.nextInt();
			Iterator it = users.iterator();
			while(it.hasNext()) {
				User user = (User) it.next();
				if(user.ID == id) {
					System.out.println("该账号已存在，请重新输入：！");
					flag = 0;
					break;
				}else {
					flag = 1;
				}
			}
			if(flag == 1) {
				System.out.println("Please input your UserName!");
				String name = sc.next();
				User newUser = new User(id,name);
				System.out.println("Please input your new password!");
				String password = sc.next();
				newUser.setPassWord(password);
				users.add(newUser);//存储newUser至集合中
				System.out.println("注册成功！");
				System.out.println("当前所有注册人员信息为："+users);
				
				break;//打破大循环
			}
		}
	}
}

class User{
	int ID;
	private String PassWord;
	String UserName;
	public User(int ID,String UserName) {
		this.ID = ID;
		this.UserName = UserName;
	}
	
	public void setPassWord(String password) {
		this.PassWord = password;
	}
	
	public String getPassWord() {
		return this.PassWord;
	}
	
	//重写toString方法，使其产生的不再是地址
	public String toString() {
		return "[账户："+this.ID+" 用户名："+this.UserName+"]";
	}
}