package 多线程问题;

class Demo  implements Runnable{
	//重写Runnable.run方法
	public void run() {
		for(int i = 0;i<100;i++)
			System.out.println(Thread.currentThread().getName()+":"+i);
	}
	/**
	 * 重写run方法时有一个判断（原run方法）
	 * public void run(){
	 * 		if(target != NULL){
	 * 			target.run();
	 * 		}
	 * }
	 * @param args
	 */
	public static void main(String[] args) {
		//创建Runnable实现类的对象
		Demo d = new Demo();
		//创建Thread对象，并将Runnable实现类对象作为实参传递给Thread对象
		Thread thread1 = new Thread(d,"狗娃");
		/**
		 * 这是一个构造方法
		 * public Thread(Runnable target,String name){
		 * 		init(null,name,0);
		 * }
		 */
		//调用Thread对象的start方法开启线程
		thread1.start();
	}
	
	
	
	
}
