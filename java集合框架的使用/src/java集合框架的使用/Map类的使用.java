package java���Ͽ�ܵ�ʹ��;

import java.util.*;
/**
 * 
 * @author ����ΰ
 * @�޸����ڣ�2017��12��7������5:27:27
 * @������map����hashtableʹ�÷���������ͬ
 */
public class Map���ʹ�� {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("1", "value1");
		map.put("2", "value2");
		map.put("����", "����");
		
		//��������ʱʹ��Iterator��������map���б���
		System.out.println("ͨ��Map.entryset���б���");
		for(Map.Entry<String, String> entry: map.entrySet()) {
			System.out.println("key " + entry.getKey()+" 's Value: "+entry.getValue());
		}
	}
}
