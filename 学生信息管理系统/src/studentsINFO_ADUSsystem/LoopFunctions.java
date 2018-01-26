package studentsINFO_ADUSsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class LoopFunctions {
	public int getOption(Scanner sc) {
		int option = 0;
		while(true) {
			try {
				option = sc.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("[��ʾ��Ϣ]������ĸ�ʽ�������������룡");
				sc.next();
			}
		}
		return option;
	}
	
	public boolean LoopOPTION(BufferedReader keyReader) {
		boolean OPTIONflag = true;
		System.out.println("�Ƿ��������(y/n)?");
		while(true) {
			try {
				String option = keyReader.readLine();
				if(option.equalsIgnoreCase("n")) {
					OPTIONflag = false;
					break;
				}else if(option.equalsIgnoreCase("y")){
					break;
				}else {
					System.out.println("[��ʾ��Ϣ]�޷�ʶ��ָ������������");
				}
			}catch(Exception e){
				System.out.println("[��ʾ��Ϣ]�޷�ʶ��ָ������������");
			}
		}
		return OPTIONflag;
	}
	
	public boolean LoopLoginAsAdmin(BufferedReader keyReader) throws IOException {
		boolean LoginFlag = true;
		while(LoginFlag) {
			System.out.println("���������Ա�˻���!");
			String adminAccount = keyReader.readLine();
			System.out.println("�������������!");
			String passWord = keyReader.readLine();
			if(adminAccount.equalsIgnoreCase("Penistrong")&&passWord.equals("chenliwei")) {
				System.out.println("[��¼�ɹ�!]");
				return LoginFlag;
			}else {
				System.out.println("[��ʾ��Ϣ]�˻������벻ƥ��!");
				LoginFlag = LoopOPTION(keyReader);
			}
		}
		return LoginFlag;//δ��¼�ɹ�ʱ����false
	}
}
