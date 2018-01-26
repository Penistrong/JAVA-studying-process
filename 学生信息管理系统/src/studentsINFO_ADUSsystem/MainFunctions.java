package studentsINFO_ADUSsystem;

import java.io.BufferedReader;
import java.io.IOException;

import databaseConnection.DataBaseConnect;
import databaseConnection.Student;

public class MainFunctions {
	
	DataBaseConnect dbc = new DataBaseConnect();//实例化数据库操作类对象
	
	public Student functionAdd(BufferedReader keyReader) throws IOException {
		String stu_ID,stu_Name,gender,tel;
		System.out.println("请输入您要添加的学生的ID");
		while(true) {
			stu_ID = keyReader.readLine();
			String reg = "[A-Z]{1}\\d{9}";
			if(stu_ID.matches(reg))
				break;
			else
				System.out.println("[您输入的学号格式不对!请重新输入]");
		}
		System.out.println("请输入您要添加的学生的姓名");
		while(true) {
			stu_Name = keyReader.readLine();
			String reg = "[\u4E00-\u9FA5]{2,5}";
			if(stu_Name.matches(reg))
				break;
			else
				System.out.println("[您输入的姓名格式不对!请重新输入]");
		}
		System.out.println("请输入您要添加的学生的性别");
		while(true) {
			gender = keyReader.readLine();
			if(gender.equals("男")|gender.equals("女"))
				break;
			else
				System.out.println("[您输入的性别格式不对!请重新输入]");
		}
		System.out.println("请输入你要添加的学生的联系电话");
		while(true) {
			tel = keyReader.readLine();
			String reg = "\\d{11}";
			if(tel.matches(reg))
				break;
			else
				System.out.println("[您输入电话的姓名格式不对!请重新输入]");
		}
		Student stu_INFO = new Student(stu_ID,stu_Name,gender,tel);
		return stu_INFO;
	}

	public Student functionDel(BufferedReader keyReader) throws IOException{
		System.out.println("[提示信息]请输入学生的学号和姓名,匹配成功才可执行操作!");
		String stu_ID,stu_Name;
		System.out.println("请输入您要删除的学生的学号");
		while(true) {
			stu_ID = keyReader.readLine();
			String reg = "[A-Z]{1}\\d{9}";
			if(stu_ID.matches(reg))
				break;
			else
				System.out.println("[您输入的学号格式不对!请重新输入]");
		}
		System.out.println("请输入您要删除的学生的姓名");
		while(true) {
			stu_Name = keyReader.readLine();
			String reg = "[\u4E00-\u9FA5]{2,5}";
			if(stu_Name.matches(reg))
				break;
			else
				System.out.println("[您输入的姓名格式不对!请重新输入]");
		}

		Student stu_INFO = new Student(stu_ID,stu_Name);
		return stu_INFO;
	}

	public Student functionSearch(BufferedReader keyReader) throws IOException{
		String stu_Name;
		System.out.println("[提示信息]请输入学生的姓名以查找学生");
		System.out.println("请输入您要查找的学生的姓名");
		while(true) {
			stu_Name = keyReader.readLine();
			String reg = "[\u4E00-\u9FA5]{2,5}";
			if(stu_Name.matches(reg))
				break;
			else
				System.out.println("[您输入的姓名格式不对!请重新输入]");
		}
		Student stu_INFO = new Student(stu_Name);
		return stu_INFO;
	}

	public Student functionUpdate(BufferedReader keyReader) throws IOException{
		Student stu_INFO = functionSearch(keyReader);
		Student stu_INFOinDB = dbc.SearchDataByName(stu_INFO.stu_Name);
		if(stu_INFOinDB.stu_Name.equals(stu_INFO.stu_Name)) {
			System.out.println("已查询到该学生的信息!\n"+stu_INFOinDB);
		}else {
			System.out.println("该学生不存在于库中!请输入正确的信息以便查找!");
			stu_INFOinDB = new Student(null,null);
		}
		return stu_INFOinDB;
	}

	public Student functionChange(BufferedReader keyReader) throws IOException{
		String stu_ID,stu_Name,gender,tel;
		System.out.println("请输入修改后的学生的ID");
		while(true) {
			stu_ID = keyReader.readLine();
			String reg = "[A-Z]{1}\\d{9}";
			if(stu_ID.matches(reg))
				break;
			else
				System.out.println("[您输入的学号格式不对!请重新输入]");
		}
		System.out.println("请输入修改后的学生的姓名");
		while(true) {
			stu_Name = keyReader.readLine();
			String reg = "[\u4E00-\u9FA5]{2,5}";
			if(stu_Name.matches(reg))
				break;
			else
				System.out.println("[您输入的姓名格式不对!请重新输入]");
		}
		System.out.println("请输入修改后的学生的性别");
		while(true) {
			gender = keyReader.readLine();
			if(gender.equals("男")|gender.equals("女"))
				break;
			else
				System.out.println("[您输入的性别格式不对!请重新输入]");
		}
		System.out.println("请输入修改后的学生的联系电话");
		while(true) {
			tel = keyReader.readLine();
			String reg = "\\d{11}";
			if(tel.matches(reg))
				break;
			else
				System.out.println("[您输入的姓名格式不对!请重新输入]");
		}
		Student stu_INFO = new Student(stu_ID,stu_Name,gender,tel);
		return stu_INFO;
	}

}
