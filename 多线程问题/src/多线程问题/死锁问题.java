package ���߳�����;
/**
 * 
 * @author ����ΰ
 * @�޸����ڣ�2017��12��9������10:59:18
 * @������java��ͬ�����ƽ�����̰߳�ȫ���⣬��Ҳ��������������
 */
class DeadLock extends Thread{
	public DeadLock(String name) {
		super(name);
	}
	//��дthread���run����
	public void run() {
		if("����".equals(Thread.currentThread().getName())) {
			synchronized ("ң����") {
				System.out.println("��������ң������׼��ȥ�õ��");
				synchronized("���") {
					System.out.println("��������ң�����͵�أ�ˬ���ᣡ");
				}
			}
		}else if("����".equals(Thread.currentThread().getName())) {
			synchronized ("���") {
				System.out.println("�������˵�أ�׼��ȥ��ң����");
				synchronized("ң����") {
					System.out.println("��������ң�����͵�أ�ˬ���ᣡ");
				}
			}
		}
	}
}
public class �������� {
	public static void main(String[] args) {
		DeadLock thread1 = new DeadLock("����");
		DeadLock thread2 = new DeadLock("����");
		//�����߳�
		thread1.start();
		thread2.start();
		//���к��������߳�һֱ���ڵȴ��Է��ͷ���ס�Ķ��󣬵���һֱ��ס
	}
}
