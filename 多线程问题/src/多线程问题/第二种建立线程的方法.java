package ���߳�����;

class Demo  implements Runnable{
	//��дRunnable.run����
	public void run() {
		for(int i = 0;i<100;i++)
			System.out.println(Thread.currentThread().getName()+":"+i);
	}
	/**
	 * ��дrun����ʱ��һ���жϣ�ԭrun������
	 * public void run(){
	 * 		if(target != NULL){
	 * 			target.run();
	 * 		}
	 * }
	 * @param args
	 */
	public static void main(String[] args) {
		//����Runnableʵ����Ķ���
		Demo d = new Demo();
		//����Thread���󣬲���Runnableʵ���������Ϊʵ�δ��ݸ�Thread����
		Thread thread1 = new Thread(d,"����");
		/**
		 * ����һ�����췽��
		 * public Thread(Runnable target,String name){
		 * 		init(null,name,0);
		 * }
		 */
		//����Thread�����start���������߳�
		thread1.start();
	}
	
	
	
	
}
