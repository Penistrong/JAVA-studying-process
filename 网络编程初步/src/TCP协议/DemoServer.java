package TCPЭ��;

import java.net.*;
import java.io.*;
/**�쳣��һ:java.net.BindException �˿ڱ�ռ�õ��쳣
 * 
 * @author ����ΰ
 * @�޸����ڣ�2018��1��18������4:23:07
 * @����������TCP����˲���:
 * 		1.��������˷���
 * 		2.���ܿͻ�������,����һ��Socket
 * 		3.��ȡ��Ӧ����������ж�ȡ��д������
 * 		4.�ر���Դ
 */
public class DemoServer {
	public static void main(String[] args) throws IOException {
		//�����ֻ������˿�
		ServerSocket serverSocket = new ServerSocket(9090);
		//��������һֱ�ȴ��ͻ�������
		Socket socket = serverSocket.accept();//���Ȼ�ȡ�ͻ�������
		//��ȡ����������,��ȡ�ͻ��˷��͵�����
		InputStream inputStream = socket.getInputStream();
		byte[] buf = new byte[1024];
		inputStream.read(buf);
		
		//�����Ϣ
		System.out.println("����˽���:"+new String(buf,0,buf.length));
		//��ͻ��˷�������
		//��ȡsocket���������,��ͻ��˷�������
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("ٯ��ʹ����(��������������)".getBytes());
		
		
		//�رշ����socket��Դ
		serverSocket.close();
	}
}
