package java���Ͽ�ܵ�ʹ��;

import java.util.*;
import java.util.ArrayList;
import java.util.Collection;

public class Java���Ͽ�ܳ��� {
	public static void main(String[] args) {
		Collection coll = new ArrayList();
		//�����п��Դ洢�κ���������
		coll.add(1);
		coll.add("����");
		coll.add(true);
		System.out.println(coll);
		
		//�����д漯��
		Collection col1 = new ArrayList();
		for(int i=0;i<3;i++) {
			Collection col2 = new ArrayList();
			for(int j = 0;j<5;j++) {
				col2.add(i + "-����-" + j);
			}
			//���ڲ㼯�ϼ��뵽��㼯����
			col1.add(col2);
		}
		System.out.println(col1);
		
		//ʹ��Iterator�������Լ��Ͻ��б���
		Iterator ite = coll.iterator();
		while(ite.hasNext()) {
			System.out.println(ite.next());
		}
	}
}
