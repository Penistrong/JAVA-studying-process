package homework;

import java.io.*;
import java.net.*;
import java.util.Properties;

public class LoginIOsystemServerDemo extends Thread{
	
	Socket socket;//�ڲ�ά�����׽���
	
	//��ȡ�������ڱ��ش洢���˻���¼
	static File file = new File("E://java��Ʒ//HomeWork//Accounts.properties");
	
	public LoginIOsystemServerDemo(Socket socket) {
		// TODO �Զ����ɵĹ��캯�����
		this.socket = socket;
	}
	static {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		//���Ĵ���
		while(true) {
			try {
				//��ȡ��Socket��������
				BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//��ȡ��Socket�������
				OutputStreamWriter socketOut = new OutputStreamWriter(socket.getOutputStream());
			
				//��ȡ�ͻ��˴�����UserINFO
				String userINFO = socketReader.readLine();
				String[] datas = userINFO.split(" ");
				//��ȡ�ͻ��˴�������Ϣ��ʶ��(����userINFOͷλ,��־Ϊ��¼��ע��)
				String option = datas[0];
				//��ȡ�û�����
				String userName = datas[1];
				//��ȡ�û�����
				String password = datas[2];
			
				System.out.println("����"+socket.getInetAddress().getHostAddress()+"����Ϣ: "+userName+"|"+password);
			
				if("a".equalsIgnoreCase(option)) {
					//ѡ���˵�¼����
					Properties properties = new Properties();
					//���������ļ�
					properties.load(new FileReader(file));
					if(properties.containsKey(userName)) {
						String LocalPassword = (String) properties.getProperty(userName);
						if(LocalPassword.equals(password)) {
							socketOut.write("����ɹ�!"+"��ӭ,"+userName+"\r\n");
						}else {
							socketOut.write("�������!\r\n");
						}	
					}else {
						socketOut.write("�û���������,����������!\r\n");
					}
					socketOut.flush();
				
				}else if("b".equalsIgnoreCase(option)) {
					//ѡ����ע�Ṧ��
					//���������ļ���(Ҫ׷��֮ǰ������)
					Properties properties = new Properties();
					//����ԭ���������ļ�
					properties.load(new FileReader(file));
					if(!properties.containsKey(userName)) {
						//�������û���ʱ
						properties.setProperty(userName, password);
						//���������ļ�
						properties.store(new FileWriter(file), "accounts");
						socketOut.write("ע��ɹ�\r\n");
					}else {
						//�����û���ʱ
						socketOut.write("���û����ѱ�ע��,����������\r\n");
					}
					socketOut.flush();
				}
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} finally {
				try {//����������Ҫ�ر�ÿ���׽���
					socket.close();
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		//����TCP�����
		ServerSocket serverSocket = new ServerSocket(9090);
		boolean flag = true;
		while(flag) {
			Socket socket = serverSocket.accept();
			//��ÿ�����ӵ��û�����һ���̷߳�������
			new LoginIOsystemServerDemo(socket).start();
		}
		serverSocket.close();
	}
}
