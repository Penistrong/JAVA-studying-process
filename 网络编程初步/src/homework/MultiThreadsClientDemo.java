package homework;

import java.net.*;
import java.io.*;
/**
 * 
 * @author 陈立伟
 * @修改日期：2018年1月20日下午3:38:11
 * @描述：多线程下载图片客户端
 */
public class MultiThreadsClientDemo {
	public static void main(String[] args) throws IOException{
		Socket socket = new Socket(InetAddress.getLocalHost(),9090);
		//获取socket的输入流
		InputStream inputStream = socket.getInputStream();
		//获取文件的输出流
		FileOutputStream fos = new FileOutputStream("E://java作品//HomeWork//FileOutputDir//DownloadedImage.jpg");
		byte[] buf = new byte[1024];
		int length = 0;
		while((length = inputStream.read(buf))!=-1) {
			fos.write(buf,0,length);
		}
		System.out.println("下载成功!");
		fos.close();
		socket.close();
	}
}
