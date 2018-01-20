package UDP协议;

import java.net.*;
import java.io.*;

public class 会丢包的情况 {
	public static void main(String[] args) throws IOException, InterruptedException{
		DatagramSocket socket = new DatagramSocket(9090);
		
		byte[] buf = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		
		while(true) {
			socket.receive(packet);
			System.out.println(new String(buf,0,packet.getLength()));
			//强制睡眠10ms,观察是否丢包
			Thread.sleep(10);
		}
	}
}
