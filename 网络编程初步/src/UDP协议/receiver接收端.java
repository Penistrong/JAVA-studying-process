package UDP协议;

import java.io.IOException;
import java.net.*;

public class receiver接收端 {
	public static void main(String[] args) throws IOException {
		//建立UDP服务(接收端需要监听端口,这里的9090是发送端设置的端口)
		DatagramSocket receiveSocket = new DatagramSocket(9090);
		//准备空的数据包用于存储数据(该数据包只用于接收,不需要设置地址端口)
		byte[] buf = new byte[1024];//1024为设置的上限字节
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
		//调用UDP服务接收数据,数据实际上存储到
		receiveSocket.receive(datagramPacket);
		//receive为阻塞型方法,会一直等待数据传入
		//使用datagramPacket.getLength()获取传来的数据包的字节数，便于转码
		System.out.println("接收端接到的数据为:"+new String(buf,0,datagramPacket.getLength()));
		
		receiveSocket.close();
	}
}
