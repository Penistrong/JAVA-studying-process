package UDPЭ��;

import java.net.*;
import java.io.*;
/**
 * Ҫʹ�ö��߳�ͬʱ�շ���Ϣ
 * @author ����ΰ
 * @�޸����ڣ�2018��1��18������2:30:19
 * @�������������Ͷ�
 */
public class ChatSender extends Thread{
	
	@Override
	public void run(){
		try {
			DatagramSocket socket = new DatagramSocket();
			//׼������,��װ���ݳɰ�
			BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			DatagramPacket packet = null;
			while((line = keyReader.readLine())!=null) {
				packet = new DatagramPacket(line.getBytes(),line.getBytes().length,InetAddress.getByName("192.168.124.23"),9090);
				//��������
				socket.send(packet);
			}
			socket.close();
		} catch (SocketException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	
	
	}
}
