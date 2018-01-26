package studentsINFO_ADUSsystem;

import java.util.Scanner;
import databaseConnection.*;
import java.io.*;

public class ADUSsystem {
	//以下为选项定义
	private static final int OPTION1 = 1;
	private static final int OPTION2 = 2;
	private static final int OPTION3 = 3;
	private static final int OPTION4 = 4;
	private static final int OPTION5 = 5;
	private static final int OPTION6 = 6;
	private static final int OPTION7 = 7;
	private static final int OPTION8 = 8;
	//student类的对象,用以储存学生信息
	Student stu_INFO = null;
	//数据库连接类的对象,用以处理数据库相关操作
	static DataBaseConnect dbc = new DataBaseConnect();
	
	static PrintINFO pi = new PrintINFO();//创建打印类对象
	static Scanner sc = new Scanner(System.in);//创建扫描器对象
	static BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));//创建缓冲字符流对象
	
	public static void main(String[] args) throws IOException {
		MainFunctions mf = new MainFunctions();
		LoopFunctions lf = new LoopFunctions();
		
		boolean MenuFlag = true;
		while(MenuFlag) {
			pi.PrintMenuINFO();
			int option = lf.getOption(sc);
			switch(option) {
			case OPTION1:
				funcOPTION1(mf, lf);
				break;
			case OPTION2:
				funcOPTION2(mf, lf);
				break;
			case OPTION3:
				funcOPTION3(mf, lf);
				break;
			case OPTION4:
				funcOPTION4(mf,lf);
				break;
			case OPTION5:
				pi.PrintSystemINFO();
				break;
			case OPTION6:
				pi.PrintAuthorINFO();
				break;
			case OPTION7:
				System.out.println("您即将退出本系统!");
				if(lf.LoopOPTION(keyReader)) {
					System.out.println("感谢您的使用!");
					System.exit(-1);
				}
				break;
			case OPTION8:
				funcOPTION8(mf,lf);
				break;
			default:
				System.out.println("[提示信息]无法识别您键入的指令请重新输入!");
				break;
			}
			
			
			
		}
	}
	
	private static void funcOPTION8(MainFunctions mf, LoopFunctions lf) throws IOException{
		boolean OPTION8flag = true;
		while(OPTION8flag) {
			if(lf.LoopLoginAsAdmin(keyReader)) {
				boolean innerFlag =true;
				while(innerFlag) {
					pi.PrintAdminMenu();
					System.out.println("[提示信息]请键入对应选项进行操作!");
					int option = lf.getOption(sc);
					switch(option) {
					case 1:
						dbc.SelectALL();
						break;
					case 2:
						dbc.SelectAllMales();
						break;
					case 3:
						dbc.SelectAllFemales();
						break;
					case 4:
						dbc.SortIntoDatabase();
						System.out.println("整理成功!");
						break;
					case 5:
						System.out.println("您即将退出管理员菜单!");
						if(lf.LoopOPTION(keyReader)) {
							innerFlag = false;
							OPTION8flag = false;
						}
						break;
					}
				}
			}else {
				System.out.println("[提示信息]即将返回主菜单");
				OPTION8flag = false;
			}
		}
	}

	private static void funcOPTION4(MainFunctions mf, LoopFunctions lf) throws IOException{
		boolean OPTION4flag = true;
		while(OPTION4flag) {
			Student stu_INFOinDB = mf.functionUpdate(keyReader);
			if(stu_INFOinDB.stu_ID!=null) {
				Student stu_INFO = mf.functionChange(keyReader);
				dbc.UpdateData(stu_INFO);
				System.out.println("[操作信息]修改成功");
				OPTION4flag = lf.LoopOPTION(keyReader);
			}else {
				System.out.println("[提示信息]您即将需要重新输入学生信息!");
				OPTION4flag = lf.LoopOPTION(keyReader);
			}
		}
	}
	
	private static void funcOPTION3(MainFunctions mf, LoopFunctions lf) throws IOException {
		boolean OPTION3flag = true;
		while(OPTION3flag) {
			Student stu_INFO = mf.functionSearch(keyReader);
			Student stu_INFOinDB = dbc.SearchDataByName(stu_INFO.stu_Name);
			if(stu_INFOinDB.stu_Name.equals(stu_INFO.stu_Name)) {
				System.out.println(stu_INFOinDB);
				System.out.println("[操作信息]查询学生成功!");
			}else {
				System.out.println("该学生不在库中!");
			}
			OPTION3flag = lf.LoopOPTION(keyReader);	
		}
	}

	private static void funcOPTION2(MainFunctions mf, LoopFunctions lf) throws IOException {
		boolean OPTION2flag = true;
		while(OPTION2flag) {
			Student stu_INFO = mf.functionDel(keyReader);
			Student stu_INFOinDB = dbc.SearchDataByID(stu_INFO.stu_ID);
			if(stu_INFOinDB.stu_ID.equals(stu_INFO.stu_ID)) {
				if(stu_INFOinDB.stu_Name.equals(stu_INFO.stu_Name)) {
					dbc.DeleteData(stu_INFO.stu_ID);
					System.out.println("[操作信息]删除学生成功");
				}else {
					System.out.println("学号与姓名不匹配!");
				}
				OPTION2flag = lf.LoopOPTION(keyReader);
			}else {
				System.out.println("该学生不存在于数据库中!");
				OPTION2flag = lf.LoopOPTION(keyReader);
			}
		}
	}

	private static void funcOPTION1(MainFunctions mf, LoopFunctions lf) throws IOException {
		boolean OPTION1flag = true;
		while(OPTION1flag) {
			Student stu_INFO = mf.functionAdd(keyReader);
			Student stu_INFOinDB = dbc.SearchDataByID(stu_INFO.stu_ID);
			if(stu_INFOinDB.stu_ID == null) {
				dbc.AddData(stu_INFO);
				System.out.println("[操作信息]添加学生成功！");
				OPTION1flag = lf.LoopOPTION(keyReader);
			}else {
				System.out.println("该学生已经存在数据库中了!");
				OPTION1flag = lf.LoopOPTION(keyReader);
			}
		}
	}
	
	
}
