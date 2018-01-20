package homework;

import java.net.*;
import java.util.HashSet;
import java.io.*;

/**
 * 
 * @author ����ΰ
 * @�޸����ڣ�2018��1��19������5:29:34
 * @������ʹ�ö��̱߳�дһ������˿��Ը�����ͻ��˷���ͼƬ
 */
public class MultiThreadsServerDemo extends Thread{
	
	Socket socket;
	//����һ���������ӹ��÷�������IP��ַ����
	static HashSet<String> IPs = new HashSet<String>();
	
	public MultiThreadsServerDemo(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			//������socket�����������
			OutputStream outputStream = socket.getOutputStream();
			//��ȡͼƬ��������
			FileInputStream fis = new FileInputStream("E://java��Ʒ//HomeWork//˫�ֻ��ش�.jpg");
			//��ȡͼƬ����
			byte[] buf = new byte[1024];
			int length = 0;
			while((length = fis.read(buf))!=-1) {
				outputStream.write(buf,0,length);
			}
			String IP = socket.getInetAddress().getHostAddress();
			if(IPs.add(IP)) {
				System.out.println("����"+IP+"�û��ɹ�����ͼƬ,��ǰ����������Ϊ:"+IPs.size());
			}else {
				System.out.println("����"+IP+"�û��Ѿ����ع���ͼƬ��!");
				OutputStreamWriter socketOut = new OutputStreamWriter(socket.getOutputStream());
				socketOut.write("���Ѿ����ع���ͼƬ��!");
				socketOut.flush();
			}
			//�ر���Դ
			fis.close();
			
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			//�����������ն�Ҫ�رղ�����ÿ��Socket
			try {
				socket.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
			
	}
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(9090);
		boolean flag = true;
		while(flag) {
			//���Ͻ������Բ�ͬ�ͻ��˵�����,�������߳��Դ�������
			Socket socket = serverSocket.accept();
			new MultiThreadsServerDemo(socket).start();
		}
		serverSocket.close();
	}
}
