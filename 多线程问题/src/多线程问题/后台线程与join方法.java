package ���߳�����;
/**
 * �ػ��߳�(Ҳ����̨�߳�)
 * @author ����ΰ
 * @�޸����ڣ�2017��12��9������4:37:32
 * @�������������߳�ֻʣ�º�̨�߳�ʱ����̨�̻߳��Զ�����
 */
class Demo1 extends Thread{
	public Demo1(String name) {
		super(name);
	}
	public void run() {
		for(int i = 1;i<=100;i++) {
			System.out.println("���°�������"+i+"%");
			if(i == 100) {
				System.out.println("���°�������ϣ����ڳ�ʼ����װ...");
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args){
		Demo1 d = new Demo1("��̨�Զ������߳�");
		//ʹ��isDaemon�ж��߳��Ƿ�Ϊ�ػ��߳�
		//System.out.println("���ػ��߳���"+d.isDaemon());
		d.setDaemon(true);//setDaemon()���߳������Ƿ�Ϊ�ػ��߳�,true����Ϊ�ػ�
		d.start();
		
		for(int i = 1;i<=100;i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}
/**
 * ע�⵱���˺�̨�̵߳������߳���ֹʱ����̨�̻߳��Զ���ֹ��
 */
































