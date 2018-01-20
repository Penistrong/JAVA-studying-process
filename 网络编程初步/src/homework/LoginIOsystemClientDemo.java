package homework;

import java.io.*;
import java.net.*;

public class LoginIOsystemClientDemo {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket(InetAddress.getLocalHost(),9090);
		//获取到Socket的输入流对象
		BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//获取到Socket的输出流对象
		OutputStreamWriter socketOut= new OutputStreamWriter(socket.getOutputStream());
		//获取到键盘的输入流对象
		BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = true;
		while(flag) {
			System.out.println("Please Choose Your Option:\n (A)Login in (B)Register in");
			String option = keyReader.readLine();
			if("a".equalsIgnoreCase(option)) {
				//选择登录功能
				getUserINFO(keyReader, option,socketOut);
				//读取服务器反馈的信息
				String line = socketReader.readLine();
				System.out.println(line);
			}else if("b".equalsIgnoreCase(option)) {
				//选择注册功能
				getUserINFO(keyReader, option,socketOut);
				String line = socketReader.readLine();
				System.out.println(line);
			}
		}
		socket.close();
	}
	//将重复的得到并传递用户信息的方法抽取出来写出方法
	public static void getUserINFO(BufferedReader keyReader, String option,OutputStreamWriter socketOut) throws IOException {
		System.out.println("Please Input Your AccountID!");
		String userName = keyReader.readLine();
		System.out.println("Please Input Your Password!");
		String password = keyReader.readLine();
		String userINFO = option+" "+userName+" "+password+"\r\n";
		//传输数据
		socketOut.write(userINFO);
		socketOut.flush();
	}
}
