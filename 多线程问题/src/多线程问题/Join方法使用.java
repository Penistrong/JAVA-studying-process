package 多线程问题;
/**
 * 
 * @author 陈立伟
 * @修改日期：2017年12月9日下午5:28:38
 * @描述：join方法，加入新的线程，待其运行完毕后才继续原线程
 */
class Gwyndolin extends Thread{
	public void run() {
		System.out.println("洛斯里克圣王已坠疯狂，火焰熄灭得过早");
		System.out.println("初火将熄，黑暗将临");
		System.out.println("洛斯里克双王子拒绝传火");
		//唤醒燃尽的诸王，需要他们继续燃烧，只有鲁道斯留了下来
		RunAway RA = new RunAway();
		RA.start();
		try {
			RA.join();
		} catch (InterruptedException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		System.out.println("需要找到新的薪王");
		//敲响钟声唤醒无火的灰烬去夺取王魂，以供初始之火燃烧
		AshenOne ao = new AshenOne();
		ao.start();
		try {
			ao.join(); //一个线程如果执行了join语句，则加入新的线程，原线程等待新线程完成任务后才能继续
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		System.out.println("Ashen One 夺走了第一块柴薪");
		System.out.println("埃尔德里奇在沙力万的帮助下吞噬了Gwyndolin");
		System.out.println("众神已逝，亚尔诺隆德辉煌不再");
		System.out.println("Ashen One 行走在深渊与黑暗之间");
	}
}

class AshenOne extends Thread{
	//override
	public void run() {
		System.out.println("无名的灰烬被钟声唤醒");
		System.out.println("Ashen One,may the flame guide you");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println("毛多弱火，鳞多弱雷，体高弱臀，体大弱门，黑暗之魂，胯下做人。怪物太大，活在裆下");
	}
}

class RunAway extends Thread{
	public void run() {
		System.out.println("诸位燃尽的薪王被唤醒");
		System.out.println("法兰的不死队回归要塞，巨人尤姆回到罪业之都，埃尔德里奇出走幽邃教堂，只有矮人王鲁道斯回到了燃命的王座之上");
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
public class Join方法使用{
	public static void main(String[] args) {
		Gwyndolin girl = new Gwyndolin();
		girl.start();
	}
}