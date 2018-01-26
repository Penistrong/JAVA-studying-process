package databaseConnection;

import java.sql.*;
import databaseConnection.Student;

public class DataBaseConnect {
	private static final String USERNAME = "C##Penistrong";
	private static final String PASSWORD = "chenliwei";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	//private static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";ʹ��service_name����
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";//ʹ��SID����
	//private static final String URL =  "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(HOST=localhost)(PORT=1521)(PROTOCOL=tcp))(CONNECT_DATA=(SERVICE_NAME=orcl.Penistrong.)))";
	//�������ݿ�����
	Connection connection = null;
	//����Ԥ����������
	PreparedStatement pstm = null;
	//����һ�����������
	ResultSet rs = null;
	/**�����ݿ�����������
	 * �������ȡ������������,����+1��Ϊ�������ݵ�numֵ
	 * @param stu_Name:ѧ������ gender:�Ա� tel:�ֻ��� stu_ID:ѧ�� stu_Num:����ѧ������
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
				count = rs.getInt(1);//�õ�Ŀǰ���ݿ��иñ��е�����(��������)
			}
			pstm = connection.prepareStatement(strStr);
			pstm.setString(1, stu_INFO.stu_ID);
			pstm.setString(2, stu_INFO.stu_Name);
			pstm.setString(3, stu_INFO.gender);
			pstm.setString(4, stu_INFO.tel);
			pstm.setInt(5, count+1);//ԭ����+1��Ϊ�µ�����ֵ
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			ReleaseResource();//����ͷ���Դ
		}
	}
	
	public Student SearchDataByName(String stuName) {
		//����ѧ����������ѧ����Ϣ
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
				//System.out.println("��ѯ��ѧ������ϢΪ:");
				//System.out.printf("%s | %s | %s | %s | %s\n",stu_ID,stu_Name,gender,tel,stu_Num);
				stu_INFO = new Student(stu_ID, stu_Name, gender, tel, stu_Num);
			}else {
				stu_INFO = new Student(null,null,null,null,0);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			ReleaseResource();
		}
		return stu_INFO;
	}
	
	public Student SearchDataByID(String stu_IDin) {
		//����ѧ��ѧ�Ų���ѧ����Ϣ
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
				//System.out.println("��ѯ��ѧ������ϢΪ:");
				//System.out.printf("%s | %s | %s | %s | %s\n",stu_ID,stu_Name,gender,tel,stu_Num);
				stu_INFO = new Student(stu_ID, stu_Name, gender, tel, stu_Num);
			}else {
				stu_INFO = new Student(null,null,null,null,0);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			ReleaseResource();
		}
		return stu_INFO;//���ʧ���򷵻ؿ�
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
			// TODO �Զ����ɵ� catch ��
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
				System.out.println("δ��ѯ���κ�ѧ����Ϣ!");
			}else {
			System.out.println("���� "+count+" ��");
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			ReleaseResource();
		}
	}

	public void SelectAllMales() {
		//��ѯ������ѧ��
		connection = getConnection();
		int count = 0;
		String sql = "select * from studentsINFO where gender = '��'";
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
				System.out.println("δ��ѯ���κ���ѧ������Ϣ!");
			}else {
				System.out.println("����: "+count+" ����ѧ��");
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			ReleaseResource();
		}
	}
	
	public void SelectAllFemales() {
		//��ѯ����Ůѧ��
		connection = getConnection();
		int count = 0;
		String sql = "select * from studentsINFO where gender = 'Ů'";
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
				System.out.println("δ��ѯ���κ�Ůѧ������Ϣ!");
			}else {
				System.out.println("����: "+count+" ��Ůѧ��");
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			ReleaseResource();
		}
		return versionDatas;
	}
	
	public Connection getConnection(){
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);//����ʧ����ʲô����
			//System.out.println("[��ʾ��Ϣ]�ѳɹ����ӵ����ݿ�,�û�Ϊ"+USERNAME);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO �Զ����ɵ� catch ��
			throw new RuntimeException("[��ʾ��Ϣ]�������ݿ�ʧ��!",e);
		}
		return connection;
	}
	
	public void ReleaseResource() {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		if(pstm!=null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}
