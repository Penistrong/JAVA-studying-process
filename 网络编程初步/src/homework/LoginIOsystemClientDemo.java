package homework;

import java.io.*;
import java.net.*;

public class LoginIOsystemClientDemo {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket(InetAddress.getLocalHost(),9090);
		//��ȡ��Socket������������
		BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//��ȡ��Socket�����������
		OutputStreamWriter socketOut= new OutputStreamWriter(socket.getOutputStream());
		//��ȡ�����̵�����������
		BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = true;
		while(flag) {
			System.out.println("Please Choose Your Option:\n (A)Login in (B)Register in");
			String option = keyReader.readLine();
			if("a".equalsIgnoreCase(option)) {
				//ѡ���¼����
				getUserINFO(keyReader, option,socketOut);
				//��ȡ��������������Ϣ
				String line = socketReader.readLine();
				System.out.println(line);
			}else if("b".equalsIgnoreCase(option)) {
				//ѡ��ע�Ṧ��
				getUserINFO(keyReader, option,socketOut);
				String line = socketReader.readLine();
				System.out.println(line);
			}
			String serverINFO = socketReader.readLine();
			String datas[] = serverINFO.split("|");
			String choice = datas[0];
			switch(choice) {
			case "2":
				System.out.println("�������ؿ�ʼ����");
				break;
			case "1":
				boolean DeepFlag = true;
				while(DeepFlag) {
					System.out.println("�����ε�¼����ϢΪ:"+datas[1]+"||"+datas[2]);
					System.out.println("��ѡ����Ҫ���еĲ���:\n ");
					DeepFlag = false;
				}
				break;
			}
		}
		socket.close();
	}
	//���ظ��ĵõ��������û���Ϣ�ķ�����ȡ����д������
	public static void getUserINFO(BufferedReader keyReader, String option,OutputStreamWriter socketOut) throws IOException {
		System.out.println("Please Input Your AccountID!");
		String userName = keyReader.readLine();
		System.out.println("Please Input Your Password!");
		String password = keyReader.readLine();
		String userINFO = option+" "+userName+" "+password+"\r\n";
		//��������
		socketOut.write(userINFO);
		socketOut.flush();
	}
}
