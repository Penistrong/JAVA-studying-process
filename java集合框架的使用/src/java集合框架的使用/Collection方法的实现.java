package java集合框架的使用;

import java.util.*;
/**
 * 
 * @author 陈立伟
 * @修改日期：2017年12月14日上午11:26:06
 * @描述：包括add,addAll,clear,remove,removeAll,retainAll,contains,isEmpty
 */
public class Collection方法的实现 {
	public static void main(String[] args) {
		Collection c = new ArrayList();
		c.add("abc");
		c.add(123);
		System.out.println("添加是否成功？"+c.add("尤姆"));
		
		//创建一个新集合
		Collection c2 = new ArrayList();
		c2.add("阿尔特留斯");
		c2.add("法兰老狼");
		
		c.addAll(c2);//将c2集合中的元素拷贝入c中
		System.out.println("c集合的元素："+c);
		
		c2.clear();
		System.out.println("c2集合的元素"+c2);
		
		System.out.println("删除c集合中的123元素是否成功？"+c.remove(123));//删除指定的元素(可传别的集合),成功则返回true
	
		//removeALL,删除某集合中与其他集合的交集
		System.out.println("删除c集合中的c2集合元素会否成功？"+c.removeAll(c2));
		
		//retainAll,保留交集元素，删除多余元素
		System.out.println("保留交集是否成功？"+c.retainAll(c2));
		
		//isEmpty判断集合是否为空
		System.out.println("c集合是否为空？"+c.isEmpty());
		
		//集合添加自定义元素
		Collection c3 = new ArrayList();
		c3.add(new Person(110,"狗娃"));
		c3.add(new Person(119,"狗剩"));
		
		//contains判断集合中是否有某元素
		System.out.println("c3中是否存在铁蛋？"+c.contains(new Person(911,"铁蛋")));
		System.out.println("c3中的元素"+c3);
		
		//在现实生活中ID号说明身份一致，不管名字如何？
		c3.add(new Person(110,"大美妞"));
		
		Object[] arr = c3.toArray();//将c集合中的所有元素存入Object数组中
		//需求：把编号是110的 人 的信息输出
		for(int i = 0;i<arr.length;i++) {
			Person p = (Person) arr[i];//将Object类型强转为Person
			if(p.ID == 110)
				System.out.println(p);
		}
	}
}

class Person {
	int ID;
	String name;
	public Person(int ID,String name) {
		System.out.printf("我是%s,我的ID序列号是%d\n",name,ID);
	}
}
