package TCPЭ��;

import java.net.*;
import java.io.*;

public class ChatServer {
	public static void main(String[] args) throws IOException {
		//����TCP�����
		ServerSocket serverSocket = new ServerSocket(9090);
		//���տͻ��˵�����,����һ��Socket
		Socket socket = serverSocket.accept();
		//��ȡ��Socket������������
		BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		//��ȡ��Socket�����������
		OutputStreamWriter socketOut = new OutputStreamWriter(socket.getOutputStream());
		
		//��ȡ���̵�����������
		BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
		
		//��ȡ�ͻ��˴��������
		String line = null;
		//readLine����ֱ������\r\n����Ϊ��Ϊһ��,readLine�Ż�ֹͣ������һ��
		//Ҫ�ڿͻ��˴�������ʱ��Ϊ������β��ƴ��\r\n
		while((line = socketReader.readLine())!=null) {
			System.out.println("����˽��յ�����"+socket.getLocalAddress()+"������:"+line);
			System.out.println("��������͸��ͻ��˵�����:");
			line = keyReader.readLine();
			socketOut.write(line+"\r\n");
			socketOut.flush();
		}
		//�ر���Դ
		serverSocket.close();
	}
}
