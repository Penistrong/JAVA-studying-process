package �����̳����Ӵ�;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ������:�������������ݴ��������
 * @author ����ΰ
 * @�޸����ڣ�2018��1��12������4:10:23
 * @������IP�� InetAddress
 * 		getlocalHost,getByName�Ȼ�ȡIP��ַ
 */
public class InternetProgramming {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress host = InetAddress.getLocalHost();//��ȡ������ַ
		System.out.println("����IP��ַΪ: " + host.getHostAddress());
		System.out.println("����������Ϊ: " + host.getHostName());
		
		//��ȡ����������IP��ַ,���� IP��ַ���ַ�����ʽ �� ������ ����һ��IP��ַ����
		InetAddress address = InetAddress.getByName("192.168.124.29");
		System.out.println("��IP��ַ��Ӧ������Ϊ:"+address.getHostName());
		
		//getAllByName����������ʽ��IP��ַ(���Ĳ���������Ϊ����)
		InetAddress[] arr = InetAddress.getAllByName("www.baidu.com");
		for(InetAddress oneadd: arr) {
			//�ٶȲ�ֹһ��IP��ַ
			System.out.println("�ٶ�������ӦIP��ַΪ:"+oneadd.getHostAddress());
		}
		
	}
}
