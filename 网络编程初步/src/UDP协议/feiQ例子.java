package UDPЭ��;

import java.io.IOException;
import java.net.*;
/**
 * ÿ���������������������ض���ʽ����,������ܵ������ݲ�����ָ����ʽ��Ὣ�䵱������
 * ���ݴ���(�൱�ڼ��ܹ���)
 * @author ����ΰ
 * @�޸����ڣ�2018��1��13������5:14:20
 * @������ʹ��UDPЭ���feiQ������Ϣ
 * feiQ���յ����ݸ�ʽΪ:
 * 	version:time:sender:ip:flag:content ;
 * 	�汾��          ʱ��       ������        IP ��ʶ��(��������ݵ�����,����ȡ32,��Ϊ������Ϣ)
 *
 *��UDPЭ������һ��IP��ַ��Ϊ�㲥��ַ,�㲥��ַΪ������Ϊ255��ַ
 *���㲥IP��ַ������Ϣʱ,��ͬһ���������������˶��ܽ���
 */
public class feiQ���� {
	public static void main(String[] args) throws IOException {
		//����UDP����
		DatagramSocket datagramSocket = new DatagramSocket();
		//��װ���ݳɰ�
		String data = "�̷�Ļҽ������Ǹ���̬��ô��?��,���ߵ�";
		data = getData(data);
		DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getByName("192.168.124.29"), 9090);
		//��������
		datagramSocket.send(packet);
		datagramSocket.close();
	}

	//�������Զ�ƴ�ӳ�ָ����ʽ������,���Է�װ���feiQ����
	public static String getData(String content) {
		StringBuilder sb = new StringBuilder();
		sb.append("1.0:");
		sb.append(System.currentTimeMillis()+":");
		sb.append("����Ů");
		sb.append("192.168.124.29:");
		sb.append("32:");
		sb.append(content);
		
		return sb.toString();
	}

}
