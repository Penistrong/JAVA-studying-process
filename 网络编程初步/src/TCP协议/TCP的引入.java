package TCPЭ��;

import java.net.*;
import java.io.*;
/**
 * TCPЭ���ܹ������ȶ���������,���ᶪ��
 * ��Ҫ����������:TCPЭ����Socket:
 * 				Socket() �ͻ�����  TCP�ͻ���һ��������Ҫ�����˽�������
 * 				ServerSocket() �������
 * @author ����ΰ
 * @�޸����ڣ�2018��1��18������3:44:40
 * @������1.����IO���������ݴ���,��������(ȷ��˫��������,�������ļ�)
 * 		2.�������ݴ����ʱ��û�д�С����
 * 		3.��������,ͨ���������ֻ��Ʊ�֤����������
 * TCP�ͻ���ʹ�ò���:
 * 1.����TCP�ͻ��˷���
 * 2.��ȡ����Ӧ��������
 * 3.д�����ȡ����
 * 4.�ر���Դ
 * 
 * Ϊ��ServerSocket�����get���/��������?
 * �ͻ�����ͨ������˻��ഫ�����ݵ�,���ServerSocketֱ�ӻ�ȡIO��,��֪����������
 * ���ͻ��˴�����,ֱ�ӵ���ServerSocket��accept����ֱ����ÿͻ������Ӳ�����һ��
 * Socket����
 */
public class TCP������ {
	public static void main(String[] args) throws IOException{
		//����TCP����
		Socket socket = new Socket(InetAddress.getLocalHost(), 9090);
		//��ȡ��Socket�����������
		OutputStream outputstream = socket.getOutputStream();
		//�����������������д��
		outputstream.write("�ҽ���������(�������̻ҿͻ���)".getBytes());
		//�����������Ǵ�Socket���ó�����,ֱ�ӹر�socket����
		
		//��ȡ������������,��ȡ����˻��͵�����
		InputStream inputStream = socket.getInputStream();
		byte[] buf = new byte[1024];
		int length = inputStream.read(buf);
		System.out.println("�ͻ��˽�������:"+new String(buf,0,length));
		
		socket.close();
	}
}
