package TCP协议;

import java.net.*;
import java.io.*;
/**异常其一:java.net.BindException 端口被占用的异常
 * 
 * @author 陈立伟
 * @修改日期：2018年1月18日下午4:23:07
 * @描述：建立TCP服务端步骤:
 * 		1.建立服务端服务
 * 		2.接受客户端链接,产生一个Socket
 * 		3.获取对应的流对象进行读取或写出数据
 * 		4.关闭资源
 */
public class DemoServer {
	public static void main(String[] args) throws IOException {
		//服务端只需监听端口
		ServerSocket serverSocket = new ServerSocket(9090);
		//以下语句会一直等待客户端链接
		Socket socket = serverSocket.accept();//首先获取客户端连接
		//获取输入流对象,读取客户端发送的内容
		InputStream inputStream = socket.getInputStream();
		byte[] buf = new byte[1024];
		inputStream.read(buf);
		
		//输出信息
		System.out.println("服务端接收:"+new String(buf,0,buf.length));
		//向客户端发送数据
		//获取socket输出流对象,向客户端发送数据
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("侬在使用我(来自土豪金服务端)".getBytes());
		
		
		//关闭服务端socket资源
		serverSocket.close();
	}
}
