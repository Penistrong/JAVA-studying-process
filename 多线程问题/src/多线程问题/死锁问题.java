package 多线程问题;
/**
 * 
 * @author 陈立伟
 * @修改日期：2017年12月9日上午10:59:18
 * @描述：java中同步机制解决了线程安全问题，但也引发了死锁现象
 */
class DeadLock extends Thread{
	public DeadLock(String name) {
		super(name);
	}
	//重写thread里的run方法
	public void run() {
		if("张三".equals(Thread.currentThread().getName())) {
			synchronized ("遥控器") {
				System.out.println("张三拿了遥控器，准备去拿电池");
				synchronized("电池") {
					System.out.println("张三拿了遥控器和电池，爽歪歪！");
				}
			}
		}else if("李四".equals(Thread.currentThread().getName())) {
			synchronized ("电池") {
				System.out.println("李四拿了电池，准备去拿遥控器");
				synchronized("遥控器") {
					System.out.println("李四拿了遥控器和电池，爽歪歪！");
				}
			}
		}
	}
}
public class 死锁问题 {
	public static void main(String[] args) {
		DeadLock thread1 = new DeadLock("张三");
		DeadLock thread2 = new DeadLock("李四");
		//开启线程
		thread1.start();
		thread2.start();
		//运行后发现俩个线程一直都在等待对方释放锁住的对象，导致一直卡住
	}
}
