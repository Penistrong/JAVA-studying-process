package homework;

import java.net.*;
import java.io.*;
/**
 * 
 * @author ����ΰ
 * @�޸����ڣ�2018��1��20������3:38:11
 * @���������߳�����ͼƬ�ͻ���
 */
public class MultiThreadsClientDemo {
	public static void main(String[] args) throws IOException{
		Socket socket = new Socket(InetAddress.getLocalHost(),9090);
		//��ȡsocket��������
		InputStream inputStream = socket.getInputStream();
		//��ȡ�ļ��������
		FileOutputStream fos = new FileOutputStream("E://java��Ʒ//HomeWork//FileOutputDir//DownloadedImage.jpg");
		byte[] buf = new byte[1024];
		int length = 0;
		while((length = inputStream.read(buf))!=-1) {
			fos.write(buf,0,length);
		}
		System.out.println("���سɹ�!");
		fos.close();
		socket.close();
	}
}
