import java.util.Scanner;
/**
 * 使用了Scanner得到输入流
 * @author 陈立伟
 * @修改日期：2017年11月30日下午4:48:39
 * @描述：主调函数，银行存款样例
 */
public class BankDemo {
	public static void main(String[] args) {
		double balance;
		CheckingAccount c = new CheckingAccount(101);
		c.getNumber();
		balance = c.getBalance();
		System.out.println("您的余额为 $"+balance);
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入您想存入的金额！");
		double deamount = sc.nextDouble();
		System.out.println("Deposting $"+deamount+"...");
		c.deposit(deamount);
		balance = c.getBalance();
		System.out.println("您的余额为 $"+balance);
		System.out.println("请输入您想取出的金额！");
		double wdamount = sc.nextDouble();
		try {
			System.out.println("\nWithdrawing $"+wdamount+"...");
			c.withdraw(wdamount);
		}catch(InsufficientFundsException e){
			System.out.println("Sorry,but you are short $"+e.getAmount());
			e.printStackTrace();
		}finally {
			balance = c.getBalance();
			System.out.println("您的余额为 $"+balance);
		}
	}
}
//printStackTrace()方法的意思是：在命令行打印异常信息在程序中出错的位置及原因。
//只要记住这是对虚拟机内部的错误信息的提交并显示到前台的人机交换的机制就行了。
//举例：编译上面后三个文件抛出的错误信息中:
