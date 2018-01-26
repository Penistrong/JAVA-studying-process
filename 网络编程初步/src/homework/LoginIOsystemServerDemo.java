package homework;

import java.io.*;
import java.net.*;
import java.util.Properties;

public class LoginIOsystemServerDemo extends Thread{
	
	Socket socket;//内部维护的套接字
	
	//获取服务器在本地存储的账户记录
	static File file = new File("E://java作品//HomeWork//Accounts.properties");
	
	public LoginIOsystemServerDemo(Socket socket) {
		// TODO 自动生成的构造函数存根
		this.socket = socket;
	}
	static {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		//核心代码
		while(true) {
			try {
				String choice = "1";
				//获取到Socket的输入流
				BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//获取到Socket的输出流
				OutputStreamWriter socketOut = new OutputStreamWriter(socket.getOutputStream());
			
				//读取客户端传来的UserINFO
				String userINFO = socketReader.readLine();
				String[] datas = userINFO.split(" ");
				//获取客户端传来的信息标识符(放在userINFO头位,标志为登录或注册)
				String option = datas[0];
				//获取用户名称
				String userName = datas[1];
				//获取用户密码
				String password = datas[2];
			
				System.out.println("来自"+socket.getInetAddress().getHostAddress()+"的信息: "+"["+option+"]"+userName+"|"+password);
			
				if("a".equalsIgnoreCase(option)) {
					//选择了登录功能
					Properties properties = new Properties();
					//加载配置文件
					properties.load(new FileReader(file));
					if(properties.containsKey(userName)) {
						String LocalPassword = (String) properties.getProperty(userName);
						if(LocalPassword.equals(password)) {
							socketOut.write("登入成功!"+"欢迎,"+userName+"\r\n");
							choice = "1";
						}else {
							socketOut.write("密码错误!\r\n");
							choice = "2";
						}	
					}else {
						socketOut.write("用户名不存在,请重新输入!\r\n");
						choice = "2";
					}
					socketOut.flush();
				
				}else if("b".equalsIgnoreCase(option)) {
					//选择了注册功能
					//创建配置文件类(要追加之前的数据)
					Properties properties = new Properties();
					//加载原来的配置文件
					properties.load(new FileReader(file));
					if(!properties.containsKey(userName)) {
						//不存在用户名时
						properties.setProperty(userName, password);
						//生成配置文件
						properties.store(new FileWriter(file), "accounts");
						socketOut.write("注册成功\r\n");
						choice = "1";
					}else {
						//存在用户名时
						socketOut.write("该用户名已被注册,请重新输入\r\n");
						choice = "2";
					}
					socketOut.flush();
				}
				sendServerINFO(socketOut,choice,userName);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} 
		}
	}
	
	public static void main(String[] args) throws IOException {
		//建立TCP服务端
		ServerSocket serverSocket = new ServerSocket(9090);
		boolean flag = true;
		while(flag) {
			Socket socket = serverSocket.accept();
			//对每个连接的用户创建一个线程服务于他
			new LoginIOsystemServerDemo(socket).start();
			//socket.close();//关闭为每一位连接的用户创建的套接字
		}
		serverSocket.close();
	}

	public void sendServerINFO(OutputStreamWriter socketOut,String choice,String userName) throws IOException{
		String IPaddress = socket.getInetAddress().getHostAddress();
		String serverINFO = choice+"|"+userName+"|"+IPaddress;
		socketOut.write(serverINFO);
		socketOut.flush();
	}

}
