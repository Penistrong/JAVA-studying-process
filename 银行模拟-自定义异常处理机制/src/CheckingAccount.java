import java.io.*;
/**
 * 
 * @author 陈立伟
 * @修改日期：2017年11月30日下午4:31:50
 * @描述：创建一个检查账户的类，用以实例化
 */
public class CheckingAccount {
	//balance为月，number为卡号
	private double balance;
	private int number;
	//构造器
	public CheckingAccount(int number) {
		this.number = number;
	}
	//方法：存钱
	public void deposit(double amount) {
		balance+=amount;
	}
	//方法：取钱
	public void withdraw(double amount) throws InsufficientFundsException{
		if(amount <= balance) {
			balance-=amount;
		}else {
			double needs = amount - balance;
			throw new InsufficientFundsException(needs);
		}
	}
	//方法：返回余额
	public double getBalance() {
		return balance;
	}
	//方法：返回卡号
	public int getNumber() {
		return number;
	}
}
