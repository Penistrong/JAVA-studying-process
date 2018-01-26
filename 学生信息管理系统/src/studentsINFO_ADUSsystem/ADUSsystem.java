package studentsINFO_ADUSsystem;

import java.util.Scanner;
import databaseConnection.*;
import java.io.*;

public class ADUSsystem {
	//����Ϊѡ���
	private static final int OPTION1 = 1;
	private static final int OPTION2 = 2;
	private static final int OPTION3 = 3;
	private static final int OPTION4 = 4;
	private static final int OPTION5 = 5;
	private static final int OPTION6 = 6;
	private static final int OPTION7 = 7;
	private static final int OPTION8 = 8;
	//student��Ķ���,���Դ���ѧ����Ϣ
	Student stu_INFO = null;
	//���ݿ�������Ķ���,���Դ������ݿ���ز���
	static DataBaseConnect dbc = new DataBaseConnect();
	
	static PrintINFO pi = new PrintINFO();//������ӡ�����
	static Scanner sc = new Scanner(System.in);//����ɨ��������
	static BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));//���������ַ�������
	
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
				System.out.println("�������˳���ϵͳ!");
				if(lf.LoopOPTION(keyReader)) {
					System.out.println("��л����ʹ��!");
					System.exit(-1);
				}
				break;
			case OPTION8:
				funcOPTION8(mf,lf);
				break;
			default:
				System.out.println("[��ʾ��Ϣ]�޷�ʶ���������ָ������������!");
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
					System.out.println("[��ʾ��Ϣ]������Ӧѡ����в���!");
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
						System.out.println("����ɹ�!");
						break;
					case 5:
						System.out.println("�������˳�����Ա�˵�!");
						if(lf.LoopOPTION(keyReader)) {
							innerFlag = false;
							OPTION8flag = false;
						}
						break;
					}
				}
			}else {
				System.out.println("[��ʾ��Ϣ]�����������˵�");
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
				System.out.println("[������Ϣ]�޸ĳɹ�");
				OPTION4flag = lf.LoopOPTION(keyReader);
			}else {
				System.out.println("[��ʾ��Ϣ]��������Ҫ��������ѧ����Ϣ!");
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
				System.out.println("[������Ϣ]��ѯѧ���ɹ�!");
			}else {
				System.out.println("��ѧ�����ڿ���!");
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
					System.out.println("[������Ϣ]ɾ��ѧ���ɹ�");
				}else {
					System.out.println("ѧ����������ƥ��!");
				}
				OPTION2flag = lf.LoopOPTION(keyReader);
			}else {
				System.out.println("��ѧ�������������ݿ���!");
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
				System.out.println("[������Ϣ]���ѧ���ɹ���");
				OPTION1flag = lf.LoopOPTION(keyReader);
			}else {
				System.out.println("��ѧ���Ѿ��������ݿ�����!");
				OPTION1flag = lf.LoopOPTION(keyReader);
			}
		}
	}
	
	
}
