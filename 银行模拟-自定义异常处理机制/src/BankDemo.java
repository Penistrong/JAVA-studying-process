import java.util.Scanner;
/**
 * ʹ����Scanner�õ�������
 * @author ����ΰ
 * @�޸����ڣ�2017��11��30������4:48:39
 * @�������������������д������
 */
public class BankDemo {
	public static void main(String[] args) {
		double balance;
		CheckingAccount c = new CheckingAccount(101);
		c.getNumber();
		balance = c.getBalance();
		System.out.println("�������Ϊ $"+balance);
		Scanner sc = new Scanner(System.in);
		System.out.println("�������������Ľ�");
		double deamount = sc.nextDouble();
		System.out.println("Deposting $"+deamount+"...");
		c.deposit(deamount);
		balance = c.getBalance();
		System.out.println("�������Ϊ $"+balance);
		System.out.println("����������ȡ���Ľ�");
		double wdamount = sc.nextDouble();
		try {
			System.out.println("\nWithdrawing $"+wdamount+"...");
			c.withdraw(wdamount);
		}catch(InsufficientFundsException e){
			System.out.println("Sorry,but you are short $"+e.getAmount());
			e.printStackTrace();
		}finally {
			balance = c.getBalance();
			System.out.println("�������Ϊ $"+balance);
		}
	}
}
//printStackTrace()��������˼�ǣ��������д�ӡ�쳣��Ϣ�ڳ����г����λ�ü�ԭ��
//ֻҪ��ס���Ƕ�������ڲ��Ĵ�����Ϣ���ύ����ʾ��ǰ̨���˻������Ļ��ƾ����ˡ�
//��������������������ļ��׳��Ĵ�����Ϣ��:
