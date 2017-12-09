package 多线程问题;

/**
 * 经典案例：生产者与消费者
 * 		     生产者不断生产，消费者不断消费
 * 		     生产者产完一个产品就去等消费者消费完再继续生产
 * @author 陈立伟
 * @修改日期：2017年12月9日下午2:12:37
 * @描述：一个线程完成了自己的任务时通知另一个线程去完成另一个任务
 */
//产品
class Product{
	String name;
	double price;//定义产品名及价格
	
	boolean flag = false;//标识产品是否生产完毕的标志,默认没有生产完成
}

//生产者
class Producer extends Thread{
	Product p;
	public Producer(Product p) {
		this.p = p;
	}
	public void run() {
		int i = 0;
		while(true) {
			synchronized(p) {
			//每俩天卖苹果
				if(p.flag == false) {
					if(i%2 == 0) {
						p.name = "苹果";
						p.price = 6.5;
					}else {
						p.name = "香蕉";
						p.price = 3.0;
					}
					System.out.println("生产者生产了："+p.name+"价格为:"+p.price);
					i++;
					p.flag = true;
					p.notify();//唤醒消费者去消费
				}else {
					//已经生产完毕等待消费者去消费
					try {
						p.wait();
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		}	
	}
}

//消费者
class Customer extends Thread{
	Product p;
	public Customer(Product p) {
		this.p = p;
	}
	
	public void run() {
		while(true) {
			synchronized(p) {
				if(p.flag == true) {
					System.out.println("消费者消费了:"+p.name+"价格为："+p.price);
					p.flag = false;
					p.notify();//唤醒生产者去生产
				}else {
					//产品还没有生产等待生产者先生产
					try {
						p.wait();
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		}
	}
}

public class 线程通讯 {
	public static void main(String[] args) {
		Product p = new Product();
		//把产品传过去，以下的p都是上面这行创建的对象
		Producer producer = new Producer(p);
		Customer customer = new Customer(p);
		
		producer.start();
		customer.start();
	}
}
/**
 * 俩方法属于Object类中定义的方法
 * 执行了wait方法的线程会进入jvm以锁对象为标识符建立的线程池中等待，同时释放了锁对象
 * 执行了notify方法的线程会进入以锁对象为标识符的池中等待线程的 <一个>，随机的 
 * notifyAll()会唤醒所有以同名锁对象为标识符的等待线程
 */
