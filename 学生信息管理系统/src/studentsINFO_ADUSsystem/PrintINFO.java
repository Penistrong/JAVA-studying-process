package studentsINFO_ADUSsystem;
/**
 * 
 * @author ����ΰ
 * @�޸����ڣ�2018��1��24������11:43:40
 * @����������ѧ������ϵͳ���йؽ���Ĵ�ӡ
 */
public class PrintINFO {
	
	//��ӡ�˵�
	public void PrintMenuINFO() {
		System.out.println("**************************");
		System.out.println("     ��ӭʹ��ѧ����Ϣ����ϵͳ  v1.0 \n Authorized by Penistrong");
		System.out.printf("1.���ѧ������\t2.ɾ��ѧ������\n3.��ѯѧ������\t4.�޸�ѧ������\n5.�鿴ϵͳ��Ϣ\t6.�鿴������Ϣ\n7.�˳�����ϵͳ\t8.����Աѡ��\n");
		System.out.println("[��ʾ��Ϣ]������Ӧѡ��ִ�в���!");
		System.out.println("**************************");
	}
	
	//��ӡϵͳ��Ϣ
	public void PrintSystemINFO(){
		System.out.println("����������������������������������������������������");
		System.out.println("[���ݿ���Ϣ]����:orcl");
		System.out.println("[����Ϣ]����:studentsINFO");
		String versionDatas = new databaseConnection.DataBaseConnect().getVersionINFO();
		String[] verData = versionDatas.split("\\|");
		for(String versionData: verData) {
			System.out.println(versionData);
		}
		System.out.println("����������������������������������������������������");
	}
	
	public void PrintAuthorINFO() {
		System.out.println("����������������������������������������������������");
		System.out.println("[����]����ΰ|Penistrong");
		System.out.println("[��;]ѧϰjava�������ݿ�ʹ��sql�Ա������ɾ�Ĳ����");
		System.out.println("[����]��л����ʹ��!");
		System.out.println("����������������������������������������������������");
	}

	public void PrintAdminMenu() {
		System.out.println("������������������������������������������������������������");
		System.out.println("[��ʾ��Ϣ]��ӭ�������Ա�˵�:\n1.��ѯ��������ѧ����Ϣ\t2.��ѯ����������ѧ��\n3.��ѯ��������Ůѧ��\t4.����ѧ��������Ϣ\n5.�˳�����Ա�˵�");
		System.out.println("������������������������������������������������������������");
	}
}
