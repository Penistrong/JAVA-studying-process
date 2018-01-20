package UDP协议;

import java.net.*;
import java.io.*;
/**
 * 建立接收端
 * @author 陈立伟
 * @修改日期：2018年1月18日下午2:54:21
 * @描述：
 */
public class ChatReceiver extends Thread{
	@Override
	public void run() {
		//建立一个监听端口的UDP服务端
		try {
			DatagramSocket socket = new DatagramSocket(9090);
			boolean flag = true;
			byte[] buf = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			while(flag) {
				socket.receive(packet);
				//packet.getAddress获取数据包的IP地址对象
				System.out.println(packet.getAddress().getHostAddress()+"说:"+ new String(buf,0,packet.getLength()));
			}
			socket.close();
		} catch (SocketException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
