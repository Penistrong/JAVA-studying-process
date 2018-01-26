package databaseConnection;

import java.sql.*;
import databaseConnection.Student;

public class DataBaseConnect {
	private static final String USERNAME = "C##Penistrong";
	private static final String PASSWORD = "chenliwei";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	//private static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";使用service_name连接
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";//使用SID连接
	//private static final String URL =  "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(HOST=localhost)(PORT=1521)(PROTOCOL=tcp))(CONNECT_DATA=(SERVICE_NAME=orcl.Penistrong.)))";
	//创建数据库连接
	Connection connection = null;
	//创建预编译语句对象
	PreparedStatement pstm = null;
	//创建一个结果集对象
	ResultSet rs = null;
	/**向数据库中增加数据
	 * 首先需获取表内数据总数,总数+1即为新添数据的num值
	 * @param stu_Name:学生姓名 gender:性别 tel:手机号 stu_ID:学号 stu_Num:表中学生人数
	 */
	public void AddData(Student stu_INFO) {
		connection = getConnection();
		int count = 0;
		String sql = "select count(*) from studentsINFO";
		String strStr = "insert into studentsINFO values(?,?,?,?,?)";
		try {
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);//得到目前数据库中该表行的总数(即总人数)
			}
			pstm = connection.prepareStatement(strStr);
			pstm.setString(1, stu_INFO.stu_ID);
			pstm.setString(2, stu_INFO.stu_Name);
			pstm.setString(3, stu_INFO.gender);
			pstm.setString(4, stu_INFO.tel);
			pstm.setInt(5, count+1);//原总数+1即为新的索引值
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			ReleaseResource();
		}
			
	}
	
	public void DeleteData(String stu_ID) {
		connection = getConnection();
		String sqlStr = "delete from studentsINFO where stu_ID = ?";
		try {
			pstm = connection.prepareStatement(sqlStr);
			pstm.setString(1, stu_ID);
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			ReleaseResource();//最后释放资源
		}
	}
	
	public Student SearchDataByName(String stuName) {
		//根据学生姓名查找学生信息
		connection = getConnection();
		Student stu_INFO = null;
		String sql = "select * from studentsINFO where stu_Name = ?";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, stuName);
			rs = pstm.executeQuery();
			if(rs.next()) {
				String stu_ID = rs.getString("stu_ID");
				String stu_Name = rs.getString("stu_Name");
				String gender = rs.getString("gender");
				String tel = rs.getString("tel");
				int stu_Num = rs.getInt("stu_Num");
				//System.out.println("查询到学生的信息为:");
				//System.out.printf("%s | %s | %s | %s | %s\n",stu_ID,stu_Name,gender,tel,stu_Num);
				stu_INFO = new Student(stu_ID, stu_Name, gender, tel, stu_Num);
			}else {
				stu_INFO = new Student(null,null,null,null,0);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			ReleaseResource();
		}
		return stu_INFO;
	}
	
	public Student SearchDataByID(String stu_IDin) {
		//根据学生学号查找学生信息
		connection = getConnection();
		Student stu_INFO = null;
		String sql = "select * from studentsINFO where stu_ID = ?";
		try {
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, stu_IDin);
			rs = pstm.executeQuery();
			if(rs.next()) {
				String stu_ID = rs.getString("stu_ID");
				System.out.println(stu_ID);
				String stu_Name = rs.getString("stu_Name");
				String gender = rs.getString("gender");
				String tel = rs.getString("tel");
				int stu_Num = rs.getInt("stu_Num");
				//System.out.println("查询到学生的信息为:");
				//System.out.printf("%s | %s | %s | %s | %s\n",stu_ID,stu_Name,gender,tel,stu_Num);
				stu_INFO = new Student(stu_ID, stu_Name, gender, tel, stu_Num);
			}else {
				stu_INFO = new Student(null,null,null,null,0);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			ReleaseResource();
		}
		return stu_INFO;//如果失败则返回空
	}
	
	public void UpdateData(Student stu_INFO) {
		connection = getConnection();
		String sqlStr = "update studentsINFO set stu_Name=?,gender=?,tel=?,stu_Num=? where stu_ID=?";
		try {
			pstm = connection.prepareStatement(sqlStr);;
			pstm.setString(5, stu_INFO.stu_ID);
			pstm.setString(1,stu_INFO.stu_Name);
			pstm.setString(2, stu_INFO.gender);
			pstm.setString(3, stu_INFO.tel);
			pstm.setInt(4, stu_INFO.stu_Num);
			pstm.execute();
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void SelectALL() {
		connection = getConnection();
		int count = 0;
		String sql = "select * from studentsINFO";
		try {
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				String stu_ID = rs.getString("stu_ID");
				String stu_Name = rs.getString("stu_Name");
				String gender = rs.getString("gender");
				String tel = rs.getString("tel");
				int stu_Num = rs.getInt("stu_Num");
				System.out.println(stu_ID+"\t"+stu_Name+"\t"+gender+"\t"+tel+"\t"+stu_Num);
				count++;
			}
			if(count == 0) {
				System.out.println("未查询到任何学生信息!");
			}else {
			System.out.println("共计 "+count+" 人");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			ReleaseResource();
		}
	}

	public void SelectAllMales() {
		//查询所有男学生
		connection = getConnection();
		int count = 0;
		String sql = "select * from studentsINFO where gender = '男'";
		try {
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				String stu_ID = rs.getString("stu_ID");
				String stu_Name = rs.getString("stu_Name");
				String gender = rs.getString("gender");
				String tel = rs.getString("tel");
				int stu_Num = rs.getInt("stu_Num");
				System.out.println(stu_ID+"\t"+stu_Name+"\t"+gender+"\t"+tel+"\t"+stu_Num);
				count++;
			}
			if(count == 0) {
				System.out.println("未查询到任何男学生的信息!");
			}else {
				System.out.println("共有: "+count+" 名男学生");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			ReleaseResource();
		}
	}
	
	public void SelectAllFemales() {
		//查询所有女学生
		connection = getConnection();
		int count = 0;
		String sql = "select * from studentsINFO where gender = '女'";
		try {
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				String stu_ID = rs.getString("stu_ID");
				String stu_Name = rs.getString("stu_Name");
				String gender = rs.getString("gender");
				String tel = rs.getString("tel");
				int stu_Num = rs.getInt("stu_Num");
				System.out.println(stu_ID+"\t"+stu_Name+"\t"+gender+"\t"+tel+"\t"+stu_Num);
				count++;
			}
			if(count == 0) {
				System.out.println("未查询到任何女学生的信息!");
			}else {
				System.out.println("共有: "+count+" 名女学生");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			ReleaseResource();
		}
	}
	
	public void SortIntoDatabase() {
		connection = getConnection();
		String sql = "select * from studentsINFO";
		String sqlStr = "update studentsINFO set stu_Num = ? where stu_Num = ?";
		try {
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();
			int counter = 1;
			while(rs.next()) {
				pstm = connection.prepareStatement(sqlStr);
				pstm.setInt(1, counter);
				pstm.setInt(2, rs.getInt("stu_Num"));
				pstm.executeUpdate();
				counter++;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ReleaseResource();
		}
	}
	
	public String getVersionINFO() {
		connection = getConnection();
		String versionDatas = "";
		String sql = "select * from v$version";
		try {
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				versionDatas = versionDatas+rs.getString("Banner")+"|";
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			ReleaseResource();
		}
		return versionDatas;
	}
	
	public Connection getConnection(){
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);//连接失败是什么鬼。。
			//System.out.println("[提示信息]已成功连接到数据库,用户为"+USERNAME);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			throw new RuntimeException("[提示信息]连接数据库失败!",e);
		}
		return connection;
	}
	
	public void ReleaseResource() {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if(pstm!=null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
