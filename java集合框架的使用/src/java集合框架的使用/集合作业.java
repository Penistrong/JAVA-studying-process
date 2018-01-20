package java集合框架的使用;

import java.util.*;
/**
 * 
 * @author 陈立伟
 * @修改日期：2017年12月15日下午4:57:23
 * @描述：使用ArrayList集合，进行简单的登陆系统模拟，查询快，增删慢
 */
public class 集合作业 {
	static Scanner sc = new Scanner(System.in);//构造扫描器对象
	static Collection users = new ArrayList();//储存用户信息的集合
	
	public static void main(String[] args) {
		initAdmin();
		while(true) {
			System.out.println("请选择功能： A.登陆   B.注册  C.信息管理系统(管理员选项)");
			String option = sc.next();
			if(option.equalsIgnoreCase("a")) {
				System.out.println("您选择了登陆功能...");
				Login();
			}else if(option.equalsIgnoreCase("b")) {
				System.out.println("您选择了注册功能...");
				Register();
			}else if(option.equalsIgnoreCase("c")){
				System.out.println("正在进入管理系统");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				AdminManagement();
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
	
	public static void AdminManagement() {
		outer:while(true) {
			System.out.println("Please type your admin's password to log in!");
			String password = sc.next();
			Iterator it = users.iterator();
			while(it.hasNext()) {
				User user = (User) it.next();
				String adminPassWord = user.getPassWord();
				if(user.UserName.equals("admin")&&adminPassWord.equals(password)) {
					System.out.println("Welcome,admin!");
					break outer;
				}else {
					System.out.println("You have typed wrong password!");
					break;
				}
			}
		}
		System.out.println("欢迎您，管理员，请选择管理选项！");
		System.out.println("A.修改用户信息 B.格式化管理系统信息 ");
		String option = sc.next();
		if(option.equalsIgnoreCase("A")) {
			EditUserInformation();
		}else if(option.equalsIgnoreCase("B")) {
			DeleteAll();
		}else {
			System.out.println("无法识别您的指令，将退出到主界面！");
		}
		
	}

	private static void EditUserInformation() {
		System.out.println("当前系统所有注册用户信息如下：");
		System.out.println(users);
		outer:while(true) {
			System.out.println("请输入您要修改的用户ID");
			int id = sc.nextInt();
			int flag = 0;
			Iterator it = users.iterator();
			while(it.hasNext()) {
				User user = (User)it.next();
				if(user.ID == id) {
					System.out.println("请输入您所要更改后的ID！");
					int changeID = sc.nextInt();
					user.ID = changeID;
					System.out.println("修改成功，退回主界面！");
					break outer;
				}else {
					flag = 1;
				}
			}
			if(flag == 1) {
				System.out.println("系统中不存在该用户！请重新输入");
			}
		}
	}
	
	private static void DeleteAll() {
		System.out.println("若您确如此做，将会导致不可挽回的用户流失信息流失！确认吗？1.确认 2.容我想想");
		int option = sc.nextInt();
		switch(option) {
		case 1:
			System.out.println("删除中");
			users.removeAll(users);
			initAdmin();//管理员信息将会被初始化
			break;
		case 2:
			break;
		default:
			System.out.println("无法识别您输入的指令");
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