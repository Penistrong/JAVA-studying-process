package databaseConnection;

public class Student {
	public String stu_ID;
	public String stu_Name;
	public String gender;
	public String tel;
	public int stu_Num;

	public Student(String stu_ID,String stu_Name,String gender,String tel,int stu_Num) {
		//构造方法1,构造完整的学生信息
		this.stu_ID = stu_ID;
		this.stu_Name = stu_Name;
		this.gender = gender;
		this.tel = tel;
		this.stu_Num = stu_Num;
	}
	
	public Student(String stu_ID,String stu_Name,String gender,String tel) {
		//构造方法2,除去用以标识学生数量的stu_Num
		this.stu_ID = stu_ID;
		this.stu_Name = stu_Name;
		this.gender = gender;
		this.tel = tel;
	}
	
	public Student(String stu_ID,String stu_Name) {
		//构造方法3,只处理学号与姓名
		this.stu_ID = stu_ID;
		this.stu_Name = stu_Name;
	}
	
	public Student(String stu_Name) {
		this.stu_Name = stu_Name;
	}
	@Override
	public String toString() {
		String stu_INFO = stu_ID+" "+stu_Name+" "+gender+" "+tel+" "+stu_Num;
		return stu_INFO;
	}
}