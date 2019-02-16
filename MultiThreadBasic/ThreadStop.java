//Stop an existing thread beautifully
public class ThreadStop {
	public static boolean flag = true;
	public static void main(String[] args) {
		new Thread(()->{
			int num = 0;
			while(flag) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " " + (num++));
			}
		}, "The main thread").start();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag = false;//能优雅停止线程的关键，就是将线程循环执行的变量掐断
	}
}
