package TCP协议;

import java.net.*;
import java.io.*;
/**
 * TCP协议能够建立稳定数据连接,不会丢包
 * 主要掌握两个类:TCP协议下Socket:
 * 				Socket() 客户端类  TCP客户端一旦启动便要与服务端进行链接
 * 				ServerSocket() 服务端类
 * @author 陈立伟
 * @修改日期：2018年1月18日下午3:44:40
 * @描述：1.基于IO流进行数据传输,面向连接(确保双方都在线,如下载文件)
 * 		2.进行数据传输的时候没有大小限制
 * 		3.面向链接,通过三次握手机制保证数据完整性
 * TCP客户端使用步骤:
 * 1.建立TCP客户端服务
 * 2.获取到对应的流对象
 * 3.写出或读取数据
 * 4.关闭资源
 * 
 * 为何ServerSocket不设计get输出/输入流呢?
 * 客户端是通过服务端互相传输数据的,如果ServerSocket直接获取IO流,不知道到底是哪
 * 个客户端传来的,直接调用ServerSocket的accept方法直到获得客户端链接并返回一个
 * Socket即可
 */
public class TCP的引入 {
	public static void main(String[] args) throws IOException{
		//建立TCP服务
		Socket socket = new Socket(InetAddress.getLocalHost(), 9090);
		//获取到Socket的输出流对象
		OutputStream outputstream = socket.getOutputStream();
		//利用输出流对象将数据写出
		outputstream.write("灰烬大人您好(来自奶奶灰客户端)".getBytes());
		//上面的输出流是从Socket里拿出来的,直接关闭socket就行
		
		//获取到输入流对象,读取服务端回送的数据
		InputStream inputStream = socket.getInputStream();
		byte[] buf = new byte[1024];
		int length = inputStream.read(buf);
		System.out.println("客户端接收数据:"+new String(buf,0,length));
		
		socket.close();
	}
}
