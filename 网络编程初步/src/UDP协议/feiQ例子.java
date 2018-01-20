package UDP协议;

import java.io.IOException;
import java.net.*;
/**
 * 每个网络程序都有其所处理的特定格式数据,如果接受到的数据不符合指定格式则会将其当做垃圾
 * 数据处理(相当于加密过程)
 * @author 陈立伟
 * @修改日期：2018年1月13日下午5:14:20
 * @描述：使用UDP协议给feiQ发送信息
 * feiQ接收的数据格式为:
 * 	version:time:sender:ip:flag:content ;
 * 	版本号          时间       发送者        IP 标识符(辨别发送数据的类型,这里取32,即为聊天信息)
 *
 *在UDP协议中有一个IP地址作为广播地址,广播地址为主机号为255地址
 *给广播IP地址发送信息时,在同一个网络段里的所有人都能接收
 */
public class feiQ例子 {
	public static void main(String[] args) throws IOException {
		//建立UDP服务
		DatagramSocket datagramSocket = new DatagramSocket();
		//封装数据成包
		String data = "侍奉的灰烬大人是个变态怎么办?急,在线等";
		data = getData(data);
		DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getByName("192.168.124.29"), 9090);
		//发送数据
		datagramSocket.send(packet);
		datagramSocket.close();
	}

	//将数据自动拼接成指定格式的数据,用以封装后给feiQ接收
	public static String getData(String content) {
		StringBuilder sb = new StringBuilder();
		sb.append("1.0:");
		sb.append(System.currentTimeMillis()+":");
		sb.append("防火女");
		sb.append("192.168.124.29:");
		sb.append("32:");
		sb.append(content);
		
		return sb.toString();
	}

}
