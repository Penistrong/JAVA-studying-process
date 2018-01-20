package UDP协议;

import java.net.*;
import java.io.*;
/**
 * UDP作为不可靠协议可能会丢失数据包
 * @author 陈立伟
 * @修改日期：2018年1月18日下午3:09:45
 * @描述：下面大致描述一下数据包丢失的情况
 * 		1.带宽不足
 * 		2.CPU处理能力不足
 */
public class 由于未建立链接可能丢失数据包的情况 {
	public static void main(String[] args) throws IOException{
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = null;
		String data = "哥哥不要啊";
		for(int i = 0;i<10;i++) {
			packet = new DatagramPacket(data.getBytes(), data.getBytes().length,InetAddress.getLocalHost(),9090);
			socket.send(packet);
		}
		socket.close();
	}
}
