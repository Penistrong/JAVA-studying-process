package UDPЭ��;

import java.io.IOException;
import java.net.*;

public class receiver���ն� {
	public static void main(String[] args) throws IOException {
		//����UDP����(���ն���Ҫ�����˿�,�����9090�Ƿ��Ͷ����õĶ˿�)
		DatagramSocket receiveSocket = new DatagramSocket(9090);
		//׼���յ����ݰ����ڴ洢����(�����ݰ�ֻ���ڽ���,����Ҫ���õ�ַ�˿�)
		byte[] buf = new byte[1024];//1024Ϊ���õ������ֽ�
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
		//����UDP�����������,����ʵ���ϴ洢��
		receiveSocket.receive(datagramPacket);
		//receiveΪ�����ͷ���,��һֱ�ȴ����ݴ���
		//ʹ��datagramPacket.getLength()��ȡ���������ݰ����ֽ���������ת��
		System.out.println("���ն˽ӵ�������Ϊ:"+new String(buf,0,datagramPacket.getLength()));
		
		receiveSocket.close();
	}
}
