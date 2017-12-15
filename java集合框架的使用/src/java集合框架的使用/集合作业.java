package java���Ͽ�ܵ�ʹ��;

import java.util.*;

public class ������ҵ {
	static Scanner sc = new Scanner(System.in);//����ɨ��������
	static Collection users = new ArrayList();//�����û���Ϣ�ļ���
	
	public static void main(String[] args) {
		initAdmin();
		while(true) {
			System.out.println("��ѡ���ܣ� A.��½   B.ע��  C.��Ϣ����ϵͳ(����Աѡ��)");
			String option = sc.next();
			if(option.equalsIgnoreCase("a")) {
				System.out.println("��ѡ���˵�½����...");
				Login();
			}else if(option.equalsIgnoreCase("b")) {
				System.out.println("��ѡ����ע�Ṧ��...");
				Register();
			}else if(option.equalsIgnoreCase("c")){
				System.out.println("���ڽ������ϵͳ");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				AdminManagement();
			}else {
				System.out.println("�޷�ʶ������ָ����������룡");
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
				User user = (User) it.next();//һ����ȡ��User����
				String userPassWord = user.getPassWord();
				System.out.println(userPassWord);
				if(user.ID == id&&userPassWord.equals(password)){
					isLogin = true;
					System.out.println("��½�ɹ�����ǰ��½�û���ϢΪ��"+user);
				}
			}
			if(isLogin == true) {
				System.out.println("��ӭ��½...������");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				System.out.println("��ӭ�����û���Ϣ����ϵͳ��");
			}else {
				System.out.println("��½ʧ�ܣ����µ�½��");
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
					System.out.println("���˺��Ѵ��ڣ����������룺��");
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
				users.add(newUser);//�洢newUser��������
				System.out.println("ע��ɹ���");
				System.out.println("��ǰ����ע����Ա��ϢΪ��"+users);
				
				break;//���ƴ�ѭ��
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
				if(user.ID==001&&adminPassWord.equals(password)) {
					System.out.println("Welcome,admin!");
					break outer;
				}else {
					System.out.println("You have typed wrong password!");
					break;
				}
			}
		}
		System.out.println("��ӭ��������Ա����ѡ�����ѡ�");
		System.out.println("A.�޸��û���Ϣ B.��ʽ������ϵͳ��Ϣ ");
		String option = sc.next();
		if(option.equalsIgnoreCase("A")) {
			EditUserInformation();
		}else if(option.equalsIgnoreCase("B")) {
			DeleteAll();
		}
		
	}

	private static void EditUserInformation() {
		System.out.println("��ǰϵͳ����ע���û���Ϣ���£�");
		System.out.println(users);
		while(true) {
			System.out.println("��������Ҫ�޸ĵ��û�ID");
			int id = sc.nextInt();
			int flag = 0;
			Iterator it = users.iterator();
			if(it.hasNext()) {
				User user = (User)it.next();
				if(user.ID == id) {
					System.out.println("����������Ҫ���ĺ��ID��");
					int changeID = sc.nextInt();
					user.ID = changeID;
					System.out.println("�޸ĳɹ����˻������棡");
					break;
				}else {
					flag = 1;
				}
			}
			if(flag == 1) {
				System.out.println("ϵͳ�в����ڸ��û�������������");
			}
		}
	}
	
	private static void DeleteAll() {
		System.out.println("����ȷ����������ᵼ�²�����ص��û���ʧ��Ϣ��ʧ��ȷ����1.ȷ�� 2.��������");
		int option = sc.nextInt();
		switch(option) {
		case 1:
			System.out.println("ɾ����");
			users.removeAll(users);
			break;
		case 2:
			break;
		default:
			System.out.println("�޷�ʶ���������ָ��");
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
	
	//��дtoString������ʹ������Ĳ����ǵ�ַ
	public String toString() {
		return "[�˻���"+this.ID+" �û�����"+this.UserName+"]";
	}
}