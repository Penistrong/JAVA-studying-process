package UDPЭ��;

import java.net.*;
import java.io.*;
/**
 * �������ն�
 * @author ����ΰ
 * @�޸����ڣ�2018��1��18������2:54:21
 * @������
 */
public class ChatReceiver extends Thread{
	@Override
	public void run() {
		//����һ�������˿ڵ�UDP�����
		try {
			DatagramSocket socket = new DatagramSocket(9090);
			boolean flag = true;
			byte[] buf = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			while(flag) {
				socket.receive(packet);
				//packet.getAddress��ȡ���ݰ���IP��ַ����
				System.out.println(packet.getAddress().getHostAddress()+"˵:"+ new String(buf,0,packet.getLength()));
			}
			socket.close();
		} catch (SocketException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
