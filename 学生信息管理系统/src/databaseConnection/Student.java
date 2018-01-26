package databaseConnection;

public class Student {
	public String stu_ID;
	public String stu_Name;
	public String gender;
	public String tel;
	public int stu_Num;

	public Student(String stu_ID,String stu_Name,String gender,String tel,int stu_Num) {
		//���췽��1,����������ѧ����Ϣ
		this.stu_ID = stu_ID;
		this.stu_Name = stu_Name;
		this.gender = gender;
		this.tel = tel;
		this.stu_Num = stu_Num;
	}
	
	public Student(String stu_ID,String stu_Name,String gender,String tel) {
		//���췽��2,��ȥ���Ա�ʶѧ��������stu_Num
		this.stu_ID = stu_ID;
		this.stu_Name = stu_Name;
		this.gender = gender;
		this.tel = tel;
	}
	
	public Student(String stu_ID,String stu_Name) {
		//���췽��3,ֻ����ѧ��������
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