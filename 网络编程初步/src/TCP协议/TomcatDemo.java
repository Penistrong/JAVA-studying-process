package TCP协议;

import java.net.*;
import java.io.*;

//模拟Tomcat服务器(作为服务端要被许多浏览器客户端链接,要使用多线程,开启许多线程为不同用户服务)
public class TomcatDemo extends Thread{
	Socket socket;
	
	public TomcatDemo(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		//获取socket的输出流对象
		try {
			OutputStream outputStream = socket.getOutputStream();
			String line = "你好我是Eclipse";
			line = "<html><head><title>伪:服务器</title></head><body>"+line+"</body></html>";
			//把数据写到浏览器上
			outputStream.write(line.getBytes());
			//关闭资源
			socket.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws IOException {
		//建立TCP服务端
		ServerSocket serverSocket = new ServerSocket(80);
		boolean flag = true;
		//不断接收客户端的链接
		while(flag) {
			Socket socket = serverSocket.accept();
			//对每个用户产生的socket,new一个Tomcat
			new TomcatDemo(socket).start();
		}
		serverSocket.close();
	}
}
