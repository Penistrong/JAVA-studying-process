package ���߳�����;
/**
 * ����һ�������˻�5000�飬������һ�����Ŵ���һ�����ſ�����ʼȡǮ����
 * ÿ��ֻ��ȡ1000�飬Ҫ��׼�����̰߳�ȫ����
 * @author ����ΰ
 * @�޸����ڣ�2017��12��8������5:43:19
 * @������
 */
class BankThread extends Thread{
	static int count = 5000;//����ĸñ���Ҫ��ɾ�̬
	public BankThread(String name) {
		super(name);
	}
	
	//Override
	public void run() {
		while(true) {
		//ʹ��synchronized�ؼ�����ס��Ҫͬ���Ĵ���
			synchronized("��") {
				if(count>0) {
					System.out.println(Thread.currentThread().getName()+"ȡ����1000�飬��ʣ��"+(count-1000)+"Ԫ");
					count = count - 1000;
				}else {
					System.out.println("�����޶ȣ����㣡");
					break;
				}
			}
		}
	}
	
	//��̬�ĺ���---����������������ֽ����ļ�--��BankThread.class
	public static synchronized void getMoney() {
		while(true) {
			if(count>0) {
				System.out.println(Thread.currentThread().getName()+"ȡ����1000�飬��ʣ��"+(count-1000)+"Ԫ");
				count = count - 1000;
			}else {
				System.out.println("�����޶ȣ����㣡");
				break;
			}
		}
	}//ʹ��ͬ������ʱ�ú���ȫ����ͬ���ˣ����ʹ�ô�����ѡ�񲿷�ͬ��������һЩɧ����
}

public class ���̴߳��� {
	public static void main(String[] args) {
		//���������̶߳���
		BankThread thread1 = new BankThread("�Ϲ�");
		BankThread thread2 = new BankThread("����");
		
		//��start���������߳�
		thread1.start();
		thread2.start();
	}
}
