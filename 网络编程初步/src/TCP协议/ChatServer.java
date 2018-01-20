package TCP协议;

import java.net.*;
import java.io.*;

public class ChatServer {
	public static void main(String[] args) throws IOException {
		//建立TCP服务端
		ServerSocket serverSocket = new ServerSocket(9090);
		//接收客户端的链接,产生一个Socket
		Socket socket = serverSocket.accept();
		//获取到Socket的输入流对象
		BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		//获取到Socket的输出流对象
		OutputStreamWriter socketOut = new OutputStreamWriter(socket.getOutputStream());
		
		//获取键盘的输入流对象
		BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
		
		//读取客户端传输的数据
		String line = null;
		//readLine方法直到读到\r\n才认为其为一行,readLine才会停止并返回一行
		//要在客户端传输数据时便为其数据尾部拼接\r\n
		while((line = socketReader.readLine())!=null) {
			System.out.println("服务端接收到来自"+socket.getLocalAddress()+"的数据:"+line);
			System.out.println("请输入回送给客户端的数据:");
			line = keyReader.readLine();
			socketOut.write(line+"\r\n");
			socketOut.flush();
		}
		//关闭资源
		serverSocket.close();
	}
}
