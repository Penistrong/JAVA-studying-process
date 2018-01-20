package TCP协议;
	
import java.io.*;
import java.net.*;
/**注意:
 * 1.如果使用BufferedReader的readLine方法要拼接上\r\n再把数据写出
 * 2.使用字符流由于缓冲空间的存在要先使用Flush才能将其写出
 * @author 陈立伟
 * @修改日期：2018年1月19日下午3:58:19
 * @描述：聊天客户端(服务端先需启动)
 */
public class ChatClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//建立TCP的客户端服务
		Socket socket = new Socket(InetAddress.getLocalHost(),9090);
		//获取Socket输出流对象
		OutputStreamWriter socketOut = new OutputStreamWriter(socket.getOutputStream());
		
		//获取Socket输入流对象
		BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		//获取键盘的输入流对象,读取数据
		BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		//不断读取键盘录入的数据,然后把数据写出
		while((line = keyReader.readLine())!=null) {
			//传过去的数据每一行尾部需加上\r\n,服务端的readLine才能正确识别
			socketOut.write(line+"\r\n");
			//要刷新
			socketOut.flush();
			System.out.println("系统提示:客户端发送数据成功");
			//读取服务端回送的数据
			line = socketReader.readLine();
			System.out.println("服务端回复:"+line);
		}
		//关闭资源
		socket.close();
		
	}
}
