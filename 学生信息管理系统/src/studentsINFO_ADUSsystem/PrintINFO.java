package studentsINFO_ADUSsystem;
/**
 * 
 * @author 陈立伟
 * @修改日期：2018年1月24日下午11:43:40
 * @描述：用于学生管理系统的有关界面的打印
 */
public class PrintINFO {
	
	//打印菜单
	public void PrintMenuINFO() {
		System.out.println("**************************");
		System.out.println("     欢迎使用学生信息管理系统  v1.0 \n Authorized by Penistrong");
		System.out.printf("1.添加学生数据\t2.删除学生数据\n3.查询学生数据\t4.修改学生数据\n5.查看系统信息\t6.查看作者信息\n7.退出管理系统\t8.管理员选项\n");
		System.out.println("[提示信息]请键入对应选项执行操作!");
		System.out.println("**************************");
	}
	
	//打印系统信息
	public void PrintSystemINFO(){
		System.out.println("——————————————————————————");
		System.out.println("[数据库信息]库名:orcl");
		System.out.println("[表信息]表名:studentsINFO");
		String versionDatas = new databaseConnection.DataBaseConnect().getVersionINFO();
		String[] verData = versionDatas.split("\\|");
		for(String versionData: verData) {
			System.out.println(versionData);
		}
		System.out.println("——————————————————————————");
	}
	
	public void PrintAuthorINFO() {
		System.out.println("——————————————————————————");
		System.out.println("[作者]陈立伟|Penistrong");
		System.out.println("[用途]学习java连接数据库使用sql对表进行增删改查操作");
		System.out.println("[结语]感谢您的使用!");
		System.out.println("——————————————————————————");
	}

	public void PrintAdminMenu() {
		System.out.println("——————————————————————————————");
		System.out.println("[提示信息]欢迎登入管理员菜单:\n1.查询库中所有学生信息\t2.查询库中所有男学生\n3.查询库中所有女学生\t4.整理学生索引信息\n5.退出管理员菜单");
		System.out.println("——————————————————————————————");
	}
}
