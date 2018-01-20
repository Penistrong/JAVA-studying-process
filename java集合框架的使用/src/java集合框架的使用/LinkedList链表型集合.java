package java集合框架的使用;

import java.util.*;
/**
 * 主要是一些LinkedList的特有方法
 * @author 陈立伟
 * @修改日期：2017年12月15日下午5:14:42
 * @描述：使用LinkedList ，链表型集合，查询速度慢，增删速度快
 * 一个元素分为俩部分：data/下个元素的地址，类似于单向链表
 * 自己也可构建一个类，内含指向下一个同类对象的next(类似指针)
 * public static class Node{
 * 		int value;
 * 		Node next;
 * 		public Node(int n){
 * 			this.value = n;
 * 			this.next = NULL;
 * 		}
 * }
 */
public class LinkedList链表型集合 {
	static LinkedList<Object> students = new LinkedList();
	
	public static void main(String[] args) {
		students.add(new Student("狗娃",001));
		//addFirst将目标元素放置于链表头
		students.addFirst(new Student("齐木楠雄",002));
		//addLast将目标元素置于链表尾端
		students.addLast(new Student("坂本大佬",000));
		
		//给予的是地址
		System.out.println("LinkedList中的首元素为："+students.getFirst());
		System.out.println("LinkedList中的尾元素为："+students.getLast());
		
		//push()与addFirst()功能一致，但涉及堆栈的不同操作
		/**
		 * 栈：先进后出：push()、pop()
		 * 队列：(双端队列):先进先出，使用LinkedList模拟存储数据结构：offer()、poll()
		 */
		//offer()与addLast()功能一致，理由同上
		//pop()移除并返回集合中的首元素(观察控制台中显示的地址变化)
		System.out.println("删除LinkedList中的首元素并将其返回"+students.pop());
		
		//descendingIterator()返回一个逆序的迭代器对象
		Iterator it = students.descendingIterator();
		System.out.println("倒序后输入学生信息");
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
		return "[学生名:"+this.name+" 学生编号："+this.ID+"]";
	}
}