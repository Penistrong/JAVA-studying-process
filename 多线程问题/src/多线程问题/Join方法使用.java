package ���߳�����;
/**
 * 
 * @author ����ΰ
 * @�޸����ڣ�2017��12��9������5:28:38
 * @������join�����������µ��̣߳�����������Ϻ�ż���ԭ�߳�
 */
class Gwyndolin extends Thread{
	public void run() {
		System.out.println("��˹���ʥ����׹��񣬻���Ϩ��ù���");
		System.out.println("����Ϩ���ڰ�����");
		System.out.println("��˹���˫���Ӿܾ�����");
		//����ȼ������������Ҫ���Ǽ���ȼ�գ�ֻ��³��˹��������
		RunAway RA = new RunAway();
		RA.start();
		try {
			RA.join();
		} catch (InterruptedException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		System.out.println("��Ҫ�ҵ��µ�н��");
		//�������������޻�Ļҽ�ȥ��ȡ���꣬�Թ���ʼ֮��ȼ��
		AshenOne ao = new AshenOne();
		ao.start();
		try {
			ao.join(); //һ���߳����ִ����join��䣬������µ��̣߳�ԭ�̵߳ȴ����߳�����������ܼ���
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		System.out.println("Ashen One �����˵�һ���н");
		System.out.println("������������ɳ����İ�����������Gwyndolin");
		System.out.println("�������ţ��Ƕ�ŵ¡�»ԻͲ���");
		System.out.println("Ashen One ��������Ԩ��ڰ�֮��");
	}
}

class AshenOne extends Thread{
	//override
	public void run() {
		System.out.println("�����Ļҽ�����������");
		System.out.println("Ashen One,may the flame guide you");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		System.out.println("ë�������۶����ף�������Σ�������ţ��ڰ�֮�꣬�������ˡ�����̫�󣬻�������");
	}
}

class RunAway extends Thread{
	public void run() {
		System.out.println("��λȼ����н��������");
		System.out.println("�����Ĳ����ӻع�Ҫ����������ķ�ص���ҵ֮�����������������������ã�ֻ�а�����³��˹�ص���ȼ��������֮��");
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
public class Join����ʹ��{
	public static void main(String[] args) {
		Gwyndolin girl = new Gwyndolin();
		girl.start();
	}
}