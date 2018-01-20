package java���Ͽ�ܵ�ʹ��;

import java.util.*;
/**
 * ��Ҫ��һЩLinkedList�����з���
 * @author ����ΰ
 * @�޸����ڣ�2017��12��15������5:14:42
 * @������ʹ��LinkedList �������ͼ��ϣ���ѯ�ٶ�������ɾ�ٶȿ�
 * һ��Ԫ�ط�Ϊ�����֣�data/�¸�Ԫ�صĵ�ַ�������ڵ�������
 * �Լ�Ҳ�ɹ���һ���࣬�ں�ָ����һ��ͬ������next(����ָ��)
 * public static class Node{
 * 		int value;
 * 		Node next;
 * 		public Node(int n){
 * 			this.value = n;
 * 			this.next = NULL;
 * 		}
 * }
 */
public class LinkedList�����ͼ��� {
	static LinkedList<Object> students = new LinkedList();
	
	public static void main(String[] args) {
		students.add(new Student("����",001));
		//addFirst��Ŀ��Ԫ�ط���������ͷ
		students.addFirst(new Student("��ľ���",002));
		//addLast��Ŀ��Ԫ����������β��
		students.addLast(new Student("�౾����",000));
		
		//������ǵ�ַ
		System.out.println("LinkedList�е���Ԫ��Ϊ��"+students.getFirst());
		System.out.println("LinkedList�е�βԪ��Ϊ��"+students.getLast());
		
		//push()��addFirst()����һ�£����漰��ջ�Ĳ�ͬ����
		/**
		 * ջ���Ƚ������push()��pop()
		 * ���У�(˫�˶���):�Ƚ��ȳ���ʹ��LinkedListģ��洢���ݽṹ��offer()��poll()
		 */
		//offer()��addLast()����һ�£�����ͬ��
		//pop()�Ƴ������ؼ����е���Ԫ��(�۲����̨����ʾ�ĵ�ַ�仯)
		System.out.println("ɾ��LinkedList�е���Ԫ�ز����䷵��"+students.pop());
		
		//descendingIterator()����һ������ĵ���������
		Iterator it = students.descendingIterator();
		System.out.println("���������ѧ����Ϣ");
		while(it.hasNext()) {
			Student student = (Student)it.next();
			System.out.println(student);
		}
		
	
	}
	
	
	
	
	
}

class Student{
	String name;
	int ID;
	public Student(String name,int ID) {
		this.name = name;
		this.ID = ID;
	}
	
	public String toString() {
		return "[ѧ����:"+this.name+" ѧ����ţ�"+this.ID+"]";
	}
}