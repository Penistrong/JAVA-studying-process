package java���Ͽ�ܵ�ʹ��;

import java.util.*;
/**
 * 
 * @author ����ΰ
 * @�޸����ڣ�2017��12��14������11:26:06
 * @����������add,addAll,clear,remove,removeAll,retainAll,contains,isEmpty
 */
public class Collection������ʵ�� {
	public static void main(String[] args) {
		Collection c = new ArrayList();
		c.add("abc");
		c.add(123);
		System.out.println("����Ƿ�ɹ���"+c.add("��ķ"));
		
		//����һ���¼���
		Collection c2 = new ArrayList();
		c2.add("��������˹");
		c2.add("��������");
		
		c.addAll(c2);//��c2�����е�Ԫ�ؿ�����c��
		System.out.println("c���ϵ�Ԫ�أ�"+c);
		
		c2.clear();
		System.out.println("c2���ϵ�Ԫ��"+c2);
		
		System.out.println("ɾ��c�����е�123Ԫ���Ƿ�ɹ���"+c.remove(123));//ɾ��ָ����Ԫ��(�ɴ���ļ���),�ɹ��򷵻�true
	
		//removeALL,ɾ��ĳ���������������ϵĽ���
		System.out.println("ɾ��c�����е�c2����Ԫ�ػ��ɹ���"+c.removeAll(c2));
		
		//retainAll,��������Ԫ�أ�ɾ������Ԫ��
		System.out.println("���������Ƿ�ɹ���"+c.retainAll(c2));
		
		//isEmpty�жϼ����Ƿ�Ϊ��
		System.out.println("c�����Ƿ�Ϊ�գ�"+c.isEmpty());
		
		//��������Զ���Ԫ��
		Collection c3 = new ArrayList();
		c3.add(new Person(110,"����"));
		c3.add(new Person(119,"��ʣ"));
		
		//contains�жϼ������Ƿ���ĳԪ��
		System.out.println("c3���Ƿ����������"+c.contains(new Person(911,"����")));
		System.out.println("c3�е�Ԫ��"+c3);
		
		//����ʵ������ID��˵�����һ�£�����������Σ�
		c3.add(new Person(110,"�����"));
		
		Object[] arr = c3.toArray();//��c�����е�����Ԫ�ش���Object������
		//���󣺰ѱ����110�� �� ����Ϣ���
		for(int i = 0;i<arr.length;i++) {
			Person p = (Person) arr[i];//��Object����ǿתΪPerson
			if(p.ID == 110)
				System.out.println(p);
		}
	}
}

class Person {
	int ID;
	String name;
	public Person(int ID,String name) {
		System.out.printf("����%s,�ҵ�ID���к���%d\n",name,ID);
	}
}
