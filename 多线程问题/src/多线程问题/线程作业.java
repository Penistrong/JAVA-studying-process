package 多线程问题;
/**
 * 
 * @author 陈立伟
 * @修改日期：2017年12月9日下午1:50:37
 * @描述：有一个容积为500L的水池，一边口进水，一边口放水，但不能同时进行。
 * 进水速度5L/s，出水速度2L/s,一旦放满就停止注水，一旦放空就停止放水
 */
class WaterPool{
	int MaxCapacity = 500;
	int capacity = 0;
}

class WaterIn extends Thread{
	WaterPool wp;
	public WaterIn(WaterPool wp) {
		this.wp = wp;
	}
	public void run(){
		while(true) {
			synchronized(wp) {
				while(wp.capacity<wp.MaxCapacity) {
					wp.capacity += 5;
					System.out.println("正在进水，水量为："+wp.capacity);
				}
				wp.notify();
				try {
					wp.wait();
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}
}

class WaterOut extends Thread{
	WaterPool wp;
	public WaterOut(WaterPool wp) {
		this.wp = wp;
	}
	public void run() {
		while(true) {
			synchronized(wp) {
				while(wp.capacity>0) {
					wp.capacity -= 2;
					System.out.println("正在放水，水量为："+wp.capacity);
				}
				wp.notify();
				try {
					wp.wait();
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}
}
public class 线程作业 {
	public static void main(String[] args) {
		WaterPool wp = new WaterPool();
		WaterIn wi = new WaterIn(wp);
		WaterOut wo = new WaterOut(wp);
		
		wi.start();
		wo.start();
	}
}
