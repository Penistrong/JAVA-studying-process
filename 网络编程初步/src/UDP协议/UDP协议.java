package UDPЭ��;

import java.io.IOException;
import java.net.*;
/**
 * �˽�UDPЭ��,������Խ�����ֱ�ӷ�����Ϣ�����������ض���Ա������
 * 			��һЩ��Ϸ
 * @author ����ΰ
 * @�޸����ڣ�2018��1��12������6:00:11
 * @������UDP �û����ݰ�Э��
 * �ص�:	1.�����ݼ���Դ��Ŀ�ķ�װΪ���ݰ�������Ҫ�������Ӽ���ʹ��
 * 		2.���ݰ���С����64K
 * 		3.������,���ɿ�
 * 		4.���轨������,�ٶȿ�
 * 		5.���ֿͻ��˺ͷ����,ֻ�ַ��Ͷ˺ͽ��ն�
 * UDP��Socket:
 * 		DatagramSocket(UDP��������)
 * 		DatagramPacket(���ݰ���)
 * 			DatagramPacket(buf,length,address,port)
 * 			��������Ϊ ���͵��������ݡ������������ݴ�С������Ŀ��IP��ַ���󡢶˿ں�
 * ʹ�ò���:
 * 		1.�ȴ򿪽��ն�
 * 		2.�ٴ򿪷��Ͷ�,���ݶ�Ӧ�˿�IP��ַ,�����ݰ�(��װ������)���͸����ն�
 * 		ps.���۽��ջ��Ƿ���,��Ҫ����UDP����,����UDP����,���Ҫ�ر���Դ,��ֹ�˿ڱ�ռ��
 */


public class UDPЭ�� {
	//��Ϊ���Ͷ�
	public static void main(String[] args) throws IOException {
		//����UDP����
		DatagramSocket datagramSocket = new DatagramSocket();
		String data = "��ð�,������ѧϰUDP�ĵ�һ������";
		//����UDP���ݰ�
		DatagramPacket datagramPacket = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getLocalHost(), 9090);
		//����UDP���������ݰ�
		datagramSocket.send(datagramPacket);
		//�ر���Դ--�ͷŵ���UDP����ʱռ�õĶ˿ں�
		datagramSocket.close();
	}
}
