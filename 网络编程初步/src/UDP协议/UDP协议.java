package UDP协议;

import java.io.IOException;
import java.net.*;
/**
 * 了解UDP协议,如区域对讲机，直接发送信息，不建立与特定人员的连接
 * 			如一些游戏
 * @author 陈立伟
 * @修改日期：2018年1月12日下午6:00:11
 * @描述：UDP 用户数据包协议
 * 特点:	1.将数据及其源和目的封装为数据包，不需要建立连接即可使用
 * 		2.数据包大小上限64K
 * 		3.无连接,不可靠
 * 		4.不需建立连接,速度快
 * 		5.不分客户端和服务端,只分发送端和接收端
 * UDP下Socket:
 * 		DatagramSocket(UDP插座服务)
 * 		DatagramPacket(数据包类)
 * 			DatagramPacket(buf,length,address,port)
 * 			参数依次为 发送的数据内容、发送数据内容大小、发送目的IP地址对象、端口号
 * 使用步骤:
 * 		1.先打开接收端
 * 		2.再打开发送端,根据对应端口IP地址,将数据包(封装了数据)发送给接收端
 * 		ps.不论接收还是发送,都要建立UDP服务,调用UDP服务,最后要关闭资源,防止端口被占用
 */


public class UDP协议 {
	//此为发送端
	public static void main(String[] args) throws IOException {
		//建立UDP服务
		DatagramSocket datagramSocket = new DatagramSocket();
		String data = "你好啊,这是我学习UDP的第一个例子";
		//创建UDP数据包
		DatagramPacket datagramPacket = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getLocalHost(), 9090);
		//调用UDP服务发送数据包
		datagramSocket.send(datagramPacket);
		//关闭资源--释放调用UDP服务时占用的端口号
		datagramSocket.close();
	}
}
