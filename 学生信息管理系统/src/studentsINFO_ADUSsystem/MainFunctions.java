package studentsINFO_ADUSsystem;

import java.io.BufferedReader;
import java.io.IOException;

import databaseConnection.DataBaseConnect;
import databaseConnection.Student;

public class MainFunctions {
	
	DataBaseConnect dbc = new DataBaseConnect();//ʵ�������ݿ���������
	
	public Student functionAdd(BufferedReader keyReader) throws IOException {
		String stu_ID,stu_Name,gender,tel;
		System.out.println("��������Ҫ��ӵ�ѧ����ID");
		while(true) {
			stu_ID = keyReader.readLine();
			String reg = "[A-Z]{1}\\d{9}";
			if(stu_ID.matches(reg))
				break;
			else
				System.out.println("[�������ѧ�Ÿ�ʽ����!����������]");
		}
		System.out.println("��������Ҫ��ӵ�ѧ��������");
		while(true) {
			stu_Name = keyReader.readLine();
			String reg = "[\u4E00-\u9FA5]{2,5}";
			if(stu_Name.matches(reg))
				break;
			else
				System.out.println("[�������������ʽ����!����������]");
		}
		System.out.println("��������Ҫ��ӵ�ѧ�����Ա�");
		while(true) {
			gender = keyReader.readLine();
			if(gender.equals("��")|gender.equals("Ů"))
				break;
			else
				System.out.println("[��������Ա��ʽ����!����������]");
		}
		System.out.println("��������Ҫ��ӵ�ѧ������ϵ�绰");
		while(true) {
			tel = keyReader.readLine();
			String reg = "\\d{11}";
			if(tel.matches(reg))
				break;
			else
				System.out.println("[������绰��������ʽ����!����������]");
		}
		Student stu_INFO = new Student(stu_ID,stu_Name,gender,tel);
		return stu_INFO;
	}

	public Student functionDel(BufferedReader keyReader) throws IOException{
		System.out.println("[��ʾ��Ϣ]������ѧ����ѧ�ź�����,ƥ��ɹ��ſ�ִ�в���!");
		String stu_ID,stu_Name;
		System.out.println("��������Ҫɾ����ѧ����ѧ��");
		while(true) {
			stu_ID = keyReader.readLine();
			String reg = "[A-Z]{1}\\d{9}";
			if(stu_ID.matches(reg))
				break;
			else
				System.out.println("[�������ѧ�Ÿ�ʽ����!����������]");
		}
		System.out.println("��������Ҫɾ����ѧ��������");
		while(true) {
			stu_Name = keyReader.readLine();
			String reg = "[\u4E00-\u9FA5]{2,5}";
			if(stu_Name.matches(reg))
				break;
			else
				System.out.println("[�������������ʽ����!����������]");
		}

		Student stu_INFO = new Student(stu_ID,stu_Name);
		return stu_INFO;
	}

	public Student functionSearch(BufferedReader keyReader) throws IOException{
		String stu_Name;
		System.out.println("[��ʾ��Ϣ]������ѧ���������Բ���ѧ��");
		System.out.println("��������Ҫ���ҵ�ѧ��������");
		while(true) {
			stu_Name = keyReader.readLine();
			String reg = "[\u4E00-\u9FA5]{2,5}";
			if(stu_Name.matches(reg))
				break;
			else
				System.out.println("[�������������ʽ����!����������]");
		}
		Student stu_INFO = new Student(stu_Name);
		return stu_INFO;
	}

	public Student functionUpdate(BufferedReader keyReader) throws IOException{
		Student stu_INFO = functionSearch(keyReader);
		Student stu_INFOinDB = dbc.SearchDataByName(stu_INFO.stu_Name);
		if(stu_INFOinDB.stu_Name.equals(stu_INFO.stu_Name)) {
			System.out.println("�Ѳ�ѯ����ѧ������Ϣ!\n"+stu_INFOinDB);
		}else {
			System.out.println("��ѧ���������ڿ���!��������ȷ����Ϣ�Ա����!");
			stu_INFOinDB = new Student(null,null);
		}
		return stu_INFOinDB;
	}

	public Student functionChange(BufferedReader keyReader) throws IOException{
		String stu_ID,stu_Name,gender,tel;
		System.out.println("�������޸ĺ��ѧ����ID");
		while(true) {
			stu_ID = keyReader.readLine();
			String reg = "[A-Z]{1}\\d{9}";
			if(stu_ID.matches(reg))
				break;
			else
				System.out.println("[�������ѧ�Ÿ�ʽ����!����������]");
		}
		System.out.println("�������޸ĺ��ѧ��������");
		while(true) {
			stu_Name = keyReader.readLine();
			String reg = "[\u4E00-\u9FA5]{2,5}";
			if(stu_Name.matches(reg))
				break;
			else
				System.out.println("[�������������ʽ����!����������]");
		}
		System.out.println("�������޸ĺ��ѧ�����Ա�");
		while(true) {
			gender = keyReader.readLine();
			if(gender.equals("��")|gender.equals("Ů"))
				break;
			else
				System.out.println("[��������Ա��ʽ����!����������]");
		}
		System.out.println("�������޸ĺ��ѧ������ϵ�绰");
		while(true) {
			tel = keyReader.readLine();
			String reg = "\\d{11}";
			if(tel.matches(reg))
				break;
			else
				System.out.println("[�������������ʽ����!����������]");
		}
		Student stu_INFO = new Student(stu_ID,stu_Name,gender,tel);
		return stu_INFO;
	}

}
