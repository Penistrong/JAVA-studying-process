package UDPЭ��;

import java.net.*;
import java.io.*;

public class �ᶪ������� {
	public static void main(String[] args) throws IOException, InterruptedException{
		DatagramSocket socket = new DatagramSocket(9090);
		
		byte[] buf = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		
		while(true) {
			socket.receive(packet);
			System.out.println(new String(buf,0,packet.getLength()));
			//ǿ��˯��10ms,�۲��Ƿ񶪰�
			Thread.sleep(10);
		}
	}
}
