package 多线程问题;
/**
 * 守护线程(也即后台线程)
 * @author 陈立伟
 * @修改日期：2017年12月9日下午4:37:32
 * @描述：当所有线程只剩下后台线程时，后台线程会自动死亡
 */
class Demo1 extends Thread{
	public Demo1(String name) {
		super(name);
	}
	public void run() {
		for(int i = 1;i<=100;i++) {
			System.out.println("更新包已下载"+i+"%");
			if(i == 100) {
				System.out.println("更新包下载完毕，正在初始化安装...");
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args){
		Demo1 d = new Demo1("后台自动更新线程");
		//使用isDaemon判断线程是否为守护线程
		//System.out.println("是守护线程吗"+d.isDaemon());
		d.setDaemon(true);//setDaemon()将线程设置是否为守护线程,true设置为守护
		d.start();
		
		for(int i = 1;i<=100;i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
/**
 * 注意当除了后台线程的其他线程终止时，后台线程会自动终止！
 */
































