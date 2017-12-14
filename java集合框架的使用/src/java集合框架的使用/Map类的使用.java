package java集合框架的使用;

import java.util.*;
/**
 * 
 * @author 陈立伟
 * @修改日期：2017年12月7日下午5:27:27
 * @描述：map类与hashtable使用方法大体相同
 */
public class Map类的使用 {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("1", "value1");
		map.put("2", "value2");
		map.put("程立", "楚霖");
		
		//当容量大时使用Iterator迭代器对map进行遍历
		System.out.println("通过Map.entryset进行遍历");
		for(Map.Entry<String, String> entry: map.entrySet()) {
			System.out.println("key " + entry.getKey()+" 's Value: "+entry.getValue());
		}
	}
}
