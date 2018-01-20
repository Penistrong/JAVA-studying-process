package homework;

import java.net.*;
import java.util.HashSet;
import java.io.*;

/**
 * 
 * @author 陈立伟
 * @修改日期：2018年1月19日下午5:29:34
 * @描述：使用多线程编写一个服务端可以给多个客户端发送图片
 */
public class MultiThreadsServerDemo extends Thread{
	
	Socket socket;
	//创建一个储存链接过该服务器的IP地址集合
	static HashSet<String> IPs = new HashSet<String>();
	
	public MultiThreadsServerDemo(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			//创建到socket的输出流对象
			OutputStream outputStream = socket.getOutputStream();
			//获取图片的输入流
			FileInputStream fis = new FileInputStream("E://java作品//HomeWork//双持环特大.jpg");
			//读取图片数据
			byte[] buf = new byte[1024];
			int length = 0;
			while((length = fis.read(buf))!=-1) {
				outputStream.write(buf,0,length);
			}
			String IP = socket.getInetAddress().getHostAddress();
			if(IPs.add(IP)) {
				System.out.println("来自"+IP+"用户成功下载图片,当前已下载人数为:"+IPs.size());
			}else {
				System.out.println("来自"+IP+"用户已经下载过该图片了!");
				OutputStreamWriter socketOut = new OutputStreamWriter(socket.getOutputStream());
				socketOut.write("您已经下载过该图片了!");
				socketOut.flush();
			}
			//关闭资源
			fis.close();
			
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			//不论怎样最终都要关闭产生的每个Socket
			try {
				socket.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
			
	}
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9090);
		boolean flag = true;
		while(flag) {
			//不断接收来自不同客户端的请求,创建多线程以处理问题
			Socket socket = serverSocket.accept();
			new MultiThreadsServerDemo(socket).start();
		}
		serverSocket.close();
	}
}
