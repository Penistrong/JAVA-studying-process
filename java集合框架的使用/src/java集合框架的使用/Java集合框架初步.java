package java集合框架的使用;

import java.util.*;
import java.util.ArrayList;
import java.util.Collection;

public class Java集合框架初步 {
	public static void main(String[] args) {
		Collection coll = new ArrayList();
		//集合中可以存储任何数据类型
		coll.add(1);
		coll.add("程立");
		coll.add(true);
		System.out.println(coll);
		
		//集合中存集合
		Collection col1 = new ArrayList();
		for(int i=0;i<3;i++) {
			Collection col2 = new ArrayList();
			for(int j = 0;j<5;j++) {
				col2.add(i + "-数据-" + j);
			}
			//把内层集合加入到外层集合中
			col1.add(col2);
		}
		System.out.println(col1);
		
		//使用Iterator迭代器对集合进行遍历
		Iterator ite = coll.iterator();
		while(ite.hasNext()) {
			System.out.println(ite.next());
		}
	}
}
