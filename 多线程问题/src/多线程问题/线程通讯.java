package ���߳�����;

/**
 * ���䰸������������������
 * 		     �����߲��������������߲�������
 * 		     �����߲���һ����Ʒ��ȥ���������������ټ�������
 * @author ����ΰ
 * @�޸����ڣ�2017��12��9������2:12:37
 * @������һ���߳�������Լ�������ʱ֪ͨ��һ���߳�ȥ�����һ������
 */
//��Ʒ
class Product{
	String name;
	double price;//�����Ʒ�����۸�
	
	boolean flag = false;//��ʶ��Ʒ�Ƿ�������ϵı�־,Ĭ��û���������
}

//������
class Producer extends Thread{
	Product p;
	public Producer(Product p) {
		this.p = p;
	}
	public void run() {
		int i = 0;
		while(true) {
			synchronized(p) {
			//ÿ������ƻ��
				if(p.flag == false) {
					if(i%2 == 0) {
						p.name = "ƻ��";
						p.price = 6.5;
					}else {
						p.name = "�㽶";
						p.price = 3.0;
					}
					System.out.println("�����������ˣ�"+p.name+"�۸�Ϊ:"+p.price);
					i++;
					p.flag = true;
					p.notify();//����������ȥ����
				}else {
					//�Ѿ�������ϵȴ�������ȥ����
					try {
						p.wait();
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
			}
		}	
	}
}

//������
class Customer extends Thread{
	Product p;
	public Customer(Product p) {
		this.p = p;
	}
	
	public void run() {
		while(true) {
			synchronized(p) {
				if(p.flag == true) {
					System.out.println("������������:"+p.name+"�۸�Ϊ��"+p.price);
					p.flag = false;
					p.notify();//����������ȥ����
				}else {
					//��Ʒ��û�������ȴ�������������
					try {
						p.wait();
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
			}
		}
	}
}

public class �߳�ͨѶ {
	public static void main(String[] args) {
		Product p = new Product();
		//�Ѳ�Ʒ����ȥ�����µ�p�����������д����Ķ���
		Producer producer = new Producer(p);
		Customer customer = new Customer(p);
		
		producer.start();
		customer.start();
	}
}
/**
 * ����������Object���ж���ķ���
 * ִ����wait�������̻߳����jvm��������Ϊ��ʶ���������̳߳��еȴ���ͬʱ�ͷ���������
 * ִ����notify�������̻߳������������Ϊ��ʶ���ĳ��еȴ��̵߳� <һ��>������� 
 * notifyAll()�ỽ��������ͬ��������Ϊ��ʶ���ĵȴ��߳�
 */
