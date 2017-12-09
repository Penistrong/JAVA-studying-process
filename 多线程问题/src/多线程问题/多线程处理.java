package 多线程问题;
/**
 * 需求：一个银行账户5000块，夫妻俩一个拿着存着一个拿着卡，开始取钱比赛
 * 每次只能取1000块，要求不准出现线程安全问题
 * @author 陈立伟
 * @修改日期：2017年12月8日下午5:43:19
 * @描述：
 */
class BankThread extends Thread{
	static int count = 5000;//共享的该变量要设成静态
	public BankThread(String name) {
		super(name);
	}
	
	//Override
	public void run() {
		while(true) {
		//使用synchronized关键字锁住需要同步的代码
			synchronized("锁") {
				if(count>0) {
					System.out.println(Thread.currentThread().getName()+"取走了1000块，还剩余"+(count-1000)+"元");
					count = count - 1000;
				}else {
					System.out.println("荒淫无度，余额不足！");
					break;
				}
			}
		}
	}
	
	//静态的函数---》函数所属的类的字节码文件--》BankThread.class
	public static synchronized void getMoney() {
		while(true) {
			if(count>0) {
				System.out.println(Thread.currentThread().getName()+"取走了1000块，还剩余"+(count-1000)+"元");
				count = count - 1000;
			}else {
				System.out.println("荒淫无度，余额不足！");
				break;
			}
		}
	}//使用同步函数时该函数全部被同步了，如果使用代码块可选择部分同步，可做一些骚操作
}

public class 多线程处理 {
	public static void main(String[] args) {
		//创建两个线程对象
		BankThread thread1 = new BankThread("老公");
		BankThread thread2 = new BankThread("老婆");
		
		//用start方法开启线程
		thread1.start();
		thread2.start();
	}
}
