package TCPЭ��;
	
import java.io.*;
import java.net.*;
/**ע��:
 * 1.���ʹ��BufferedReader��readLine����Ҫƴ����\r\n�ٰ�����д��
 * 2.ʹ���ַ������ڻ���ռ�Ĵ���Ҫ��ʹ��Flush���ܽ���д��
 * @author ����ΰ
 * @�޸����ڣ�2018��1��19������3:58:19
 * @����������ͻ���(�������������)
 */
public class ChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//����TCP�Ŀͻ��˷���
		Socket socket = new Socket(InetAddress.getLocalHost(),9090);
		//��ȡSocket���������
		OutputStreamWriter socketOut = new OutputStreamWriter(socket.getOutputStream());
		
		//��ȡSocket����������
		BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		//��ȡ���̵�����������,��ȡ����
		BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		//���϶�ȡ����¼�������,Ȼ�������д��
		while((line = keyReader.readLine())!=null) {
			//����ȥ������ÿһ��β�������\r\n,����˵�readLine������ȷʶ��
			socketOut.write(line+"\r\n");
			//Ҫˢ��
			socketOut.flush();
			System.out.println("ϵͳ��ʾ:�ͻ��˷������ݳɹ�");
			//��ȡ����˻��͵�����
			line = socketReader.readLine();
			System.out.println("����˻ظ�:"+line);
		}
		//�ر���Դ
		socket.close();
		
	}
}
