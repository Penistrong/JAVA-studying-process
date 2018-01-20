package TCPЭ��;

import java.net.*;
import java.io.*;

//ģ��Tomcat������(��Ϊ�����Ҫ�����������ͻ�������,Ҫʹ�ö��߳�,��������߳�Ϊ��ͬ�û�����)
public class TomcatDemo extends Thread{
	Socket socket;
	
	public TomcatDemo(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		//��ȡsocket�����������
		try {
			OutputStream outputStream = socket.getOutputStream();
			String line = "�������Eclipse";
			line = "<html><head><title>α:������</title></head><body>"+line+"</body></html>";
			//������д���������
			outputStream.write(line.getBytes());
			//�ر���Դ
			socket.close();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) throws IOException {
		//����TCP�����
		ServerSocket serverSocket = new ServerSocket(80);
		boolean flag = true;
		//���Ͻ��տͻ��˵�����
		while(flag) {
			Socket socket = serverSocket.accept();
			//��ÿ���û�������socket,newһ��Tomcat
			new TomcatDemo(socket).start();
		}
		serverSocket.close();
	}
}
