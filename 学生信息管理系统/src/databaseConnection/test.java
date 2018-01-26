package databaseConnection;

public class test {
	static String stu_ID,stu_Name,gender,tel;
	
	public static void main(String[] args) {
		DataBaseConnect stu_db = new DataBaseConnect();
		stu_ID = "U201717014";
		stu_Name = "³ÂÁ¢Î°";
		gender = "ÄÐ";
		tel = "18296276027";
		//stu_db.AddData(stu_ID, stu_Name, gender, tel);
		//Student stu_INFO = stu_db.SearchData("³ÂÁ¢Î°");
		//System.out.println(stu_INFO);
		Student stu_INFO = stu_db.SearchDataByName(stu_Name);
		System.out.println(stu_INFO);
		stu_INFO = stu_db.SearchDataByID(stu_ID);
		System.out.println(stu_INFO);
		stu_INFO = stu_db.SearchDataByID(null);
		System.out.println(stu_INFO);
	}
}
