package UDPЭ��;

import java.net.*;
import java.io.*;
/**
 * UDP��Ϊ���ɿ�Э����ܻᶪʧ���ݰ�
 * @author ����ΰ
 * @�޸����ڣ�2018��1��18������3:09:45
 * @�����������������һ�����ݰ���ʧ�����
 * 		1.������
 * 		2.CPU������������
 */
public class ����δ�������ӿ��ܶ�ʧ���ݰ������ {
	public static void main(String[] args) throws IOException{
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = null;
		String data = "��粻Ҫ��";
		for(int i = 0;i<10;i++) {
			packet = new DatagramPacket(data.getBytes(), data.getBytes().length,InetAddress.getLocalHost(),9090);
			socket.send(packet);
		}
		socket.close();
	}
}
