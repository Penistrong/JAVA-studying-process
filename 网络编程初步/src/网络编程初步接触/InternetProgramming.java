package 网络编程初步接触;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 网络编程:解决计算机间数据传输的问题
 * @author 陈立伟
 * @修改日期：2018年1月12日下午4:10:23
 * @描述：IP类 InetAddress
 * 		getlocalHost,getByName等获取IP地址
 */
public class InternetProgramming {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress host = InetAddress.getLocalHost();//获取本机地址
		System.out.println("本机IP地址为: " + host.getHostAddress());
		System.out.println("本机主机名为: " + host.getHostName());
		
		//获取其他机器的IP地址,根据 IP地址的字符串形式 或 主机名 生成一个IP地址对象
		InetAddress address = InetAddress.getByName("192.168.124.29");
		System.out.println("该IP地址对应主机名为:"+address.getHostName());
		
		//getAllByName返回数组形式的IP地址(传的参数还可以为域名)
		InetAddress[] arr = InetAddress.getAllByName("www.baidu.com");
		for(InetAddress oneadd: arr) {
			//百度不止一个IP地址
			System.out.println("百度域名对应IP地址为:"+oneadd.getHostAddress());
		}
		
	}
}
