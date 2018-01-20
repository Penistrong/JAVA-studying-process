package UDP协议;

import java.net.*;
import java.io.*;
/**
 * 要使用多线程同时收发信息
 * @author 陈立伟
 * @修改日期：2018年1月18日下午2:30:19
 * @描述：建立发送端
 */
public class ChatSender extends Thread{
	
	@Override
	public void run(){
		try {
			DatagramSocket socket = new DatagramSocket();
			//准备数据,封装数据成包
			BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			DatagramPacket packet = null;
			while((line = keyReader.readLine())!=null) {
				packet = new DatagramPacket(line.getBytes(),line.getBytes().length,InetAddress.getByName("192.168.124.23"),9090);
				//发送数据
				socket.send(packet);
			}
			socket.close();
		} catch (SocketException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	
	
	}
}
