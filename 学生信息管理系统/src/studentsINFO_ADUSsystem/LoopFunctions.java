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
				System.out.println("[提示信息]您输入的格式不对请重新输入！");
				sc.next();
			}
		}
		return option;
	}
	
	public boolean LoopOPTION(BufferedReader keyReader) {
		boolean OPTIONflag = true;
		System.out.println("是否继续操作(y/n)?");
		while(true) {
			try {
				String option = keyReader.readLine();
				if(option.equalsIgnoreCase("n")) {
					OPTIONflag = false;
					break;
				}else if(option.equalsIgnoreCase("y")){
					break;
				}else {
					System.out.println("[提示信息]无法识别指令请重新输入");
				}
			}catch(Exception e){
				System.out.println("[提示信息]无法识别指令请重新输入");
			}
		}
		return OPTIONflag;
	}
	
	public boolean LoopLoginAsAdmin(BufferedReader keyReader) throws IOException {
		boolean LoginFlag = true;
		while(LoginFlag) {
			System.out.println("请输入管理员账户名!");
			String adminAccount = keyReader.readLine();
			System.out.println("请输入管理密码!");
			String passWord = keyReader.readLine();
			if(adminAccount.equalsIgnoreCase("Penistrong")&&passWord.equals("chenliwei")) {
				System.out.println("[登录成功!]");
				return LoginFlag;
			}else {
				System.out.println("[提示信息]账户或密码不匹配!");
				LoginFlag = LoopOPTION(keyReader);
			}
		}
		return LoginFlag;//未登录成功时返回false
	}
}
