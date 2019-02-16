//Show how daemon thread works
public class DaemonThread {
	public static void main(String[] args) {
		Thread user = new Thread(()->{
			for (int x = 0; x < 10; x ++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "-" + x);
			}
		}, "User thread");
		Thread daemon = new Thread(()->{
			for (int x = 0; x < 100; x ++) {//for daemon, this loop only works for 10 steps
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Daemon thread works in " + x + " proceeds");
			}
		});
		daemon.setDaemon(true);
		user.start();
		daemon.start();
	}
}
