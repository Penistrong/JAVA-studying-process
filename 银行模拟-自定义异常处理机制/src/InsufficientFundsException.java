import java.io.*;
/**
 * 
 * @author ����ΰ
 * @�޸����ڣ�2017��11��30������4:33:19
 * @����������һ���ض����쳣����ר�������˻���������
 */
public class InsufficientFundsException extends Exception {
	private double amount;//˽�л�������
	public InsufficientFundsException(double amount) {
		this.amount=amount;
	}
	public double getAmount() {
		return amount;
	}
}
