import java.io.*;
/**
 * 
 * @author 陈立伟
 * @修改日期：2017年11月30日下午4:33:19
 * @描述：定义一个特定的异常处理，专属银行账户余额检查机制
 */
public class InsufficientFundsException extends Exception {
	private double amount;//私有化操作数
	public InsufficientFundsException(double amount) {
		this.amount=amount;
	}
	public double getAmount() {
		return amount;
	}
}
