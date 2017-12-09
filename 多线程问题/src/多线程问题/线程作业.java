package ���߳�����;
/**
 * 
 * @author ����ΰ
 * @�޸����ڣ�2017��12��9������1:50:37
 * @��������һ���ݻ�Ϊ500L��ˮ�أ�һ�߿ڽ�ˮ��һ�߿ڷ�ˮ��������ͬʱ���С�
 * ��ˮ�ٶ�5L/s����ˮ�ٶ�2L/s,һ��������ֹͣעˮ��һ���ſվ�ֹͣ��ˮ
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
					System.out.println("���ڽ�ˮ��ˮ��Ϊ��"+wp.capacity);
				}
				wp.notify();
				try {
					wp.wait();
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
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
					System.out.println("���ڷ�ˮ��ˮ��Ϊ��"+wp.capacity);
				}
				wp.notify();
				try {
					wp.wait();
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
		}
	}
}
public class �߳���ҵ {
	public static void main(String[] args) {
		WaterPool wp = new WaterPool();
		WaterIn wi = new WaterIn(wp);
		WaterOut wo = new WaterOut(wp);
		
		wi.start();
		wo.start();
	}
}
